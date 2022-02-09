package com.neueda.atm.services;

import com.neueda.atm.models.WithdrawalNotesBalance;

import javax.xml.bind.ValidationException;

public interface ATMNotesService {

    WithdrawalNotesBalance updateAndGetTransactionNotes(Double amount) throws ValidationException;
}
