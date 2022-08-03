package com.dani.cloovy;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    private static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static final String STORE_PATH="store";
    public static final String PRODUCT_PATH ="product";
    public static final String ACCOUNTS_PATH ="accounts";


    public static DatabaseReference getReference(String path){
        return firebaseDatabase.getReference(path);

    }
}
