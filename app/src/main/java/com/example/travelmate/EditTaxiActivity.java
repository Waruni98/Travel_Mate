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

public class EditTaxiActivity extends AppCompatActivity {

    private TextInputEditText taxiNameEdt,taxiTypeEdt,taxiLocationEdt,taxiContactEdt,taxiNumEdt,taxiImageEdt,taxiDescEdt;
    private Button updateTaxiBtn,deleteTaxiBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String taxiID;
    private TaxiRVModal taxiRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_taxi);
        firebaseDatabase = FirebaseDatabase.getInstance();
        taxiNameEdt = findViewById(R.id.idEdtTaxiName);
        taxiTypeEdt = findViewById(R.id.idEdtTaxiType);
        taxiLocationEdt = findViewById(R.id.idEdtTaxiLName);
        taxiContactEdt = findViewById(R.id.idEdtTaxiCNum);
        taxiNumEdt = findViewById(R.id.idEdtTaxiVNum);
        taxiImageEdt = findViewById(R.id.idEdtTaxiImage);
        taxiDescEdt = findViewById(R.id.idEdtTaxiDesc);
        updateTaxiBtn = findViewById(R.id.idBtnUpdate);
        deleteTaxiBtn = findViewById(R.id.idBtnDelete);
        loadingPB= findViewById(R.id.idPBLoading);
        taxiRVModal = getIntent().getParcelableExtra("taxi");
        if (taxiRVModal!=null){
            taxiNameEdt.setText(taxiRVModal.getTaxiName());
            taxiTypeEdt.setText(taxiRVModal.getTaxiType());
            taxiLocationEdt.setText(taxiRVModal.getTaxiLocation());
            taxiContactEdt.setText(taxiRVModal.getTaxiContact());
            taxiNumEdt.setText(taxiRVModal.getTaxiNumber());
            taxiDescEdt.setText(taxiRVModal.getTaxiDescription());
            taxiID = taxiRVModal.getTaxiID();
        }


        databaseReference = firebaseDatabase.getReference("Taxis").child(taxiID);
        updateTaxiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                String taxiName = taxiNameEdt.getText().toString();
                String taxiType = taxiTypeEdt.getText().toString();
                String taxiLocation = taxiLocationEdt.getText().toString();
                String taxiContact = taxiContactEdt.getText().toString();
                String taxiNumber = taxiNumEdt.getText().toString();
                String taxiDesc = taxiDescEdt.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("taxiName",taxiName);
                map.put("taxiType",taxiType);
                map.put("taxiLocation",taxiLocation);
                map.put("taxiContact",taxiContact);
                map.put("taxiNumber",taxiNumber);
                map.put("taxiDescription",taxiDesc);
                map.put("taxiID",taxiID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditTaxiActivity.this, "Updated...!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditTaxiActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditTaxiActivity.this, "Fail to Update !!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteTaxiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTaxi();
            }
        });
    }
    private void deleteTaxi(){
        databaseReference.removeValue();
        Toast.makeText(this, "Deleted...!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditTaxiActivity.this,MainActivity.class));
    }
}