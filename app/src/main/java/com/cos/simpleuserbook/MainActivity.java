package com.cos.simpleuserbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.cos.simpleuserbook.adaptor.UserAdaptor;
import com.cos.simpleuserbook.model.User;
import com.cos.simpleuserbook.provider.UserProvider;
import com.cos.simpleuserbook.util.InitSetting;

public class MainActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "MainActivity2";

    private MainActivity mContext = this;
    private RecyclerView rvUsers; // 리니어 아니라서 방향설정 필요 없지?
    private RecyclerView.LayoutManager layoutManager;
    private UserAdaptor userAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: 그림 그려짐" );

        init();
        initAdaptor();
        initData();
        initListener();
    }

    @Override
    public void init() {
        rvUsers = findViewById(R.id.rvUsers);
    }

    @Override
    public void initListener() {
        rvUsers.setOnClickListener(v -> {
            Log.d(TAG, "MainInitListener: 클릭됨");
        });
    }

    @Override
    public void initData() {
        UserProvider userProvider = new UserProvider();
        userAdaptor.addItems(userProvider.findAll());
    }

    @Override
    public void initAdaptor() {
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvUsers.setLayoutManager(layoutManager);
        userAdaptor = new UserAdaptor(mContext);
        rvUsers.setAdapter(userAdaptor);
    }
}