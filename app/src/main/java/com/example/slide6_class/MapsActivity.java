package com.example.slide6_class;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.slide6_class.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private EditText editTextTextPersonName;
    private EditText editTextTextPersonName2;
    private EditText editTextTextPersonName3;
    private Button button2;
    private Button btn1;
    private GoogleMap mMap;
    private AlertDialog alertDialog;
    private ActivityMapsBinding binding;
    private SearchView seachView;
    private List<mapModel> mapModelList;
    private mapDAO mapDAO;
    private mapModel mapModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btn1 = (Button) findViewById(R.id.btn1);
        seachView = (SearchView) findViewById(R.id.seachView);
        mapModelList = new ArrayList<>();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.btn1_rows,null);
                builder.setView(view1);
                editTextTextPersonName = (EditText) view1.findViewById(R.id.editTextTextPersonName);
                editTextTextPersonName2 = (EditText) view1.findViewById(R.id.editTextTextPersonName2);
                editTextTextPersonName3 = (EditText) view1.findViewById(R.id.editTextTextPersonName3);
                if(editTextTextPersonName.equals("")){
                    Toast.makeText(getApplicationContext(), "Khong duoc de trong", Toast.LENGTH_SHORT).show();
                }else if(editTextTextPersonName2.equals("")){
                    Toast.makeText(getApplicationContext(), "Khong duoc de trong", Toast.LENGTH_SHORT).show();
                }else if(editTextTextPersonName3.equals("")){
                    Toast.makeText(getApplicationContext(), "Khong duoc de trong", Toast.LENGTH_SHORT).show();
                }
                button2 = (Button) view1.findViewById(R.id.button2);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mapModel = new mapModel();
                        mapDAO = new mapDAO(getApplicationContext());
                        String Name = editTextTextPersonName.getText().toString();
                        mapModel.setNameStore(Name);
                        String KinhDo = editTextTextPersonName2.getText().toString();
                        mapModel.setKinhDo(KinhDo);
                        String ViDo = editTextTextPersonName3.getText().toString();
                        mapModel.setViDo(ViDo);
                        mapDAO.insertMAPS(mapModel);
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
        List<Address> addressList = null;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        seachView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = seachView.getQuery().toString();
                List<Address> addressList = null;

                if (location!= null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                    mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng , 14.0f) );
                }
                List<Address> finalAddressList = addressList;
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Address address = finalAddressList.get(0);
                        Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
                        intent.putExtra("Lat",address.getLongitude());
                        intent.putExtra("Lng",address.getLatitude());
                        startActivity(intent);
                        return false;
                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
                startActivity(intent);
                return false;
            }
        });
        // Add a marker in Sydney and move the camera
        LatLng TPThaiBinh = new LatLng(20.447518, 106.331515);
        mMap.addMarker(new MarkerOptions().position(TPThaiBinh).title("Marker in Tp Thai Binh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TPThaiBinh));
        //caiugshjkaihfbjaoIhjv
    }
}