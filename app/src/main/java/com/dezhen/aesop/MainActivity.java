package com.dezhen.aesop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;

import com.dezhen.aesop.nav_fragment.Fragment_About;
import com.dezhen.aesop.nav_fragment.Fragment_BBC;
import com.dezhen.aesop.nav_fragment.Fragment_Collection;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private BottomNavigationView bottomnavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set default fragment
        fragmentReplace( new Fragment_BBC());
        //
        getID();

        //nav operation
        bottomnavigation.setOnItemSelectedListener(item -> {

           switch (item.getItemId()) {
                case R.id.nav_bbc: fragmentReplace( new Fragment_BBC()); break;
                case R.id.nav_collection: fragmentReplace(new Fragment_Collection()); break;
                case R.id.nav_about: fragmentReplace(new Fragment_About()); break;
            }

            return true;
        });
    }

    //get ID
    private void getID() {
        context = getApplicationContext();
        bottomnavigation = findViewById(R.id.nav_view);
    }

    //transaction
    private void fragmentReplace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_nav, fragment).commit();
    }

}