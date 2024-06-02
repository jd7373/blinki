package com.example.userblink_itclone

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.userblink_itclone.databinding.FragmentOTPBinding
import com.example.userblink_itclone.viewmodels.AuthViewmodel
import kotlinx.coroutines.launch

class OTPFragment : Fragment() {

   private val viewmodel : AuthViewmodel by viewModels()
    private lateinit var binding: FragmentOTPBinding
    private lateinit var usernumber :String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentOTPBinding.inflate(layoutInflater)
        getusernumber()
        customisingEnteringOTP()
        sendotp()

        onBackbuttonClicked()


        return binding.root
    }

    private fun sendotp() {
Utils.showDialog(requireContext(),"sending otp")
        viewmodel.sendOTP(usernumber,requireActivity())
        viewmodel.apply {
            sendOTP(usernumber,requireActivity())
            lifecycleScope.launch {
                otpSet.collect{
                    if(it){
                        Utils.hideDialog()
                        Utils.showToast(requireContext(),"OTP Sent")

                    }
                }

            }
        }


    }

    private fun onBackbuttonClicked() {
      binding.tbOtpFragment.setOnClickListener{
          findNavController().navigate(R.id.action_OTPFragment_to_signInFragment)
      }

    }

    private fun customisingEnteringOTP() {
        val editTexts = arrayOf(binding.etOtp1,binding.etOtp2,binding.etOtp3,binding.etOtp4,binding.etOtp5,binding.etOtp6)
        for (i in editTexts.indices){
            editTexts[i].addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int,count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int,count: Int, after: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length==1) {
                        if (i < editTexts.size - 1) {
                            editTexts[i + 1].requestFocus()
                        }
                    }else if (s?.length ==0){
                        if(i > 0){
                            editTexts[i-1].requestFocus()
                        }
                }

            }
            })


        }

    }

    private fun getusernumber() {
        val bundle = arguments
        usernumber = bundle?.getString("number").toString()
        binding.tvUserNumber.text = usernumber
    }

}
