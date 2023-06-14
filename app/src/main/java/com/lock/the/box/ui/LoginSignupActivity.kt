package com.lock.the.box.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lock.the.box.R
import com.lock.the.box.databinding.ActivityLoginSignupBinding

import com.lock.the.box.helper.BasePreferencesManager
import com.lock.the.box.helper.ToastUtils
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
                override fun onSuccess(o: Any) {
                    val responceAuthModel: BaseResponseData = o as BaseResponseData
                    ToastUtils.showLongToast(responceAuthModel.message)
//                    BasePreferencesManager.putBoolean(BasePreferencesManager.IS_LOGIN,true)
                    val i = Intent(this@LoginSignupActivity, SignUpActivity::class.java)
//                    i.putExtra("loginFor", loginFor)
//                    i.putExtra("afterlogindata", afterlogindata)
                    i.putExtra("mobile", phoneNo.trim { it <= ' ' })
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(i)
//                    finish()
                    pd.dismiss()
                }

                override fun onError(retroError: RetroError) {
                    ToastUtils.showShortToast(retroError.toString())

                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /* private void getUserProfile(String currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {
                            //doFaceBookLogin();
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            //     String email = object.getString("email");
                            String id = object.getString("id");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }
*/
/*
    private fun GoogleLogin() {
        try {
            val authApiHelper = ApiClient.getClient().create(AuthApiHelper::class.java)
            val requestLoginModel = RequestAuthModel()
            requestLoginModel.phoneNo=""
            val call: Call<ResponceAuthModel>
            call = authApiHelper.doLoging(requestLoginModel)
            call.enqueue(object : CallbackManager<ResponceAuthModel?>() {
                override fun onSuccess(o: Any) {
                    val responceAuthModel: ResponceAuthModel = o as ResponceAuthModel

                }

                override fun onError(retroError: RetroError) {

                }
            })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
*/
}