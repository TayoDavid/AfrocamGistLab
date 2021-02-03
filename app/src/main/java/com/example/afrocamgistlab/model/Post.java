package com.example.afrocamgistlab.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post implements Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("post_text")
    @Expose
    private String postText;
    @SerializedName("posted_for")
    @Expose
    private String postedFor;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("posted_by")
    @Expose
    private Integer postedBy;
    @SerializedName("post_location")
    @Expose
    private String postLocation;
    @SerializedName("post_type")
    @Expose
    private String postType;
    @SerializedName("post_images")
    @Expose
    private List<String> postImage;
    @SerializedName("post_video")
    @Expose
    private String postVideo;
    @SerializedName("like_count")
    @Expose
    private Integer likeCount;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("post_date")
    @Expose
    private String postDate;
    @SerializedName("post_privacy")
    @Expose
    private String postPrivacy;
    @SerializedName("post_id")
    @Expose
    private Integer postId;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = null;
    @SerializedName("liked_by")
    @Expose
    private List<User> likedBy = null;
    @SerializedName("bg_image_post")
    @Expose
    private Boolean bgImagePost;
    @SerializedName("bg_map_post")
    @Expose
    private Boolean bgMapPost;
    @SerializedName("shared_post_data")
    @Expose
    private Post sharedPostData;
    @SerializedName("liked")
    @Expose
    private Boolean liked;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("profile_image_url")
    @Expose
    private String profileImageUrl;
    @SerializedName("following")
    @Expose
    private Boolean following;
    @SerializedName("enable_follow")
    @Expose
    private Boolean enableFollow;
    @SerializedName("requestedUser")
    @Expose
    private Boolean requestedUser;

    public Post() {
    }

    protected Post(Parcel in) {
        id = in.readString();
        postText = in.readString();
        postedFor = in.readString();
        groupId = in.readString();
        if (in.readByte() == 0) {
            postedBy = null;
        } else {
            postedBy = in.readInt();
        }
        postLocation = in.readString();
        postType = in.readString();
        if (in.readByte() == 0) {
            likeCount = null;
        } else {
            likeCount = in.readInt();
        }
        if (in.readByte() == 0) {
            commentCount = null;
        } else {
            commentCount = in.readInt();
        }
        postDate = in.readString();
        postPrivacy = in.readString();
        if (in.readByte() == 0) {
            postId = null;
        } else {
            postId = in.readInt();
        }
        comments = in.createTypedArrayList(Comment.CREATOR);
        likedBy = in.createTypedArrayList(User.CREATOR);
        byte tmpBgImagePost = in.readByte();
        bgImagePost = tmpBgImagePost == 0 ? null : tmpBgImagePost == 1;
        byte tmpBgMapPost = in.readByte();
        bgMapPost = tmpBgMapPost == 0 ? null : tmpBgMapPost == 1;
        sharedPostData = in.readParcelable(Post.class.getClassLoader());
        byte tmpLiked = in.readByte();
        liked = tmpLiked == 0 ? null : tmpLiked == 1;
        firstName = in.readString();
        lastName = in.readString();
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        userName = in.readString();
        byte tmp_private = in.readByte();
        _private = tmp_private == 0 ? null : tmp_private == 1;
        profileImageUrl = in.readString();
        byte tmpFollowing = in.readByte();
        following = tmpFollowing == 0 ? null : tmpFollowing == 1;
        byte tmpEnableFollow = in.readByte();
        enableFollow = tmpEnableFollow == 0 ? null : tmpEnableFollow == 1;
        byte tmpRequestedUser = in.readByte();
        requestedUser = tmpRequestedUser == 0 ? null : tmpRequestedUser == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(postText);
        dest.writeString(postedFor);
        dest.writeString(groupId);
        if (postedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(postedBy);
        }
        dest.writeString(postLocation);
        dest.writeString(postType);
        if (likeCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(likeCount);
        }
        if (commentCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(commentCount);
        }
        dest.writeString(postDate);
        dest.writeString(postPrivacy);
        if (postId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(postId);
        }
        dest.writeTypedList(comments);
        dest.writeTypedList(likedBy);
        dest.writeByte((byte) (bgImagePost == null ? 0 : bgImagePost ? 1 : 2));
        dest.writeByte((byte) (bgMapPost == null ? 0 : bgMapPost ? 1 : 2));
        dest.writeParcelable(sharedPostData, flags);
        dest.writeByte((byte) (liked == null ? 0 : liked ? 1 : 2));
        dest.writeString(firstName);
        dest.writeString(lastName);
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(userName);
        dest.writeByte((byte) (_private == null ? 0 : _private ? 1 : 2));
        dest.writeString(profileImageUrl);
        dest.writeByte((byte) (following == null ? 0 : following ? 1 : 2));
        dest.writeByte((byte) (enableFollow == null ? 0 : enableFollow ? 1 : 2));
        dest.writeByte((byte) (requestedUser == null ? 0 : requestedUser ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @SerializedName("request_buttons")

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostedFor() {
        return postedFor;
    }

    public void setPostedFor(String postedFor) {
        this.postedFor = postedFor;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(String postLocation) {
        this.postLocation = postLocation;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostPrivacy() {
        return postPrivacy;
    }

    public void setPostPrivacy(String postPrivacy) {
        this.postPrivacy = postPrivacy;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

    public Boolean getBgImagePost() {
        return bgImagePost;
    }

    public void setBgImagePost(Boolean bgImagePost) {
        this.bgImagePost = bgImagePost;
    }

    public Boolean getBgMapPost() {
        return bgMapPost;
    }

    public void setBgMapPost(Boolean bgMapPost) {
        this.bgMapPost = bgMapPost;
    }

    public Post getSharedPostData() {
        return sharedPostData;
    }

    public List<String> getPostImage() {
        return postImage;
    }

    public void setPostImage(List<String> postImage) {
        this.postImage = postImage;
    }

    public String getPostVideo() {
        return postVideo;
    }

    public void setPostVideo(String postVideo) {
        this.postVideo = postVideo;
    }

    public void setSharedPostData(Post sharedPostData) {
        this.sharedPostData = sharedPostData;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public Boolean getEnableFollow() {
        return enableFollow;
    }

    public void setEnableFollow(Boolean enableFollow) {
        this.enableFollow = enableFollow;
    }

    public Boolean getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(Boolean requestedUser) {
        this.requestedUser = requestedUser;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", postText='" + postText + '\'' +
                ", postedFor='" + postedFor + '\'' +
                ", groupId='" + groupId + '\'' +
                ", postedBy=" + postedBy +
                ", postLocation='" + postLocation + '\'' +
                ", postType='" + postType + '\'' +
                ", postImage=" + postImage +
                ", postVideo='" + postVideo + '\'' +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", postDate='" + postDate + '\'' +
                ", postPrivacy='" + postPrivacy + '\'' +
                ", postId=" + postId +
                ", comments=" + comments +
                ", likedBy=" + likedBy +
                ", bgImagePost=" + bgImagePost +
                ", bgMapPost=" + bgMapPost +
                ", sharedPostData=" + sharedPostData +
                ", liked=" + liked +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", _private=" + _private +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", following=" + following +
                ", enableFollow=" + enableFollow +
                ", requestedUser=" + requestedUser +
                '}';
    }
}
