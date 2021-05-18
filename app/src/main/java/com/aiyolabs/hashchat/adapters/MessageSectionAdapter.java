package com.aiyolabs.hashchat.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiyolabs.hashchat.R;
import com.aiyolabs.hashchat.model.CategoryPojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MessageSectionAdapter extends RecyclerView.Adapter<MessageSectionAdapter.MessageSectionViewHolder> {
    List<CategoryPojo> categoryList=new ArrayList<>();
    private Context mContext;
    public MessageSectionAdapter(Context context, List<CategoryPojo> list)
    {
        this.mContext=context;
        this.categoryList=list;
    }

    @NonNull
    @Override
    public MessageSectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_section_cardview,viewGroup,false);
        return new MessageSectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageSectionViewHolder messageSectionViewHolder, int i) {

        messageSectionViewHolder.tvTitle.setText(categoryList.get(i).getCategoryTitle());
        if(categoryList.get(i).getMessagePojoList().isEmpty())
        {
            messageSectionViewHolder.noMessage.setVisibility(View.VISIBLE);
            messageSectionViewHolder.noMessage.setText("Currently No Messages Available");
        }else {
            messageSectionViewHolder.noMessage.setVisibility(View.GONE);
        }

        Messageadapter messageadapter=new Messageadapter(mContext,categoryList.get(i).getMessagePojoList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        messageSectionViewHolder.categoryRecycler.setHasFixedSize(true);
        messageSectionViewHolder.categoryRecycler.setLayoutManager(linearLayoutManager);
        messageSectionViewHolder.categoryRecycler.setAdapter(messageadapter);


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

     class MessageSectionViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.categoryTitle)
        TextView tvTitle;

        @BindView(R.id.message_recycler)
        RecyclerView categoryRecycler;

        @BindView((R.id.no_message_TV))
        TextView noMessage;
        public MessageSectionViewHolder(@NonNull View itemView) {
            super(itemView) ;
            ButterKnife.bind(this, itemView);
        }
    }
}
