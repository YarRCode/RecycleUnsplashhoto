package com.testrpackage.recycleviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecycleViewInterface {

    ArrayList<RecyckeModel> recycleModel;

    String nameImage[];
    String urlOfImage[];
    String authorName[];

    String url;
    String width;
    String height;
    Double[] resol;

    RecyclerView recyclerView;
    RecycleViewAdapter adapter;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        recycleModel = new ArrayList<>();

        url = "https://api.unsplash.com/photos/?client_id=0S9-KU3rjgOP_giD2WihrAudKIoGugenTQVv_XyDK1o";

        nameImage = new String[10];
        urlOfImage = new String[10];
        authorName = new String[10];
        resol = new Double[10];

        recyclerView = findViewById(R.id.recycleView);

        httpCall(url);
    }

    public void httpCall(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray mainArrayJson = new JSONArray(response);
                            for (int i = 0; i < 10; i++) {
                                JSONObject list = mainArrayJson.getJSONObject(i);
                                JSONObject urls = list.getJSONObject("urls");
                                JSONObject users = list.getJSONObject("user");

                                if(list.getString("alt_description") != "null")
                                    nameImage[i] = list.getString("alt_description");
                                else
                                    nameImage[i] = "Without Name";

                                width = list.getString("width");
                                height = list.getString("height");

                                resol[i] = Double.parseDouble(width)/Double.parseDouble(height);

                                urlOfImage[i] = urls.getString("raw");
                                authorName[i] = users.getString("name");
                            }

                            setUpRecyckeModel();

                            adapter = new RecycleViewAdapter(MainActivity.this, recycleModel, MainActivity.this);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    private void setUpRecyckeModel() {

        for(int i = 0; i < nameImage.length; i++) {
            recycleModel.add(new RecyckeModel(nameImage[i],
                    urlOfImage[i],
                    authorName[i],
                    resol[i]));

        }
    }

    @Override
    public void onItemClick(int position) {
        intent = new Intent(MainActivity.this, GallaryActivity.class);
        intent.putExtra("url", recycleModel.get(position).getUrlOfImage());
        intent.putExtra("resol", recycleModel.get(position).getResol());
        startActivity(intent);
    }
}