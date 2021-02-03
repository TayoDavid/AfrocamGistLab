package com.example.afrocamgistlab.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment implements Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("post_id")
    @Expose
    private Integer postId;
    @SerializedName("comment_text")
    @Expose
    private String commentText;
    @SerializedName("commented_by")
    @Expose
    private User commentedBy;
    @SerializedName("comment_parent_id")
    @Expose
    private Integer commentParentId;
    @SerializedName("comment_date")
    @Expose
    private String commentDate;
    @SerializedName("comment_id")
    @Expose
    private Integer commentId;
    @SerializedName("sub_comments")
    @Expose
    private List<Object> subComments = null;
    @SerializedName("my_comment")
    @Expose
    private Boolean myComment;
    @SerializedName("liked")
    @Expose
    private Boolean liked;
    @SerializedName("comment_likes")
    @Expose
    private Integer commentLikes;

    public Comment() {
    }

    protected Comment(Parcel in) {
        id = in.readString();
        if (in.readByte() == 0) {
            postId = null;
        } else {
            postId = in.readInt();
        }
        commentText = in.readString();
        commentedBy = in.readParcelable(User.class.getClassLoader());
        if (in.readByte() == 0) {
            commentParentId = null;
        } else {
            commentParentId = in.readInt();
        }
        commentDate = in.readString();
        if (in.readByte() == 0) {
            commentId = null;
        } else {
            commentId = in.readInt();
        }
        byte tmpMyComment = in.readByte();
        myComment = tmpMyComment == 0 ? null : tmpMyComment == 1;
        byte tmpLiked = in.readByte();
        liked = tmpLiked == 0 ? null : tmpLiked == 1;
        if (in.readByte() == 0) {
            commentLikes = null;
        } else {
            commentLikes = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        if (postId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(postId);
        }
        dest.writeString(commentText);
        dest.writeParcelable(commentedBy, flags);
        if (commentParentId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(commentParentId);
        }
        dest.writeString(commentDate);
        if (commentId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(commentId);
        }
        dest.writeByte((byte) (myComment == null ? 0 : myComment ? 1 : 2));
        dest.writeByte((byte) (liked == null ? 0 : liked ? 1 : 2));
        if (commentLikes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(commentLikes);
        }
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public User getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(User commentedBy) {
        this.commentedBy = commentedBy;
    }

    public Integer getCommentParentId() {
        return commentParentId;
    }

    public void setCommentParentId(Integer commentParentId) {
        this.commentParentId = commentParentId;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public List<Object> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<Object> subComments) {
        this.subComments = subComments;
    }

    public Boolean getMyComment() {
        return myComment;
    }

    public void setMyComment(Boolean myComment) {
        this.myComment = myComment;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Integer getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Integer commentLikes) {
        this.commentLikes = commentLikes;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", postId=" + postId +
                ", commentText='" + commentText + '\'' +
                ", commentedBy=" + commentedBy +
                ", commentParentId=" + commentParentId +
                ", commentDate='" + commentDate + '\'' +
                ", commentId=" + commentId +
                ", subComments=" + subComments +
                ", myComment=" + myComment +
                ", liked=" + liked +
                ", commentLikes=" + commentLikes +
                '}';
    }
}
