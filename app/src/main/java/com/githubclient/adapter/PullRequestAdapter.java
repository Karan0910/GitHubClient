package com.githubclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.githubclient.R;
import com.githubclient.databinding.PullReqItemBinding;
import com.githubclient.model.BranchResponse;
import com.githubclient.model.PullRequestResponse;

import java.util.List;

public class PullRequestAdapter   extends
        RecyclerView.Adapter<PullRequestAdapter.ViewHolder> {

    private List<PullRequestResponse> pullRequestList;
    private Context context;

    public PullRequestAdapter(List<PullRequestResponse> pullRequestList, Context context) {
        this.pullRequestList = pullRequestList;
        this.context=context;
    }

    @Override
    public PullRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        PullReqItemBinding branchView = PullReqItemBinding.inflate(inflater, parent, false);

        // Return a new holder instance
        PullRequestAdapter.ViewHolder viewHolder = new PullRequestAdapter.ViewHolder(branchView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PullRequestAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        PullRequestResponse pullRequestResponse = pullRequestList.get(position);

        // Picasso.get().load(pullRequestResponse.getOwner().getAvatarUrl()).into(holder.ownerImage);

        holder.binding.pullNumberTxt.setText("#"+pullRequestResponse.getNumber());
        holder.binding.pullReqName.setText(pullRequestResponse.getTitle());
        holder.binding.descriptionTxt.setText(pullRequestResponse.getBody());

        if(pullRequestResponse.getState().equalsIgnoreCase("open")){
             holder.binding.approvalStatusImg.setImageResource(R.drawable.ic_merge_selector);
             holder.binding.approvalStatusImg.setEnabled(!pullRequestResponse.isLocked());
        } else {
            holder.binding.approvalStatusImg.setImageResource(R.drawable.ic_closed);
            holder.binding.approvalStatusImg.setEnabled(false);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return pullRequestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        PullReqItemBinding binding;
        public ImageView branchImage;
        public TextView branchName;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(PullReqItemBinding itemView) {
            super(itemView.getRoot());

            binding=itemView;
        }
    }
}
