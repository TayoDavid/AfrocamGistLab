package com.example.afrocamgistlab.utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private static final String TOKEN = "1349c73031d66e55d10d3fb98bc38fb8039140ca8401845c7b5cb159e06b5c0f";

    @NotNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        //rewrite the request to add bearer token
        Request newRequest = chain.request().newBuilder()
                .header("Authorization","Bearer " + TOKEN)
                .build();

        return chain.proceed(newRequest);
    }
}
