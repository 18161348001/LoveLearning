package com.yangpeng.love.okhttp.request;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.FormBody;
/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.ypengsdk.okhttp.request
 * 文件名:CommonRequest
 * 创建者:yangpeng
 * 创建时间：2018/3/14   10:28
 * 描述：接收参数 为我们生成request对象
 */

public class CommonRequest
{

    /**
     * 不带请求头的Post请求
     * @param url
     * @param params
     * @return 返回一个创建好的request
     */
    public  static  Request creatPostRequest(String url,RequestParams params)
    {
        return creatPostRequest(url,params,null);
    }


    /**可以带请求头的Post请求
     * @param url
     * @param params
     * @param headers
     * @return 返回一个创建好的request
     */
    public  static Request creatPostRequest(String url,RequestParams params,RequestParams headers)
    {
       //添加参数
      FormBody.Builder mFormBodyBuild=new FormBody.Builder();
      if (params!=null)
      {
          for (Map.Entry<String,String>entry:params.urlParams.entrySet())
          {
              mFormBodyBuild.add(entry.getKey(),entry.getValue());

          }

      }

        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }

        //构建request
        FormBody requestBody=mFormBodyBuild.build();
        Headers requestHeaders=mHeaderBuild.build();
        Request request=new Request.Builder().url(url)
                        .post(requestBody)
                        .headers(requestHeaders)
                        .build();

        return request;

    }


    /**
     * 不带头的带参数get请求
     * @param url
     * @param params
     * @return 返回一个创建好的request
     *    url?key=value&y=3
     */
    public  static Request creatGetRequest(String url,RequestParams params)
    {
        StringBuilder urlBuilder=new StringBuilder(url).append("?");
        if (params!=null)
        {
            for (Map.Entry<String,String> entry:params.urlParams.entrySet())
            {
               urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return  new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length()-1)).get().build();

    }


    /**
     *带头的带参数get请求
     * @param url
     * @param params
     * @param headers
     * @return  返回一个创建好的request
     */
    public  static Request creatGetRequest(String url,RequestParams params,RequestParams headers)
    {

        StringBuilder urlBuilder=new StringBuilder(url).append("?");
        if (params!=null)
        {
            for (Map.Entry<String,String> entry:params.urlParams.entrySet())
            {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }

        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }

        Headers mHeaders = mHeaderBuild.build();

        Request request=new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length()-1))
                         .get()
                         .headers(mHeaders)
                          .build();
            return  request;

    }














}
