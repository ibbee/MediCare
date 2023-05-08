package com.practice.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HealthArticlesDetailsActivity extends AppCompatActivity {
    ImageView img;
    Button btnBack;
    TextView tvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);
        img=findViewById(R.id.imageView);
        btnBack.findViewById(R.id.buttonHDBack);
        tvl=findViewById(R.id.textView_titleHD);

        Intent intent=getIntent();
        tvl.setText(intent.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            int resId=bundle.getInt("text2");
            img.setImageResource(resId);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesDetailsActivity.this,HealthArticleActivity.class));
            }
        });
    }


}