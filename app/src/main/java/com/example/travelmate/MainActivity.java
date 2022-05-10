package com.example.travelmate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  AgencyRVAdapter.agencyClickInterface{

    private RecyclerView agencyRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<AgencyRVModel> agencyRVModelArrayList;
    private RelativeLayout bottomSheetRL;
    private AgencyRVAdapter agencyRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agencyRV=findViewById(R.id.idRVAgencies);
        loadingPB= findViewById(R.id.idPBLoading);
        addFAB=findViewById(R.id.idAddFAB);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Agencies");
        agencyRVModelArrayList=new ArrayList<>();
        bottomSheetRL=findViewById(R.id.idRLBSheet);
        agencyRVAdapter=new AgencyRVAdapter(agencyRVModelArrayList,this,this);
        agencyRV.setLayoutManager(new LinearLayoutManager( this));
        agencyRV.setAdapter(agencyRVAdapter);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,InsertAgencyDetails.class));
            }
        });
        getAllAgencies();
    }

    private void getAllAgencies(){
        agencyRVModelArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                loadingPB.setVisibility(View.GONE);
                agencyRVModelArrayList.add(snapshot.getValue(AgencyRVModel.class));
                agencyRVAdapter.notifyDataSetChanged();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                loadingPB.setVisibility(View.GONE);
                agencyRVAdapter.notifyDataSetChanged();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                loadingPB.setVisibility(View.GONE);
                agencyRVAdapter.notifyDataSetChanged();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                loadingPB.setVisibility(View.GONE);
                agencyRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public void onAgencyClick(int position) {
        displayBottomSheet(agencyRVModelArrayList.get(position));
    }

    private void displayBottomSheet(AgencyRVModel agencyRVModel){

        final BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView agencyNameTV= layout.findViewById(R.id.idTVAgencyName);
        TextView locationlinkTV= layout.findViewById(R.id.idTVLocationLink);
        TextView contactNumberTV= layout.findViewById(R.id.idTVContactNumber);
        TextView descriptionTV= layout.findViewById(R.id.idTVDescription);
        ImageView agencyIV= layout.findViewById(R.id.idIVAgency);
        Button editBtn= layout.findViewById(R.id.idBtnEdit);


        agencyNameTV.setText(agencyRVModel.getAgencyName());
        locationlinkTV.setText(agencyRVModel.getLocationLink());
        contactNumberTV.setText(agencyRVModel.getContactNumber());
        descriptionTV.setText(agencyRVModel.getDescription());
        Picasso.get().load(agencyRVModel.getLogoLink()).into(agencyIV);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,EditAgencyDetails.class);
                intent.putExtra("agency",agencyRVModel);
                startActivity(intent);
            }
        });


    }


}