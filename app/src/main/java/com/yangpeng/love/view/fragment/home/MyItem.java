package com.yangpeng.love.view.fragment.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.yangpeng.love.R;
import com.yangpeng.love.entity.RecommandBodyValue;

import java.util.ArrayList;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.love.view.fragment.home
 * 文件名:MyItem
 * 创建者:yangpeng
 * 创建时间：2018/3/27   13:19
 * 描述：TODO
 */

public class MyItem  extends RelativeLayout{

    private LayoutInflater  mInflater;
    private RelativeLayout mLayout;

    public MyItem(Context context, ArrayList<RecommandBodyValue> data) {
        this(context,null,data);
    }

    public MyItem(Context context, AttributeSet attrs, ArrayList<RecommandBodyValue> data) {
        super(context, attrs);
        mInflater=LayoutInflater.from(context);
        init();

    }

    private void init() {
      mLayout= (RelativeLayout) mInflater.inflate(R.layout.item_home_recommand_layout,this);


    }


}
