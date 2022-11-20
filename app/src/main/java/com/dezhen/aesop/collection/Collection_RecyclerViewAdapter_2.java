package com.dezhen.aesop.collection;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dezhen.aesop.R;
import com.dezhen.aesop.nav_fragment.Collection_Card;

import java.util.ArrayList;

public class Collection_RecyclerViewAdapter_2 extends RecyclerView.Adapter<Collection_RecyclerViewAdapter_2.CollectionViewHolder>{
    private static Context context;
    private ArrayList<Collection_Card> arrayList;

    public Collection_RecyclerViewAdapter_2(Context context, ArrayList<Collection_Card> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Collection_RecyclerViewAdapter_2.CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_recyclerview_content, parent, false);
        return new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position).getCoTitle());
        holder.imageView.setImageResource(arrayList.get(position).getCoImageResource());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CollectionViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.co_title);
            imageView = itemView.findViewById(R.id.co_image);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, Co_Content_2.class);
                    Bundle bundle = new Bundle();
                    final String TAG = "Co_Content_2";
                    switch(position){
                        case 0:
                            bundle.putInt(TAG, 0); break;
                        case 1:
                            bundle.putInt(TAG, 1); break;
                        case 2:
                            bundle.putInt(TAG, 2); break;
                        case 3:
                            bundle.putInt(TAG, 3); break;
                        case 4:
                            bundle.putInt(TAG, 4); break;
                        case 5:
                            bundle.putInt(TAG, 5); break;
                        case 6:
                            bundle.putInt(TAG, 6); break;
                        case 7:
                            bundle.putInt(TAG, 7); break;
                    }
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
