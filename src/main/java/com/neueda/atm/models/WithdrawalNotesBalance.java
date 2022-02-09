package com.neueda.atm.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawalNotesBalance {

    private Long fiftyNotes = 0L;
    private Long twentyNotes= 0L;
    private Long tenNotes= 0L;
    private Long fiveNotes= 0L;
    private Double remainingBalance;
}
