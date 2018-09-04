package com.chatty.android.chattyClient.model;

public class FriendItemEntry {
  String profile_image;
  String name;
  String bio;
  String created_at;

  public FriendItemEntry(String profile_image, String name, String bio, String created_at) {
    this.profile_image = profile_image;
    this.name = name;
    this.bio = bio;
    this.created_at = created_at;
  }

  public String getProfile_image() {
    return profile_image;
  }

  public void setProfile_image(String profile_image) {
    this.profile_image = profile_image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }
}
