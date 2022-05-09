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

import java.util.HashMap;
import java.util.Map;

public class EditAgencyDetails extends AppCompatActivity {

    private TextInputEditText agencyNameEdt,locationLinkEdt,contactNumberEdt,logoEdt,descriptionEdt;
    private Button updateAgencyBtn,deleteAgencyBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String agencyId;
    private AgencyRVModel agencyRVModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agency_details);

        agencyNameEdt=findViewById(R.id.editAgencyName);
        locationLinkEdt=findViewById(R.id.editLocatin);
        contactNumberEdt= findViewById(R.id.editContactNumber);
        logoEdt=findViewById(R.id.editAgencyLogo);
        descriptionEdt=findViewById(R.id.editDescription);
        updateAgencyBtn=findViewById(R.id.btnUpdateAgency);
        deleteAgencyBtn=findViewById(R.id.btnDeleteAgency);
        loadingPB=findViewById(R.id.idPBLoading);
        firebaseDatabase=FirebaseDatabase.getInstance();

        agencyRVModel= getIntent().getParcelableExtra("agency");
        if (agencyRVModel!=null){
            agencyNameEdt.setText(agencyRVModel.getAgencyName());
            locationLinkEdt.setText(agencyRVModel.getLocationLink());
            contactNumberEdt.setText(agencyRVModel.getContactNumber());
            logoEdt.setText(agencyRVModel.getLogoLink());
            descriptionEdt.setText(agencyRVModel.getDescription());
            agencyId = agencyRVModel.getAgencyId();

        }

        databaseReference=firebaseDatabase.getReference("Agencies").child(agencyId);

        updateAgencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(view.VISIBLE);
                String agencyName = agencyNameEdt.getText().toString();
                String locationLink = locationLinkEdt.getText().toString();
                String contactNumber = contactNumberEdt.getText().toString();
                String logo = logoEdt.getText().toString();
                String description = descriptionEdt.getText().toString();

                Map<String,Object> map= new HashMap<>();
                map.put("agencyName",agencyName);
                map.put("locationLink",locationLink);
                map.put("contactNumber",contactNumber);
                map.put("logoLink",logo);
                map.put("description",description);
                map.put("agencyId",agencyId);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditAgencyDetails.this,"Agency Details Updated",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditAgencyDetails.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(EditAgencyDetails.this, "Fail to Update Details", Toast.LENGTH_SHORT).show();
                        
                    }
                });


            }
        });

        deleteAgencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               deleteAgencyDetails();
            }
        });

    }
    private void deleteAgencyDetails(){
        databaseReference.removeValue();
        Toast.makeText(this, "Agency Details Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditAgencyDetails.this,MainActivity.class));
    }
}