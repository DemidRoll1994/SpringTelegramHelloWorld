package com.example.springtelegramhelloworld.back.database;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    //@Transactional
    //@Modifying
    //@Query("update tg_data t set t.msg_numb = t.msg_numb + 1 where t.id is not null and t.id = :id")
    //void updateMsgNumberByUserId(@Param("id") long id);
}