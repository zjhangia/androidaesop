package com.dezhen.aesop.bbc;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dezhen.aesop.CheckFileExist;
import com.dezhen.aesop.CheckInternet;
import com.dezhen.aesop.R;
import com.dezhen.aesop.ReadStreamFormText;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;


public class BBC_Content_0 extends AppCompatActivity {

    private Context context;
    private TextView textView_content;
    private Toolbar toolbar_title;
    private String bbc_content_title_string;
    private String bbc_content_text_string;
    private String bbc_content_url_string;

    //download status with three flags
    //0 means buffing
    //1 means file not exist need to be downloaded
    //2 means download successfully
    private int downloadstatus = 1;

    private final static String urlOriginal = "https://aesop.pages.dev/mp3/";

    //bottom
    private ImageView imageView_play;
    private TextView textView_title;

    //dialog
    private TextView music_bbc_title;
    private SeekBar music_bbc_seekBar;
    private TextView music_bbc_starttime;
    private TextView music_bbc_endtime;
    private ImageView music_bbc_backward;
    private ImageView music_bbc_play;
    private ImageView music_bbc_forward;

    //test media player
    private MediaPlayer mediaPlayer;
    private String bbc_mp3_filename;
    //
    //
    private FileExistsHandler fileExist_handler;
    private DownloadHandler downloadhandler;

    //
    private String parentDirectory;

    private int position;
    private BottomSheetDialog bottomSheetDialog;

    //
    private long current_position;
    private long total_time;

