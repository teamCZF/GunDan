package com.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.Data.Friend;
import com.base.basepedo.R;
import com.ui.adapter.userAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class RankActivity extends Activity {
    private List<Friend>  friendList=new ArrayList<Friend>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initFriends();
        userAdapter adapter=new userAdapter(RankActivity.this,R.layout.friend_item,friendList);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Friend friend=friendList.get(i);
                Toast.makeText(RankActivity.this,friend.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initFriends(){
        Friend f1=new Friend("小欣欣",R.mipmap.head6,"56123");
        friendList.add(f1);
        Friend f2=new Friend("小虹虹",R.mipmap.head4,"40000");
        friendList.add(f2);
        Friend f3=new Friend("小媛媛",R.mipmap.head3,"40000");
        friendList.add(f3);
        Friend f4=new Friend("奈司",R.mipmap.head1,"39871");
        friendList.add(f4);
        Friend f5=new Friend("宇宙第一",R.mipmap.head5,"32000");
        friendList.add(f5);
        Friend f6=new Friend("老司机",R.mipmap.head5,"30000");
        friendList.add(f6);
    }
}
