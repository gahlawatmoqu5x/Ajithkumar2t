package com.aiyolabs.hashchat.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiyolabs.hashchat.R;
import com.aiyolabs.hashchat.model.MessagePojo;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Messageadapter extends RecyclerView.Adapter<Messageadapter.MessageViewHolder> {
    List<MessagePojo> msgList=new ArrayList<>();
    private Context mcontext;


    public Messageadapter(Context context,List<MessagePojo> msgList)
    {
        this.mcontext=context;
        this.msgList=msgList;
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_cardview,viewGroup,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int i) {
        if(msgList.get(i).getAnimation().equalsIgnoreCase("true"))
        {
            messageViewHolder.message_TV.setVisibility(View.GONE);
            messageViewHolder.senderName_TV.setVisibility(View.GONE);
            messageViewHolder.animationTextView.setVisibility(View.VISIBLE);
            messageViewHolder.animationSenderName.setVisibility(View.VISIBLE);
            messageViewHolder.animationSenderName.setText(msgList.get(i).getNumber());
            messageViewHolder.animationTextView.setText(msgList.get(i).getMessage());
        }else {
            messageViewHolder.message_TV.setVisibility(View.VISIBLE);
            messageViewHolder.animationTextView.setVisibility(View.GONE);
            messageViewHolder.animationSenderName.setVisibility(View.GONE);
            messageViewHolder.senderName_TV.setText(msgList.get(i).getNumber());
            messageViewHolder.message_TV.setText(msgList.get(i).getMessage());
        }




    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

     class MessageViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.sender_name)
        TextView senderName_TV;
        @BindView(R.id.message_TV)
        TextView message_TV;
        @BindView(R.id.animation_message_TV)
        TextView animationTextView;
        @BindView(R.id.animation_sender_name)
        TextView animationSenderName;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            YoYo.with(Techniques.FadeIn)
                    .duration(900)
                    .repeat(5)
                    .playOn(itemView.findViewById(R.id.animation_message_TV));
            YoYo.with(Techniques.FadeIn)
                    .duration(900)
                    .repeat(5)
                    .playOn(itemView.findViewById(R.id.animation_sender_name));
        }


    }
}
