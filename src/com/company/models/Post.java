package com.company.models;

public class Post {

    // member variables
    protected int postId;
    protected String postTime;
    protected String postUsername;
    protected String postType;
    protected String postCaption;
    protected String postImage;
    protected String postText;

    // getters and setters

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostUsername() {
        return postUsername;
    }

    public void setPostUsername(String postUsername) {
        this.postUsername = postUsername;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostCaption() {
        return postCaption;
    }

    public void setPostCaption(String postCaption) {
        this.postCaption = postCaption;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    // constructors
    public Post() {
        this(0, null, null, null, null, null, null);
    }

    public Post(int id, String time, String username, String type,
                String caption, String image, String text) {
        this(time, username, type, caption, image, text);
        this.postId = id;

    }

    public Post(String time, String username, String type,
                String caption, String image, String text) {
        this.postTime = time;
        this.postUsername = username;
        this.postType = type;
        this.postCaption = caption;
        this.postImage = image;
        this.postText = text;
    }

    @Override
    public String toString() {
        return String.format("Username: %s, Text: %s", this.getPostUsername(), this.getPostText());
    }
}
