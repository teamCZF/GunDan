package com.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Data.Friend;
import com.base.basepedo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class userAdapter extends ArrayAdapter<Friend>{
    private int resourceId;
    public userAdapter(Context context, int textViewResourceId,
                       List<Friend> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Friend user=getItem(position);//获取当前项的实例
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.friendImage=(ImageView) view.findViewById(R.id.friend_image);
            viewHolder.friendName=(TextView) view.findViewById(R.id.friend_name);
            viewHolder.friendStep=(TextView) view.findViewById(R.id.friend_step);
            view.setTag(viewHolder);
        }
        else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }

        viewHolder.friendImage.setImageResource(user.getImageId());
        viewHolder.friendName.setText(user.getName());
        viewHolder.friendStep.setText(user.getStep());
        return view;
    }
    class ViewHolder{
        ImageView friendImage;
        TextView friendName;
        TextView friendStep;
    }
}