    //
    private ProgressHandler progressHandler;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbc_content_ads);
        loadAds();
        getID();

        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("BBC_Content_0");
        getContentString(position);//get specific string
        setContent();//set string to textview
        getMusicURLString(position);
        getMusicName(position);

        //the play button and the bottomsheet operation
        //the idea is that we first download the file which is mp3 and then play local
        //all things done must be with the internet
        //I don't know whether it work under android 10 or not

        //1. to check file exist or not then decide the download button or play button
        //****************     1     ****************
        fileExist_handler = new FileExistsHandler();
        Thread fileExist_thread = new Thread(FileExistRunnable);
        fileExist_thread.start();

        //bottomsheet dialog
        bottomSheetDialog = new BottomSheetDialog(BBC_Content_0.this, R.style.BottomSheetDialog);
        View dialog = LayoutInflater.from(BBC_Content_0.this).inflate(R.layout.bbc_bottomsheetdialog, null);
        bottomSheetDialog.setContentView(dialog);
        //dialog
        getDialogID(dialog);
        //
        dialogSetContent();
    }

    //****************     1     ****************
    class FileExistsHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                //file exists
                if(!CheckInternet.isConnected(context)){
                    makeToast("Please make sure you have internet access!");
                }
                imageView_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                setMediaPlayer();
                imageViewPlayOperation();
                bottomSheetTitileOperation();
                dialogOperation();
            }else{
                //file not exists
                if(!CheckInternet.isConnected(context)){
                    makeToast("Please make sure you have internet access!");
                }
                //makeToast( bbc_mp3_filename + " not exist");
                imageView_play.setImageResource(R.drawable.ic_baseline_arrow_circle_down_24);
                //as file not exit then you need to download the file. in order to do that you should click the imageview_play button
                //which  at the moment is the down arrow
                imageView_play.setOnClickListener(v -> {
                    //to check the internet
                    if(CheckInternet.isConnected(context)){
                        //makeToast("downloading");
                        /*if(CheckFileExist.isFileExist(getFileFullPath() + ".temp")){
                            makeToast("Downloading is in progress!");
                        }else{
                            //**********************   2   ***************************
                            downloadhandler = new DownloadHandler();
                            Thread downloadThread = new Thread(DownloadRunnable);
                            downloadThread.start();
                            //**********************   2   ***************************
                        }*/
                        if(downloadstatus == 0){
                            makeToast("Buffing...");
                        }else if(downloadstatus == 1){
                            makeToast("Buffing...");
                            downloadstatus = 0;
                            downloadhandler = new DownloadHandler();
                            Thread downloadThread = new Thread(DownloadRunnable);
                            downloadThread.start();
                        }
                    }else{
                        makeToast("No Internet!");
                    }
                });
            }
        }
    }
    Runnable FileExistRunnable = new Runnable() {

        @Override
        public void run() {
            if (CheckFileExist.isFileExist(getFileFullPath())) {
                Message message = new Message();
                message.what = 0;
                fileExist_handler.sendMessage(message);
            }else{
                Message message = new Message();
                message.what = 1;
                fileExist_handler.sendMessage(message);
            }
        }
    };
    //****************     1     ****************

    //**********************   2   ***************************
    class DownloadHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //download successfully
            if(msg.what == 0){
                //set downloadstatus to 2
                downloadstatus = 2;
                //makeToast("Download successfully");
                //cancel animation
                imageView_play.clearAnimation();
                renameFile();
                //download successfully first to play the music meanwhile set the image to pause
                //should consider the download timeout
                setMediaPlayer();
                if(mediaPlayer.getDuration() != 0){
                    mediaPlayer.start();
                    imageView_play.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                    music_bbc_play.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                    //
                    imageViewPlayOperation();
                    //
                    bottomSheetTitileOperation();
                    //
                    dialogOperation();
                }else{
                    makeToast("Timeout, Please Try Again.");
                }

            }
        }
    }
    Runnable DownloadRunnable = new Runnable() {
        @Override
        public void run() {
            //set Animation
            setAnimation(imageView_play, R.anim.rotate);
            //download file
            new DownloadFileFromInternet(bbc_content_url_string, parentDirectory, bbc_mp3_filename + ".temp").getFileFromInternet();
            Message message = new Message();
            message.what = 0;
            downloadhandler.sendMessage(message);
        }
    };

    //**********************   2   ***************************

    //*************************   3     ******************
    class ProgressHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                if(mediaPlayer.isPlaying()){
                    music_bbc_starttime.setText(timeFormat(current_position));
                }
                mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                    imageView_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                    music_bbc_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                    music_bbc_seekBar.setProgress(0);
                    music_bbc_starttime.setText("00:00");
                });
            }
        }
    }

    Runnable progressRunnable = new Runnable() {

        @Override
        public void run() {
            while(true){
                current_position = mediaPlayer.getCurrentPosition();
                try {
                    Thread.sleep(100);
                    Message message = new Message();
                    message.what = 0;
                    progressHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    //*************************   3     ******************

    //*************************   4     ******************
    class SeekBarThread extends Thread {
        @Override
        public void run() {
            super.run();
            if(mediaPlayer.isPlaying()){
                music_bbc_seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
            mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                imageView_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                music_bbc_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                music_bbc_seekBar.setProgress(0);
                music_bbc_starttime.setText("00:00");
            });
            if(music_bbc_seekBar.getProgress() <= music_bbc_seekBar.getMax()){
                music_bbc_seekBar.postDelayed(this, 100);
            }
        }
    }
    //*************************   4     ******************

    private void imageViewPlayOperation(){
        imageView_play.setOnClickListener(v -> {

            if(mediaPlayer.isPlaying()){
                imageView_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                mediaPlayer.pause();
                //bottomsheet dialog status
                music_bbc_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
            }else{
                imageView_play.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                mediaPlayer.start();
                music_bbc_play.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
            }
            //
        });
    }

    private void dialogOperation() {
        music_bbc_endtime.setText(timeFormat(total_time));
        //set start time
        //*************************   3     ******************
        progressHandler = new ProgressHandler();
        Thread progressThread = new Thread(progressRunnable);
        progressThread.start();
        //*************************   3     ******************

        //play
        music_bbc_play.setOnClickListener(v -> {
            if(mediaPlayer.isPlaying()){
                imageView_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                mediaPlayer.pause();
                //bottomsheet dialog status
                music_bbc_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
            }else{
                imageView_play.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                mediaPlayer.start();
                music_bbc_play.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
            }
        });

        //backward 10 seconds operation
        music_bbc_backward.setOnClickListener(v->{
            long current_time_position = mediaPlayer.getCurrentPosition() - 1000 * 10;
            if(current_time_position >= 0){
                mediaPlayer.seekTo((int)current_time_position);
                music_bbc_seekBar.setProgress((int)current_time_position);
                music_bbc_starttime.setText(timeFormat((int)current_time_position));
            }
        });
        //forward 10 seconds operation
        music_bbc_forward.setOnClickListener(v->{
            long current_time_position = mediaPlayer.getCurrentPosition() + 1000 * 10;
            if(current_time_position <= mediaPlayer.getDuration()){
                mediaPlayer.seekTo((int)current_time_position);
                music_bbc_seekBar.setProgress((int)current_time_position);
                music_bbc_starttime.setText(timeFormat((int)current_time_position));
            }
        });

        //seekbar
        music_bbc_seekBar.setMax(mediaPlayer.getDuration());
        //*************************   4     ******************
        new SeekBarThread().start();
        //*************************   4     ******************

        music_bbc_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });
    }

    private void bottomSheetTitileOperation(){
        textView_title.setOnClickListener(v -> bottomSheetDialog.show());
    }
    //
    private void setMediaPlayer(){
        mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(getFileFullPath());
            mediaPlayer.prepare();
            //meanwhile init the data
            total_time = mediaPlayer.getDuration();
        }catch(Exception e){
            makeToast(e.getMessage());
        }
    }


    //time format
    private String timeFormat(long time){
        Formatter formatter_minutes = new Formatter();
        Formatter formatter_seconds = new Formatter();
        int time_minutes = (int) (time / 1000 / 60);
        int time_seconds = (int) (time / 1000 % 60);
        return formatter_minutes.format("%02d", time_minutes)
                + ":" + formatter_seconds.format("%02d", time_seconds);
    }

    //rename file
    private void renameFile(){
        File file = new File(getFileFullPath() + ".temp");
        File newFile = new File(getFileFullPath());
        file.renameTo(newFile);
    }
    private void getID(){
        context = getApplicationContext();
        textView_content = findViewById(R.id.bbc_content_text);
        toolbar_title = findViewById(R.id.topAppBar_title);
        imageView_play = findViewById(R.id.bbc_music_play);
        textView_title = findViewById(R.id.bbc_music_title);
        parentDirectory = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() + File.separator;
    }
    private void getDialogID(View view) {
        music_bbc_title = view.findViewById(R.id.music_title);
        music_bbc_seekBar = view.findViewById(R.id.music_seekbar);
        music_bbc_starttime = view.findViewById(R.id.music_starttime);
        music_bbc_endtime = view.findViewById(R.id.music_endtime);
        music_bbc_backward = view.findViewById(R.id.music_replay_10s);
        music_bbc_play = view.findViewById(R.id.music_play);
        music_bbc_forward = view.findViewById(R.id.music_forward_10s);
    }

    private void setContent(){
        toolbar_title.setTitle(bbc_content_title_string);
        textView_content.setText(bbc_content_text_string);
        textView_title.setText(bbc_content_title_string);
    }
    private void dialogSetContent(){
        music_bbc_title.setText(bbc_content_title_string);
    }

    //title text string
    private void getContentString(int position){
        switch (position){
            case 0:
                bbc_content_title_string = getStringTitle(R.string.bbc_01);
                bbc_content_text_string = getStringText(R.raw.bbc_01);break;
            case 1:
                bbc_content_title_string = getStringTitle(R.string.bbc_02);
                bbc_content_text_string = getStringText(R.raw.bbc_02);break;
            case 2:
                bbc_content_title_string = getStringTitle(R.string.bbc_03);
                bbc_content_text_string = getStringText(R.raw.bbc_03);break;
            case 3:
                bbc_content_title_string = getStringTitle(R.string.bbc_04);
                bbc_content_text_string = getStringText(R.raw.bbc_04);break;
            case 4:
                bbc_content_title_string = getStringTitle(R.string.bbc_05);
                bbc_content_text_string = getStringText(R.raw.bbc_05);break;
            case 5:
                bbc_content_title_string = getStringTitle(R.string.bbc_06);
                bbc_content_text_string = getStringText(R.raw.bbc_06);break;
            case 6:
                bbc_content_title_string = getStringTitle(R.string.bbc_07);
                bbc_content_text_string = getStringText(R.raw.bbc_07);break;
            case 7:
                bbc_content_title_string = getStringTitle(R.string.bbc_08);
                bbc_content_text_string = getStringText(R.raw.bbc_08);break;
        }
    }
    //mp3 url
    private void getMusicURLString(int position){
        switch(position) {
            case 0: bbc_content_url_string = getURL("01%20The%20Hare%20and%20the%20Tortoise.mp3"); break;
            case 1: bbc_content_url_string = getURL("02%20The%20Frogs%20and%20the%20Ox%20.mp3"); break;
            case 2: bbc_content_url_string = getURL("03%20The%20Fox%20and%20the%20Crow.mp3"); break;
            case 3: bbc_content_url_string = getURL("04%20The%20Monkey%20as%20King%20.mp3"); break;
            case 4: bbc_content_url_string = getURL("05%20The%20Bundle%20of%20Sticks%20.mp3"); break;
            case 5: bbc_content_url_string = getURL("06%20The%20Gnat%20and%20the%20Lion%20.mp3"); break;
            case 6: bbc_content_url_string = getURL("07%20The%20North%20Wind%20and%20the%20Sun.mp3"); break;
            case 7: bbc_content_url_string = getURL("08%20The%20Fox%20and%20the%20Grapes%20.mp3"); break;
        }
    }

    //mp3 file name
    private void getMusicName(int position){
        switch(position){
            case 0: bbc_mp3_filename = "thehareandthetortoise.mp3";break;
            case 1: bbc_mp3_filename = "thefrogsandtheox.mp3"; break;
            case 2: bbc_mp3_filename = "thefoxandthecrow.mp3"; break;
            case 3: bbc_mp3_filename = "themonkeyasking.mp3"; break;
            case 4: bbc_mp3_filename = "thebundleofsticks.mp3"; break;
            case 5: bbc_mp3_filename = "thegnatandthelion.mp3"; break;
            case 6: bbc_mp3_filename = "thenorthwindandthesun.mp3"; break;
            case 7: bbc_mp3_filename = "thefoxandthegrapes.mp3"; break;
        }
    }
    //mp3 file path
    private String getFileFullPath(){
        return parentDirectory + bbc_mp3_filename;
    }

    private String getStringTitle(int resource){
        return getResources().getString(resource);
    }

    //content text
    private String getStringText(int resource){
        return new ReadStreamFormText().getString(getResources().openRawResource(resource));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        clearMediaPlayer();
    }

    private void clearMediaPlayer() {
        if(mediaPlayer != null) mediaPlayer.stop();
    }

    private String getURL(String url){
        return (urlOriginal + url);
    }

    private void makeToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    //set Animation
    private void setAnimation(View view, int animID){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), animID);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);

        view.startAnimation(animation);
    }

    //load ads
    private void loadAds() {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, initializationStatus -> {});
        // Set your test devices. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
        // to get test ads on this device."
       /* MobileAds.setRequestConfiguration(
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

