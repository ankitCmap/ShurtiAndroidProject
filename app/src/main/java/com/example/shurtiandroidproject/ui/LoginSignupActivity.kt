package com.example.shurtiandroidproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.databinding.ActivityLoginSignupBinding
import com.example.shurtiandroidproject.helper.BasePreferencesManager
import com.example.shurtiandroidproject.roomdatabase.BaseActivity

class LoginSignupActivity :BaseActivity() {

    private var result:Boolean=true
    lateinit var binding:ActivityLoginSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_login_signup)
        initView()
    }

    private fun initView() {
        binding.loginSkip.setOnClickListener {
            BasePreferencesManager.putBoolean(BasePreferencesManager.IS_SKIP,true)
            val intent = Intent(this@LoginSignupActivity, NewMainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
        binding.apply {

            loginContinueBtn.setOnClickListener {
                if(result){
                    val i = Intent(this@LoginSignupActivity, LoginWithPasswordActivity::class.java)

                    startActivity(i)
                    finish()
                }else{
                    val i = Intent(this@LoginSignupActivity, SignUpActivity::class.java)

                    startActivity(i)
                    finish()
                }

            }


            /*if (loginPhoneNumber.text.toString().trim { it <= ' ' } == "") {
                loginPhoneNumber.error = "This field is compulsory!"
                loginPhoneNumber.requestFocus()
            }else {
                BasePreferencesManager.putBoolean(BasePreferencesManager.IS_LOGIN,true)
                val intent = Intent(this@LoginSignupActivity, NewMainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finish()
            }*/
        }

    }
}