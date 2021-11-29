package com.nidhallourimi.awsimageupload.profile;

import com.nidhallourimi.awsimageupload.bucket.BucketName;
import com.nidhallourimi.awsimageupload.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@org.springframework.stereotype.Service

public class UserProfileService {
    private final FileStore fileStore;
    private final UserProfilesDataAccessService userProfilesDataAccessService;
    @Autowired
    public UserProfileService(FileStore fileStore, UserProfilesDataAccessService userProfilesDataAccessService) {
        this.fileStore = fileStore;
        this.userProfilesDataAccessService = userProfilesDataAccessService;
    }

    List<UserProfile> getUserProfiles(){
        return userProfilesDataAccessService.getUserProfiles();

    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        //1.check if image is not empty
        if (file.isEmpty()){
            throw new IllegalStateException("cannot upload empty file ["+file.getSize()+"]");
        }
        //2.if file is an image
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(),IMAGE_PNG.getMimeType(),IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException(" file ["+file.getContentType()+"] must be an image");
        }

        //3.the user exist in our database (this fo fake data)

        UserProfile user = userProfilesDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("the user % not found", userProfileId)));
        //4.Grab some metadata from file if any
        Map<String,String> metadata =new HashMap<>();
        metadata.put("content-Type",file.getContentType());
        metadata.put("content-length",String.valueOf(file.getSize()));

        //5.store the image in s3 and update database (userProfileImageLink) with s3 image link


        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),user.getUserProfileId());
        System.out.println(path);
        System.out.println(metadata);
        try {
            System.out.println(file.getInputStream());
        } catch (IOException e) {
            System.out.println(e);;
        }
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        System.out.println("file name " +file);
        try {
            fileStore.save(path,fileName, Optional.of(metadata),file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
