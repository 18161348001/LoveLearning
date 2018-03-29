package com.yangpeng.love.okhttp;


import com.yangpeng.love.okhttp.https.HttpsUtils;
import com.yangpeng.love.okhttp.listener.DisposeDataHandle;
import com.yangpeng.love.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.ypengsdk.okhttp
 * 文件名:CommonOkhttpClient
 * 创建者:yangpeng
 * 创建时间：2018/3/14   10:27
 * 描述：请求的发送，请求的参数配置，https的支持
 */

public class CommonOkhttpClient
{

    private  static  final  int TIME_OUT=30; //超时参数 30s
    private  static OkHttpClient mOkHttpClient;

    //为我们的client去配置参数

    static
    {
        //创建我们client对象的构建者
        OkHttpClient.Builder okhttpBuilder=new OkHttpClient.Builder();
        //为构建者填充超时时间和单位
        okhttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS );
        okhttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        //允许请求重定向
        okhttpBuilder.followRedirects(true);
        //支持https
        okhttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

       okhttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(),HttpsUtils.initTrustManager());

        //生成client 对象
        mOkHttpClient= okhttpBuilder.build();

    }


    /**
     * 发送具体的http/https请求
     * @param request
     * @param handle
     * @return Call
     */
    public  static Call sendRequest(Request request, DisposeDataHandle handle)
    {
        Call call=mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return  call;
    }




    /**
     * @return 单例okhttpclient
     */
     public static  OkHttpClient getOkHttpClient()
     {
         return   mOkHttpClient;
     }




}



