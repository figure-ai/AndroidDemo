package com.example.anwser_mac.fragmenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anwser_mac on 2017/4/6.
 */

public class LeftFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //重写onCreateView方法，然后通过infalte()方法将刚才定义的left_fragment布局加载进来
        View view = inflater.inflate(R.layout.left_fragmen, container, false);
        return view;
    }
}
