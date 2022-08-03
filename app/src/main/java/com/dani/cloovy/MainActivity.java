package com.dani.cloovy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private FrameLayout container;
    private NavigationBarView navigationBarView;
    private final AccountFragment accountFragment = new AccountFragment();
    private final StoreFragment storeFragment = new StoreFragment();
    private final ProductFragment productFragment = new ProductFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadFragment(storeFragment);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_store:
                        loadFragment(storeFragment);
                        return true;
                    case R.id.action_product:
                        loadFragment(productFragment);
                        return true;
                    case R.id.action_account:
                        loadFragment(accountFragment);
                        return true;
                }
                return false;
            }
        });

    }

    private void initView(){
        container = findViewById(R.id.container);
        navigationBarView = findViewById(R.id.bottom_navigation);
    }
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
}