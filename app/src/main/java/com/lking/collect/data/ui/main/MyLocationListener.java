package com.lking.collect.data.ui.main;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

/**
 * @author : Lking
 *         CreateTime: 2017/9/25
 *         Email ： 1599863094@qq.com
 *         explain ：
 */
public class MyLocationListener implements BDLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {
        Log.e("LKing","定位type = "+location.getLocType());
        Log.e("LKing","定位时间 = "+location.getTime());
        Log.e("LKing","定位纬度 = "+location.getLatitude());
        Log.e("LKing","定位经度 = "+location.getLongitude());
        Log.e("LKing","定位精准度 = "+location.getRadius());

        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            Log.e("LKing","当前为GPS定位--------------");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            Log.e("LKing","当前为网络定位--------------");

        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            Log.e("LKing","当前为离线定位--------------");

        } else if (location.getLocType() == BDLocation.TypeServerError) {
            Log.e("LKing","当前定位失败--------------");

        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            Log.e("LKing","当前网络不通--------------");

        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            Log.e("LKing","当前为手机处于飞行模式，可以试着重启手机--------------");

        }

    }
}
