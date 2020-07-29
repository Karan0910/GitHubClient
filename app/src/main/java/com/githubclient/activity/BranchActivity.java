package com.githubclient.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.githubclient.R;
import com.githubclient.Utils.Constants;
import com.githubclient.adapter.BranchAdapter;
import com.githubclient.adapter.RepoAdapter;
import com.githubclient.api.GetDataService;
import com.githubclient.api.RetrofitClientInstance;
import com.githubclient.databinding.ActivityBranchBinding;
import com.githubclient.fragment.BranchFragment;
import com.githubclient.fragment.PrFragment;
import com.githubclient.model.BranchResponse;
import com.githubclient.model.ReposResponse;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BranchActivity extends AppCompatActivity {

    private String[] titles = new String[]{"Branches", "Pull Requests"};


    ActivityBranchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityBranchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(this));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();


    }


    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new BranchFragment();
                case 1:
                    return new PrFragment();
            }
            return new BranchFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}