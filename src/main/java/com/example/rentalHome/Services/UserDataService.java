package com.example.rentalHome.Services;
import com.example.rentalHome.Domains.UserCredentials;
import com.example.rentalHome.Domains.UserData;
import java.util.List;

public interface UserDataService {
    public UserCredentials userSingIn(UserCredentials userCredentials);
    public UserData userLogIn(UserCredentials userlogin);
    public List<UserData> showAllUser();
    public UserData createUser(UserData user);
    public UserData searchUser(Long id);
    public UserData updateUser(UserData user);
    public void deleteUser(Long id);
}
