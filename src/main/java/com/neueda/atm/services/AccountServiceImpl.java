package com.neueda.atm.services;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.xml.bind.ValidationException;

import com.neueda.atm.models.BalanceCheck;
import com.neueda.atm.models.WithdrawalNotesBalance;
import com.neueda.atm.models.dto.AccountDTO;
import com.neueda.atm.models.dto.WithdrawDTO;
import com.neueda.atm.models.entities.Account;
import com.neueda.atm.repositories.AccountRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ATMNotesService atmNotesService;

    @Override
    @Transactional(value = TxType.NEVER)
    public Optional<BalanceCheck> getBalance(AccountDTO accountDTO) throws ValidationException {
        log.info("Getting balance");
        BalanceCheck balance = null;
        Optional<Account> userAccount = checkIfExistsAndReturn(accountDTO);
         if(userAccount.isPresent()){
             log.info("Assigning balance values");
             balance = new BalanceCheck();
             balance.setOpeningBalance(userAccount.get().getOpeningBalance());
             balance.setOverDraft(userAccount.get().getOverdraft());
             balance.setMaxWithdrawal(userAccount.get().getMaximunWithdrawal());
         }
         log.info("Returning balance values");
         if(Objects.isNull(balance)){
             return Optional.empty();
         }else{
             return Optional.ofNullable(balance);
         }
    }

    @Override
    @Transactional(value = TxType.REQUIRED, rollbackOn = ValidationException.class)
    public Optional<WithdrawalNotesBalance> putWithdraw(WithdrawDTO withdrawDTO) throws ValidationException {
        log.info("Withdrawing money");
        WithdrawalNotesBalance withdraw = null;
        if(withdrawDTO.getWithdrawAmount() % 5 != 0){
            throw new ValidationException("You have to withdraw a money quantity multiple of 5");
        }

        Optional<Account> userAccount = checkIfExistsAndReturn(withdrawDTO.getAccount());
        if(userAccount.isPresent()){
            if(withdrawDTO.getWithdrawAmount() > userAccount.get().getMaximunWithdrawal()  ){
                throw new ValidationException("The money you want to withdraw is higher than your funds");
            }else{
                log.info("Doing withdrawal");
                withdraw = new WithdrawalNotesBalance();
                log.info("Getting withdraw notes");
                withdraw = atmNotesService.updateAndGetTransactionNotes(withdrawDTO.getWithdrawAmount());
                log.info("Updating account opening balance");
                userAccount.get().setOpeningBalance(userAccount.get().getOpeningBalance() - withdrawDTO.getWithdrawAmount());
                log.info("Updating withdraw remaining balance");
                withdraw.setRemainingBalance(userAccount.get().getOpeningBalance());

            }
        }
        if(Objects.isNull(withdraw)){
            return Optional.empty();
        }else{
            return Optional.ofNullable(withdraw);
        }
    }

    private Optional<Account> checkIfExistsAndReturn(AccountDTO accountDTO) throws ValidationException {
        log.info("Checking if account exists");
        Optional<Account> userAccount = accountRepository.findById(accountDTO.getNumber());
        if(userAccount.isPresent()){
            log.info("Account exists");
            if(userAccount.get().getPin().equals(accountDTO.getPin())){
                log.info("PIN correct");
                return userAccount;
            }else{
                log.error("PIN INCORRECT");
                throw new ValidationException("The introduced PIN is incorrect !!!!");
            }
        }else{
            log.error("Account number does not exists");
            throw new ValidationException("The account number "+accountDTO.getNumber()+ " does not exists !");
        }
    }
    
}
