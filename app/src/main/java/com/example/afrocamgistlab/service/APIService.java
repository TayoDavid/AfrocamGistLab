package com.example.afrocamgistlab.service;

import com.example.afrocamgistlab.model.CommentResponse;
import com.example.afrocamgistlab.model.EditComment;
import com.example.afrocamgistlab.model.MediaFile;
import com.example.afrocamgistlab.model.Post;
import com.example.afrocamgistlab.model.PostComment;
import com.example.afrocamgistlab.utils.ApiResponse;
import com.example.afrocamgistlab.utils.SingleApiResponse;
import com.example.afrocamgistlab.utils.StatusResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {

    @GET("api/posts/afroswagger")
    Call<ApiResponse<Post>> getAfroswaggerPosts();

    @GET("api/posts/afrotalent")
    Call<ApiResponse<Post>> getAfrotalentPosts();

    @GET("api/posts/most-popular")
    Call<ApiResponse<Post>> getMostPopularPosts();

    @POST("api/posts/{id}/report")
    Call<SingleApiResponse<String>> reportPost(@Path("id") int id);

    @POST("api/posts/{id}/report")
    Call<SingleApiResponse<String>> likePost(@Path("id") int id);

    @POST("api/posts")
    Call<SingleApiResponse<Post>> addPost(@Body Post post);

    @Multipart
    @POST("api/posts/upload")
    Call<SingleApiResponse<List<MediaFile>>> uploadImage(@Part("image") RequestBody image);

    @Multipart
    @POST("api/posts/upload-video")
    Call<SingleApiResponse<MediaFile>> uploadVideo(@Part("video") RequestBody requestFile);

    @POST("api/comments/")
    Call<SingleApiResponse<CommentResponse>> addComment(@Body PostComment comment);

    @DELETE("api/comments/{id}")
    Call<StatusResponse> deleteComment(@Path("id") int id);

    @PUT("api/comments/{id}")
    Call<SingleApiResponse<CommentResponse>> updateComment(@Path("id") int id, @Body EditComment postData);

}
