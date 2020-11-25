package com.manuelarestrepo.adoptapp.ui.mapas

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.data.server.Refugios

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPoiClickListener {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportActionBar?.hide()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        setUpMap()

        mMap.setOnPoiClickListener(this)

        mMap.uiSettings.isZoomControlsEnabled = true

        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        loadData()
    }

    private fun loadData() {
        val database = FirebaseDatabase.getInstance()
        val refugiosRef = database.getReference("refugios")

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dato: DataSnapshot in snapshot.children) {
                    val refugios = dato.getValue(Refugios::class.java)
                    refugios?.let {
                        val pos = LatLng(refugios.latitud, refugios.longitud)
                        mMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(refugios.latitud, refugios.longitud))
                                .title(refugios.nombreRefugio)
                                .snippet(refugios.telefono)
                        )
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 17f))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        }
        refugiosRef.addValueEventListener(postListener)
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
    }

    override fun onPoiClick(poi: PointOfInterest?) {
        Toast.makeText(this, "Nombre: ${poi?.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {

    }
}