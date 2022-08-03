package com.dani.cloovy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    private ArrayList<Store> listStore;
    private Context context;
    private StoreListener storeListener;

    public StoreAdapter( ArrayList<Store>listStore,Context context,StoreListener storeListener) {
        this.listStore =listStore;
        this.context = context;
        this.storeListener = storeListener;
    }

    @NonNull
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        final String storename = listStore.get(position).getNamestore();
        final String address = listStore.get(position).getAddress();
        final Integer telp = listStore.get(position).getTelp();

        holder.txtStoreName.setText(storename);
        holder.txtAddress.setText(address);
        holder.txtTelp.setText("Telp = "+ String.valueOf(telp));
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,StoreEditActivity.class);
                intent.putExtra("data", listStore.get(position));
                context.startActivity(intent);
                Toast.makeText(context, "Edit Button", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeListener.deleteStore(listStore.get(position),position);
            }
        });
        holder.btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Fitur Maps Sedang Dalam Pengembangan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStore.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgStore;
        private TextView txtStoreName;
        private TextView txtAddress;
        private TextView txtTelp;
        private Button btnedit;
        private Button btndelete;
        private Button btnmaps;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStore =itemView.findViewById(R.id.img_store);
            txtStoreName =itemView.findViewById(R.id.txt_storename);
            txtAddress =itemView.findViewById(R.id.txt_address);
            txtTelp =itemView.findViewById(R.id.txt_telp);
            btnedit = itemView.findViewById(R.id.btn_edit);
            btndelete = itemView.findViewById(R.id.btn_delete);
            btnmaps = itemView.findViewById(R.id.btn_maps);
        }
    }
    public interface StoreListener{
        void deleteStore(Store store , int position);
    }
}
