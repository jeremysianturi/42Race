package com.example.fortytworace.util.dialog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.core.utils.PreferenceEntity
import com.example.core.utils.UserPreference
import com.example.fortytworace.R
import com.example.fortytworace.databinding.ActivitySimpleDialogBinding
import com.example.fortytworace.ui.login.Login
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class SimpleDialog : DialogFragment() {

    companion object {

        const val TAG = "SimpleDialog"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String, subTitle: String): SimpleDialog {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = SimpleDialog()
            fragment.arguments = args
            return fragment
        }

        private lateinit var mPreference: UserPreference
        private lateinit var mPreferenceEntity: PreferenceEntity

        private var _binding: ActivitySimpleDialogBinding? = null
        private val binding get() = _binding!!

        private var codeError: String? = ""

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivitySimpleDialogBinding.inflate(inflater, container, false)
        dialog?.setCancelable(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPreference = UserPreference(requireActivity())
        mPreferenceEntity = mPreference.getPref()

        codeError = arguments?.getString(KEY_TITLE)

        Timber.tag(TAG).e("Key_code : $codeError")


        if (codeError == "401") {
            // set text button
            binding.btnPositive.text = "Go to Login"
            // set param islogin = false
            mPreferenceEntity.isLogin = false
            mPreference.setPref(mPreferenceEntity)
        }
        setupView(view)
        setupClickListeners(view)

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

    }

    private fun setupView(view: View) {
        binding.tvTitle.text = arguments?.getString(KEY_TITLE)
        binding.tvSubTitle.text = arguments?.getString(KEY_SUBTITLE)
    }

    private fun setupClickListeners(view: View) {
        binding.btnPositive.setOnClickListener {
            if (codeError == "401") {
                val gotoLogin = Intent(context, Login::class.java)
                startActivity(gotoLogin)
            }
            dismiss()
        }
//        binding.btnNegative.setOnClickListener {
//            dismiss()
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}