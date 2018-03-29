package com.yangpeng.love.application;

import android.app.Application;

import com.yangpeng.love.utils.T;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.love.application
 * 文件名:BaseApplication
 * 创建者:yangpeng
 * 创建时间：2018/3/13   16:37
 * 描述：自定义application
 */

public class BaseApplication extends Application
{

    /**
     * 1.初始化
     *
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
        /**
         * 初始化Toast
         */
        T.init(this);
    }
}
