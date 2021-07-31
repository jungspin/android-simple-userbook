package com.cos.simpleuserbook.adaptor;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.simpleuserbook.MainActivity;
import com.cos.simpleuserbook.R;
import com.cos.simpleuserbook.SubActivity;
import com.cos.simpleuserbook.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.MyViewHolder>{

    private static final String TAG = "UserAdaptor";

    private MainActivity mContext;
    private UserAdaptor userAdaptor = this;

    public UserAdaptor(MainActivity mContext){
        this.mContext = mContext;
    }

    private List<User> users = new ArrayList<>();

    // 컬렉션 데이터 세팅
    public void addItems(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    // 추가
    public void addItem(User user){
        this.users.add(user);
        notifyDataSetChanged();
        // 아직 안 끝남
    }

    // 수정
    public void updateItem(int index, User user){
        //User u = users.get(index);
        Log.d(TAG, "updateItem: index: " + index);
        Log.d(TAG, "adaptor updateItem: " + user.getName());

//        u.setName(user.getName());
//        u.setTel(user.getTel());
//        u.setHomePage(user.getHomePage());
    }

    // 삭제
    public void removeItem(int index){
        users.remove(index);
        notifyDataSetChanged();
    }

    // 데이터 가져오기
    public User getUser(int index){
        User user = users.get(index);
        return user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = // xml 을 메모리에 띄워주는 클래스
                (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 리사이클러 뷰의 모든 것이 여기 담김
        View view = layoutInflater.inflate(R.layout.user_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = users.get(position);
        holder.setItem(user); // 그림에 데이터 끼워줌
        holder.itemView.setOnClickListener(v -> {
            Log.d(TAG, "onBindViewHolder: 클릭됨");
            Intent intent = new Intent(
                    mContext,
                    SubActivity.class
            );
            intent.putExtra("index", position);
            intent.putExtra("user", getUser(position));
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    // user_list_item 의 정보가 들어가나? 디자인이니까
    class MyViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "MyViewHolder";

        private MyViewHolder myViewHolder = this;
        private TextView tvName;
        private TextView tvTel;
        private TextView tvHomePage;


        public MyViewHolder(View itemView) {
            super(itemView);


            tvName = itemView.findViewById(R.id.tvName);
            //tvTel = itemView.findViewById(R.id.tvTel);
            //tvHomePage = itemView.findViewById(R.id.tvHomePage);

            initListener();
        }

        public void initListener(){

        }

        public void setItem(User user){
            tvName.setText(user.getName());
            //tvTel.setText(user.getTel());
            //tvHomePage.setText(user.getHomePage());
        }
    }


}
