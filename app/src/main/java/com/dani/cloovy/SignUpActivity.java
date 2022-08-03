package com.dani.cloovy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private String TAG = SignUpActivity.class.getSimpleName();

    private String key;
    private String username;
    private String password;

    private EditText editusername;
    private EditText editpassword;
    private Button btnsignup;
    private Button btnsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAccount();
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView(){
        editusername = findViewById(R.id.edit_username);
        editpassword = findViewById(R.id.edit_password);
        btnsignup = findViewById(R.id.btn_signup);
        btnsignin = findViewById(R.id.btn_signin);
    }

    private Boolean isValid(EditText editText , String data){
        if (!TextUtils.isEmpty(data)&& !data.equals("")){
            return true;
        }else{
            editText.setError("field tidak boleh kosong");
            return false;
        }
    }

    private void addAccount(){
        key= FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).push().getKey();
        username = editusername.getText().toString().trim();
        password = editpassword.getText().toString().trim();

        if (isValid(editusername,username)&&isValid(editpassword,password)){
            Account account = new Account(key,username,password);
            FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).child(key).setValue(account);
            Toast.makeText(SignUpActivity.this, "Akun Berhasil ditambahkan", Toast.LENGTH_LONG).show();

            onBackPressed();
        }
    }
}