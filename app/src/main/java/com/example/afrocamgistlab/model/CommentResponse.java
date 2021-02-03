package com.example.afrocamgistlab.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentResponse implements Parcelable {

    @SerializedName("post_id")
    @Expose
    private int postId;
    @SerializedName("comment_text")
    @Expose
    private String commentText;
    @SerializedName("story_id")
    @Expose
    private int storyId;
    @SerializedName("commented_by")
    @Expose
    private int commentedBy;
    @SerializedName("comment_parent_id")
    @Expose
    private int commentParentId;
    @SerializedName("comment_date")
    @Expose
    private String commentDate;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;
    @SerializedName("comment_id")
    @Expose
    private int commentId;
    @SerializedName("_id")
    @Expose
    private String id;

    public CommentResponse(int postId, String commentText, int commentedBy, int commentId) {
        this.postId = postId;
        this.commentText = commentText;
        this.commentedBy = commentedBy;
        this.commentId = commentId;
    }

    protected CommentResponse(Parcel in) {
        postId = in.readInt();
        commentText = in.readString();
        storyId = in.readInt();
        commentedBy = in.readInt();
        commentParentId = in.readInt();
        commentDate = in.readString();
        commentStatus = in.readString();
        commentId = in.readInt();
        id = in.readString();
    }

    public static final Creator<CommentResponse> CREATOR = new Creator<CommentResponse>() {
        @Override
        public CommentResponse createFromParcel(Parcel in) {
            return new CommentResponse(in);
        }

        @Override
        public CommentResponse[] newArray(int size) {
            return new CommentResponse[size];
        }
    };

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

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(int commentedBy) {
        this.commentedBy = commentedBy;
    }

    public int getCommentParentId() {
        return commentParentId;
    }

    public void setCommentParentId(int commentParentId) {
        this.commentParentId = commentParentId;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CommentResponse{" +
                "postId=" + postId +
                ", commentText='" + commentText + '\'' +
                ", storyId=" + storyId +
                ", commentedBy=" + commentedBy +
                ", commentParentId=" + commentParentId +
                ", commentDate='" + commentDate + '\'' +
                ", commentStatus='" + commentStatus + '\'' +
                ", commentId=" + commentId +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(postId);
        dest.writeString(commentText);
        dest.writeInt(storyId);
        dest.writeInt(commentedBy);
        dest.writeInt(commentParentId);
        dest.writeString(commentDate);
        dest.writeString(commentStatus);
        dest.writeInt(commentId);
        dest.writeString(id);
    }
}
