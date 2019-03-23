package com.example.rentalHome.Repositories;

import com.example.rentalHome.Domains.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSelfcredentialsRepository extends JpaRepository<UserCredentials,Long>
{
    @Query(value = "SELECT * FROM  jsoninput_user_credentials  WHERE  email_id = :email AND password = :password",nativeQuery = true)
    UserCredentials userLoginCheck(@Param("email") String email, @Param("password") String password);
}
