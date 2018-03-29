package com.yangpeng.love.adapter;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yangpeng.love.R;
import com.yangpeng.love.entity.BaseRecommandModel;
import com.yangpeng.love.entity.RecommandBodyValue;
import com.yangpeng.love.utils.GlideUtil;
import com.yangpeng.love.utils.ImageLoaderUtil;
import com.yangpeng.love.utils.Util;
import com.yangpeng.love.utils.Utils;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名：LoveLearning
 * 包名：com.yangpeng.love.adapter
 * 文件名:CourseAdapter
 * 创建者:yangpeng
 * 创建时间：2018/3/15   20:20
 * 描述：自定义首页listview适配器
 */

public class CourseAdapter extends BaseAdapter{

    private static final int CARD_COUNT = 4;
    private static final int VIDOE_TYPE = 0;
    private static final int CARD_TYPE_ONE = 1;
    private static final int CARD_TYPE_TWO = 2;
    private static final int CARD_TYPE_THREE = 3;



    private Context mContext;
    private ArrayList<RecommandBodyValue> mdatas;
    private LayoutInflater mInflater;




    public CourseAdapter(Context context, ArrayList<RecommandBodyValue> mdata) {
        this.mContext = context;
        this.mdatas = mdata;
        mInflater=LayoutInflater.from(mContext);


    }

    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         ViewHolder mViewHolder = null;
        //拿到子条目的type
        int type=getItemViewType(position);
        //拿到数据
        final RecommandBodyValue mValue= (RecommandBodyValue) getItem(position);
        if (convertView==null)
        {
            switch (type)
            {
                case  VIDOE_TYPE:
                    mViewHolder=new ViewHolder();
                    break;

                case  CARD_TYPE_ONE:
//                    //多图item布局
                   mViewHolder=new ViewHolder();
                   convertView=mInflater.inflate(R.layout.item_product_card_two_layout,parent,false);
                   //初始化控件
                    mViewHolder.mLogoView =  convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView =  convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView =  convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView =  convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductLayout=convertView.findViewById(R.id.product_photo_layout);
                    break;

                case CARD_TYPE_TWO:
                    //单图item
                    mViewHolder=new ViewHolder();
                    convertView=mInflater.inflate(R.layout.item_product_card_one_layout,parent,false);
                    //初始化控件
                    mViewHolder.mLogoView =  convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView =  convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView =  convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView =  convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductView=convertView.findViewById(R.id.product_photo_view);
                    break;

                case CARD_TYPE_THREE:
                    mViewHolder=new ViewHolder();
                    convertView=mInflater.inflate(R.layout.item_product_card_three_layout,parent,false);
                    mViewHolder.mViewPager=convertView.findViewById(R.id.pager);

                    break;
            }

            convertView.setTag(mViewHolder);
            }
        else
        {
           mViewHolder= (ViewHolder) convertView.getTag();
        }

        switch (type) {


            case VIDOE_TYPE:

                break;

            case CARD_TYPE_ONE:
               GlideUtil.loadImageView(mContext,mValue.logo,mViewHolder.mLogoView);
                mViewHolder.mTitleView.setText(mValue.title);
                mViewHolder.mInfoView.setText(mValue.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(mValue.text);
                mViewHolder.mPriceView.setText(mValue.price);
                mViewHolder.mFromView.setText(mValue.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(mValue.zan));
                mViewHolder.mProductLayout.removeAllViews();
                //动态添加多个imageview
                for (String url : mValue.url) {
                    mViewHolder.mProductLayout.addView(createImageView(url));
                }
                break;

            case CARD_TYPE_TWO:
                GlideUtil.loadImageView(mContext, mValue.logo, mViewHolder.mLogoView);
                mViewHolder.mTitleView.setText(mValue.title);
                mViewHolder.mInfoView.setText(mValue.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(mValue.text);
                mViewHolder.mPriceView.setText(mValue.price);
                mViewHolder.mFromView.setText(mValue.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(mValue.zan));
                //为单个ImageView加载远程图片
              GlideUtil.loadImageView(mContext,mValue.url.get(0),mViewHolder.mProductView);
                break;

            case CARD_TYPE_THREE:
           //为viewpager添加数据
                ArrayList<RecommandBodyValue>bodyValueArrayList=Util.handleData(mValue);
                mViewHolder.mViewPager.setPageMargin(Utils.dip2px(mContext,16));
                mViewHolder.mViewPager.setAdapter(new HotSalePagerAdapter(mContext,bodyValueArrayList));
                //默认让第一个视图就是中间位置
                mViewHolder.mViewPager.setCurrentItem(bodyValueArrayList.size()*100);
                break;

        }
        return convertView;
    }


    //动态添加ImageView
    private View createImageView(String url)
    {
        ImageView imageView=new ImageView(mContext);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(Utils.dip2px(mContext, 100), LinearLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin=Utils.dip2px(mContext,10);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideUtil.loadImageView(mContext,url,imageView);
        return imageView;
    }


    public class ViewHolder {
        //所有Card共有属性
        public CircleImageView mLogoView;
        public TextView mTitleView;
        public TextView mInfoView;
        public TextView mFooterView;
        //Video Card特有属性
        public RelativeLayout mVieoContentLayout;
        public ImageView mShareView;

        //Video Card外所有Card具有属性
        public TextView mPriceView;
        public TextView mFromView;
        public TextView mZanView;
        //Card One特有属性
        public LinearLayout mProductLayout;
        //Card Two特有属性
        public ImageView mProductView;
        //Card Three特有属性
        public ViewPager mViewPager;
    }



    @Override
    public int getItemViewType(int position) {
        RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        return value.type;
    }


    @Override
    public int getViewTypeCount() {
        return CARD_COUNT;
    }


}
