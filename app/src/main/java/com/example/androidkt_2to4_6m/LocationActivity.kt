package com.example.androidkt_2to4_6m

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidkt_2to4_6m.databinding.ActivityLocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.Locale

class LocationActivity : AppCompatActivity() {
    lateinit var binding: ActivityLocationBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1000
    var pgbar: ProgressBar?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient=LocationServices.getFusedLocationProviderClient(this@LocationActivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        pgbar=findViewById(R.id.progress)


        if (checkPermisssion()) {
            println("Permission checked")
            getLastLocation()
        } else {
            //  binding.progress.visibility = View.GONE
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation()
                } else {
                    Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkPermisssion() : Boolean{
        return ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION
        )==PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun getLastLocation() {
        pgbar?.visibility = View.VISIBLE
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location->
             pgbar?.visibility= View.GONE
                if(location!=null){
                    var userLong = location.longitude
                    var userLat = location.latitude
                    Log.e("address", "onCreate:${getCompleteAddressString(userLat,userLong)} ", )
                    var address=getCompleteAddressString(userLat,userLong)
                    binding.tvlocation?.setText(address)
                    binding.tvLatitude?.setText(userLat.toString())
                    binding.tvLongitude?.setText(userLong.toString())
                }
            }
    }
    private fun getCompleteAddressString(LATITUDE: Double, LONGITUDE: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0]

                val addressString = address.getAddressLine(0) // Get the main address line

                // Remove the Place ID from the address string if it's present
                val placeIdIndex = addressString.indexOf(" ")
                if (placeIdIndex != -1) {
                    return addressString.substring(placeIdIndex + 1)
                } else {
                    return addressString
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "No address found"
    }
}