package com.reelsdownloder.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.reelsdownloder.R;

public class enbl extends AppCompatActivity {

    private InterstitialAd interstitialAd;
    private final String TAG = enbl.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enbl);

        ImageView btn = (ImageView) findViewById(R.id.insta_bt);
        AudienceNetworkAds.initialize(this);


        AdView adView = new AdView(this, "560529944623764_560530341290391", AdSize.BANNER_HEIGHT_50);

// Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

// Add the ad view to your activity layout
        adContainer.addView(adView);

// Request an ad
        adView.loadAd();

        interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");


        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
                showAdWithDelay();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
            interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdWithDelay();
               /* Toast.makeText(enbl.this, "This is my Toast message!",
                        Toast.LENGTH_LONG).show();*/
                Intent iIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");

                //iIntent.setComponent(new ComponentName( "com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
                iIntent.setData( Uri.parse( "https://www.instagram.com/ishubhambranwal/") );
                startActivity(iIntent);

            }
        });


    }

        private void showAdWithDelay() {
            /**
             * Here is an example for displaying the ad with delay;
             * Please do not copy the Handler into your project
             */
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Toast.makeText(enbl.this, "This is my Toast message! for ADs",
                            Toast.LENGTH_LONG).show();
                    // Check if interstitialAd has been loaded successfully
                    if(interstitialAd == null || !interstitialAd.isAdLoaded()) {
                        return;
                    }
                    // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
                    if(interstitialAd.isAdInvalidated()) {
                        return;
                    }
                    // Show the ad
                  // interstitialAd.show();
                }
            }, 20000);

    }
}