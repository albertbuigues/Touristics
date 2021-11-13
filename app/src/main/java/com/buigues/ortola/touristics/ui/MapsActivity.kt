package com.buigues.ortola.touristics.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.buigues.ortola.touristics.R
import com.buigues.ortola.touristics.databinding.ActivityMapsBinding
import com.buigues.ortola.touristics.model.entity.PointOfInterest
import com.buigues.ortola.touristics.viewmodel.MapsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val mapsViewModel: MapsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            permissions ->
                if (permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)) {
                    Log.d("fine_location", "Permission granted")
                } else {
                    Log.d("fine_location", "Permission not granted")
                    getBackToMainActivity()
                    Toast.makeText(this, "Necessites acceptar els permisos de geolocalització per a realitzar la ruta", Toast.LENGTH_LONG).show()
                }
                if (permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)) {
                    Log.d("coarse_location", "Permission granted")
                } else {
                    Log.d("coarse_location", "Permission not granted")
                    getBackToMainActivity()
                    Toast.makeText(this, "Necessites acceptar els permisos de geolocalització per a realitzar la ruta", Toast.LENGTH_LONG).show()
                }
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        requestLocationPermissions()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        lifecycleScope.launch {
            val listOfPoints = getRoutePoints()
            for (point in listOfPoints) {
                mMap.addMarker(MarkerOptions()
                    .position(LatLng( point.latitude, point.longitude))
                    .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_AZURE
                    ))
                )
                if (point == listOfPoints[0]) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(point.latitude, point.longitude), 18f))
                }
            }
        }
    }

    private fun requestLocationPermissions() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) -> {
                Log.d("fine_location", "Permission already granted")
            }
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                Log.d("coarse_location", "Permission already granted")
            }
            else -> {
                requestPermissionLauncher.launch(permissions)
            }
        }
    }

    private fun getBackToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun getRouteId(): Int {
        return intent.getIntExtra("routeId", 0)
    }

    // Gets the points from room repository through ViewModel
    private suspend fun getRoutePoints(): List<PointOfInterest> {
        val route = getRouteId()
        return mapsViewModel.getRoutePoints(route)
    }
}