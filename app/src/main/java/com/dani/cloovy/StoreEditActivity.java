package com.dani.cloovy;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;

public class StoreEditActivity extends AppCompatActivity {

    private EditText editStoreName;
    private EditText editAddress;
    private EditText editTelp;
    private Button btnsumbit;

    private String storename;
    private String address;
    private Integer telp;
    private String key;
    private Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_edit);

        initView();


        store = (Store) getIntent().getSerializableExtra("data");
        if(store != null){
            editStoreName.setText(store.getNamestore());
            editAddress.setText(store.getAddress());
            editTelp.setText(String.valueOf(store.getTelp()));
            btnsumbit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updatestore(store);
                }
            });
        }else {
            btnsumbit.setOnClickListener(view -> {
                addstore();
            });
        }
    }



    private void initView(){
        editStoreName = findViewById(R.id.edit_storename);
        editAddress = findViewById(R.id.edit_address);
        editTelp  = findViewById(R.id.edit_telp);
        btnsumbit = findViewById(R.id.btn_submit);



    }

    private Boolean isValid(EditText editText , String data){
        if (!TextUtils.isEmpty(data)&& !data.equals("")){
            return true;
        }else{
            editText.setError("field tidak boleh kosong");
            return false;
        }
    }


    private void addstore(){
        key = FirebaseUtils.getReference(FirebaseUtils.STORE_PATH).push().getKey();
        storename = editStoreName.getText().toString().trim();
        address =  editAddress.getText().toString().trim();

        if(isValid(editStoreName,storename) && isValid(editAddress,address) && isValid(editTelp,editTelp.getText().toString().trim())){

            telp = Integer.parseInt(editTelp.getText().toString());

            Store store = new Store(key , storename , address , telp);

            FirebaseUtils.getReference(FirebaseUtils.STORE_PATH).child(key).setValue(store);

            Toast.makeText(StoreEditActivity.this, "Item Sudah Ditambahkan", Toast.LENGTH_SHORT).show();

            onBackPressed();

        }

    }

    private void updatestore(Store store){
        store.setNamestore(editStoreName.getText().toString().trim());
        store.setAddress(editAddress.getText().toString().trim());
        store.setTelp(Integer.parseInt(editTelp.getText().toString().trim()));

        FirebaseUtils.getReference(FirebaseUtils.STORE_PATH).child(store.getKey()).setValue(store).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(StoreEditActivity.this, "Item Sukses Di Update", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });
    }
}