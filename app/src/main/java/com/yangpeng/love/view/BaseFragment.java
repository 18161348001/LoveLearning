package com.yangpeng.love.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.love.view
 * 文件名:BaseFragment
 * 创建者:yangpeng
 * 创建时间：2018/3/14   8:35
 * 描述：基类fragment   用来处理所有fragment的公共行为
 */

public class BaseFragment extends Fragment
{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
