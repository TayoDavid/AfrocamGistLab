package com.example.afrocamgistlab.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afrocamgistlab.R;
import com.example.afrocamgistlab.listener.OnItemClickListener;
import com.example.afrocamgistlab.model.Post;
import com.example.afrocamgistlab.service.APIService;
import com.example.afrocamgistlab.utils.ApiClient;
import com.example.afrocamgistlab.utils.ApiResponse;
import com.example.afrocamgistlab.adapters.PostRecyclerViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AfroswaggerFragment extends Fragment implements OnItemClickListener<Post> {

    private final List<Post> posts = new ArrayList<>();
    private PostRecyclerViewAdapter postRecyclerViewAdapter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        postRecyclerViewAdapter = new PostRecyclerViewAdapter(posts);
        postRecyclerViewAdapter.attachListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(postRecyclerViewAdapter);

        APIService apiService = ApiClient.getRetrofit().create(APIService.class);

        Call<ApiResponse<Post>> getPostsCall = apiService.getAfroswaggerPosts();
        getPostsCall.enqueue(new Callback<ApiResponse<Post>>() {
            @Override
            public void onResponse(@NotNull Call<ApiResponse<Post>> call, @NotNull Response<ApiResponse<Post>> response) {
                if (response.code() == 200) {
                    if (response.body().getData().size() > 0) {
                        progressBar.setVisibility(View.GONE);
                        postRecyclerViewAdapter.addUpdate(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ApiResponse<Post>> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });

        return view;
    }

    @Override
    public void onItemClick(Post post) {

    }
}