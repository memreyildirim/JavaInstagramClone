package com.mey.javainstagramclone.model;

public class Post {

    public String downloadUrl;
    public String comment;
    public String userEmail;

    public Post(String downloadUrl, String comment, String userEmail) {
        this.downloadUrl = downloadUrl;
        this.comment = comment;
        this.userEmail = userEmail;
    }
}
