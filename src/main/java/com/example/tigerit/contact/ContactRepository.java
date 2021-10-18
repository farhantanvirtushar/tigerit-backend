package com.example.tigerit.contact;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {

    @Query(value = "select * from contact where user_id = :#{#userId} and first_name||last_name like :#{#search} order by first_name,last_name limit :#{#limit} offset :#{#offset}",nativeQuery = true)
    List<Contact> findWithUserId(Long userId,String search,int limit,int offset);
}

