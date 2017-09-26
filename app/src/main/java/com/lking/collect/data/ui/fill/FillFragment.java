package com.lking.collect.data.ui.fill;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lking.collect.data.R;
import com.lking.collect.data.ui.util.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Lking
 *         CreateTime: 2017/9/18
 *         Email ： 1599863094@qq.com
 *         explain ：
 */
public class FillFragment extends Fragment implements View.OnClickListener{
    private MyGridView mMyGridView;
    // 图片封装为一个数组
    private int[] icon = { R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};
    private String[] iconName = { "通讯录", "日历", "照相机",
            "时钟", "游戏", "短信",
            "铃声","设置", "语音",
            "天气"};
    private List<Map<String, Object>> mDataList;
    private SimpleAdapter mSimpleAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fill_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String [] from ={"image","text"};
        int [] to = {R.id.mygridview_img,R.id.mygridview_txt};
        mMyGridView = view.findViewById(R.id.fill_fragment_gv);
        mSimpleAdapter = new SimpleAdapter(getActivity(),getData(),R.layout.mygridview_item,from,to);
        mMyGridView.setAdapter(mSimpleAdapter);
        mMyGridView.setOnItemClickListener(new MyListener());
    }

    @Override
    public void onClick(View view) {

    }

    public List<Map<String, Object>> getData() {
        mDataList = new ArrayList<Map<String, Object>>();
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            mDataList.add(map);
        }
        return mDataList;
    }

    class MyListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(getActivity(),FillActivity.class);
            intent.putExtra("position",position);
            getActivity().startActivity(intent);
            Log.e("LKing","position = "+position);
        }
    }
}
