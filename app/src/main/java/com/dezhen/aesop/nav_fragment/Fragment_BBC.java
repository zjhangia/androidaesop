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
import com.dezhen.aesop.bbc.BBC_RecyclerView_Adapter_0;
import com.dezhen.aesop.bbc.BBC_RecyclerView_Adapter_1;
import com.dezhen.aesop.bbc.BBC_RecyclerView_Adapter_2;
import com.dezhen.aesop.bbc.BBC_RecyclerView_Adapter_3;
import com.dezhen.aesop.bbc.BBC_RecyclerView_Adapter_4;

import java.util.ArrayList;

public class Fragment_BBC extends Fragment {

    private RecyclerView[] recyclerview;

    private final int[] imageViewResources = new int[40];
    private final String[] title = new String[40];

    private Context context;
    private View view;
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_bbc, container, false);
        recyclerview = new RecyclerView[5];

        //getID
        getID();


        /*for test*/

        //init data
        initData();

        ArrayList<BBC_Card> arrayList = new ArrayList<>();
        for(int i = 0; i < 8; i ++){
            arrayList.add(new BBC_Card(title[i], imageViewResources[i]));
        }
        //set layout manger
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //recyclerview set layout
        //recyclerview.setLayoutManager(layoutManager);
        recyclerview[0].setLayoutManager(layoutManager);//be careful
        recyclerview[0].setAdapter(new BBC_RecyclerView_Adapter_0(context, arrayList));


        //add resource
        ArrayList<BBC_Card> arrayList_1 = new ArrayList<>();
        for(int i = 8; i < 16; i ++){
            arrayList_1.add(new BBC_Card(title[i], imageViewResources[i]));
        }

        LinearLayoutManager layoutManager_1 = new LinearLayoutManager(context);
        layoutManager_1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview[1].setLayoutManager(layoutManager_1);
        recyclerview[1].setAdapter(new BBC_RecyclerView_Adapter_1(context, arrayList_1));


        ArrayList<BBC_Card> arrayList_2 = new ArrayList<>();
        for(int i = 16; i < 24; i ++){
            arrayList_2.add(new BBC_Card(title[i], imageViewResources[i]));
        }
        LinearLayoutManager layoutManager_2 = new LinearLayoutManager(context);
        layoutManager_2.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerview[2].setLayoutManager(layoutManager_2);
        recyclerview[2].setAdapter(new BBC_RecyclerView_Adapter_2(context, arrayList_2));

        ArrayList<BBC_Card> arrayList_3 = new ArrayList<>();
        for(int i = 24; i < 32; i ++){
            arrayList_3.add(new BBC_Card(title[i], imageViewResources[i]));
        }
        LinearLayoutManager layoutManager_3 = new LinearLayoutManager(context);
        layoutManager_3.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerview[3].setLayoutManager(layoutManager_3);
        recyclerview[3].setAdapter(new BBC_RecyclerView_Adapter_3(context, arrayList_3));

        ArrayList<BBC_Card> arrayList_4 = new ArrayList<>();
        for(int i = 32; i < 40; i ++){
            arrayList_4.add(new BBC_Card(title[i], imageViewResources[i]));
        }
        LinearLayoutManager layoutManager_4 = new LinearLayoutManager(context);
        layoutManager_4.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerview[4].setLayoutManager(layoutManager_4);
        recyclerview[4].setAdapter(new BBC_RecyclerView_Adapter_4(context, arrayList_4));

        return view;
    }

    //get id
    private void getID() {

        context = getContext();


        recyclerview[0] = view.findViewById(R.id.bbc_recyclerview_no_0);

        recyclerview[1] = view.findViewById(R.id.bbc_recyclerview_no_1);

        recyclerview[2] = view.findViewById(R.id.bbc_recyclerview_no_2);

        recyclerview[3] = view.findViewById(R.id.bbc_recyclerview_no_3);

        recyclerview[4] = view.findViewById(R.id.bbc_recyclerview_no_4);
    }
    //init data
    private void initData(){
        imageViewResources[0] = R.mipmap.a01;
        imageViewResources[1] = R.mipmap.a02;
        imageViewResources[2] = R.mipmap.a03;
        imageViewResources[3] = R.mipmap.a04;
        imageViewResources[4] = R.mipmap.a05;
        imageViewResources[5] = R.mipmap.a06;
        imageViewResources[6] = R.mipmap.a07;
        imageViewResources[7] = R.mipmap.a08;
        imageViewResources[8] = R.mipmap.a09;
        imageViewResources[9] = R.mipmap.a10;
        imageViewResources[10] = R.mipmap.a11;
        imageViewResources[11] = R.mipmap.a12;
        imageViewResources[12] = R.mipmap.a13;
        imageViewResources[13] = R.mipmap.a14;
        imageViewResources[14] = R.mipmap.a15;
        imageViewResources[15] = R.mipmap.a16;
        imageViewResources[16] = R.mipmap.a17;
        imageViewResources[17] = R.mipmap.a18;
        imageViewResources[18] = R.mipmap.a19;
        imageViewResources[19] = R.mipmap.a20;
        imageViewResources[20] = R.mipmap.a21;
        imageViewResources[21] = R.mipmap.a22;
        imageViewResources[22] = R.mipmap.a23;
        imageViewResources[23] = R.mipmap.a24;
        imageViewResources[24] = R.mipmap.a25;
        imageViewResources[25] = R.mipmap.a26;
        imageViewResources[26] = R.mipmap.a27;
        imageViewResources[27] = R.mipmap.a28;
        imageViewResources[28] = R.mipmap.a29;
        imageViewResources[29] = R.mipmap.a30;
        imageViewResources[30] = R.mipmap.a31;
        imageViewResources[31] = R.mipmap.a32;
        imageViewResources[32] = R.mipmap.a33;
        imageViewResources[33] = R.mipmap.a34;
        imageViewResources[34] = R.mipmap.a35;
        imageViewResources[35] = R.mipmap.a36;
        imageViewResources[36] = R.mipmap.a37;
        imageViewResources[37] = R.mipmap.a38;
        imageViewResources[38] = R.mipmap.a39;
        imageViewResources[39] = R.mipmap.a40;

        title[0] = getTitleString(R.string.bbc_01);
        title[1] = getTitleString(R.string.bbc_02);
        title[2] = getTitleString(R.string.bbc_03);
        title[3] = getTitleString(R.string.bbc_04);
        title[4] = getTitleString(R.string.bbc_05);
        title[5] = getTitleString(R.string.bbc_06);
        title[6] = getTitleString(R.string.bbc_07);
        title[7] = getTitleString(R.string.bbc_08);
        title[8] = getTitleString(R.string.bbc_09);
        title[9] = getTitleString(R.string.bbc_10);
        title[10] = getTitleString(R.string.bbc_11);
        title[11] = getTitleString(R.string.bbc_12);
        title[12] = getTitleString(R.string.bbc_13);
        title[13] = getTitleString(R.string.bbc_14);
        title[14] =  getTitleString(R.string.bbc_15);
        title[15] = getTitleString(R.string.bbc_16);
        title[16] = getTitleString(R.string.bbc_17);
        title[17] = getTitleString(R.string.bbc_18);
        title[18] = getTitleString(R.string.bbc_19);
        title[19] = getTitleString(R.string.bbc_20);
        title[20] = getTitleString(R.string.bbc_21);
        title[21] = getTitleString(R.string.bbc_22);
        title[22] = getTitleString(R.string.bbc_23);
        title[23] = getTitleString(R.string.bbc_24);
        title[24] = getTitleString(R.string.bbc_25);
        title[25] = getTitleString(R.string.bbc_26);
        title[26] = getTitleString(R.string.bbc_27);
        title[27] = getTitleString(R.string.bbc_28);
        title[28] = getTitleString(R.string.bbc_29);
        title[29] = getTitleString(R.string.bbc_30);
        title[30] = getTitleString(R.string.bbc_31);
        title[31] = getTitleString(R.string.bbc_32);
        title[32] = getTitleString(R.string.bbc_33);
        title[33] = getTitleString(R.string.bbc_34);
        title[34] = getTitleString(R.string.bbc_35);
        title[35] = getTitleString(R.string.bbc_36);
        title[36] = getTitleString(R.string.bbc_37);
        title[37] = getTitleString(R.string.bbc_38);
        title[38] = getTitleString(R.string.bbc_39);
        title[39] = getTitleString(R.string.bbc_40);

    }
    private String getTitleString(int resource){
        return getResources().getString(resource);
    }
}
