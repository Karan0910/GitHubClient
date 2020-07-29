package com.githubclient.api;

import android.content.SharedPreferences;

import com.githubclient.model.AuthResponse;
import com.githubclient.model.BranchResponse;
import com.githubclient.model.PullRequestResponse;
import com.githubclient.model.ReposResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @Headers("Accept:application/json")
    @POST("access_token")
    Call<AuthResponse> getAccessToken(@Query( "client_id") String client_id, @Query( "client_secret") String client_secret,
                                      @Query( "code") String code);


    @GET("/user/repos")
    Call<List<ReposResponse>> getRepos(@Header("Authorization")String token);


    @GET("repos/{owner}/{repo}/branches")
    Call<List<BranchResponse>> getBranches(@Header("Authorization")String token, @Path("owner") String owner, @Path("repo") String repo);


    @GET("repos/{owner}/{repo}/pulls")
    Call<List<PullRequestResponse>> getPullRequests(@Header("Authorization")String token, @Path("owner") String owner, @Path("repo") String repo, @Query("state") String state);
}