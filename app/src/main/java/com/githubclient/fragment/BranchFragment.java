package com.githubclient.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.githubclient.R;
import com.githubclient.Utils.Constants;
import com.githubclient.activity.BranchActivity;
import com.githubclient.adapter.BranchAdapter;
import com.githubclient.api.GetDataService;
import com.githubclient.api.RetrofitClientInstance;
import com.githubclient.model.BranchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BranchFragment extends Fragment {


    RecyclerView rvbranch ;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();

        String ownerName =getActivity().getIntent().getStringExtra("owner_name");
        String repoName= getActivity().getIntent().getStringExtra("repo_name");

        SharedPreferences pref = getActivity().getSharedPreferences(Constants.sharedPref, 0);
        SharedPreferences.Editor editor = pref.edit();
        String auth_code= pref.getString("access_token", null);

        System.out.println(ownerName+repoName+auth_code);

        GetDataService service = RetrofitClientInstance.getRetrofitInstanceForRepo().create(GetDataService.class);
        Call<List<BranchResponse>> call = service.getBranches("token "+auth_code,ownerName,repoName);
        call.enqueue(new Callback<List<BranchResponse>>() {
            @Override
            public void onResponse(Call<List<BranchResponse>> call, Response<List<BranchResponse>> response) {
                //System.out.println("here is the body"+response);


                BranchAdapter adapter = new BranchAdapter(response.body(), context);
                rvbranch.setAdapter(adapter);
                DividerItemDecoration itemDecor = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                rvbranch.addItemDecoration(itemDecor);
                rvbranch.setLayoutManager(new LinearLayoutManager(context));
            }

            @Override
            public void onFailure(Call<List<BranchResponse>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_branch, container, false);

        rvbranch= view.findViewById(R.id.rv_branch_repo);

        return view;
    }
}