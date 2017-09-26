package com.lking.collect.data.ui.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lking.collect.data.R;
import com.lking.collect.data.ui.fill.FillFragment;
import com.lking.collect.data.ui.mine.MineFragment;
import com.lking.collect.data.ui.query.QueryFragment;
import com.lking.collect.data.ui.synchronous.SynchronousFragment;

public class MainActivity extends Activity implements View.OnClickListener{
    private TextView mTitleTxt;
    private RelativeLayout mFillLayout;
    private ImageView mFillImg;
    private TextView mFillTxt;
    private RelativeLayout mSynchronousLayout;
    private ImageView mSynchronousImg;
    private TextView mSynchronousTxt;
    private RelativeLayout mQueryLayout;
    private ImageView mQueryImg;
    private TextView mQueryTxt;
    private RelativeLayout mMineLayout;
    private ImageView mMineImg;
    private TextView mMineTxt;

    private FillFragment mFillFragment;
    private SynchronousFragment mSynchronousFragment;
    private QueryFragment mQueryFragment;
    private MineFragment mMineFragment;

    /** 百度定位获取*/
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLocate();


        //注册监听函数
        initViews();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mTitleTxt = (TextView)findViewById(R.id.main_title);
        mFillLayout = (RelativeLayout) findViewById(R.id.main_fill_layout);
        mFillImg = (ImageView) findViewById(R.id.main_fill_img);
        mFillTxt = (TextView) findViewById(R.id.main_fill_txt);
        mSynchronousLayout = (RelativeLayout) findViewById(R.id.main_synchronous_layout);
        mSynchronousImg = (ImageView) findViewById(R.id.main_synchronous_img);
        mSynchronousTxt = (TextView) findViewById(R.id.main_synchronous_txt);
        mQueryLayout = (RelativeLayout) findViewById(R.id.main_query_layout);
        mQueryImg = (ImageView) findViewById(R.id.main_query_img);
        mQueryTxt = (TextView) findViewById(R.id.main_query_txt);
        mMineLayout = (RelativeLayout) findViewById(R.id.main_mine_layout);
        mMineImg = (ImageView) findViewById(R.id.main_mine_img);
        mMineTxt = (TextView) findViewById(R.id.main_mine_txt);
        mFillLayout.setOnClickListener(this);
        mSynchronousLayout.setOnClickListener(this);
        mQueryLayout.setOnClickListener(this);
        mMineLayout.setOnClickListener(this);
        setBottomView(true,false,false,false);
        setDefalutFragment();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (view.getId()){
            case R.id.main_fill_layout:
                mTitleTxt.setText("First");
                if(null == mFillFragment){
                    mFillFragment = new FillFragment();
                }
                transaction.replace(R.id.main_content, mFillFragment);
                transaction.commit();
                setBottomView(true,false,false,false);
                break;
            case R.id.main_synchronous_layout:
                mTitleTxt.setText("Second");
                if(null == mSynchronousFragment){
                    mSynchronousFragment = new SynchronousFragment();
                }
                transaction.replace(R.id.main_content, mSynchronousFragment);
                transaction.commit();
                setBottomView(false,true,false,false);
                break;
            case R.id.main_query_layout:
                mTitleTxt.setText("Third");
                if(null == mQueryFragment){
                    mQueryFragment = new QueryFragment();
                }
                transaction.replace(R.id.main_content, mQueryFragment);
                transaction.commit();
                setBottomView(false,false,true,false);
                break;
            case R.id.main_mine_layout:
                mTitleTxt.setText("Fourth");
                if(null == mMineFragment){
                    mMineFragment = new MineFragment();
                }
                transaction.replace(R.id.main_content, mMineFragment);
                transaction.commit();
                setBottomView(false,false,false,true);
                break;
            default:
                break;
        }
    }

    /**
     * 设置底部按钮
     * @param isFill
     * @param isSynchronous
     * @param isQuery
     * @param isMine
     */
    private void setBottomView(boolean isFill, boolean isSynchronous, boolean isQuery, boolean isMine) {
        mFillImg.setSelected(isFill);
        mFillTxt.setSelected(isFill);

        mSynchronousImg.setSelected(isSynchronous);
        mSynchronousTxt.setSelected(isSynchronous);

        mQueryImg.setSelected(isQuery);
        mQueryTxt.setSelected(isQuery);

        mMineImg.setSelected(isMine);
        mMineTxt.setSelected(isMine);
    }

    /**
     * 设置默认Fragment
     */
    private void setDefalutFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mFillFragment = new FillFragment();
        transaction.replace(R.id.main_content, mFillFragment);
        transaction.commit();
    }

    /**
     * 定位
     */
    private void startLocate() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 5000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        //开启定位
        mLocationClient.start();
    }
}










































