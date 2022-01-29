package com.example.fortytworace.ui.businesslist.detailbusiness

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.core.data.Resource
import com.example.core.domain.model.Business
import com.example.core.domain.model.BusinessReview
import com.example.core.utils.PreferenceEntity
import com.example.core.utils.UserPreference
import com.example.fortytworace.R
import com.example.fortytworace.databinding.ActivityDetailBusinessBinding
import com.example.fortytworace.helper.loadImage
import com.example.fortytworace.util.dialog.SimpleDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.json.JSONObject
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailBusiness : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val PARENT_DATA = "materi"
    }

    private val tag = DetailBusiness::class.java.simpleName
    private lateinit var binding: ActivityDetailBusinessBinding
    private lateinit var adapter : DetailBusinessReviewAdapter
    private val detailBusinessReviewViewModel : DetailBusinessReviewViewModel by viewModels()

    private lateinit var mPreference: UserPreference
    private lateinit var mPreferenceEntity: PreferenceEntity

    //bottomSheet
    private var bottomSheetDialog: BottomSheetDialog? = null

    var dataBusiness: Business? = null
    private lateinit var businessAlias : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //biar keyboard ga lgsg popup
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialog)

        // user preferences
        mPreference = UserPreference(this)
        mPreferenceEntity = mPreference.getPref()

        // get data intent
        dataBusiness = intent.getParcelableExtra<Business>(EXTRA_DATA)
        val name = dataBusiness?.name
        val photoUrl = dataBusiness?.imageUrl
        val categories = dataBusiness?.categoriesTitle
        businessAlias = dataBusiness?.alias.toString()

        //setup Actionbar and navigasi up
        val actionbar = supportActionBar
        actionbar?.title = "Detail Business"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        //function
        getOperationHours(dataBusiness?.alias)
        collectData(dataBusiness)

        // method review
        setupObserver()
        buildList()

    }

    private fun collectData(materi: Business?) {
        if (materi != null) {

            binding.tvContenttitle.text = materi.categoriesTitle
            binding.tvNameDetailwahana.text = materi.name

            val trim1 = materi.locationDisplayAddress.replace("[","").replace("]","")
            binding.tvDateDetailwahana.text = trim1

            binding.tvTitleDesc.text = "Services :"

            val trim2 = materi.transactions.replace("[","").replace("]","").replace(","," and ")
            binding.tvWahanaDesc.text = trim2

            binding.tvTotalviewWahana.text = materi.rating.toString()
            binding.ivContentDetail.loadImage(this,materi.imageUrl)

            binding.tvTitleComment.text = "HOURS OF OPERATION"



        }
    }


    private fun setupObserver() {
        detailBusinessReviewViewModel.getbusinessReview(businessAlias).observe(this) { data ->
            Timber.tag(tag).d("observer_businessreview${data.message}")
            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressbarCreatewahana.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressbarCreatewahana.visibility = View.GONE
                        adapter.setData(data.data)
                        Timber.tag(tag).d("observer_businessreview_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressbarCreatewahana.visibility = View.GONE
//                        val message = ErrorMessageSplit.message(data.message.toString())
//                        val code = ErrorMessageSplit.code(data.message.toString())
                        SimpleDialog.newInstance(data.message.toString(),data.message.toString())
                            .show(supportFragmentManager, SimpleDialog.TAG)
                    }
                }

            }
        }
    }

    private fun buildList() {
        adapter = DetailBusinessReviewAdapter()
        binding.rvWahanaComment.setHasFixedSize(true)
        binding.rvWahanaComment.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvWahanaComment.adapter = adapter

        binding.rvWahanaComment.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // using fast android networking (sorry not using model class)
    private fun getOperationHours(alias : String?){

        val urlGetOperationHours = "https://api.yelp.com/v3/businesses/$alias"
        Timber.d("url get : $urlGetOperationHours")
        AndroidNetworking.get(urlGetOperationHours)
            .addHeaders("Authorization", "Bearer ${mPreferenceEntity.token}")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    val jsonObject = response?.getJSONArray("hours")?.getJSONObject(0)?.getJSONArray("open")
                    val objLength = jsonObject?.length()
                    Timber.d("check length open array: $objLength")

                    var open1 = "-1"
                    var close1 = "-1"
                    var open2 = "-1"
                    var close2 = "-1"
                    var open3 = "-1"
                    var close3 = "-1"
                    var open4 = "-1"
                    var close4 = "-1"
                    var open5 = "-1"
                    var close5 = "-1"
                    var open6 = "-1"
                    var close6 = "-1"
                    var open7 = "-1"
                    var close7 = "-1"

                    for (i in 0 until objLength!!){
                        Timber.d("check value i : $i")
                        when (i){
                            0 -> {
                                open1 = jsonObject.getJSONObject(i).getInt("start").toString()
                                close1 = jsonObject.getJSONObject(i).getInt("end").toString()
                            }

                            1 -> {
                                open2 = jsonObject.getJSONObject(i).getInt("start").toString()
                                close2 = jsonObject.getJSONObject(i).getInt("end").toString()
                            }

                            2 -> {
                                open3 = jsonObject.getJSONObject(i).getInt("start").toString()
                                close3 = jsonObject.getJSONObject(i).getInt("end").toString()
                            }

                            3 -> {
                                open4 = jsonObject.getJSONObject(i).getInt("start").toString()
                                close4 = jsonObject.getJSONObject(i).getInt("end").toString()
                            }

                            4 -> {
                                open5 = jsonObject.getJSONObject(i).getInt("start").toString()
                                close5 = jsonObject.getJSONObject(i).getInt("end").toString()
                            }

                            5 -> {
                                open6 = jsonObject.getJSONObject(i).getInt("start").toString()
                                close6 = jsonObject.getJSONObject(i).getInt("end").toString()
                            }

                            6 -> {
                                open7 = jsonObject.getJSONObject(i).getInt("start").toString()
                                close7 = jsonObject.getJSONObject(i).getInt("end").toString()
                            }

                        }

                    }

                    val dayOff = "Day Off"

                    if (!open1.equals("-1")){
                        open1 = open1.substring(0, 2) + "." + open1.substring(2, open1.length)
                        close1 = close1.substring(0, 2) + "." + close1.substring(2, close1.length)
                        binding.tvMonday.text = "Monday : $open1 - $close1"
                    } else {
                        binding.tvMonday.text = "Monday : $dayOff"
                    }

                    if (!open2.equals("-1")){
                        open2 = open2.substring(0, 2) + "." + open2.substring(2, open2.length)
                        close2 = close2.substring(0, 2) + "." + close2.substring(2, close2.length)
                        binding.tvTuesday.text = "Tuesday : $open2 - $close2"
                    } else {
                        binding.tvTuesday.text = "Tuesday : $dayOff"
                    }

                    if (!open3.equals("-1")){
                        open3 = open3.substring(0, 2) + "." + open3.substring(2, open3.length)
                        close3 = close3.substring(0, 2) + "." + close3.substring(2, close3.length)
                        binding.tvWednesday.text = "Wednesday : $open3 - $close3"
                    } else {
                        binding.tvWednesday.text = "Wednesday : $dayOff"
                    }

                    if (!open4.equals("-1")){
                        open4 = open4.substring(0, 2) + "." + open4.substring(2, open4.length)
                        close4 = close4.substring(0, 2) + "." + close4.substring(2, close4.length)
                        binding.tvThursday.text = "Thursday : $open4 - $close4"
                    } else {
                        binding.tvThursday.text = "Thursday : $dayOff"
                    }

                    if (!open5.equals("-1")){
                        open5 = open5.substring(0, 2) + "." + open5.substring(2, open5.length)
                        close5 = close5.substring(0, 2) + "." + close5.substring(2, close5.length)
                        binding.tvFriday.text = "Friday : $open5 - $close5"
                    } else {
                        binding.tvFriday.text = "Friday : $dayOff"
                    }

                    if (!open6.equals("-1")){
                        open6 = open6.substring(0, 2) + "." + open6.substring(2, open6.length)
                        close6 = close6.substring(0, 2) + "." + close6.substring(2, close6.length)
                        binding.tvSaturday.text = "Saturday : $open6 - $close6"
                    } else {
                        binding.tvSaturday.text = "Saturday : $dayOff"
                    }

                    if (!open7.equals("-1")){
                        open7 = open7.substring(0, 2) + "." + open7.substring(2, open7.length)
                        close7 = close7.substring(0, 2) + "." + close7.substring(2, close7.length)
                        binding.tvSunday.text = "Sunday : $open7 - $close7"
                    } else {
                        binding.tvSunday.text = "Sunday : $dayOff"
                    }

                }

                override fun onError(anError: ANError?) {

                    Timber.d("on Error get operation hours : ${anError?.message}")
                    Toast.makeText(this@DetailBusiness, "Something Wrong!", Toast.LENGTH_SHORT).show()

                }

            })
    }
}