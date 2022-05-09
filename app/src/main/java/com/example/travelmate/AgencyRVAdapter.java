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

public class AgencyRVAdapter extends RecyclerView.Adapter<AgencyRVAdapter.ViewHolder> {
    private ArrayList<AgencyRVModel> agencyRVModelArrayList;
    private Context context;
    int lastPos=-1;
    private agencyClickInterface agencyClickInterface;

    public AgencyRVAdapter(ArrayList<AgencyRVModel> agencyRVModelArrayList, Context context, AgencyRVAdapter.agencyClickInterface agencyClickInterface) {
        this.agencyRVModelArrayList = agencyRVModelArrayList;
        this.context = context;
        this.agencyClickInterface = agencyClickInterface;
    }

    @NonNull
    @Override
    public AgencyRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.agency_rv_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AgencyRVModel agencyRVModel= agencyRVModelArrayList.get(position);
        holder.agencyNameTV.setText(agencyRVModel.getAgencyName());
        Picasso.get().load(agencyRVModel.getLogoLink()).into(holder.agencyIV);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agencyClickInterface.onAgencyClick(holder.getAdapterPosition());

            }
        });
    }

    public void  setAnimation(View itemView,int position){
        if (position>lastPos){
            Animation animation= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos=position;
        }

    }
    @Override
    public int getItemCount() {
        return agencyRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView agencyNameTV;
        private ImageView agencyIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            agencyNameTV=itemView.findViewById(R.id.agencyNameTV);
            agencyIV = itemView.findViewById(R.id.idIVAgency);


        }
    }
    public interface agencyClickInterface{
        void onAgencyClick(int position);
    }
}
