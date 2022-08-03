package com.dani.cloovy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private ArrayList<Product> listProduct;
    private Context context;
    private ProductAdapter.ProductListener productListener;

    public ProductAdapter(ArrayList<Product>listProduct, Context context, ProductAdapter.ProductListener productListener){
        this.listProduct =listProduct;
        this.context = context;
        this.productListener = productListener;
    }
    @NonNull
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent,false);
        ProductAdapter.ViewHolder viewHolder = new ProductAdapter.ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final String productname = listProduct.get(position).getProductname();
        final String desc = listProduct.get(position).getDesc();
        final Integer qty = listProduct.get(position).getQty();

        holder.txtProductName.setText(productname);
        holder.txtDesc.setText(desc);
        holder.txtQty.setText("Quantity = "+ String.valueOf(qty));
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductEditActivity.class);
                intent.putExtra("data1", listProduct.get(position));
                context.startActivity(intent);
                Toast.makeText(context, "Edit Button", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productListener.deleteProduct(listProduct.get(position),position);
            }
        });
        holder.btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Fitur Order Sedang Dalam Pengembangan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct;
        private TextView txtProductName;
        private TextView txtDesc;
        private TextView txtQty;
        private Button btnedit;
        private Button btndelete;
        private Button btnorder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct =itemView.findViewById(R.id.img_store);
            txtProductName =itemView.findViewById(R.id.txt_productname);
            txtDesc =itemView.findViewById(R.id.txt_desc);
            txtQty =itemView.findViewById(R.id.txt_qty);
            btnedit = itemView.findViewById(R.id.btn_edit);
            btndelete = itemView.findViewById(R.id.btn_delete);
            btnorder = itemView.findViewById(R.id.btn_order);
        }
    }
    public interface ProductListener{
        void deleteProduct(Product product , int position);
    }

}
