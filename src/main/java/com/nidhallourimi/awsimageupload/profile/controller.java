package com.nidhallourimi.awsimageupload.profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("api/v1/user-profile")

public class controller {

    private final UserProfileService userProfileService;
    @Autowired
    public controller(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }



    @GetMapping
    public List<UserProfile> getUserProfiles(){
        return userProfileService.getUserProfiles();
    }

    @PostMapping(path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadUserProfileImage(@PathVariable ("userProfileId")UUID userProfileId,
                                       @RequestParam("file")MultipartFile file){
        userProfileService.uploadUserProfileImage(userProfileId,file);

    }

    @GetMapping("{userProfileId}/image/download")
    public byte[] downloadUserProfilesImage(@PathVariable("userProfileId") UUID userProfileId){
      return   userProfileService.downloadUserProfilesImage(userProfileId);

    }

}
