package com.yangpeng.love.view.fragment.home;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yangpeng.love.R;
import com.yangpeng.love.entity.RecommandFooterValue;
import com.yangpeng.love.utils.GlideUtil;


/**
 * @author: vision
 * @function:
 * @date: 16/9/2
 */
public class HomeBottomItem extends RelativeLayout {

    private Context mContext;
    /**
     * UI
     */
    private RelativeLayout mRootView;
    private TextView mTitleView;
    private TextView mInfoView;
    private TextView mInterestingView;
    private ImageView mImageOneView;
    private ImageView mImageTwoView;

    /**
     * Data
     */
    private RecommandFooterValue mData;


    public HomeBottomItem(Context context, RecommandFooterValue data) {
        this(context, null, data);
    }

    public HomeBottomItem(Context context, AttributeSet attrs, RecommandFooterValue data) {
        super(context, attrs);
        mContext = context;
        mData = data;

        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = (RelativeLayout) inflater.inflate(R.layout.item_home_recommand_layout, this); //添加到当前布局中
        mTitleView =  mRootView.findViewById(R.id.title_view);
        mInfoView =  mRootView.findViewById(R.id.info_view);
        mInterestingView = mRootView.findViewById(R.id.interesting_view);
        mImageOneView = mRootView.findViewById(R.id.icon_1);
        mImageTwoView =  mRootView.findViewById(R.id.icon_2);

        mTitleView.setText(mData.title);
        mInfoView.setText(mData.info);
        mInterestingView.setText(mData.from);
        GlideUtil.loadImageView(mContext, mData.imageOne,mImageOneView);
        GlideUtil.loadImageView(mContext, mData.imageTwo,mImageTwoView);
    }
}
