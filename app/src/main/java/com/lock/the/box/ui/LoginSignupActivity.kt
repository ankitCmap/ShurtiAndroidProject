package com.lock.the.box.ui

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.lock.the.box.R
import com.lock.the.box.databinding.ActivityLoginSignupBinding
import com.lock.the.box.helper.BasePreferencesManager
import com.lock.the.box.helper.Utils
import com.lock.the.box.model.BaseResponseData
import com.lock.the.box.network.api.ApiClient
import com.lock.the.box.network.api.AuthApiHelper
import com.lock.the.box.network.api.CallbackManager
import com.lock.the.box.network.api.RequestAuthModel
import com.lock.the.box.network.api.RetroError
import com.lock.the.box.roomdatabase.BaseActivity

class LoginSignupActivity : BaseActivity() {

    private var result:Boolean=true
    lateinit var binding: ActivityLoginSignupBinding

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
                if (loginPhoneNumber.text.toString().trim { it <= ' ' } == "") {
                    loginPhoneNumber.error = "This field is compulsory!"
                    loginPhoneNumber.requestFocus()
                }else {
                    loginWithOTP(loginPhoneNumber.text.toString())
                }

            }

        }

    }
    private fun loginWithOTP(phoneNo: String) {
        try {
            val pd = ProgressDialog(this)
            pd.setMessage("please wait..")
            pd.show()
            val authApiHelper = ApiClient.getClient().create(AuthApiHelper::class.java)
            val loginWithOtpModel = RequestAuthModel()
            loginWithOtpModel.phoneNo = phoneNo
            val call: retrofit2.Call<BaseResponseData> = authApiHelper.doLoging(loginWithOtpModel)
            call.enqueue(object : CallbackManager<BaseResponseData>() {
                @SuppressLint("SuspiciousIndentation")
                override fun onSuccess(o: Any) {
                    val responceAuthModel: BaseResponseData = o as BaseResponseData
                    Toast.makeText(applicationContext,responceAuthModel.message.toString(),Toast.LENGTH_LONG).show()
                    Log.e("encryptedValue : ",Utils.encryptedValue(responceAuthModel.message.toString()))
//                  BasePreferencesManager.putBoolean(BasePreferencesManager.IS_LOGIN,true)
                    val i = Intent(this@LoginSignupActivity, SignUpActivity::class.java)
                    i.putExtra("phone_number", binding.loginPhoneNumber.text.toString().trim())
                    startActivity(i)
                    pd.dismiss()
                }

                override fun onError(retroError: RetroError) {
                    Toast.makeText(applicationContext,retroError.toString(),Toast.LENGTH_LONG).show()
                    pd.dismiss()
                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}