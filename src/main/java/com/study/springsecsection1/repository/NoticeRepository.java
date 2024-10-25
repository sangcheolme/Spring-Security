package com.study.springsecsection1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.study.springsecsection1.model.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query("select n from Notice n where CURDATE() between n.noticBegDt and n.noticEndDt")
    List<Notice> findAllActiveNotices();

}
