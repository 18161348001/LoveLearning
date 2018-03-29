package com.yangpeng.love.adapter;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.yangpeng.love.R;
import com.yangpeng.love.entity.RecommandBodyValue;
import com.yangpeng.love.utils.GlideUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.love.adapter
 * 文件名:HotSalePagerAdapter
 * 创建者:yangpeng
 * 创建时间：2018/3/26   19:01
 * 描述：自定义viewpager的适配器
 */

public class HotSalePagerAdapter extends PagerAdapter
{

     private Context mContext;
     private ArrayList<RecommandBodyValue> mBodyValuesList;
     private LayoutInflater mInflater;

    public HotSalePagerAdapter(Context mContext, ArrayList<RecommandBodyValue> mBodyValues) {
        this.mContext = mContext;
        this.mBodyValuesList = mBodyValues;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {

        /**
         * 设置item数量为无限大
         */
        return Integer.MAX_VALUE;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /**
         * 获取的当前的数据  取 position%mdata.size ==>(0,mdata-1);
         */
        final RecommandBodyValue mValue=mBodyValuesList.get(position%mBodyValuesList.size());
        View rootView=mInflater.inflate(R.layout.item_hot_product_pager_layout,null);
        TextView titleView =  rootView.findViewById(R.id.title_view);
        TextView infoView = rootView.findViewById(R.id.info_view);
        TextView gonggaoView =  rootView.findViewById(R.id.gonggao_view);
        TextView saleView =  rootView.findViewById(R.id.sale_num_view);
        ImageView   imageViews1=  rootView.findViewById(R.id.image_one);
        ImageView  imageViews2 =  rootView.findViewById(R.id.image_two);
        ImageView  imageViews3 =  rootView.findViewById(R.id.image_three);

       //设置内容
        titleView.setText(mValue.title);
        infoView.setText(mValue.price);
        gonggaoView.setText(mValue.info);
        saleView.setText(mValue.text);

        //加载图片
        GlideUtil.loadImageView(mContext,mValue.url.get(0),imageViews1);
        GlideUtil.loadImageView(mContext,mValue.url.get(1),imageViews2);
        GlideUtil.loadImageView(mContext,mValue.url.get(2),imageViews3);

       container.addView(rootView);
        return rootView;

    }

    /**
     * 销毁item
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
             container.removeView((View) object);
    }


    /**
     *  判断
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }






}
