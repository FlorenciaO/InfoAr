package com.educacionit.infoar.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.educacionit.infoar.databinding.FragmentUserMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UserMapFragment: Fragment() {

    companion object {
        const val ID_FRAGMENT = 123
        const val PARAM_USERNAME = "PARAM_USERNAME"
        const val PARAM_ADDRESS = "PARAM_ADDRESS"
        const val PARAM_LAT = "PARAM_LAT"
        const val PARAM_LNG = "PARAM_LNG"
    }

    private lateinit var binding: FragmentUserMapBinding
    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserMapBinding.inflate(inflater, container, false)

        val userName = arguments?.getString(PARAM_USERNAME)
        val address = arguments?.getString(PARAM_ADDRESS).orEmpty()

        val lat = arguments?.getDouble(PARAM_LAT, -34.0) ?: -34.0
        val lng = arguments?.getDouble(PARAM_LNG, 151.0) ?: 151.0

        binding.mapView.apply {
            onCreate(savedInstanceState)
            getMapAsync {
                map = it
                setUpMap(lat, lng, address)
            }
        }

        binding.userAddressTv.text = address
        binding.usernameTv.text = userName

        return binding.root
    }

    private fun setUpMap(lat: Double, lng: Double, address: String) {
        val latLng = LatLng(lat, lng)
        Log.d("UserMapFragment", "Lat: $lat, Lng: $lng")
        val marker = MarkerOptions().position(latLng).title(address)
        map?.clear()
        map?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        map?.addMarker(marker)
    }

}