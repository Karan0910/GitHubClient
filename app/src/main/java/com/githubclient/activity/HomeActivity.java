package com.githubclient.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.githubclient.R;
import com.githubclient.Utils.Constants;
import com.githubclient.adapter.RepoAdapter;
import com.githubclient.api.GetDataService;
import com.githubclient.api.RetrofitClientInstance;
import com.githubclient.model.AuthResponse;
import com.githubclient.model.ReposResponse;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.repos_recycler_view)
    RecyclerView repoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.sharedPref, 0);
        SharedPreferences.Editor editor = pref.edit();
        String auth_code= pref.getString("access_token", null);



        GetDataService service = RetrofitClientInstance.getRetrofitInstanceForRepo().create(GetDataService.class);
        Call<List<ReposResponse>> call = service.getRepos("token "+auth_code);
        call.enqueue(new Callback<List<ReposResponse>>() {
            @Override
            public void onResponse(Call<List<ReposResponse>> call, Response<List<ReposResponse>> response) {
                System.out.println(response.body().size());
                RecyclerView rvRepo = (RecyclerView) findViewById(R.id.repos_recycler_view);


                // Create adapter passing in the sample user data
                RepoAdapter adapter = new RepoAdapter(response.body(),HomeActivity.this);
                // Attach the adapter to the recyclerview to populate items
                rvRepo.setAdapter(adapter);
                // Set layout manager to position the items
                DividerItemDecoration itemDecor = new DividerItemDecoration(HomeActivity.this, DividerItemDecoration.VERTICAL);
                rvRepo.addItemDecoration(itemDecor);
                rvRepo.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
            }

            @Override
            public void onFailure(Call<List<ReposResponse>> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}