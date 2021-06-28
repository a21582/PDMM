package com.ipca.formulaworld.ui.Map

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ipca.formulaworld.R
import com.ipca.formulaworld.ui.more.MoreFragment
import kotlinx.android.synthetic.main.fragment_map.*


/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment(), OnMapReadyCallback{
    // TODO: Rename and change types of parameters
    private lateinit var mMap: GoogleMap
    private lateinit var back_map_button: Button

    private lateinit var currentLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mGoogleMap: GoogleMap

    val REQUEST_CODE = 101

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        map_view.onCreate(savedInstanceState)
        map_view.onResume()

        map_view.getMapAsync(this)

        fusedLocationClient=LocationServices.getFusedLocationProviderClient(this.context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_map_button = view.findViewById<Button>(R.id.back_map_button)

        back_map_button.setOnClickListener {
            val ft: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            ft?.replace(R.id.fragment_placeholder, MoreFragment())
            ft?.commit()
        }

        localization_button.setOnClickListener() {
            val intent = Intent(activity, MyLocation::class.java)
            startActivity(intent)
        }
        }



        override fun onMapReady(map: GoogleMap) {
        map?.let {
            mMap = it
        }
        // Add a marker in Portugal and move the camera

        val portugal = LatLng(37.23194857318783, -8.62820165376542)
        mMap.addMarker(
            MarkerOptions()
                .position(portugal)
                .title("Autódromo Internacional do Algarve")
        )
            mMap.addMarker(MarkerOptions().position(LatLng(37.23212719730587,-8.620087629124821)).title("hello"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(portugal, 14F))
        }

        private fun fetchLocation(){

        }





}