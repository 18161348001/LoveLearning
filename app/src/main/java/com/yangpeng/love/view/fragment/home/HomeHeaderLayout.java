package com.yangpeng.love.view.fragment.home;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yangpeng.love.R;
import com.yangpeng.love.adapter.PhotoPagerAdapter;
import com.yangpeng.love.entity.RecommandFooterValue;
import com.yangpeng.love.entity.RecommandHeadValue;
import com.yangpeng.love.utils.GlideUtil;
import com.yangpeng.love.view.viewpagerindictor.CirclePageIndicator;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * @author: vision
 * @function:
 * @date: 16/9/2
 */
public class HomeHeaderLayout extends RelativeLayout {

    private Context mContext;

    /**
     * UI
     */
    private RelativeLayout mRootView;
    private AutoScrollViewPager mViewPager;
    private CirclePageIndicator mPagerIndictor;
    private TextView mHotView;
    private PhotoPagerAdapter mAdapter;
    private ImageView[] mImageViews = new ImageView[4];
    private LinearLayout mFootLayout;

    /**
     * Data
     */
    private RecommandHeadValue mHeaderValue;


    public HomeHeaderLayout(Context context, RecommandHeadValue headerValue) {
        this(context, null, headerValue);
    }

    public HomeHeaderLayout(Context context, AttributeSet attrs, RecommandHeadValue headerValue) {
        super(context, attrs);
        mContext = context;
        mHeaderValue = headerValue;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = (RelativeLayout) inflater.inflate(R.layout.listview_home_head_layout, this);
        mViewPager =  mRootView.findViewById(R.id.pager);
        mPagerIndictor = mRootView.findViewById(R.id.pager_indictor_view);
        mHotView =  mRootView.findViewById(R.id.zuixing_view);
        mImageViews[0] =  mRootView.findViewById(R.id.head_image_one);
        mImageViews[1] =  mRootView.findViewById(R.id.head_image_two);
        mImageViews[2] =  mRootView.findViewById(R.id.head_image_three);
        mImageViews[3] =  mRootView.findViewById(R.id.head_image_four);
        mFootLayout = mRootView.findViewById(R.id.content_layout);

        mAdapter = new PhotoPagerAdapter(mContext, mHeaderValue.ads, true);
        mViewPager.setAdapter(mAdapter);
        mViewPager.startAutoScroll(3000);
        mPagerIndictor.setViewPager(mViewPager);

        /**
         * 加载4张图片
         */
        for (int i = 0; i < mImageViews.length; i++) {
            GlideUtil.loadImageView(mContext, mHeaderValue.middle.get(i), mImageViews[i]);
        }

        /**
         * 创建2个view
         */
        for (RecommandFooterValue value : mHeaderValue.footer) {
            mFootLayout.addView(createItem(value));
        }
        mHotView.setText(mContext.getString(R.string.today_zuixing));
    }

    private HomeBottomItem createItem(RecommandFooterValue value) {
        HomeBottomItem item = new HomeBottomItem(mContext, value);
        return item;
    }
}
