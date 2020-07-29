package com.githubclient.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.githubclient.BuildConfig;
import com.githubclient.R;
import com.githubclient.Utils.Constants;
import com.githubclient.api.GetDataService;
import com.githubclient.api.RetrofitClientInstance;
import com.githubclient.model.AuthResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.in_app_login_bt)
    Button getStartedBt;
    @BindView(R.id.browser_login_bt)
    Button browserLoginBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getDataFromIntent();

        getStartedBt.setVisibility(View.INVISIBLE);
        getStartedBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        browserLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/login/oauth/authorize?client_id="+Constants.clientId+"&redirect_uri="+ Constants.redirectUi+"&scope=repo"));
                startActivity(browserIntent);
            }
        });

    }

    private void getDataFromIntent(){
        if(getIntent().getData()!=null){
            Uri uri = Uri.parse(getIntent().getData().toString());
            String code = uri.getQueryParameter("code");
            getAccessToken(code);
        }
    }

    private void getAccessToken(String code) {

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<AuthResponse> call = service.getAccessToken(BuildConfig.API_CLIENT_ID,BuildConfig.API_SECRET,code);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                System.out.println("Here ois the code "+response.body().toString());

                SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.sharedPref, 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("access_token", response.body().getAccess_token());
                editor.commit();

                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}