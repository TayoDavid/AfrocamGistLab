package com.example.afrocamgistlab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditComment {

    @SerializedName("comment_text")
    @Expose
    private String commentText;

    public EditComment(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
