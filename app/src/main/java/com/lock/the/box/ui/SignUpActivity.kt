package com.lock.the.box.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.util.Util
import com.lock.the.box.R
import com.lock.the.box.adapter.helper.Utils
import com.lock.the.box.databinding.ActivitySignupBinding
import com.lock.the.box.network.RetrofitHelper
import com.lock.the.box.network.WebServices
import com.lock.the.box.repository.SignUpRepository
import com.lock.the.box.repository.VerifyOtpRepository
import com.lock.the.box.roomdatabase.BaseActivity
import com.lock.the.box.ui.home.HomeFragment
import com.lock.the.box.viewmodel.SignUpViewModel
import com.lock.the.box.viewmodel.VerifyOtpViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignUpActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivitySignupBinding
    var modile: String? = null
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var verifyOtpViewModel: VerifyOtpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modile = intent.getStringExtra("phone_number")
        signUpViewModel = getViewModel()
        verifyOtpViewModel = getOtpViewModel()
        initView()
        binding.register.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
        setObserver()
        setOtpObserver()
    }

    private fun initView() {

        binding.apply {
            mobileLine.text = "We have sent 4 digit OTP on $modile "
            changeNum.setOnClickListener {
                finish()
            }
            /*register.setOnClickListener {
               *//* val i = Intent(this@SignUpActivity, NewMainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
                finish()*//*
            }*/

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

    fun getOtpViewModel(): VerifyOtpViewModel {
        return ViewModelProvider(
            this, VerifyOtpViewModel.Factory(
                VerifyOtpRepository(
                    RetrofitHelper.createRetrofitService(
                        WebServices::class.java
                    )
                )
            )
        ).get("", VerifyOtpViewModel::class.java)
    }

    fun setObserver() {
        signUpViewModel.signUpResponse.observe(this) {
            if (it.status != null) {
                if (it.status==1) {
                    /*val mFragmentManager = supportFragmentManager
                    val mFragmentTransaction = mFragmentManager.beginTransaction()
                    val mFragment = HomeFragment()
                    mFragmentTransaction.add(R.id.frame_layout, mFragment).commit()*/
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    val i = Intent(this, NewMainActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }
    }

    fun setOtpObserver() {
        verifyOtpViewModel.signUpResponse.observe(this) {
            if (it.data !=null) {
               // if (it.status==1) {
                binding.register.setBackgroundColor(resources.getColor(R.color.green_text))
                binding.register.text = "VERIFIED"
                binding.signupLayout.visibility = View.VISIBLE
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                Utils.hideSoftKeyBoard(this, binding.register )
               // }
            }
        }
    }

    override fun onClick(p0: View?) {
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
                    val hashMap: HashMap<String, Any> = HashMap<String, Any>() //define empty hashmap
                    hashMap.put("user", modile.toString())
                    hashMap.put("uname", binding.customerName.text.toString())
                    hashMap.put("email", binding.customerEmail.text.toString().trim())
                    hashMap.put("city", "noida")
                    hashMap.put("state", "up")
                    hashMap.put("device_type", "Android")
                    hashMap.put("device_id", "AS12233")
                    hashMap.put("lat", "33.3333")
                    hashMap.put("long", "43.333")
                    hashMap.put("referrer_code", "9990ASDe333")
                    hashMap.put("password", binding.customerNewPass.text.toString().trim())
                      signUpViewModel.signUpResponse(hashMap,this)
                }

            }

            R.id.register -> {
                val otp: String = binding.etOtp.text.toString().trim()
                val hashMap: HashMap<String, Any> = HashMap<String, Any>() //define empty hashmap
                hashMap.put("phone_no", modile.toString())
                hashMap.put("otp_code", otp)
                verifyOtpViewModel.signUpResponse(hashMap,this)
                }

            }
        }

    }

   /* private suspend fun verifyOtp(otp: String) {

        val hashMap: HashMap<String, Any> = HashMap<String, Any>() //define empty hashmap
        hashMap.put("phone_no", modile.toString())
        hashMap.put("otp_code", otp)

        val data = verifyOtpRepository.otpRequest(hashMap)

        if (!data.data?.token.isNullOrEmpty()) {
            binding.register.setBackgroundColor(resources.getColor(R.color.green_text))
            binding.signupLayout.visibility = View.VISIBLE
            Toast.makeText(this, data.message, Toast.LENGTH_LONG).show()
           // Utils.hideSoftKeyBoard(this, binding.register )
        }
    }*/
