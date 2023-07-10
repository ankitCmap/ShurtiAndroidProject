package com.lock.the.box.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lock.the.box.R
import com.lock.the.box.adapter.helper.BasePreferencesManager
import com.lock.the.box.adapter.helper.Utils
import com.lock.the.box.databinding.ActivitySignupBinding
import com.lock.the.box.network.RetrofitHelper
import com.lock.the.box.network.WebServices
import com.lock.the.box.repository.SignUpRepository
import com.lock.the.box.repository.VerifyOtpRepository
import com.lock.the.box.roomdatabase.BaseActivity
import com.lock.the.box.viewmodel.SignUpViewModel
import com.lock.the.box.viewmodel.VerifyOtpViewModel
import java.util.Locale


class SignUpActivity : BaseActivity(), View.OnClickListener {
    private var mId: String? = ""
    private var countryName: String =""
    private var cityName: String =""
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    lateinit var binding: ActivitySignupBinding
    var modile: String? = null
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var verifyOtpViewModel: VerifyOtpViewModel

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

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
            if (it.status==1) {
                BasePreferencesManager.putBoolean(BasePreferencesManager.IS_LOGIN,true)
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                val i = Intent(this, NewMainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
                finish()
            }
        }
    }

    fun setOtpObserver() {
        verifyOtpViewModel.signUpResponse.observe(this) {
            if (it.status == "1") {
                if(it.message=="User logged in"){
                    BasePreferencesManager.putBoolean(BasePreferencesManager.IS_LOGIN,true)
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    val i = Intent(this, NewMainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(i)
                    finish()
                }else{
                    binding.register.setBackgroundColor(resources.getColor(R.color.green_text))
                    binding.register.text = "VERIFIED"
                    binding.signupLayout.visibility = View.VISIBLE
                    binding.etOtp.visibility=View.GONE
                    binding.llTimer.visibility=View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    Utils.hideSoftKeyBoard(this, binding.register)
                    getLocation()
                }

            } else {
                binding.etOtp.visibility=View.VISIBLE
                binding.llTimer.visibility=View.VISIBLE
                binding.register.setBackgroundColor(resources.getColor(R.color.colorSplash))
                binding.register.text = "VERIFY"
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                Utils.hideSoftKeyBoard(this, binding.register)
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
                } else {
                    val hashMap: HashMap<String, Any> =
                        HashMap<String, Any>() //define empty hashmap
                    hashMap.put("user", modile.toString())
                    hashMap.put("uname", binding.customerName.text.toString())
                    hashMap.put("email", binding.customerEmail.text.toString().trim())
                    hashMap.put("city", cityName)
                    hashMap.put("state", "up")
                    hashMap.put("device_type", "Android")
                    mId?.let { hashMap.put("device_id", it) }
                    hashMap.put("lat",latitude)
                    hashMap.put("long",longitude)
                    hashMap.put("referrer_code", "")
                    hashMap.put("password", binding.customerNewPass.text.toString().trim())
                    signUpViewModel.signUpResponse(hashMap, this)
                }

            }

            R.id.register -> {
                val otp: String = binding.etOtp.text.toString().trim()
                val hashMap: HashMap<String, Any> = HashMap<String, Any>() //define empty hashmap
                hashMap.put("phone_no", modile.toString())
                hashMap.put("otp_code", otp)
                verifyOtpViewModel.signUpResponse(hashMap, this)
            }

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
                        latitude=list[0].latitude
                        longitude=list[0].longitude
                        countryName=list[0].countryName
                        cityName=list[0].locality
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

