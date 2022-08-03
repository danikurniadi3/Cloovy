package com.dani.cloovy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {
    @Nullable
    private TextView txtUsername;
    private Button btnlogout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);

        initView(view);

        txtUsername.setText(MyPreferences.getSharedPreferences().getString(MyPreferences.USERNAME,"username"));

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPreferences.getEditor().clear().commit();
                Intent intent = new Intent(getActivity(),SplashScreenActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return  view;

    }

    private void initView(View view){
        txtUsername= view.findViewById(R.id.txt_username);
        btnlogout = view.findViewById(R.id.btn_logout);
    }
}
