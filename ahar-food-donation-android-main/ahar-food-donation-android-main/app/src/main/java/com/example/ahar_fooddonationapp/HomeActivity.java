package com.example.ahar_fooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahar_fooddonationapp.Models.Organization;
import com.example.ahar_fooddonationapp.Models.OrganizationAdapter;
import com.example.ahar_fooddonationapp.databinding.ActivityHomeBinding;
import com.example.ahar_fooddonationapp.databinding.ActivitySignUpBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {
    List<Organization> organizationsList= new ArrayList();
    ArrayList<String> headers= new ArrayList();
    ActivityHomeBinding activityHomeBinding;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;
    private String name="";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        activityHomeBinding.getLocationIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    OnGPS();
                } else {
                    getLocation();
                }
            }
        });
        activityHomeBinding.searchLocationIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,
                        "Searching for.." +activityHomeBinding.searchOrgET.getText().toString(),
                        Toast.LENGTH_SHORT
                         )
                        .show();
            }
        });
        activityHomeBinding.btnDonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityHomeBinding.recyclerview.setVisibility(view.INVISIBLE);
                activityHomeBinding.secondView.setVisibility(view.VISIBLE);
            }
        });
        activityHomeBinding.btnOrganizations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityHomeBinding.secondView.setVisibility(view.INVISIBLE);
                activityHomeBinding.recyclerview.setVisibility(view.VISIBLE);
            }
        });
    }
//
    private String getCityName(double lon, double lat){
        String cityName= "NOT FOUND";
        Geocoder gcd= new Geocoder(getBaseContext(), Locale.getDefault());
        try{
            List<Address> addresses = gcd.getFromLocation(lat,lon,10);
            for(Address adr: addresses){
                if(adr!=null){
                    String city= adr.getLocality();
                    if(city!=null && !city.equals("")){
                        cityName= city;
                    }
                }
            }
        }
        catch (Exception e){
            Log.d("TAG","CITY NOT FOUND");
            Toast.makeText(this,"User City NOT FOUND",Toast.LENGTH_SHORT ).show();
        }
        return "City: "+cityName;

    }
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                HomeActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                HomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                activityHomeBinding.showLocation.setText(getCityName(longi,lat));
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void fetchOrganizations(){

        OkHttpClient client= new OkHttpClient();
        //https://api.json-generator.com/templates/7Ixas8byUD7_/data
        String url="http://192.168.0.102:8080/api/organization/near-organizations/"+0+"/"+0;
        Request reqest=new Request.Builder()
                .url(url)
                .build();
        client.newCall(reqest).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                HomeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activityHomeBinding.showLocation.setText(e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String res= response.body().string();
                    HomeActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray jsonArray = new JSONArray(res);
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject js= jsonArray.getJSONObject(i);
                                    String email=jsonArray
                                            .getJSONObject(i)
                                            .get("organizationEmail")+" ";
                                    String name=jsonArray
                                            .getJSONObject(i)
                                            .get("organizationName")+" ";
                                    String phone=jsonArray
                                            .getJSONObject(i)
                                            .get("phoneNumber")+" ";
                                    String location=jsonArray
                                            .getJSONObject(i)
                                            .get("location")+" ";
                                    Organization temp= new Organization(name,email,phone,location," ");
                                    organizationsList.add(temp);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            finally {
                                OrganizationAdapter adapter = new OrganizationAdapter(HomeActivity.this,organizationsList);
                                recyclerView= findViewById(R.id.recyclerview);
                                recyclerView.setAdapter(adapter);
                            }

                        }
                    });
                }
            }
        });

        Toast.makeText(HomeActivity.this, "Long:"+longitude+" Lat:"+latitude, Toast.LENGTH_SHORT).show();

    }






}

