package com.githubclient.model;

import com.google.gson.annotations.SerializedName;

public class PullRequestResponse {

    private String number;
    private String title;
    private String state;
    @SerializedName("merged_at")
    private String mergedAt;
    private String body;

    public boolean isLocked() {
        return locked;
    }

    private boolean locked;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMergedAt() {
        return mergedAt;
    }

    public void setMergedAt(String mergedAt) {
        this.mergedAt = mergedAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



}
