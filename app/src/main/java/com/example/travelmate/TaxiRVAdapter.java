package com.example.travelmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TaxiRVAdapter extends RecyclerView.Adapter<TaxiRVAdapter.ViewHolder> {
    private ArrayList<TaxiRVModal> taxiRVModalArrayList;
    private Context context;
    private TaxiClickInterface taxiClickInterface;
    int lastPos = -1;

    public TaxiRVAdapter(ArrayList<TaxiRVModal> taxiRVModalArrayList, Context context, TaxiClickInterface taxiClickInterface) {
        this.taxiRVModalArrayList = taxiRVModalArrayList;
        this.context = context;
        this.taxiClickInterface = taxiClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.taxi_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaxiRVModal taxiRVModal = taxiRVModalArrayList.get(position);
        holder.taxiNameTV.setText(taxiRVModal.getTaxiName());
        holder.taxiTypeTV.setText(taxiRVModal.getTaxiType());
        Picasso.get().load(taxiRVModal.getTaxiImage()).into(holder.taxiIV);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taxiClickInterface.onTaxiClick(holder.getAdapterPosition());
            }
        });
    }
    private void setAnimation(View itemView,int position){
        if (position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }
    @Override
    public int getItemCount() {
        return taxiRVModalArrayList.size();
    }

    public interface TaxiClickInterface{
        void onTaxiClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView taxiNameTV,taxiTypeTV;
        private ImageView taxiIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taxiNameTV = itemView.findViewById(R.id.idTVTaxiName);
            taxiTypeTV = itemView.findViewById(R.id.idTVType);
            taxiIV = itemView.findViewById(R.id.idIVTaxi);
        }
    }


}
