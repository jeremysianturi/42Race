package com.example.fortytworace.ui.businesslist

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.core.data.Resource
import com.example.core.domain.model.CategoryList
import com.example.core.utils.ErrorMessageSplit
import com.example.fortytworace.R
import com.example.fortytworace.databinding.ActivityMainPageBinding
import com.example.fortytworace.ui.businesslist.detailbusiness.DetailBusiness
import com.example.fortytworace.util.dialog.ErrorBottomSheet
import com.example.fortytworace.util.dialog.SimpleDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class BusinessActivity : AppCompatActivity() {

    private val tag = BusinessActivity::class.java.simpleName.toString()

    private lateinit var binding: ActivityMainPageBinding
    private lateinit var adapter : BusinessAdapter
    private val businessViewModel : BusinessViewModel by viewModels()

    private var searchByType: String = "All Categories"
    private var sortingByType: String = "Rating"

    private var term : String = ""
    private var latitude : String = ""
    private var longitude : String = ""

    private lateinit var listDataCategorySpinner : ArrayList<String>
    private lateinit var listSortCategory : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //prevent keyboard popping out
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        ErrorBottomSheet.instance("Filter only work when button search on keyboard is pressed", "First, choose both filter/dropdown then search by char!")
            .show(supportFragmentManager, ErrorBottomSheet.TAG)

        // onclick
        onclick()

        // setup toolbar
        toolbarSetup()

        // assign value
        term = "delis"
        latitude = "37.786882"
        longitude = "-122.399972"
//        searchByType = "Search By: All Categories"

        // for category
        listDataCategorySpinner = ArrayList()
        listDataCategorySpinner.add("All Categories")
        listDataCategorySpinner.add("Business Name")
        listDataCategorySpinner.add("Postal Code")
        listDataCategorySpinner.add("Cuisine Type")

        // for sorting
        listSortCategory = ArrayList()
        listSortCategory.add("Rating")
        listSortCategory.add("Distance")

        // search
//        binding.layoutToolbar.edtSearch.doOnTextChanged { text, start, before, count ->
//            businessViewModel.searchQuery.value = text.toString()
//            setupObserver(term,latitude,longitude)
//            buildList()
//        }

        binding.layoutToolbar.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){

                businessViewModel.searchQuery.value = binding.layoutToolbar.edtSearch.text.toString()
                Timber.d("check value search char : ${binding.layoutToolbar.edtSearch.text.toString()}")

                businessViewModel.search(searchByType,sortingByType).observe(this) { data ->
                    Timber.d("check value searchByType at business activity 1 : $searchByType dan sortingByType: $sortingByType dan data : $data")
                    if (data.isEmpty()){

                        // repeat bcs bug
                        businessViewModel.search(searchByType,sortingByType).observe(this) { dataRepeat ->
                            adapter.setData(dataRepeat)
                        }

                    } else {
                        adapter.setData(data)
                    }

                }

                // hiding keyboard
                binding.layoutToolbar.edtSearch.hideKeyboard()
                true
            } else {
                false
            }
        }

        // method
        setupObserver(term,latitude,longitude)
        buildList()

    }

    private fun setupObserver(term: String, latitude: String, longitude: String) {

        businessViewModel.getBusinesses(term, latitude, longitude).observe(this) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarProposal.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarProposal.visibility = View.GONE
                        adapter.setData(data.data)
                        Timber.tag(tag).d("observer_business_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarProposal.visibility = View.GONE
                        Timber.tag(tag).d("error_message ${data.message}")
                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
                            .show(supportFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }

    }

    private fun buildList() {

        adapter = BusinessAdapter()
        binding.rvBusiness.setHasFixedSize(true)
        binding.rvBusiness.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvBusiness.adapter = adapter

        binding.rvBusiness.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        adapter.onItemClick = { selectData ->
            val mIntent = Intent(this, DetailBusiness::class.java)
            mIntent.putExtra(DetailBusiness.EXTRA_DATA, selectData)
            startActivity(mIntent)
        }
    }

    private fun onclick() {
        binding.apply {

            // filter category
            layoutAddContent.spinnerCategory.setOnClickListener {

                val spinnerDialogDataTypes = SpinnerDialog(
                    this@BusinessActivity, listDataCategorySpinner, "Select Item :", R.style.DialogAnimations_SmileWindow
                )

                spinnerDialogDataTypes.bindOnSpinerListener { s, i ->
                    val name = listDataCategorySpinner[i]

                    binding.layoutAddContent.spinnerCategory.text = "Search by : $name"
                    searchByType = name
                    Timber.d("check searchByType : $searchByType")


                }
                spinnerDialogDataTypes.showSpinerDialog()

            }

            // sorting
            layoutAddContent.spinnerSorting.setOnClickListener {

                val spinnerDialogDataTypes = SpinnerDialog(
                    this@BusinessActivity, listSortCategory, "Select Item :", R.style.DialogAnimations_SmileWindow
                )

                spinnerDialogDataTypes.bindOnSpinerListener { s, i ->
                    val type = listSortCategory[i]

                    binding.layoutAddContent.spinnerSorting.text = "Sorting by : $type"
                    sortingByType = type
                    Timber.d("check sortingByType : $sortingByType")
                }
                spinnerDialogDataTypes.showSpinerDialog()

            }

        }
    }

    private fun toolbarSetup() {

        binding.layoutToolbar.apply {
            ivNavigationBack.setOnClickListener { onBackPressed() }

            Glide.with(this@BusinessActivity)
                .load(R.drawable.banner_fortytwo)
                .into(imageView)

            tvTittle1.text = getString(R.string.tv_fortytwo)
            tvTittle2.text = getString(R.string.tv_local_businesses)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}