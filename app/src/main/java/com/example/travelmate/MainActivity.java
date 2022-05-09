package com.example.travelmate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
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

public class MainActivity extends AppCompatActivity implements TaxiRVAdapter.TaxiClickInterface {

    private RecyclerView taxiRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<TaxiRVModal> taxiRVModalArrayList;
    private RelativeLayout bottomSheetRL;
    private TaxiRVAdapter taxiRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taxiRV = findViewById(R.id.idRVTaxis);
        loadingPB = findViewById(R.id.idPBLoading);
        addFAB = findViewById(R.id.idAddFAB);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Taxis");
        taxiRVModalArrayList = new ArrayList<>();
        bottomSheetRL = findViewById(R.id.idRLBSheet);
        taxiRVAdapter = new TaxiRVAdapter(taxiRVModalArrayList,this,this);
        taxiRV.setLayoutManager(new LinearLayoutManager(this));
        taxiRV.setAdapter(taxiRVAdapter);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddTaxiActivity.class));
            }
        });
        getAllTaxis();


    }
    private void getAllTaxis(){
        taxiRVModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                taxiRVModalArrayList.add(snapshot.getValue(TaxiRVModal.class));
                taxiRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                taxiRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                loadingPB.setVisibility(View.GONE);
                taxiRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                taxiRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onTaxiClick(int position) {
        displayBottomSheet(taxiRVModalArrayList.get(position));
    }

    private void displayBottomSheet(TaxiRVModal taxiRVModal){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView taxiNameTV = layout.findViewById(R.id.idTVTaxiName);
        TextView taxiDescTV = layout.findViewById(R.id.idTVDescription);
        TextView taxiLocation = layout.findViewById(R.id.idTVLocation);
        TextView taxiType = layout.findViewById(R.id.idTVType);
        ImageView taxiIV = layout.findViewById(R.id.idIVTaxi);
        Button editBtn = layout.findViewById(R.id.idBtnEdit);
        Button viewDetailsBtn = layout.findViewById(R.id.idBtnViewDetails);


        taxiNameTV.setText(taxiRVModal.getTaxiName());
        taxiDescTV.setText(taxiRVModal.getTaxiDescription());
        taxiLocation.setText(taxiRVModal.getTaxiLocation());
        taxiType.setText(taxiRVModal.getTaxiType());
        Picasso.get().load(taxiRVModal.getTaxiImage()).into(taxiIV);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,EditTaxiActivity.class);
                i.putExtra("taxi",taxiRVModal);
                startActivity(i);
            }
        });

        viewDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(taxiRVModal.getTaxiName()));
                startActivity(i);
            }
        });
    }
}