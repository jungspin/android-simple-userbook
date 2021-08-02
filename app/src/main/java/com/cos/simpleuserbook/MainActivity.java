package com.cos.simpleuserbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cos.simpleuserbook.adaptor.UserAdaptor;
import com.cos.simpleuserbook.model.User;
import com.cos.simpleuserbook.provider.UserProvider;
import com.cos.simpleuserbook.util.InitSetting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "MainActivity2";

    private MainActivity mContext = this;
    private RecyclerView rvUsers;
    private RecyclerView.LayoutManager layoutManager;
    private UserAdaptor userAdaptor;
    private FloatingActionButton fabAdd;
    private NavigationView nav;


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
        nav = findViewById(R.id.nav);
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

        // nav!!!!! 나중에 핼퍼클래스 배우면 수정해야됨!!!!!!!
        nav.setNavigationItemSelectedListener(item -> {
            Log.d(TAG, "setNavigationItemSelectedListener: " + item.getTitle());

            switch (item.getItemId()){
                case R.id.navMain:
                    Log.d(TAG, "item.getItemId(): "+ item.getItemId());
                    Intent intent = new Intent(
                            MainActivity.this, // 내화면에서 내화면 가기
                            MainActivity.class
                    );
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case R.id.navLogin:
                    Log.d(TAG, "item.getItemId(): "+ item.getItemId());
                    Intent intent2 = new Intent(
                            MainActivity.this, // 내화면에서 내화면 가기
                            LoginActivity.class
                    );
                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent2);
                    break;
                default:
                    break;
            }
            return true;
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

    public void mRvScroll(){
        rvUsers.scrollToPosition(userAdaptor.getItemCount()-1); // 카운트가 0부터 시작하기 때문에 -1 해줘야함
    }
}