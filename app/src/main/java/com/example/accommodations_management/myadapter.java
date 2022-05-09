package com.example.accommodations_management;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;


public class myadapter extends RecyclerView.Adapter<myadapter.MyViewholder> {

    Context context;

    ArrayList<AccModel>list;

    public myadapter(Context context, ArrayList<AccModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
       AccModel AccModel =list.get(position);
        holder.name.setText(AccModel.getName());
        holder.location.setText(AccModel.getLocation());
        holder.description.setText(AccModel.getDescription());
        holder.venue.setText(AccModel.getVenue_type());
        holder.contact.setText(AccModel.getDescription());
        Glide.with(holder.img.getContext()).load(AccModel.getSurl()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        ImageView edit,delete;
        TextView name,location,description,venue,contact;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            img=(CircleImageView) itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            location=(TextView)itemView.findViewById(R.id.locationtext);
            description=(TextView)itemView.findViewById(R.id.descriptiontext);
            venue=(TextView)itemView.findViewById(R.id.venuetext);
            contact=(TextView)itemView.findViewById(R.id.contacttext);




            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);

        }
    }

}
