package com.dezhen.aesop.collection;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dezhen.aesop.CheckInternet;
import com.dezhen.aesop.R;
import com.dezhen.aesop.ReadStreamFormText;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.Collections;

public class Co_Content_1 extends AppCompatActivity {
    private TextView textview_co_content;
    private String co_content_title_string;
    private String co_content_text_string;
    private Toolbar toolbar_title;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_content);

        getID();
        loadAds();
        if(!CheckInternet.isConnected(context)){
            makeToast("Please make sure you have internet access!");
        }
        //data
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("Co_Content_1");
        getContentString(position);
        setContent();
    }

    private void getID(){
        toolbar_title = findViewById(R.id.co_topAppBar_title);
        textview_co_content = findViewById(R.id.co_content_text);
        context = getApplicationContext();
    }
    private void setContent(){
        toolbar_title.setTitle(co_content_title_string);
        textview_co_content.setText(co_content_text_string);
    }
    private void getContentString(int position){
        switch(position){
            case 0:
                co_content_title_string = getStringTitle(R.string.c_09);
                co_content_text_string = getStringText(R.raw.c_09); break;
            case 1:
                co_content_title_string = getStringTitle(R.string.c_10);
                co_content_text_string = getStringText(R.raw.c_10); break;
            case 2:
                co_content_title_string = getStringTitle(R.string.c_11);
                co_content_text_string = getStringText(R.raw.c_11); break;
            case 3:
                co_content_title_string = getStringTitle(R.string.c_12);
                co_content_text_string = getStringText(R.raw.c_12); break;

            case 4:
                co_content_title_string = getStringTitle(R.string.c_13);
                co_content_text_string = getStringText(R.raw.c_13); break;
            case 5:
                co_content_title_string = getStringTitle(R.string.c_14);
                co_content_text_string = getStringText(R.raw.c_14); break;

            case 6:
                co_content_title_string = getStringTitle(R.string.c_15);
                co_content_text_string = getStringText(R.raw.c_15); break;
            case 7:
                co_content_title_string = getStringTitle(R.string.c_16);
                co_content_text_string = getStringText(R.raw.c_16); break;
        }
    }
    private String getStringTitle(int resource){
        return getResources().getString(resource);
    }
    private String getStringText(int resource){
        return new ReadStreamFormText().getString(getResources().openRawResource(resource));
    }
    private void makeToast(String toast){
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
    //load ads
    private void loadAds() {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, initializationStatus -> {});
        // Set your test devices. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
        // to get test ads on this device."
        /*MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(Collections.singletonList("ABCDEF012345"))
                        .build());*/

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        AdView adView = findViewById(R.id.adView);

        // Create an ad request.
        AdRequest adRequest = new AdRequest.Builder().build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }
}
