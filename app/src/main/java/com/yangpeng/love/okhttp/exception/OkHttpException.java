package com.yangpeng.love.okhttp.exception;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.ypengsdk.okhttp.exception
 * 文件名:OkhttpException
 * 创建者:yangpeng
 * 创建时间：2018/3/14   13:34
 * 描述：自定义异常类,返回ecode,emsg到业务层
 */

public class OkHttpException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * 异常码
     */
    private int ecode;

    /**
     * 异常信息
     */
    private Object emsg;

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}