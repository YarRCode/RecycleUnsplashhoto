package com.testrpackage.recycleviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GallaryActivity extends AppCompatActivity {

    String url;
    Double resol;
    int doubleToInt;

    ImageView galleryImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);

        init();
    }

    public void init() {
        galleryImage = findViewById(R.id.galleryImage);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            url = null;
        } else {
            url = extras.getString("url");
            resol = extras.getDouble("resol");

            doubleToInt = (int) ((int) 4000*resol);
            Picasso.get().load(url).resize(doubleToInt, 4000).onlyScaleDown().into(galleryImage);
        }
    }
}