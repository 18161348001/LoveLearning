package com.yangpeng.love.okhttp.response;
import android.os.Handler;
import android.os.Looper;


import com.yangpeng.love.okhttp.exception.OkHttpException;
import com.yangpeng.love.okhttp.listener.DisposeDataHandle;
import com.yangpeng.love.okhttp.listener.DisposeDataListener;
import com.yangpeng.love.utils.ResponseEntityToModule;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.ypengsdk.okhttp.listener
 * 文件名:CommonJsonCallback
 * 创建者:yangpeng
 * 创建时间：2018/3/14   13:40
 * 描述：自定义callback类 处理返回的JSON
 */

public  class CommonJsonCallback  implements Callback
{

    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error



    private Class<?>mClass;
    private DisposeDataListener mListener;
    private Handler mMainHandler;


    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mListener=handle.mListener;
        this.mClass=handle.mClass;
    }






    @Override
    public void onFailure(Call call, final IOException e)
    {
        //请求失败
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                //抛出异常到应用层
                mListener.onFailure(new OkHttpException(NETWORK_ERROR,e));
            }
        });
    }


    @Override
    public void onResponse(Call call, final Response response) throws IOException
    {
        final  String result=response.body().string();
         mMainHandler.post(new Runnable() {
             @Override
             public void run() {
                 handleResponse(result);
             }
         });

    }

    private void handleResponse(Object responseObj)
    {
        /**
         * 如果传入的json数据为空
         */
         if (responseObj==null||responseObj.toString().equals(""))
         {
             //抛出异常到应用层
             mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
             return;
         }


        try {
            /**
             * 协议确定后看这里如何修改
             */
            JSONObject result = new JSONObject(responseObj.toString());
            if (mClass == null) {
                mListener.onSuccess(result);
            } else {
                Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                if (obj != null) {
                    mListener.onSuccess(obj);
                } else {
                    mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }

    }
}
