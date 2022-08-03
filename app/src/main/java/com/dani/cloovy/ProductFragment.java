package com.dani.cloovy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductFragment extends Fragment implements ProductAdapter.ProductListener{
    private String TAG = StoreFragment.class.getSimpleName();

    private ArrayList<Product> listProduct ;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private FloatingActionButton fab;
    private RecyclerView rvitem;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);
        initView(view);

        layoutManager = new LinearLayoutManager(getActivity());
        rvitem.setLayoutManager(layoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getActivity(),ProductEditActivity.class);
                startActivity(intent);
            }
        });

        FirebaseUtils.getReference(FirebaseUtils.PRODUCT_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listProduct = new ArrayList<>();
                for (DataSnapshot dataSnapshot:snapshot.getChildren() ){
                    Product product = dataSnapshot.getValue(Product.class);
                    product.setKey(dataSnapshot.getKey());
                    listProduct.add(product);
                }
                adapter = new ProductAdapter(listProduct,getActivity(),ProductFragment.this);
                rvitem.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getDetails()+"|"+error.getMessage());
            }
        });
        return  view;

    }

    private void initView(View view){
        fab = view.findViewById(R.id.fab);
        rvitem = view.findViewById(R.id.rv_item);

    }


    @Override
    public void deleteProduct(Product product, int position) {
        FirebaseUtils.getReference(FirebaseUtils.PRODUCT_PATH).child(product.getKey()).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Data Berhasil di Hapus", Toast.LENGTH_LONG).show();
                    }
                });

    }
}
