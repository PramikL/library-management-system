package com.example.lms.repo;

import com.example.lms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from users where user_name=?1"
    )
    User getUserByUserName(String userName);
}
