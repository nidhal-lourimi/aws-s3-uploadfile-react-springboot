package com.nidhallourimi.awsimageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("nidhallourimi-image-upload");
    private final String bucketName ;



    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
