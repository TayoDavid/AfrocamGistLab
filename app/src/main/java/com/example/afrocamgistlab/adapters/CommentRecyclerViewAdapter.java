package com.example.afrocamgistlab.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afrocamgistlab.CommentActivity;
import com.example.afrocamgistlab.R;
import com.example.afrocamgistlab.model.CommentResponse;
import com.example.afrocamgistlab.service.APIService;
import com.example.afrocamgistlab.utils.ApiClient;
import com.example.afrocamgistlab.utils.StatusResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {

    private List<CommentResponse> mComments;
    private ProgressBar progressBar;

    public CommentRecyclerViewAdapter(List<CommentResponse> mComments) {
        this.mComments = mComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View commentView = inflater.inflate(R.layout.comment_item, parent, false);
        progressBar = parent.findViewById(R.id.comment_progress_bar);
        return new ViewHolder(commentView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentResponse currentComment = mComments.get(position);
        if (currentComment != null) {
            holder.mCommentText.setText(currentComment.getCommentText());
            holder.mUserName.setText(String.valueOf(currentComment.getCommentedBy()));
        }

        APIService apiService = ApiClient.getRetrofit().create(APIService.class);

        holder.mTxtOption.setOnClickListener(view -> {

            PopupMenu popupMenu = new PopupMenu(view.getContext(), holder.mTxtOption);
            // Inflating the popup menu
            popupMenu.inflate(R.menu.comment_menu);

            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.delete_comment_menu) {
                    if (currentComment != null) {
                        Call<StatusResponse> deleteCommentCall = apiService.deleteComment(currentComment.getCommentId());
                        deleteCommentCall.enqueue(new Callback<StatusResponse>() {
                            @Override
                            public void onResponse(@NotNull Call<StatusResponse> call, @NotNull Response<StatusResponse> response) {
                                if (response.code() == 200) {
                                    mComments.remove(position);
                                    notifyItemRemoved(position);
                                }
                            }
                            @Override
                            public void onFailure(@NotNull Call<StatusResponse> call, @NotNull Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    } else {
                        Toast.makeText(view.getContext(), "Nothing to delete bro!", Toast.LENGTH_LONG).show();
                        view.getContext().startActivity(new Intent(view.getContext(), CommentActivity.class));
                    }
                } else if (itemId == R.id.edit_comment_menu) {
                    String commentText = currentComment.getCommentText();
                    int commentId = currentComment.getCommentId();

                    mComments.remove(position);
                    notifyItemRemoved(position);
                    addUpdate(mComments);

                    Intent editCommentActivity = new Intent(view.getContext(), CommentActivity.class);
                    editCommentActivity.putExtra("commentId", commentId);
                    editCommentActivity.putExtra("commentText", commentText);

                    view.getContext().startActivity(editCommentActivity);
                }

                return false;
            });

            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return mComments != null ? mComments.size() : 0;
    }

    public void addUpdate(List<CommentResponse> commentResponses) {
        this.mComments = commentResponses;
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mUserImage;
        public final TextView mUserName;
        public final TextView mCommentText;
        public final TextView mTxtCommentLike;
        public final TextView mTxtCommentReply;
        public final TextView mTxtOption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mView = itemView;
            this.mUserImage = itemView.findViewById(R.id.img_profile);
            this.mUserName = itemView.findViewById(R.id.txt_user_name);
            this.mCommentText = itemView.findViewById(R.id.txt_comment_text);
            this.mTxtCommentLike = itemView.findViewById(R.id.txt_comment_like);
            this.mTxtCommentReply = itemView.findViewById(R.id.txt_comment_reply);
            this.mTxtOption = itemView.findViewById(R.id.txt_comment_option);
        }
    }
}
