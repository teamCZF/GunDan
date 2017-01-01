package com.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.base.basepedo.R;

/**
 * Created by chen on 2017/1/1.
 */

//public class collection extends Activity implements ViewSwitcher.ViewFactory
//{
    /*private Button btnl;
    private Button btnr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection);
        btnl = (Button)findViewById(R.id.collection_left);
        btnr = (Button)findViewById(R.id.collection_right);
        btnl.setOnClickListener(this);
        btnr.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.collection_left:

                break;
            case R.id.collection_right:

                break;
        }
    }*/


    public class collection extends Activity implements ViewSwitcher.ViewFactory
    {

        private ImageSwitcher switcher;
        private Button forward;
        private Button next;
        // 图片索引
        private  int index = 0;
        // 显示的图片资源

        private  final Integer[] imagelist =
                { R.mipmap.head1, R.mipmap.head2, R.mipmap.head3, R.mipmap.head4 };

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.collection);

            forward = (Button) findViewById(R.id.collection_left);
            next = (Button) findViewById(R.id.collection_right);
            switcher = (ImageSwitcher) findViewById(R.id.collection_image);
            switcher.setFactory(this);
            switcher.setImageResource(imagelist[index]);

            // 上一张
            forward.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    index--;
                    if (index < 0)
                    {
                        index = imagelist.length - 1;
                    }
                    switcher.setImageResource(imagelist[index]);
                }
            });
            // 下一张
            next.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    index++;
                    if (index >= imagelist.length)
                    {
                        index = 0;
                    }
                    switcher.setImageResource(imagelist[index]);
                }
            });
        }

        // 用于显示图片
        @Override
        public View makeView()
        {
            return new ImageView(this);
        }
    }

//}
