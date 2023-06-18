package com.lock.the.box.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.lock.the.box.databinding.ActivitySignupBinding
import com.lock.the.box.roomdatabase.BaseActivity

class SignUpActivity: BaseActivity() {
    lateinit var binding: ActivitySignupBinding
    var  modile:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modile = intent.getStringExtra("mobile")
        initView()
    }

    private fun initView() {

        binding.apply {
            mobileLine.text="We have sent 4 digit OTP on $modile "
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

       /* if (v.getId() == R.id.register) {
            emailValidator()
            val check: Boolean = validate(email.getText().toString())
            if (customerName.getText().toString() == "") {
                customerName.setError("This field is compulsory!")
                customerName.requestFocus()
            } else if (customerName.getText().toString().length < 5) {
                customerName.setError("Please enter your complete name!")
                customerName.requestFocus()
            } else if (customerName.toString().matches("[a-zA-Z ]+")) {
                customerName.setError(resources.getText(R.string.can_not_use_specail) + " name!")
                customerName.requestFocus()
            } else if (email.getText().toString() != "" && !check) {
                email.setError("Please enter a valid email address!")
                email.requestFocus()
            } else if (email.getText().toString() == "") {
                email.setError("This field is compulsory!")
                email.requestFocus()
            } else if (passwordEt.getText().toString() == "") {
                passwordEt.setError("This field is compulsory!")
                passwordEt.requestFocus()
            } else if (passwordEt.getText().toString().trim { it <= ' ' }.length < 5) {
                passwordEt.setError("Password is too short!")
                passwordEt.requestFocus()
            } else if (!repasswordEt.getText().toString().trim { it <= ' ' }
                    .matches("[a-zA-Z0-9]+")) {
                repasswordEt.setError("Password accept only alphabets and numbers!")
                repasswordEt.requestFocus()
            } else if (repasswordEt.getText().toString() == "") {
                repasswordEt.setError("This field is compulsory!")
                repasswordEt.requestFocus()
            } else if (repasswordEt.getText().toString() != passwordEt.getText().toString()) {
                repasswordEt.setError("Password does not match")
                repasswordEt.requestFocus()
            } else if (otp.getText().toString() == "") {
                otp.setError("Verify your mobile number!")
                otp.requestFocus()
            } else {*/
                //   logRegisterEvent(customerName.getText().toString(),email.getText().toString(),mobile_txt);

//            }
//        }


    }

}