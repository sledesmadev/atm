package com.neueda.atm.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Notes")
@Data
@NoArgsConstructor
public class Notes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fiftyNotes;
    private Long twentyNotes;
    private Long tenNotes;
    private Long fiveNotes; 
    

}
