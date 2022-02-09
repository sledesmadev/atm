package com.neueda.atm.repositories;

import com.neueda.atm.models.entities.Notes;
import org.springframework.data.repository.CrudRepository;

public interface ATMNotesRepository extends CrudRepository<Notes, Long> {
}
