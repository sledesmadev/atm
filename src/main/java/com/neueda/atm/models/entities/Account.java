package com.neueda.atm.models.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ACCOUNTS")
@Data
@NoArgsConstructor
public class Account implements Serializable{

    @Id
    private Long number;
    private Long pin;
    private Double openingBalance;
    private Double overdraft;

    public Double getMaximunWithdrawal(){
        return openingBalance + overdraft;
    }

}
