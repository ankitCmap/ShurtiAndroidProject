package com.lock.the.box.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.lock.the.box.databinding.ActivitySignupBinding
import com.lock.the.box.roomdatabase.BaseActivity

class SignUpActivity : BaseActivity()/*, View.OnClickListener */{
    lateinit var binding: ActivitySignupBinding
    var modile: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modile = intent.getStringExtra("mobile")
        initView()
//        binding.register?.setOnClickListener(this)
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

    /*override fun onClick(p0: View?) {
        TODO("Not yet implemented")
        when (p0?.id) {
            R.id.register ->
                //emailValidator()
              //  val check : Boolean = validate(binding.customerEmail.text.toString())
            if (binding.customerName.text.toString() == "") {
                binding.customerName.error = "This field is compulsory!"
            } else if (binding.customerName.text.toString().length < 5) {
                binding.customerName.error = "Please enter your complete name!"
            } else if (binding.customerName.toString().matches("[a-zA-Z ]+")) {
                binding.customerName.error = resources.getText(R.string.can_not_use_specail) + " name!"
            } else if (binding.customerEmail.text.toString() != "" && !check) {
                binding.customerEmail.error = "Please enter a valid email address!"
            } else if (binding.customerEmail.text.toString() == "") {
                binding.customerEmail.error = "This field is compulsory!"
            } else if (binding.customerNewPass.text.toString() == "") {
                binding.customerNewPass.error = "This field is compulsory!"
            } else if (binding.customerNewPass.text.toString().trim { it <= ' ' }.length < 5) {
                binding.customerNewPass.setError("Password is too short!")
            } else if (!binding.customerRePass.text.toString().trim { it <= ' ' }
                    .matches("[a-zA-Z0-9]+")) {
                binding.customerRePass.error = "Password accept only alphabets and numbers!"
            } else if (binding.customerRePass.text.toString() == "") {
                binding.customerRePass.error = "This field is compulsory!"
            } else if (binding.customerNewPass.text.toString() != binding.customerRePass.text.toString()) {
                binding.customerRePass.error = "Password does not match"
            } else if (binding.otp.text.toString() == "") {
               // binding.otp.error("Verify your mobile number!")
            } else {
                logRegisterEvent(
                    customerName.getText().toString(),
                    email.getText().toString(),
                    mobile_txt
                )
            }
        }

    }*/

}