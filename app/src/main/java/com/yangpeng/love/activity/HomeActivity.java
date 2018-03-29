package com.yangpeng.love.activity;
import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.yangpeng.love.R;
import com.yangpeng.love.view.fragment.HomeFragment;
import com.yangpeng.love.view.fragment.MessageFragment;
import com.yangpeng.love.view.fragment.MineFragment;

/**
 * @function : 1.创建首页所有的fragment
 */

public class HomeActivity extends BaseActivity  implements View.OnClickListener{

    private LinearLayout mHomeLayout;
    private LinearLayout mMessageLayout;
    private LinearLayout mMineLayout;
    private  FragmentTransaction fragmentTransaction;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private ImageView mHomeView;
    private ImageView mMessageView;
    private ImageView mMineView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //更改状态栏颜色 支持4.4+版本
        changStatusBarColor(R.color.color_fed952);
        setContentView(R.layout.activity_home_layout);
        //初始化view
        initView();
        //初始化首页默认fragment
       fragmentTransaction = getSupportFragmentManager().beginTransaction();
       mHomeFragment=new HomeFragment();
       fragmentTransaction.replace(R.id.content_layout,mHomeFragment).commit();

       //申请权限
        request();

    }

    private void request() {

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE,Manifest.permission.VIBRATE
                ,Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 152);
    }

    private void initView()
    {
        mHomeLayout=findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mMessageLayout=findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout=findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);

        mHomeView=findViewById(R.id.home_image_view);
        //设置默认选择第一个图标
        mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
        mMessageView=findViewById(R.id.message_image_view);
        mMineView=findViewById(R.id.mine_image_view);


    }




    //处理点击事件
    @Override
    public void onClick(View v)
    {
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();

        switch (v.getId())
        {
            case R.id.home_layout_view:
                //更改图标颜色
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);
                //首先隐藏其他的fragment
                hideFragment(fm,mMessageFragment);
                hideFragment(fm,mMineFragment);
                //将我们的Homefragment显示出来
                if (mHomeFragment==null)
                {
                    mHomeFragment=new HomeFragment();
                    fm.add(R.id.content_layout,mHomeFragment);
                }
               else
                   {
                       //已经创建过了
                       fm.show(mHomeFragment);
                   }

                break;


            case R.id.message_layout_view:
               //更改图标颜色
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);
                //首先隐藏其他的fragment
                hideFragment(fm,mHomeFragment);
                hideFragment(fm,mMineFragment);
                //将我们的Messagefragment显示出来
                if (mMessageFragment==null)
                {
                   mMessageFragment =new MessageFragment();
                    fm.add(R.id.content_layout,mMessageFragment);
                }
                else
                {
                    //已经创建过了
                    fm.show(mMessageFragment);

                }

                break;



            case R.id.mine_layout_view:
                //更改图标颜色
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                //首先隐藏其他的fragment
                hideFragment(fm,mMessageFragment);
                hideFragment(fm,mHomeFragment);
                //将我们的Minefragment显示出来
                if (mMineFragment==null)
                {
                    mMineFragment=new MineFragment();
                    fm.add(R.id.content_layout,mMineFragment);
                }
                else
                {
                    //已经创建过了
                    fm.show(mMineFragment);
                }


                break;



        }

        //提交事务
        fm.commit();

    }

    //隐藏某个fragment
    private void hideFragment(FragmentTransaction fm, Fragment fragment)
    {
        if (fragment!=null)
        {
            fm.hide(fragment);
        }

    }

}
