package com.githubclient.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.githubclient.R;
import com.githubclient.api.GetDataService;
import com.githubclient.api.RetrofitClientInstance;
import com.githubclient.model.AuthResponse;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;

    boolean loadingFinished = true;
    boolean redirect = false;
    String code="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //Log.i("Login",getIntent().getData().toString());


      /*  webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {


                System.out.println("here is the url"+urlNewString);
                if(urlNewString.contains("code=")){
                    Uri uri = Uri.parse(urlNewString);
                    code = uri.getQueryParameter("code");
                    System.out.println(code);
                    getAccessToken();
                    return  true;
                }
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });

        webview.loadUrl("https://github.com/login/oauth/authorize?client_id=5de8469f03ad7a768257");*/
    }




}