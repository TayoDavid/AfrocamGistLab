package com.example.afrocamgistlab.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AffectedEntity {

    @SerializedName("comment_count")
    @Expose
    private int commentCount;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

}
