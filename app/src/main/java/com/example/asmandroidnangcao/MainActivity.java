package com.example.asmandroidnangcao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asmandroidnangcao.view.QLSVActivity;
import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {

    private LinearLayout lnQLSV;
    private LinearLayout lnMap;
    private LinearLayout lnNews;
    private LinearLayout lnSocial;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(MainActivity.this, QLSVActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(MainActivity.this,MapsActivity.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(MainActivity.this,RssActivity.class));
                    return true;
                case R.id.navigation_social:
                    startActivity(new Intent(MainActivity.this,FacebookActivity.class));
                    return true;
            }
            return false;
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        lnQLSV = (LinearLayout) findViewById(R.id.lnQLSV);
        lnMap = (LinearLayout) findViewById(R.id.lnMap);
        lnNews = (LinearLayout) findViewById(R.id.lnNews);
        lnSocial = (LinearLayout) findViewById(R.id.lnSocial);

        FacebookSdk.sdkInitialize(getApplicationContext());

        lnQLSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QLSVActivity.class));
            }
        });

        lnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,MapsActivity.class));

            }
        });

        lnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RssActivity.class));
            }
        });

        lnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FacebookActivity.class));
            }
        });


    }

}
