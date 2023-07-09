package com.lock.the.box.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lock.the.box.R
import com.lock.the.box.databinding.ActivityLoginSignupBinding
import com.lock.the.box.adapter.helper.BasePreferencesManager
import com.lock.the.box.adapter.helper.Utils
import com.lock.the.box.model.BaseResponseData
import com.lock.the.box.network.api.ApiClient
import com.lock.the.box.network.api.AuthApiHelper
import com.lock.the.box.network.api.CallbackManager
import com.lock.the.box.network.api.RequestAuthModel
import com.lock.the.box.network.api.RetroError
import com.lock.the.box.roomdatabase.BaseActivity
import java.util.Locale

class LoginSignupActivity : BaseActivity() {

    private var result:Boolean=true
    lateinit var binding: ActivityLoginSignupBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_login_signup)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()

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
                    Log.e("encryptedValue : ", Utils.encryptedValue(responceAuthModel.message.toString()))
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

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location != null) {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>


                        Toast.makeText(this, "Latitude\n${list[0].latitude}\n" +
                                " Longitude\n${list[0].longitude}\n" +
                                "Country Name\n${list[0].countryName}\n" +
                                "Locality\n${list[0].locality}\n" +
                                "Address\n${list[0].getAddressLine(0)}"
                            , Toast.LENGTH_LONG).show()

                    }
                }
            } else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }
}