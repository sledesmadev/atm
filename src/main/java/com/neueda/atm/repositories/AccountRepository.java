package com.neueda.atm.repositories;

import com.neueda.atm.models.entities.Account;

import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account, Long>  {
    
}
