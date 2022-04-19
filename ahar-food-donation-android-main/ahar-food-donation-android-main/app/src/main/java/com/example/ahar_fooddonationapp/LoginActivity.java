package com.example.ahar_fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahar_fooddonationapp.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView signUpText;
    private ProgressDialog progressDialog;
    private final String loginUrl="https://localhost:44347/api/Authenticate/login";
    private String user= "abtahee.farhan.ador.68@gmail.com";
    private String pass= "Password@123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username= findViewById(R.id.etUsername);
        password= findViewById(R.id.etPassword);
        loginButton= findViewById(R.id.btn_signIn);
        signUpText = findViewById(R.id.tvClickSignUp);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Signing in");
        progressDialog.setMessage("Signing in your Account");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this
                        ,HomeActivity.class);
                startActivity(intent);
            }

        });


        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sendLoginRequest(){
        Toast.makeText(LoginActivity.this, "CAME HERE Line 83",Toast.LENGTH_SHORT).show();
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginActivity.this, "CAME HERE line 91",Toast.LENGTH_SHORT);
                        parseJson(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "CAME HERE 98",Toast.LENGTH_SHORT);
                        Log.d("login",error.toString());

                    }
                }){
            //@Override
            protected Map<String,String> getParmas() throws AuthFailureError {
                Map<String,String>params =new HashMap<String, String>();
                params.put("username",user);
                params.put("password",pass);
                return params;

            };
        };
        queue.add(stringRequest);

    }
    public void parseJson(String jsonStr){
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject(jsonStr);
            if (jsonObject.get("REGISTER").equals("OK")){
                //
                Toast.makeText(LoginActivity.this, "CAME HERE",Toast.LENGTH_SHORT);
                showAlert(jsonObject.get("token").toString());
            }else {
                //showAlert(jsonObject.get("msg").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void showAlert(String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert=builder.create();
        alert.show();
    }
}