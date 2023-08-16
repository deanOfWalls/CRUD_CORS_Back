package com.deanofwalls.CRUD_DEMO.repository;

//import javax.transaction.Transactional;

import com.deanofwalls.CRUD_DEMO.model.PersonModel;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface PersonRepository extends CrudRepository<PersonModel, Long> {

}

