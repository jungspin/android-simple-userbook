package com.cos.simpleuserbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.cos.simpleuserbook.adaptor.UserAdaptor;
import com.cos.simpleuserbook.model.User;
import com.cos.simpleuserbook.util.InitSetting;

public class SubActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "SubActivity";

    private SubActivity subActivity = this;


    private TextView tvNameSub;
    private TextView tvTelSub;
    private TextView tvHomePageSub;
    //private Button btnUpdate;
    //private Button btnDel;
    private User user;
    private int index;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 데이터를 받아야지
        Intent intent = getIntent();
        index = (int) intent.getIntExtra("index", index);
        user = (User) intent.getSerializableExtra("user");

        Log.d(TAG, "onCreate: name : " + user.getName());
        Log.d(TAG, "onCreate: index : " + index);

        init();
        initData();
        initListener();
    }

    public void updateItem(User u){
        tvNameSub.setText(u.getName());
        tvTelSub.setText(u.getTel());
        tvHomePageSub.setText(u.getHomePage());
    }

    @Override
    public void initListener() {
        tvTelSub.setOnClickListener(v -> {
            Log.d(TAG, "subInitListener: 클릭됨");
            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:"+user.getTel())
            );
            startActivity(intent);
        });
        tvHomePageSub.setOnClickListener(v -> {
            Log.d(TAG, "tvHomePageSub initListener: 클릭됨");
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(user.getHomePage())
            );
            startActivity(intent);
        });
//        btnUpdate.setOnClickListener(v -> {
//            Log.d(TAG, "initListener: btnUpdate :  클릭됨");
//
//
//        });
//        btnDel.setOnClickListener(v -> {
//            Log.d(TAG, "initListener: btnDel :  클릭됨");
//        });
    }

    @Override
    public void initData() {
        tvNameSub.setText(user.getName());
        tvTelSub.setText(user.getTel());
        tvHomePageSub.setText(user.getHomePage());
    }

    @Override
    public void init() {
        tvNameSub = findViewById(R.id.tvNameSub);
        tvTelSub = findViewById(R.id.tvTelSub);
        tvHomePageSub = findViewById(R.id.tvHomePageSub);
        //btnUpdate = findViewById(R.id.btnUpdate);
        //btnDel = findViewById(R.id.btnDel);
    }

    @Override
    public void initAdaptor() {

    }
}