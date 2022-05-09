package com.example.travelmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTaxiActivity extends AppCompatActivity {

    private TextInputEditText taxiNameEdt,taxiTypeEdt,taxiLocationEdt,taxiContactEdt,taxiNumEdt,taxiImageEdt,taxiDescEdt;
    private Button addTaxiBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String taxiID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_taxi);
        taxiNameEdt = findViewById(R.id.idEdtTaxiName);
        taxiTypeEdt = findViewById(R.id.idEdtTaxiType);
        taxiLocationEdt = findViewById(R.id.idEdtTaxiLName);
        taxiContactEdt = findViewById(R.id.idEdtTaxiCNum);
        taxiNumEdt = findViewById(R.id.idEdtTaxiVNum);
        taxiImageEdt = findViewById(R.id.idEdtTaxiImage);
        taxiDescEdt = findViewById(R.id.idEdtTaxiDesc);
        addTaxiBtn = findViewById(R.id.idBtnAddTaxi);
        loadingPB= findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("taxis");

        addTaxiBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loadingPB.setVisibility(View.VISIBLE);
                String taxiName = taxiNameEdt.getText().toString();
                String taxiType = taxiTypeEdt.getText().toString();
                String taxiLocation = taxiLocationEdt.getText().toString();
                String taxiContact = taxiContactEdt.getText().toString();
                String taxiNumber = taxiNumEdt.getText().toString();
                String taxiImage = taxiImageEdt.getText().toString();
                String taxiDesc = taxiDescEdt.getText().toString();
                taxiID = taxiName;
                TaxiRVModal taxiRVModal = new TaxiRVModal(taxiName,taxiType,taxiLocation,taxiContact,taxiNumber,taxiImage,taxiDesc,taxiID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(taxiID).setValue(taxiRVModal);
                        Toast.makeText(AddTaxiActivity.this, "Taxi Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddTaxiActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddTaxiActivity.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}