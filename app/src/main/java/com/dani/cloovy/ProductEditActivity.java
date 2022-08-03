package com.dani.cloovy;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;

public class ProductEditActivity extends AppCompatActivity {
    private EditText editProductName;
    private EditText editDesc;
    private EditText editQty;
    private Button btnsumbit;

    private String productname;
    private String desc;
    private Integer qty;
    private String key;
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        initView();

        product = (Product) getIntent().getSerializableExtra("data1");
        if(product != null){
            editProductName.setText(product.getProductname());
            editDesc.setText(product.getDesc());
            editQty.setText(String.valueOf(product.getQty()));
            btnsumbit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateproduct(product);
                }
            });
        }else {
            btnsumbit.setOnClickListener(view -> {
                addproduct();
            });
        }

    }

    private void initView(){
        editProductName = findViewById(R.id.edit_productname);
        editDesc = findViewById(R.id.edit_desc);
        editQty  = findViewById(R.id.edit_qty);
        btnsumbit = findViewById(R.id.btn_submit);
    }

    private Boolean isValid(EditText editText , String data1){
        if (!TextUtils.isEmpty(data1)&& !data1.equals("")){
            return true;
        }else{
            editText.setError("field tidak boleh kosong");
            return false;
        }
    }

    private void addproduct(){
        key = FirebaseUtils.getReference(FirebaseUtils.PRODUCT_PATH).push().getKey();
        productname = editProductName.getText().toString().trim();
        desc =  editDesc.getText().toString().trim();

        if(isValid(editProductName,productname) && isValid(editDesc,desc) && isValid(editQty,editQty.getText().toString().trim())){

            qty = Integer.parseInt(editQty.getText().toString());

            Product product = new Product(key , productname , desc , qty);

            FirebaseUtils.getReference(FirebaseUtils.PRODUCT_PATH).child(key).setValue(product);

            Toast.makeText(ProductEditActivity.this, "Item Sudah Ditambahkan", Toast.LENGTH_SHORT).show();

            onBackPressed();

        }

    }

    private void updateproduct(Product product){
        product.setProductname(editProductName.getText().toString().trim());
        product.setDesc(editDesc.getText().toString().trim());
        product.setQty(Integer.parseInt(editQty.getText().toString().trim()));

        FirebaseUtils.getReference(FirebaseUtils.PRODUCT_PATH).child(product.getKey()).setValue(product).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ProductEditActivity.this, "Item Sukses Di Update", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });
    }
}