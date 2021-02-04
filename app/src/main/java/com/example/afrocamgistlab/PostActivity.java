package com.example.afrocamgistlab;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.afrocamgistlab.model.MediaFile;
import com.example.afrocamgistlab.model.Post;
import com.example.afrocamgistlab.service.APIService;
import com.example.afrocamgistlab.utils.ApiClient;
import com.example.afrocamgistlab.utils.SingleApiResponse;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private static final int IMAGE = 1;
    private static final int VIDEO = 2;
    private APIService apiService;
    private String postType = "text";
    private String postImage;
    private String postVideo;

    EditText postText;
    LinearLayout uploadBtn;
    TextView submit;
    ImageView imageView;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar postToolbar = findViewById(R.id.post_toolbar);
        postToolbar.setTitle("Post");
        setSupportActionBar(postToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        apiService = ApiClient.getRetrofit().create(APIService.class);

        postText = findViewById(R.id.edt_post_content);
        uploadBtn = findViewById(R.id.upload_layout_btn);
        submit = findViewById(R.id.add_post);
        imageView = findViewById(R.id.img_upload);
        videoView = findViewById(R.id.video_upload);

        uploadBtn.setOnClickListener(v -> {
            showPictureDialog();
        });

        submit.setOnClickListener(v -> {
            String text = postText.getText().toString().trim();
            Post post = new Post();
            if (text != null) {
                post.setPostText(text);
            }
            if (postImage != null) {
                List<String> postImages = new ArrayList<>();
                postImages.add(postImage);
                post.setPostImage(postImages);
            }if (postVideo != null) {
                List<String> postVideos = new ArrayList<>();
                postVideos.add(postVideo);
                post.setPostImage(postVideos);
            }
            post.setPostType(postType);

            Call<SingleApiResponse<Post>> addPostCall = apiService.addPost(post);
            addPostCall.enqueue(new Callback<SingleApiResponse<Post>>() {
                @Override
                public void onResponse(@NotNull Call<SingleApiResponse<Post>> call, @NotNull Response<SingleApiResponse<Post>> response) {
                    if (response.code() == 200) {
                        Toast.makeText(PostActivity.this, "Hurray! You added a post.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(PostActivity.this, MainActivity.class));
                    }
                }

                @Override
                public void onFailure(@NotNull Call<SingleApiResponse<Post>> call, @NotNull Throwable t) {
                    t.printStackTrace();
                }
            });
        });

    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Image Post",
                "Video Post" };
        pictureDialog.setItems(pictureDialogItems,
                (dialog, which) -> {
                    switch (which) {
                        case 0:
                            chooseImageFromGallery();
                            break;
                        case 1:
                            chooseVideoFromGallery();
                            break;
                    }
                });
        pictureDialog.show();
    }

    private void chooseImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        postType = "image";

        startActivityForResult(galleryIntent, IMAGE);
    }

    private void chooseVideoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI);

        postType = "video";

        startActivityForResult(galleryIntent, VIDEO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Log.d("Select ","Canceled!");
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                Uri contentURI = data.getData();
                videoView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageURI(contentURI);

                File file = new File(getPath(contentURI));
                postImage = file.getName();

                if (contentURI != null) {
                    uploadImage(getPath(contentURI));
                }
            }

        } else if (requestCode == 2) {
            Uri contentURI = data.getData();
            imageView.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(contentURI);
            videoView.requestFocus();
            videoView.start();

            File file = new File(getPath(contentURI));
            postVideo = file.getName();

            if (contentURI != null) {
                uploadVideo(getPath(contentURI));
            }
        }
    }

    private void uploadImage(String fileUrl) {
        MediaType MEDIA_TYPE_PNG = fileUrl.endsWith("png") ?
                MediaType.parse("image/png") : MediaType.parse("image/jpeg");
        File file = new File(fileUrl);
        RequestBody requestFile = RequestBody.create(MEDIA_TYPE_PNG, file);
        Call<SingleApiResponse<List<MediaFile>>> uploadResponse = apiService.uploadImage(requestFile);
        uploadResponse.enqueue(new Callback<SingleApiResponse<List<MediaFile>>>() {
            @Override
            public void onResponse(@NotNull Call<SingleApiResponse<List<MediaFile>>> call, @NotNull retrofit2.Response<SingleApiResponse<List<MediaFile>>> response) {
                Log.i("UPLOAD RESPONSE", response.body().toString());
            }

            @Override
            public void onFailure(@NotNull Call<SingleApiResponse<List<MediaFile>>> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void uploadVideo(String fileUrl) {
        MediaType MEDIA_TYPE_PNG = fileUrl.endsWith("mp4") ?
                MediaType.parse("video/mp4") : MediaType.parse("video/mpeg4");
        File file = new File(fileUrl);
        RequestBody requestFile = RequestBody.create(MEDIA_TYPE_PNG, file);
        Call<SingleApiResponse<MediaFile>> uploadResponse = apiService.uploadVideo(requestFile);
        uploadResponse.enqueue(new Callback<SingleApiResponse<MediaFile>>() {
            @Override
            public void onResponse(@NotNull Call<SingleApiResponse<MediaFile>> call, @NotNull retrofit2.Response<SingleApiResponse<MediaFile>> response) {
                Log.i("UPLOAD RESPONSE", response.body().toString());
            }

            @Override
            public void onFailure(@NotNull Call<SingleApiResponse<MediaFile>> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
}