package com.yangpeng.love.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.love.utils
 * 文件名:T
 * 创建者:yangpeng
 * 创建时间：2018/3/15   20:23
 * 描述：自定义封装Toast
 */

public class T
{

    private static Toast toast;

    public  static  void  init(Context mContext)
    {
        toast=Toast.makeText(mContext,"",Toast.LENGTH_SHORT);

    }


    public  static void  showToast(String content)
    {
        if (toast!=null)
        {
            toast.setText(content);
            toast.show();
        }

    }




}


