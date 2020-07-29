package com.githubclient.model;

public class AuthResponse {

    /**
     * access_token : 6509c5a61649931cd56003aa928cbc5f79f8cc2e
     * scope :
     * token_type : bearer
     */
    private String access_token;
    private String scope;
    private String token_type;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getScope() {
        return scope;
    }

    public String getToken_type() {
        return token_type;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "access_token='" + access_token + '\'' +
                ", scope='" + scope + '\'' +
                ", token_type='" + token_type + '\'' +
                '}';
    }

}
