package com.example.afrocamgistlab.adapters;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.afrocamgistlab.CommentActivity;
import com.example.afrocamgistlab.R;
import com.example.afrocamgistlab.listener.OnItemClickListener;
import com.example.afrocamgistlab.model.Comment;
import com.example.afrocamgistlab.model.CommentResponse;
import com.example.afrocamgistlab.model.Post;
import com.example.afrocamgistlab.service.APIService;
import com.example.afrocamgistlab.utils.ApiClient;
import com.example.afrocamgistlab.utils.SingleApiResponse;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> {

    private List<Post> posts;
    private OnItemClickListener<Post> listener;

    public PostRecyclerViewAdapter(List<Post> items) {
        posts = items;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Gson gs = new Gson();
        String js = gs.toJson(posts.get(position));
        Post post = gs.fromJson(js, Post.class);

        APIService apiService = ApiClient.getRetrofit().create(APIService.class);

        holder.mPostAuthor.setText(post.getFirstName());
        holder.mAuthorDescription.setText(post.getPostLocation());
//        holder.mUserAvatar.setImageURI(Uri.parse(ApiClient.BASE_URL + post.getProfileImageUrl()));
        holder.mLikeCount.setText(String.valueOf(post.getLikeCount()));
        holder.mCommentCount.setText(String.valueOf(post.getCommentCount()));
        holder.mPostText.setText(post.getPostText());
        String baseUrl = ApiClient.BASE_URL;

        switch (post.getPostType()) {
            case "video":
                if (post.getPostVideo() != null) {
                    holder.mPostVideo.setVideoURI(Uri.parse(baseUrl + post.getPostVideo()));
                    holder.mPostImage.setVisibility(View.GONE);
                    holder.mPostVideo.setVisibility(View.VISIBLE);
                }
                break;
            case "image":
                if (post.getPostImage() != null) {
                    holder.mPostImage.setImageURI(Uri.parse(baseUrl + post.getPostImage().get(0)));
                }
                break;
            default:
                // Todo Shared
                break;
        }
        holder.mPostReport.setOnClickListener(v -> {
            Call<SingleApiResponse<String>> reportCall = apiService.reportPost(post.getPostId());
            reportCall.enqueue(new Callback<SingleApiResponse<String>>() {
                @Override
                public void onResponse(@NotNull Call<SingleApiResponse<String>> call, @NotNull Response<SingleApiResponse<String>> response) {
                    Log.i("RESPONSE", response.body().toString());
                    if (response.code() == 200) {
                        Toast.makeText(v.getContext(), response.body().getData(), LENGTH_LONG).show();
                    } else
                        Toast.makeText(v.getContext(), "Nothing happened!", LENGTH_LONG).show();
                }

                @Override
                public void onFailure(@NotNull Call<SingleApiResponse<String>> call, @NotNull Throwable t) {
                    t.printStackTrace();
                }
            });
        });

        holder.mPostCommentIcon.setOnClickListener(v -> {
            Intent commentIntent = new Intent(v.getContext(), CommentActivity.class);
            List<Comment> comments = post.getComments();
            ArrayList<CommentResponse> commentsForIntent = new ArrayList<>();
            for (Comment comment : comments) {
                commentsForIntent.add(new CommentResponse(comment.getPostId(), comment.getCommentText(),
                        comment.getCommentedBy().getUserId(), comment.getCommentId()));
            }
            commentIntent.putExtra("postId", post.getPostId());
            commentIntent.putParcelableArrayListExtra("commentList", commentsForIntent);
            v.getContext().startActivity(commentIntent);
        });
    }

    @Override
    public int getItemCount() {
        return posts != null ? posts.size() : 0;
    }

    public void attachListener(OnItemClickListener<Post> listener) {
        this.listener = listener;
    }

    public void addUpdate(List<Post> posts) {
        this.posts = posts;
        this.notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mPostAuthor;
        public final TextView mAuthorDescription;
        public final ImageView mPostImage;
        public final VideoView mPostVideo;
        public final ImageView mUserAvatar;
        public final TextView mPostText;
        public final ImageView mPostLikeIcon;
        public final TextView mLikeCount;
        public final ImageView mPostShare;
        public final ImageView mPostReport;
        public final ImageView mPostCommentIcon;
        public final TextView mCommentCount;
        public final EditText mAddComment;
        public final Button mSendComment;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mPostAuthor = itemView.findViewById(R.id.post_author);
            mAuthorDescription = itemView.findViewById(R.id.author_description);
            mPostImage = itemView.findViewById(R.id.post_image);
            mPostVideo = itemView.findViewById(R.id.post_video);
            mUserAvatar = itemView.findViewById(R.id.user_avatar);
            mPostText = itemView.findViewById(R.id.post_text);
            mPostLikeIcon = itemView.findViewById(R.id.post_like);
            mLikeCount = itemView.findViewById(R.id.like_count);
            mPostShare = itemView.findViewById(R.id.post_share);
            mPostReport = itemView.findViewById(R.id.post_report);
            mPostCommentIcon = itemView.findViewById(R.id.post_comment);
            mCommentCount = itemView.findViewById(R.id.comment_count);
            mAddComment = itemView.findViewById(R.id.edt_post_comment);
            mSendComment = itemView.findViewById(R.id.add_comment);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPostText.getText() + "'";
        }
    }
}