package com.example.anwser_mac.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by anwser_mac on 2017/3/31.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    //定义一个内部类，ViewHolder继承自RecyclerView.ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitNme;
        //定义构造函数
        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitNme = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    //定义一个构造函数，把要展示的数据源传进来
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    /**因为ViewHolder是继承自RecyclerView.Adapter的，所以必须重写
     * onCreateViewHolder()：用于创建ViewHolder实例
     * onBindViewHolder()：用于对RecyclerView子项的数据进行赋值的，会在每个子项滚动到屏幕内的时候执行。
     * getItemCount()：用于告诉RecyclerView一共有多少个子项
     * 3个方法*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载fruit_item布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        //创建viewHolder实例，把加载的布局出入到构造函数中
        final ViewHolder holder = new ViewHolder(view);

        //注册点击事件
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "点击了背景视图"+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "点击了图片"+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //通过position参数得到当前的Fruit实例
        Fruit fruit = mFruitList.get(position);
        //把数据设置到ViewHolder中。
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitNme.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        //返回数据源的长度
        return mFruitList.size();
    }
}




