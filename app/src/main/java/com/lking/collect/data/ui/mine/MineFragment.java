package com.lking.collect.data.ui.mine;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lking.collect.data.R;

/**
 * @author : Lking
 *         CreateTime: 2017/9/18
 *         Email ： 1599863094@qq.com
 *         explain ：
 */
public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_fragment, container, false);
    }
}