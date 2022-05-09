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

public class InsertAgencyDetails extends AppCompatActivity {

    private TextInputEditText agencyNameEdt,locationLinkEdt,contactNumberEdt,logoEdt,descriptionEdt;
    private Button addAgencyBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String agencyId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_agency_details);

        agencyNameEdt=findViewById(R.id.editAgencyName);
        locationLinkEdt=findViewById(R.id.editLocatin);
        contactNumberEdt= findViewById(R.id.editContactNumber);
        logoEdt=findViewById(R.id.editAgencyLogo);
        descriptionEdt=findViewById(R.id.editDescription);
        addAgencyBtn=findViewById(R.id.btnAddAgency);
        loadingPB=findViewById(R.id.idPBLoading);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Agencies");

        addAgencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String agencyName = agencyNameEdt.getText().toString();
                String locationLink = locationLinkEdt.getText().toString();
                String contactNumber = contactNumberEdt.getText().toString();
                String logo = logoEdt.getText().toString();
                String description = descriptionEdt.getText().toString();
                agencyId=agencyName;
                AgencyRVModel agencyRVModel= new AgencyRVModel(agencyId,agencyName,locationLink,contactNumber,logo,description);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(agencyId).setValue(agencyRVModel);
                        Toast.makeText(InsertAgencyDetails.this, "Agency Added..",Toast.LENGTH_SHORT).show();

                                startActivity(new Intent( InsertAgencyDetails.this,MainActivity.class));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(InsertAgencyDetails.this,"Error is"+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
}