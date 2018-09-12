package com.mbhgroup.cricketasiacup2018;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * Created by appsd on 5/31/2018.
 */


public class Home extends AppCompatActivity {

    CardView mainBTClick;
    TextView moreapps, shareapps;
    ImageView img;
    InterstitialAd ads;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        addAdss();

        ads = new InterstitialAd(this);
        ads.setAdUnitId(getString(R.string.defualt_ins));
        ads.loadAd(new AdRequest.Builder().build());

        mainBTClick = findViewById(R.id.fifa_id);
        mainBTClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ads.isLoaded()) {

                    ads.show();
                } else {

                    startActivity(new Intent(Home.this, MainActivity.class));

                }
            }
        });

        ads.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                ads.loadAd(new AdRequest.Builder().build());
                startActivity(new Intent(Home.this, MainActivity.class));

            }

        });




        shareapps = findViewById(R.id.share_id);
        shareapps.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                final String appPackageName = getPackageName();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareSubText = "WhatsApp - The Great Chat App";
                String shareBodyText = "https://play.google.com/store/apps/details?id=" + appPackageName;
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubText);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(shareIntent, "Share With"));

            }
        });


        moreapps = findViewById(R.id.moreapps);
        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

    }



    // Exit Application
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    // Exit Application
//comment line test
    // ads start
    private void addAdss() {
        // add AdMob
        AdView adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.defualt_add));
        adView.setAdSize(AdSize.SMART_BANNER);

        LinearLayout.LayoutParams adLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        adView.setLayoutParams(adLayoutParams);

        //
        LinearLayout layout = new LinearLayout(this);
        layout.addView(adView);
        layout.setGravity(Gravity.BOTTOM);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        addContentView(layout, layoutParams);

        // load ad
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
// ads end


}
