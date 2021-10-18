package com.example.tigerit.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {


    @Query(value = "select * from user where email = :#{#email}",nativeQuery = true)
    List<User> findWithEmail(String email);
}
