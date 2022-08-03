package com.dani.cloovy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    private String TAG = SignInActivity.class.getSimpleName();
    private String key;
    private String username;
    private String password;

    private EditText editusername;
    private EditText editpassword;
    private Button btnsignin;
    private Button btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInAccount();
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        editusername = findViewById(R.id.edit_username);
        editpassword = findViewById(R.id.edit_password);
        btnsignin = findViewById(R.id.btn_signin);
        btnsignup = findViewById(R.id.btn_signup);
    }

//    private Boolean isValid(EditText editText , String data){
//        if (!TextUtils.isEmpty(data)&& !data.equals("")){
//            return true;
//        }else{
//            editText.setError("field tidak boleh kosong");
//            return false;
//        }
//    }

    private void signInAccount(){
        username =editusername.getText().toString().trim();
        password =editpassword.getText().toString().trim();

        Query query = FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Account account = dataSnapshot.getValue(Account.class);
                        if (account.getUsername().equals(username)&& account.getPassword().equals(password)){
                            MyPreferences.getEditor().putBoolean(MyPreferences.IS_LOGIN,true);
                            MyPreferences.getEditor().putString(MyPreferences.USERNAME,account.getUsername());
                            MyPreferences.getEditor().commit();
                            Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                            Toast.makeText(SignInActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getDetails()+"|"+error.getMessage());
            }
        });
    }
}