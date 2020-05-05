package com.example.yashoza.billreminder_devit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Pending extends Fragment implements BillsAdapter.BillsAdapterListener {
    RecyclerView pendingbills;

    private FirebaseAuth mAuth=FirebaseAuth.getInstance();;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("bills");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_pending, container, false);
        pendingbills = v.findViewById(R.id.pendingList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        pendingbills.setLayoutManager(layoutManager);
        String uid = mAuth.getCurrentUser().getUid();

        final List<Bill> bills = new ArrayList<>();
        final BillsAdapter adapter = new BillsAdapter(getContext(), bills, this);
        pendingbills.setAdapter(adapter);

        myRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bills.clear();
                for (DataSnapshot values : dataSnapshot.getChildren()){
                    Bill bill = values.getValue(Bill.class);
                    bills.add(bill);
                    adapter.notifyDataSetChanged();
                    pendingbills.invalidate();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        return v;
    }

    @Override
    public void onBillClicked(Bill  bill,View view) {
        Bundle bundle= ActivityOptions.makeSceneTransitionAnimation(getActivity(), view.findViewById(R.id.type_image), view.findViewById(R.id.type_image).getTransitionName()).toBundle();
        Intent intent= new Intent(getActivity(),BillDetailsActivity.class);
        startActivity(intent,bundle);
    }
}
