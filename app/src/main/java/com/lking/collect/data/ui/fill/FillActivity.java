package com.lking.collect.data.ui.fill;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lking.collect.data.R;

/**
 * @author : Lking
 *         CreateTime: 2017/9/18
 *         Email ： 1599863094@qq.com
 *         explain ：
 */
public class FillActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_activity_layout);

        int position = getIntent().getExtras().getInt("position",0);
        Log.e("LKing","positionfrgfg = "+position);

    }
}
