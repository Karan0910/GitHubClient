package com.githubclient.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.githubclient.R;
import com.githubclient.Utils.Constants;
import com.githubclient.adapter.BranchAdapter;
import com.githubclient.adapter.PullRequestAdapter;
import com.githubclient.api.GetDataService;
import com.githubclient.api.RetrofitClientInstance;
import com.githubclient.databinding.FragmentPrBinding;
import com.githubclient.model.BranchResponse;
import com.githubclient.model.PullRequestResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrFragment extends Fragment {

    FragmentPrBinding binding;
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
        Call<List<PullRequestResponse>> call = service.getPullRequests("token "+auth_code,ownerName,repoName,"all");
        call.enqueue(new Callback<List<PullRequestResponse>>() {
            @Override
            public void onResponse(Call<List<PullRequestResponse>> call, Response<List<PullRequestResponse>> response) {
                //System.out.println("here is the body"+response);


                PullRequestAdapter adapter = new PullRequestAdapter(response.body(), context);
                binding.rvPullReqRepo.setAdapter(adapter);
                DividerItemDecoration itemDecor = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                binding.rvPullReqRepo.addItemDecoration(itemDecor);
                binding.rvPullReqRepo.setLayoutManager(new LinearLayoutManager(context));
            }

            @Override
            public void onFailure(Call<List<PullRequestResponse>> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPrBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        return view;
    }
}