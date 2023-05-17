package com.example.shurtiandroidproject.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.InputType
import androidx.core.widget.ImageViewCompat
import com.example.shurtiandroidproject.databinding.ActivityLogingPasswordBinding
import com.example.shurtiandroidproject.roomdatabase.BaseActivity

class LoginWithPasswordActivity:BaseActivity() {
    lateinit var binding: ActivityLogingPasswordBinding
    var passV: Boolean? = false
    var passwordStatus:kotlin.Boolean? = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogingPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
//        binding=DataBindingUtil.setContentView(this, R.layout.activity_loging_password)
    }

    private fun initView() {
    binding.apply {
        lnResetPassWord.setOnClickListener {
            val intent = Intent(this@LoginWithPasswordActivity, ChangePasswordActivity::class.java)
            intent.putExtra("from_login_page", true)
//            intent.putExtra("password_status", passwordStatus)
//            intent.putExtra("login_phone_no", phoneNumber)
            startActivity(intent)
        }

        lnChangePhoneNum.setOnClickListener {
            super@LoginWithPasswordActivity.onBackPressed()
        }
        
        lnPassEt.setOnClickListener {
            passV = if (passV!!) {
                ImageViewCompat.setImageTintMode(lnPassVisibility, PorterDuff.Mode.SRC_ATOP)
                ImageViewCompat.setImageTintList(
                    lnPassVisibility,
                    ColorStateList.valueOf(Color.parseColor("#2F80ED"))
                )
                lnPassEt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                lnPassEt.setSelection(lnPassEt.length())
                true
            } else {
                ImageViewCompat.setImageTintList(lnPassVisibility, null)
                lnPassEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                lnPassEt.setSelection(lnPassEt.length())
                false
            }
        }

        lnLoginBtn.setOnClickListener {
            /*if (!lnPassEt.getText().toString().trim { it <= ' ' }.matches("[a-zA-Z 0-9]+")) {
                passwordEt.setError("Only alpha numberic text is acceptable")
            } else if (passwordEt.getText().toString().trim { it <= ' ' }.length == 0) {
                passwordEt.setError("Enter password")
            } else {
                booksfortune.bookchor.mainFiles.LoginWithPassword_new.LoginWithPasswordService()
                    .execute()
            }*/

            val i = Intent(this@LoginWithPasswordActivity, NewMainActivity::class.java)
//            i.putExtra("loginFor", loginFor)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
            finish()
        }

        lnLoginWithOtpBtn.setOnClickListener {
            val intent = Intent(this@LoginWithPasswordActivity, LoginSignupActivity::class.java)
            startActivity(intent)
        }

    }
    }
}