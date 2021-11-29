package com.nidhallourimi.awsimageupload.profile;

import com.nidhallourimi.awsimageupload.datastore.FakeUserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public class UserProfilesDataAccessService {
    private final FakeUserProfileDataStore fakeUserProfileDataStore;
        @Autowired
    public UserProfilesDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

    List<UserProfile> getUserProfiles(){
            return fakeUserProfileDataStore.getUserProfiles();
    }
}
