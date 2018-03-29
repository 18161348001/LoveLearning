package com.yangpeng.love.view.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangpeng.love.R;
import com.yangpeng.love.view.BaseFragment;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.love.view
 * 文件名:MessageFragment
 * 创建者:yangpeng
 * 创建时间：2018/3/14   8:34
 * 描述：消息fragment
 */

public class MessageFragment extends BaseFragment
{




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message_laytou,container,false);
    }




}
