package com.company.models;

public class Users {

    protected int userId;
    protected String username;
    protected String password;
    protected String gender;
    protected int age;
    protected String userType;
    protected int onlineStatus;
    protected String relationshipStatus;
    protected String levelOfEduction;

    public Users() {
        this(0, null, null, null, 0, null, 0, null, null);
    }

    public Users(String username, String password, String gender,
                 int age, String userType, int onlineStatus, String relationshipStatus,
                 String levelOfEduction) {
        this(0, null, null, null, 0, null, 0, null, null);
    }

    public Users(int id, String username, String password, String gender,
                 int age, String userType, int onlineStatus, String relationshipStatus,
                 String levelOfEduction) {

    }

    public String toString() {
        return String.format("Username: %s, Age: %d", this.getUsername(), this.getAge());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getLevelOfEduction() {
        return levelOfEduction;
    }

    public void setLevelOfEduction(String levelOfEduction) {
        this.levelOfEduction = levelOfEduction;
    }
}
