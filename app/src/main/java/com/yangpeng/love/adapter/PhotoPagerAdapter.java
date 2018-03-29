package com.yangpeng.love.adapter;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.github.chrisbanes.photoview.PhotoView;
import com.yangpeng.love.utils.GlideUtil;
import java.util.ArrayList;




public class PhotoPagerAdapter extends PagerAdapter {

    private Context mContext;

    private boolean mIsMatch;
    private ArrayList<String> mData;


    public PhotoPagerAdapter(Context context, ArrayList<String> list, boolean isMatch) {
        mContext = context;
        mData = list;
        mIsMatch = isMatch;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        ImageView photoView;
        if (mIsMatch) {
            photoView = new ImageView(mContext);
            photoView.setScaleType(ScaleType.FIT_XY);
        } else {
            //使用 PhotoView进行网络图片和本地图片的加载，缩放和点击事件处理
            photoView = new PhotoView(mContext);
        }
        GlideUtil.loadImageView(mContext,mData.get(position),photoView);
        container.addView(photoView, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
