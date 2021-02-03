package com.example.afrocamgistlab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostComment {

    @SerializedName("post_id")
    @Expose
    private Integer postId;
    @SerializedName("comment_text")
    @Expose
    private String commentText;

    public PostComment(int postId, String commentText) {
        this.postId = postId;
        this.commentText = commentText;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
