package com.aiyolabs.hashchat.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryPojo {
    public String categoryTitle;
    public List<MessagePojo> messagePojoList=new ArrayList<>();

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public List<MessagePojo> getMessagePojoList() {
        return messagePojoList;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public void setMessagePojoList(List<MessagePojo> messagePojoList) {
        this.messagePojoList = messagePojoList;
    }
}
