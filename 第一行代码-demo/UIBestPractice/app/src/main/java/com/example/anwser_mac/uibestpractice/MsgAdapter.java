package com.example.anwser_mac.uibestpractice;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anwser_mac on 2017/4/1.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.Viewholder> {

    private List<Msg> mMsgList;

    static class Viewholder extends RecyclerView.ViewHolder {

        android.widget.LinearLayout leftLayout;

        android.widget.LinearLayout rightLayout;

        TextView leftMsg;

        TextView rightMsg;

        public Viewholder(View view) {
            super(view);
            leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            leftMsg = (TextView) view.findViewById(R.id.left_msg);
            rightMsg = (TextView) view.findViewById(R.id.right_msg);
        }
    }


    public MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    @Override
    public MsgAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(MsgAdapter.Viewholder holder, int position) {
        Msg msg = mMsgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            //如果是收到的消息，则显示在左边的消息布局，将右边的消息布局隐藏
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        } else if(msg.getType() == Msg.TYPE_SENT) {
            //如果是发出的消息，则显示在右边的消息布局，将左边的消息布局隐藏
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
