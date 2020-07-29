package com.githubclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.githubclient.R;
import com.githubclient.activity.BranchActivity;
import com.githubclient.model.BranchResponse;
import com.githubclient.model.ReposResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BranchAdapter  extends
        RecyclerView.Adapter<BranchAdapter.ViewHolder> {

    private List<BranchResponse> branchList;
    private Context context;

    public BranchAdapter(List<BranchResponse> branchList, Context context) {
        this.branchList = branchList;
        this.context=context;
    }

    @Override
    public BranchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View branchView = inflater.inflate(R.layout.branch_item_layout, parent, false);

        // Return a new holder instance
        BranchAdapter.ViewHolder viewHolder = new BranchAdapter.ViewHolder(branchView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(BranchAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        BranchResponse reposResponse = branchList.get(position);

       // Picasso.get().load(reposResponse.getOwner().getAvatarUrl()).into(holder.ownerImage);

        holder.branchName.setText(reposResponse.getName());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(context, BranchActivity.class);
                intent.putExtra("owner_name",reposResponse.getOwner().getLogin());
                intent.putExtra("repo_name",reposResponse.getName());
                context.startActivity(intent);*/
            }
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return branchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView branchImage;
        public TextView branchName;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);

            branchImage = itemView.findViewById(R.id.branch_image);
            branchName = itemView.findViewById(R.id.branch_name);
        }
    }
}
