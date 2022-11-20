package com.dezhen.aesop.bbc;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dezhen.aesop.R;
import com.dezhen.aesop.nav_fragment.BBC_Card;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BBC_RecyclerView_Adapter_2 extends RecyclerView.Adapter<BBC_RecyclerView_Adapter_2.BBCViewHolder> {

    private static Context context;
    private ArrayList<BBC_Card> arrayList;

    public BBC_RecyclerView_Adapter_2(Context context, ArrayList<BBC_Card> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @NotNull
    @Override
    public BBC_RecyclerView_Adapter_2.BBCViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bbc_recyclerview_content, parent, false);
        return new BBCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BBC_RecyclerView_Adapter_2.BBCViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position).getTitle());
        holder.imageView.setImageResource(arrayList.get(position).getImageResource());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class BBCViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;

        public BBCViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.bbc_title);
            imageView = itemView.findViewById(R.id.bbc_image);
            cardView = itemView.findViewById(R.id.bbc_cardview);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    //create intent
                    Intent intent = new Intent(context, BBC_Content_2.class);
                    Bundle bundle = new Bundle();
                    final String TAG = "BBC_Content_2";
                    switch(position) {
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

                    intent.putExtras(bundle);//passing the number
                    context.startActivity(intent);
                    //Toast.makeText(context, "More To Come", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}


