package com.nidhallourimi.awsimageupload.datastore;

import com.nidhallourimi.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static  final List<UserProfile> USER_PROFILES= new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("40870731-669a-40dd-90ea-67a3cde2b059"),"janetjones",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("5c3571c9-7c78-455d-a644-33037c35331e"),"antoniojones",null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES ;
    }
}
