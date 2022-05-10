package com.example.accommodations_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;




public class Accommodation extends AppCompatActivity {
    RecyclerView recview;
    myadapter adapter;
    FloatingActionButton fb;
    DatabaseReference databaseReference;
    ArrayList<AccModel> list;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation);
        setTitle("Accommodation..");

        recview = findViewById(R.id.RecyclerView);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("hotel");
        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new myadapter(this, list);
        recview.setAdapter(adapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    AccModel accModel = dataSnapshot.getValue(AccModel.class);
                    list.add(accModel);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(Accommodation.this,"successful",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Accommodation.this,""+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }}


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<AccModel> options =
                new FirebaseRecyclerOptions.Builder<AccModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students").orderByChild("course").startAt(s).endAt(s+"\uf8ff"), AccModel.class)
                        .build();

        adapter=new myadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }*/
