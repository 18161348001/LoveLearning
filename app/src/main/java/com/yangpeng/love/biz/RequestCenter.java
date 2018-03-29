package com.yangpeng.love.biz;


import com.yangpeng.love.entity.BaseRecommandModel;
import com.yangpeng.love.okhttp.CommonOkhttpClient;
import com.yangpeng.love.okhttp.listener.DisposeDataHandle;
import com.yangpeng.love.okhttp.listener.DisposeDataListener;
import com.yangpeng.love.okhttp.request.CommonRequest;
import com.yangpeng.love.okhttp.request.RequestParams;

/**
 * @author: vision
 * @function:
 * @date: 16/8/12
 */
public class RequestCenter {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
       CommonOkhttpClient.sendRequest(CommonRequest.
               creatGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }




    public static void requestRecommandData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener, BaseRecommandModel.class);
    }


}
