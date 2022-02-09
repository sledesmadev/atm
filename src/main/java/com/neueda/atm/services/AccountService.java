package com.neueda.atm.services;

import com.neueda.atm.models.BalanceCheck;
import com.neueda.atm.models.WithdrawalNotesBalance;
import com.neueda.atm.models.dto.AccountDTO;
import com.neueda.atm.models.dto.WithdrawDTO;

import javax.security.auth.login.LoginException;
import javax.xml.bind.ValidationException;
import java.util.Optional;

public interface AccountService {

    public Optional<BalanceCheck> getBalance(AccountDTO userDTO) throws ValidationException;

    public Optional<WithdrawalNotesBalance> putWithdraw(WithdrawDTO withdrawDTO) throws  ValidationException;

}
