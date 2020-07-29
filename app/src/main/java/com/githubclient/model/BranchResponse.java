package com.githubclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BranchResponse {

    private String name;
    private Commit commit;

   /* @SerializedName("protected")
    @Expose
    private String protectedFlag;*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

   /* public String getProtectedFlag() {
        return protectedFlag;
    }

    public void setProtectedFlag(String protectedFlag) {
        this.protectedFlag = protectedFlag;
    }
*/

}
