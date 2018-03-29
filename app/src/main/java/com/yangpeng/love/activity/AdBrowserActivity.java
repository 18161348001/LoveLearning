package com.yangpeng.love.activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.yangpeng.love.R;
public class AdBrowserActivity extends BaseActivity {

    public  static  final  String KEY_URL="key_url";
    private String url;
    private WebView mWebView;
    private ProgressBar  mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_browser_layout);
        initIntent();
        initView();
        initData();



    }

    private void initIntent() {
        Intent intent=getIntent();
        url=intent.getStringExtra(KEY_URL);
    }

    private void initView() {
       mWebView=findViewById(R.id.web_view);
        mProgressBar=findViewById(R.id.m_progress);
    }

    private void initData() {

        //加载网页
        //设置支持JS
        mWebView.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        //设置回调函数监听进度
        mWebView.setWebChromeClient(new WebViewClient());
        //加载网页
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new android.webkit.WebViewClient()
        {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                /**
                 * 判断版本大小7.0+
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(request.toString());
                }
                //接受一直在本地显示的事件
                return true;
            }
        });



    }


    public  class WebViewClient extends WebChromeClient {

        //监听进度
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress==100)
            {
                //隐藏进度控件
              mProgressBar .setVisibility(View.GONE);
            }

        }
    }




}


