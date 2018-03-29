package com.yangpeng.love.view.fragment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.yangpeng.love.R;
import com.yangpeng.love.activity.AdBrowserActivity;
import com.yangpeng.love.adapter.CourseAdapter;
import com.yangpeng.love.biz.RequestCenter;
import com.yangpeng.love.entity.BaseRecommandModel;
import com.yangpeng.love.entity.RecommandBodyValue;
import com.yangpeng.love.okhttp.listener.DisposeDataListener;
import com.yangpeng.love.utils.T;
import com.yangpeng.love.view.BaseFragment;
import com.yangpeng.love.view.fragment.home.HomeHeaderLayout;
import com.yangpeng.love.zxing.app.CaptureActivity;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.love.view
 * 文件名:HomeFragment
 * 创建者:yangpeng
 * 创建时间：2018/3/14   8:34
 * 描述：主页fragment
 */


public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private static final int REQUEST_QRCODE = 0x01;
    private static final String TAG = "1828382";


    /**
     * UI
     */
    private ImageView  anim_loading;
    private ImageView  mQRCodeView;
    private ImageView  mCategoryView;
    private ListView  mListView;
    private EditText mSearchView;


    /**
     * data
     */
    private CourseAdapter mAdapter;
    private BaseRecommandModel mRecommandData;




    public HomeFragment() {

    }


    //创建homefragment类
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();//加载数据
    }


    //创建视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_home_layout, container, false);
       initView(view);
        return view;
    }



    //初始化视图
    private void initView(View view)
    {
        anim_loading=view.findViewById(R.id.anim_loading_data);
        mQRCodeView=view.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView=view.findViewById(R.id.category_view);
        mSearchView=view.findViewById(R.id.search_view);
        mListView=view.findViewById(R.id.list_view);
        /**
         * 设置 未加载出内容时显示的动画
         */
        AnimationDrawable animationLoading = (AnimationDrawable) anim_loading.getDrawable();
        animationLoading.start();

    }


    private void requestRecommandData()
    {


        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                /**
                 * 数据获取成功 处理UI变化
                 */
                mRecommandData= (BaseRecommandModel) responseObj;
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {

                /**
                 * 信息获取失败
                 */
                showErrorView();
            }
        });




    }

    private void showErrorView()
    {
        //数据为空
        T.showToast(getString(R.string.error_show));
    }

    private void showSuccessView()
    {

        //判断数据不为空
        if (mRecommandData.data!=null&&mRecommandData.data.list.size()>0)
        {
            //关闭动画
            anim_loading.setVisibility(View.GONE);
            //显示listview
            mListView.setVisibility(View.VISIBLE);
            //为listview添加头
            mListView.addHeaderView(new HomeHeaderLayout(getActivity(), mRecommandData.data.head));
            //设置适配器数据
            mAdapter=new CourseAdapter(getActivity(),mRecommandData.data.list);
            mListView.setAdapter(mAdapter);

        }else
        {

            showErrorView();
        }



    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.qrcode_view:
                    doOpenCamera();
                break;

        }


    }


    private void doOpenCamera() {
            Intent intent = new Intent(getActivity(), CaptureActivity.class);
            startActivityForResult(intent, REQUEST_QRCODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           switch (requestCode)
           {
               case REQUEST_QRCODE:
                   String code = null;
                   if (resultCode == Activity.RESULT_OK)
                   {
                       code = data.getStringExtra("SCAN_RESULT");
                   }if (resultCode==300)
                   {
                     code = data.getStringExtra("result");
                    }

                       if (code.contains("http") || code.contains("https")) {
                           Intent intent = new Intent(getActivity(), AdBrowserActivity.class);
                           intent.putExtra(AdBrowserActivity.KEY_URL, code);
                           getActivity().startActivity(intent);
                       } else {
                           Toast.makeText(getActivity(), code, Toast.LENGTH_SHORT).show();
                       }

                   break;

           }


    }
}
