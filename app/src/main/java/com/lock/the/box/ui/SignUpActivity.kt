package com.lock.the.box.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lock.the.box.R
import com.lock.the.box.databinding.ActivitySignupBinding
import com.lock.the.box.network.RetrofitHelper
import com.lock.the.box.network.WebServices
import com.lock.the.box.repository.SignUpRepository
import com.lock.the.box.repository.VerifyOtpRepository
import com.lock.the.box.roomdatabase.BaseActivity
import com.lock.the.box.viewmodel.SignUpViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignUpActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivitySignupBinding
    var modile: String? = null
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var verifyOtpRepository: VerifyOtpRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modile = intent.getStringExtra("phone_number")
        initView()
        verifyOtpRepository = VerifyOtpRepository(
            RetrofitHelper.createRetrofitService(
                WebServices::class.java
            )
        )
        signUpViewModel = getViewModel()
        setObserver()
        binding.register.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
    }

    private fun initView() {

        binding.apply {
            mobileLine.text = "We have sent 4 digit OTP on $modile "
            changeNum.setOnClickListener {
                finish()
            }
            register.setOnClickListener {
                val i = Intent(this@SignUpActivity, NewMainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
                finish()
            }

            resend.setOnClickListener {
                object : CountDownTimer(30000, 700) {
                    override fun onTick(millisUntilFinished: Long) {
                        timer.text = "Resend OTP in : ${millisUntilFinished / 1000} seconds"
                        resend.visibility = View.GONE
                        timer.visibility = View.VISIBLE
                    }

                    override fun onFinish() {
                        timer.visibility = View.GONE
                        resend.visibility = View.VISIBLE
                    }
                }.start()
            }

            object : CountDownTimer(30000, 700) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    timer.text = "Resend OTP in : ${millisUntilFinished / 1000} seconds"
                    resend.visibility = View.GONE
                    timer.visibility = View.VISIBLE
                }

                override fun onFinish() {
                    timer.visibility = View.GONE
                    resend.visibility = View.VISIBLE
                }
            }.start()
        }
    }

    fun getViewModel(): SignUpViewModel {
        return ViewModelProvider(
            this, SignUpViewModel.Factory(
                SignUpRepository(
                    RetrofitHelper.createRetrofitService(
                        WebServices::class.java
                    )
                )
            )
        ).get("", SignUpViewModel::class.java)
    }

    fun setObserver() {
        signUpViewModel.signUpResponse.observe(this) {
            if (it.status != null) {
                Toast.makeText(this, "Login Successful.", Toast.LENGTH_LONG).show()
            }
        }
    }

    override  fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_register -> {
                if (binding.customerName.text.toString()
                        .isEmpty() && binding.customerName.text.toString().length < 5
                ) {
                    binding.customerName.error = "Please enter your name"
                } else if (binding.customerEmail.text.toString()
                        .isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(binding.customerEmail.text.toString())
                        .matches()
                ) {
                    binding.customerEmail.error = "Please enter a valid email address!"
                } else if (binding.customerNewPass.text.toString().isEmpty()) {
                    binding.customerNewPass.error = "This field is compulsory!"
                } else if (binding.customerNewPass.text.toString().trim().length < 5) {
                    binding.customerNewPass.error = "Password is too short!"
                } else if (binding.customerRePass.text.toString().isEmpty()) {
                    binding.customerRePass.error = "This field is compulsory!"
                } else if (binding.customerNewPass.text.toString() != binding.customerRePass.text.toString()) {
                    binding.customerRePass.error = "Password does not match"
                } else/* if (binding.otpp.text.toString().isEmpty()) {
                   // binding.otpp.error("Verify your mobile number!")
                } else */ {
                    //val abc: String
                    var json = JSONObject()
                    json.put("user", modile)
                    json.put("uname", binding.customerName.text.toString())
                    json.put("email", binding.customerEmail.text.toString().trim())
                    json.put("city", "noida")
                    json.put("state", "up")
                    json.put("device_type", "Android")
                    json.put("device_id", "AS12233")
                    json.put("lat", "33.3333")
                    json.put("long", "43.333")
                    json.put("referrer_code", "9990ASDe333")
                    json.put("password", binding.customerNewPass.text.toString().trim())

                    signUpViewModel.signUpResponse(json)
                }

            }

            R.id.register -> {
                val opt:String = binding.otpp.text.toString().trim()
                GlobalScope.launch {
                    modile?.let { verifyOtp(opt) }
                }

            }
        }

    }

    private suspend fun verifyOtp(otp: String) {
        var json = JSONObject()
        json.put("phone_no", modile)
        json.put("otp_code", otp)
        verifyOtpRepository.otpRequest(json)


    }
}