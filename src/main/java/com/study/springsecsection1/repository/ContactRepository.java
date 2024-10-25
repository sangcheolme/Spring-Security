package com.study.springsecsection1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.study.springsecsection1.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {


}
