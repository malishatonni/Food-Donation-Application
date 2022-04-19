package com.example.ahar_fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahar_fooddonationapp.Models.HttpsTrustManager;
import com.example.ahar_fooddonationapp.databinding.ActivitySignUpBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding activitySignUpBinding;
    private String Url="http://192.168.0.102:8080/api/organization";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // avoid network on main thread issue
        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy.Builder()
                .permitAll()
                .build();

        StrictMode.setThreadPolicy(policy);
        //binding to review
        activitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        //since binding is used
        setContentView(activitySignUpBinding.getRoot());
        // hide the bar
        getSupportActionBar().hide();
        activitySignUpBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPostRequest();
            }
        });
        activitySignUpBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this
                ,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void doPostRequest() {
        OkHttpClient client=new OkHttpClient();
        MediaType JSON=MediaType.parse(("application/json;charset=utf-8"));
        JSONObject actualData=new JSONObject();

        try {
            actualData.put("fullname",activitySignUpBinding.etFullName.getText().toString());
            actualData.put("email",activitySignUpBinding.etEmail.getText().toString());
            actualData.put("password",activitySignUpBinding.etPassword.getText().toString());
            actualData.put("phonenumber",activitySignUpBinding.etPhone.getText().toString());
        } catch (JSONException e) {
            Log.d("HTTP","Jsaon expection");
            e.printStackTrace();
        }
        RequestBody body= RequestBody.create(JSON,actualData.toString());
        Log.d("OKHTTP","RequestBody Created");
        Request newReq=new Request.Builder()
                .url("http://192.168.0.102:8080/api/authenticate/register")
                .post(body)
                .build();
        try {
            Response res=client.newCall(newReq).execute();
            if(res.isSuccessful()){
                Toast.makeText(SignUpActivity.this,
                        "Account Created Successfully.You can log in",
                        Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(SignUpActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(SignUpActivity.this,
                        "Could not sign up",
                        Toast.LENGTH_SHORT)
                        .show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}