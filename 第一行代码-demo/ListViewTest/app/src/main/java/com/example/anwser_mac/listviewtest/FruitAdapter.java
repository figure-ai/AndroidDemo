package com.example.anwser_mac.listviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anwser_mac on 2017/3/31.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;
    //重写构造方法，将上下文、ListView子项布局的ID和数据都传进来
    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    //重写getView()方法，这个方法在子项被滚动屏幕内时调用
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取当前项的Fruit实例
        Fruit fruit = getItem(position);
        View view;
        //创建一个viewHolder来缓存控件，这样就不用每次加载的时候都要调用findViewById()来获得控件的实例了
        ViewHodler viewHolder;
        // 加载布局
        if (convertView == null) {
            //创建
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHodler();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            //把viewHolder存入view中
            view.setTag(viewHolder);
        } else {
            view = convertView;
            //重新取出viewHolder
            viewHolder = (ViewHodler) view.getTag();
        }
        //设置布局显示的数据
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;

    }
}

class ViewHodler {

    ImageView fruitImage;

    TextView fruitName;
}
