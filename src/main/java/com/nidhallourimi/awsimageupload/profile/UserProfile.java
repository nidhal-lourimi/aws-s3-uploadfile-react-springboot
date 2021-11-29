package com.nidhallourimi.awsimageupload.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


public class UserProfile {

    private UUID userProfileId;
    private String userName;
    private String userProfilerImageLink; //S3 key

    public UserProfile(UUID userProfileId, String userName, String userProfilerImageLink) {
        this.userProfileId = userProfileId;
        this.userName = userName;
        this.userProfilerImageLink = userProfilerImageLink;
    }

    public UUID getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(UUID userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Optional<String> getUserProfilerImageLink() {
        return Optional.ofNullable( userProfilerImageLink);
    }

    public void setUserProfilerImageLink(String userProfilerImageLink) {
        this.userProfilerImageLink = userProfilerImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId,that.userProfileId) &&
                Objects.equals(userName,that.userName) &&
                        Objects.equals(userProfilerImageLink,that.userProfilerImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, userName, userProfilerImageLink);
    }
}
