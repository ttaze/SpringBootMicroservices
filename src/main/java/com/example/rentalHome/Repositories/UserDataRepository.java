package com.example.rentalHome.Repositories;

import com.example.rentalHome.Domains.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,Long>
{
    @Query(value = "select * from user_data",nativeQuery = true)
    List<UserData> getAllUser();

    @Query(value = "select * from user_data where email_id = :email_id",nativeQuery = true)
    UserData getByEmail(@Param("email_id") String email);

    @Query(value = "select * from user_data where user_id = :user_id",nativeQuery = true)
    UserData searchUserId(@Param("user_id") Long userId);
}
