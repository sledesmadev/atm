package com.neueda.atm.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceCheck {

    private Double openingBalance;
    private Double overDraft;
    private Double maxWithdrawal;
}
