package com.example.afrocamgistlab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afrocamgistlab.adapters.CommentRecyclerViewAdapter;
import com.example.afrocamgistlab.model.CommentResponse;
import com.example.afrocamgistlab.model.EditComment;
import com.example.afrocamgistlab.model.PostComment;
import com.example.afrocamgistlab.service.APIService;
import com.example.afrocamgistlab.utils.ApiClient;
import com.example.afrocamgistlab.utils.SingleApiResponse;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private final List<CommentResponse> comments = new ArrayList<>();
    private CommentRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar commentToolbar = findViewById(R.id.comment_toolbar);
        commentToolbar.setTitle("Comment");
        setSupportActionBar(commentToolbar);

        adapter = new CommentRecyclerViewAdapter(comments);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        int postId = intent.getIntExtra("postId", 0);
        ArrayList<CommentResponse> commentsFromIntent = intent.getParcelableArrayListExtra("commentList");
        if (commentsFromIntent != null) {
            comments.addAll(commentsFromIntent);
            adapter.addUpdate(comments);
            Log.i("COMMENTS FROM INTENT", comments.toString());
        }
        int commentId = intent.getIntExtra("commentId", 0);
        String commentTextFromIntent = intent.getStringExtra("commentText");

        RecyclerView commentRecycleView = findViewById(R.id.lst_comments);
        TextView addComment = findViewById(R.id.add_comment);
        TextView updateComment = findViewById(R.id.update_comment);
        EditText commentText = findViewById(R.id.edt_add_comment);
        progressBar = findViewById(R.id.comment_progress_bar);

        if (commentTextFromIntent != null) {
            commentText.setText(commentTextFromIntent);
            addComment.setVisibility(View.GONE);
            updateComment.setVisibility(View.VISIBLE);
        }

        commentRecycleView.setLayoutManager(new LinearLayoutManager(this));
        commentRecycleView.setAdapter(adapter);

        APIService apiService = ApiClient.getRetrofit().create(APIService.class);

        addComment.setOnClickListener(v -> {
            String userComment = commentText.getText().toString().trim();
            PostComment comment = new PostComment(postId, userComment);

            Call<SingleApiResponse<CommentResponse>> getPostsCall = apiService.addComment(comment);

            if (!userComment.isEmpty())  {
                progressBar.setVisibility(View.VISIBLE);
                getPostsCall.enqueue(new Callback<SingleApiResponse<CommentResponse>>() {
                    @Override
                    public void onResponse(@NotNull Call<SingleApiResponse<CommentResponse>> call, @NotNull Response<SingleApiResponse<CommentResponse>> response) {
                        if (response.code() == 200) {
                            if (response.body().getData() != null) {
                                comments.add(response.body().getData());
                                adapter.addUpdate(comments);
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                commentText.setText("");
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<SingleApiResponse<CommentResponse>> call, @NotNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            } else {
                LinearLayout linearLayout = findViewById(R.id.comment_activity_layout);
                Snackbar.make(linearLayout, "Comment text can not be blank!", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });

        updateComment.setOnClickListener(v -> {
            String userUpdatedComment = commentText.getText().toString().trim();

            Call<SingleApiResponse<CommentResponse>> updateCommentCall = apiService.updateComment(commentId, new EditComment(userUpdatedComment));
            if (!userUpdatedComment.isEmpty())  {
                progressBar.setVisibility(View.VISIBLE);
                updateCommentCall.enqueue(new Callback<SingleApiResponse<CommentResponse>>() {
                    @Override
                    public void onResponse(@NotNull Call<SingleApiResponse<CommentResponse>> call, @NotNull Response<SingleApiResponse<CommentResponse>> response) {
                        if (response.code() == 200) {
                            if (response.body().getStatus()) {
                                comments.add(response.body().getData());
                                adapter.addUpdate(comments);
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                commentText.setText("");
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<SingleApiResponse<CommentResponse>> call, @NotNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            } else {
                LinearLayout linearLayout = findViewById(R.id.comment_activity_layout);
                Snackbar.make(linearLayout, "Comment text can not be blank!", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });

    }

}