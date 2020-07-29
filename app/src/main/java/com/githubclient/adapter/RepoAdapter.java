package com.githubclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.githubclient.R;
import com.githubclient.activity.BranchActivity;
import com.githubclient.model.ReposResponse;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RepoAdapter extends
        RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private List<ReposResponse> repoList;
    private Context context;

    public RepoAdapter(List<ReposResponse> repoList,Context context) {
        this.repoList = repoList;
        this.context=context;
    }

    @Override
    public RepoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.repo_item_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data model based on position
        ReposResponse reposResponse = repoList.get(position);

        Picasso.get().load(reposResponse.getOwner().getAvatarUrl()).into(holder.ownerImage);

        holder.repoName.setText(reposResponse.getFullName());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BranchActivity.class);
                intent.putExtra("owner_name",reposResponse.getOwner().getLogin());
                intent.putExtra("repo_name",reposResponse.getName());
                context.startActivity(intent);
            }
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView githubImage;
        public ImageView ownerImage;
        public TextView repoName;
        private ConstraintLayout repoContainerLayout;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);

            githubImage = itemView.findViewById(R.id.git_logo);
            ownerImage =  itemView.findViewById(R.id.repo_owner_logo);
            repoName = itemView.findViewById(R.id.repo_name);
            repoContainerLayout=itemView.findViewById(R.id.repo_item_container);
        }
    }
}
