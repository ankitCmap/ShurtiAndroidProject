package com.example.shurtiandroidproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.databinding.ActivitySocialBinding
import com.example.shurtiandroidproject.helper.BasePreferencesManager
import com.example.shurtiandroidproject.roomdatabase.BaseActivity
import com.facebook.*
import com.facebook.CallbackManager.Factory.create
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import org.json.JSONException
import java.util.*


class SocialActivity : BaseActivity() {
    lateinit var binding: ActivitySocialBinding
    lateinit var callbackManager:CallbackManager
    lateinit var accessToken:AccessToken
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_social)
        callbackManager = create()
        initView()

    }

    private fun initView() {
        binding.llFacebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))
        }
        binding.tvcontinue.setOnClickListener {
            BasePreferencesManager.putBoolean(BasePreferencesManager.IS_LOGIN, true)
            val intent = Intent(this@SocialActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    accessToken= AccessToken.getCurrentAccessToken()!!
                    facedata()

                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(error: FacebookException) {
                    // App code
                }
            })
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun facedata(){
        val request = GraphRequest.newMeRequest(
            accessToken
        ) { obj, _ ->
            try {
                BasePreferencesManager.putBoolean(BasePreferencesManager.IS_LOGIN, true)
                BasePreferencesManager.putString(BasePreferencesManager.FB_ID, obj?.getString("id"))
                BasePreferencesManager.putString(
                    BasePreferencesManager.FB_USER_NAME,
                    obj?.getString("name")
                )
                BasePreferencesManager.putString(
                    BasePreferencesManager.FB_USER_DOB,
                    obj?.getString("birthday")
                )
                BasePreferencesManager.putString(
                    BasePreferencesManager.FB_USER_GENDER,
                    obj?.getString("gender")
                )
                BasePreferencesManager.putString(
                    BasePreferencesManager.FB_PROFILE_PATH,
                    obj?.getString("link")
                )
                val intent = Intent(this@SocialActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } catch (e: JSONException) {
                e.toString()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,link")
        request.parameters = parameters
        request.executeAsync()


    }
}