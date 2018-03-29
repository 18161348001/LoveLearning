package com.yangpeng.love.activity;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yangpeng.love.utils.StatusBarUtil;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.love.activity
 * 文件名:BaseActivity
 * 创建者:yangpeng
 * 创建时间：2018/3/14   8:56
 * 描述：基类Activity，用来处理一些公共事件，如：数据统计
 */

public class BaseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 修改状态栏颜色
     * @param color
     */
    public void  changStatusBarColor(@ColorRes int color)
    {

        StatusBarUtil.setStatusBarColor(this,color);

    }



}
