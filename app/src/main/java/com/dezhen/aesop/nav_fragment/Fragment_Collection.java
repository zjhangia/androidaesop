package com.dezhen.aesop.nav_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dezhen.aesop.R;
import com.dezhen.aesop.collection.Collection_RecyclerViewAdapter_0;
import com.dezhen.aesop.collection.Collection_RecyclerViewAdapter_1;
import com.dezhen.aesop.collection.Collection_RecyclerViewAdapter_2;
import com.dezhen.aesop.collection.Collection_RecyclerViewAdapter_3;
import com.dezhen.aesop.collection.Collection_RecyclerViewAdapter_4;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Fragment_Collection extends Fragment {
    private RecyclerView[] recyclerview;
    private final int[] imageViewResources = new int[40];
    private final String[] title = new String[40];
    private Context context;
    private View view;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        //return inflater.inflate(R.layout.fragment_collection, container, false);
        view = inflater.inflate(R.layout.fragment_collection, container, false);
        recyclerview = new RecyclerView[5];

        //getID
        getID();
        //initDATA
        initData();

        ArrayList<Collection_Card> arrayList_0 = new ArrayList<>();
        for(int i = 0; i < 8; i ++){
            arrayList_0.add(new Collection_Card(title[i], imageViewResources[i]));
        }
        LinearLayoutManager layoutManager_0 = new LinearLayoutManager(context);
        layoutManager_0.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview[0].setLayoutManager(layoutManager_0);
        recyclerview[0].setAdapter(new Collection_RecyclerViewAdapter_0(context, arrayList_0));

        ArrayList<Collection_Card> arrayList_1 = new ArrayList<>();
        for(int i = 8; i < 16; i ++){
            arrayList_1.add(new Collection_Card(title[i], imageViewResources[i]));
        }
        LinearLayoutManager layoutManager_1 = new LinearLayoutManager(context);
        layoutManager_1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview[1].setLayoutManager(layoutManager_1);
        recyclerview[1].setAdapter(new Collection_RecyclerViewAdapter_1(context, arrayList_1));

        ArrayList<Collection_Card> arrayList_2 = new ArrayList<>();
        for(int i = 16; i < 24; i ++){
            arrayList_2.add(new Collection_Card(title[i], imageViewResources[i]));
        }
        LinearLayoutManager layoutManager_2 = new LinearLayoutManager(context);
        layoutManager_2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview[2].setLayoutManager(layoutManager_2);
        recyclerview[2].setAdapter(new Collection_RecyclerViewAdapter_2(context, arrayList_2));

        ArrayList<Collection_Card> arrayList_3 = new ArrayList<>();
        for(int i = 24; i < 32; i ++){
            arrayList_3.add(new Collection_Card(title[i], imageViewResources[i]));
        }
        LinearLayoutManager layoutManager_3 = new LinearLayoutManager(context);
        layoutManager_3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview[3].setLayoutManager(layoutManager_3);
        recyclerview[3].setAdapter(new Collection_RecyclerViewAdapter_3(context, arrayList_3));

        ArrayList<Collection_Card> arrayList_4 = new ArrayList<>();
        for(int i = 32; i < 40; i ++){
            arrayList_4.add(new Collection_Card(title[i], imageViewResources[i]));
        }
        LinearLayoutManager layoutManager_4 = new LinearLayoutManager(context);
        layoutManager_4.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview[4].setLayoutManager(layoutManager_4);
        recyclerview[4].setAdapter(new Collection_RecyclerViewAdapter_4(context, arrayList_4));

        return view;
    }
    private void getID(){
        context = getContext();
        recyclerview[0] = view.findViewById(R.id.collection_recyclerview_no_0);
        recyclerview[1] = view.findViewById(R.id.collection_recyclerview_no_1);
        recyclerview[2] = view.findViewById(R.id.collection_recyclerview_no_2);
        recyclerview[3] = view.findViewById(R.id.collection_recyclerview_no_3);
        recyclerview[4] = view.findViewById(R.id.collection_recyclerview_no_4);
    }

    private void initData(){
        imageViewResources[0] = R.mipmap.c01;
        imageViewResources[1] = R.mipmap.c02;
        imageViewResources[2] = R.mipmap.c03;
        imageViewResources[3] = R.mipmap.c04;
        imageViewResources[4] = R.mipmap.c05;
        imageViewResources[5] = R.mipmap.c06;
        imageViewResources[6] = R.mipmap.c07;
        imageViewResources[7] = R.mipmap.c08;
        imageViewResources[8] = R.mipmap.c09;
        imageViewResources[9] = R.mipmap.c10;
        imageViewResources[10] = R.mipmap.c11;
        imageViewResources[11] = R.mipmap.c12;
        imageViewResources[12] = R.mipmap.c13;
        imageViewResources[13] = R.mipmap.c14;
        imageViewResources[14] = R.mipmap.c15;
        imageViewResources[15] = R.mipmap.c16;
        imageViewResources[16] = R.mipmap.c17;
        imageViewResources[17] = R.mipmap.c18;
        imageViewResources[18] = R.mipmap.c19;
        imageViewResources[19] = R.mipmap.c20;
        imageViewResources[20] = R.mipmap.c21;
        imageViewResources[21] = R.mipmap.c22;
        imageViewResources[22] = R.mipmap.c23;
        imageViewResources[23] = R.mipmap.c24;
        imageViewResources[24] = R.mipmap.c25;
        imageViewResources[25] = R.mipmap.c26;
        imageViewResources[26] = R.mipmap.c27;
        imageViewResources[27] = R.mipmap.c28;
        imageViewResources[28] = R.mipmap.c29;
        imageViewResources[29] = R.mipmap.c30;
        imageViewResources[30] = R.mipmap.c31;
        imageViewResources[31] = R.mipmap.c32;
        imageViewResources[32] = R.mipmap.c33;
        imageViewResources[33] = R.mipmap.c34;
        imageViewResources[34] = R.mipmap.c35;
        imageViewResources[35] = R.mipmap.c36;
        imageViewResources[36] = R.mipmap.c37;
        imageViewResources[37] = R.mipmap.c38;
        imageViewResources[38] = R.mipmap.c39;
        imageViewResources[39] = R.mipmap.c40;

        title[0] = getTitleString(R.string.c_01);
        title[1] = getTitleString(R.string.c_02);
        title[2] = getTitleString(R.string.c_03);
        title[3] = getTitleString(R.string.c_04);
        title[4] = getTitleString(R.string.c_05);
        title[5] = getTitleString(R.string.c_06);
        title[6] = getTitleString(R.string.c_07);
        title[7] = getTitleString(R.string.c_08);
        title[8] = getTitleString(R.string.c_09);
        title[9] = getTitleString(R.string.c_10);
        title[10] = getTitleString(R.string.c_11);
        title[11] = getTitleString(R.string.c_12);
        title[12] = getTitleString(R.string.c_13);
        title[13] = getTitleString(R.string.c_14);
        title[14] = getTitleString(R.string.c_15);
        title[15] = getTitleString(R.string.c_16);
        title[16] = getTitleString(R.string.c_17);
        title[17] = getTitleString(R.string.c_18);
        title[18] = getTitleString(R.string.c_19);
        title[19] = getTitleString(R.string.c_20);
        title[20] = getTitleString(R.string.c_21);
        title[21] = getTitleString(R.string.c_22);
        title[22] = getTitleString(R.string.c_23);
        title[23] = getTitleString(R.string.c_24);
        title[24] = getTitleString(R.string.c_25);
        title[25] = getTitleString(R.string.c_26);
        title[26] = getTitleString(R.string.c_27);
        title[27] = getTitleString(R.string.c_28);
        title[28] = getTitleString(R.string.c_29);
        title[29] = getTitleString(R.string.c_30);
        title[30] = getTitleString(R.string.c_31);
        title[31] = getTitleString(R.string.c_32);
        title[32] = getTitleString(R.string.c_33);
        title[33] = getTitleString(R.string.c_34);
        title[34] = getTitleString(R.string.c_35);
        title[35] = getTitleString(R.string.c_36);
        title[36] = getTitleString(R.string.c_37);
        title[37] = getTitleString(R.string.c_38);
        title[38] = getTitleString(R.string.c_39);
        title[39] = getTitleString(R.string.c_40);
    }

    private String getTitleString(int resource){
        return getResources().getString(resource);
    }


}

