package com.pasc.tanush.registrationmodule;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qrCode extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Button button, done_button;
    ImageView iv;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        //Set the toolbar as the app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggler = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggler);
        toggler.syncState();


        iv = (ImageView) findViewById(R.id.image);
        done_button = (Button) findViewById(R.id.done);

        String idQR = getIntent().getStringExtra("qrId");

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(idQR, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            iv.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getBaseContext(), EventRegistrationActivity.class);
                startActivity(intent1);
                finish();
            }
        });





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.refresh:
                finish();
                Intent intent1 = new Intent(getBaseContext(), WorkshopRegistrationActivity.class);
                startActivity(intent1);
                return true;

            case R.id.logout:
                finish();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                return true;
//            case R.id.changeTheme :
//                int count = 0;
//                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
//                    count = 1;
//                final int finalCount = count;
//                if (count == 1) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    finish();
//                    Intent intent2=new Intent(getBaseContext(),WorkshopRegistrationActivity.class);
//                    startActivity(intent2);
//
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    finish();
//                    Intent intent3=new Intent(getBaseContext(),WorkshopRegistrationActivity.class);
//                    startActivity(intent3);
//
//                }


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        //super.onCreateOptionsMenu(menu)
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);
        mDrawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.events:
                Intent intent = new Intent(getBaseContext(), EventRegistrationActivity.class);
                startActivity(intent);

                return true;
            case R.id.workshops:
                Intent intent1 = new Intent(getBaseContext(), WorkshopRegistrationActivity.class);
                startActivity(intent1);
        }
        return true;
    }

}
