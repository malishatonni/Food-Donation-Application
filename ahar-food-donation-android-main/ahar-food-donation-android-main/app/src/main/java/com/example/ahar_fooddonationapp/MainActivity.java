package com.example.ahar_fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ahar_fooddonationapp.Models.Organization;
import com.example.ahar_fooddonationapp.databinding.ActivityHomeBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    List<Organization> organizationsList= new ArrayList();
    ArrayList<String> headers= new ArrayList();
    ActivityHomeBinding activityHomeBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchOrganizations();
    }
    public void fetchOrganizations(){
        OkHttpClient client= new OkHttpClient();
        //https://api.json-generator.com/templates/7Ixas8byUD7_/data
        String url="http://192.168.0.102:8080/api/organization/near-organizations/"+0+"/"+0;
        Request reqest=new Request.Builder()
                .url(url)
                .build();
        client.newCall(reqest).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                MainActivity.this.runOnUiThread(new Runnable() {
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
                    MainActivity.this.runOnUiThread(new Runnable() {
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
                                Toast.makeText(MainActivity.this, " "+organizationsList.get(0).getOrganizationPhone(),Toast.LENGTH_SHORT);
                                //activityHomeBinding.showLocation.setText( ""+organizationsList.get(0).getOrganizationPhone());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });
    }
}