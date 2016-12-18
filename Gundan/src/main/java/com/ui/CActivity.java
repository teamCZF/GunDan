package com.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.Data.Friend;
import com.base.basepedo.R;
import com.ui.adapter.userAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class CActivity extends Activity {
    private List<Friend>  friendList=new ArrayList<Friend>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        initFriends();
        userAdapter adapter=new userAdapter(CActivity.this,R.layout.friend_item,friendList);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Friend friend=friendList.get(i);
            }
        });
    }
    private void initFriends(){
        Friend f1=new Friend("欣欣",R.drawable.dilan_haoyou,"56123");
        friendList.add(f1);
        Friend f2=new Friend("虹虹",R.drawable.dilan_haoyou,"40000");
        friendList.add(f2);
        Friend f3=new Friend("T  T",R.drawable.dilan_haoyou,"39871");
        friendList.add(f3);
    }
}
