package com.cos.simpleuserbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.cos.simpleuserbook.adaptor.UserAdaptor;
import com.cos.simpleuserbook.model.User;
import com.cos.simpleuserbook.provider.UserProvider;
import com.cos.simpleuserbook.util.InitSetting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "MainActivity2";

    private MainActivity mContext = this;
    private RecyclerView rvUsers;
    private RecyclerView.LayoutManager layoutManager;
    private UserAdaptor userAdaptor;
    private FloatingActionButton fabAdd;


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
        fabAdd = findViewById(R.id.fabAdd);
    }

    @Override
    public void initListener() {
        rvUsers.setOnClickListener(v -> {
            Log.d(TAG, "MainInitListener: 클릭됨");
        });
        // fab 로 추가
        fabAdd.setOnClickListener(v -> {
            userAdaptor.addItem(new User("newbie", "01099997777", "https://www.notion.so/jungspin/Android-375d6e2e454f42efbdd60620258dbdf7"));
        });
        // 스와이프로 삭제
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                userAdaptor.removeItem(index);
            }
        }).attachToRecyclerView(rvUsers);
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

    public void mRvScroll(){
        rvUsers.scrollToPosition(userAdaptor.getItemCount()-1); // 카운트가 0부터 시작하기 때문에 -1 해줘야함
    }
}