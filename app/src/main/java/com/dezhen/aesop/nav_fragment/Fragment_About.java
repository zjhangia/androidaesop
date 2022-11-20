package com.dezhen.aesop.nav_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dezhen.aesop.R;
import com.dezhen.aesop.ReadStreamFormText;

import org.jetbrains.annotations.NotNull;

public class Fragment_About extends Fragment {

    private TextView textView_about_collection;
    private TextView textView_about_bbc;

    private View view;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_about, container, false);

        getID();
        textView_about_collection.setText(new ReadStreamFormText().getString(getResources().openRawResource(R.raw.about_collection)));
        textView_about_bbc.setText(new ReadStreamFormText().getString(getResources().openRawResource(R.raw.about_bbc)));

        return view;

    }
    private void getID(){
        textView_about_collection = view.findViewById(R.id.text_about_collection);
        textView_about_bbc = view.findViewById(R.id.text_about_bbc);
    }
}
