package com.neueda.atm.models.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WithdrawDTO {

    private AccountDTO account;
    private Double withdrawAmount;

}
