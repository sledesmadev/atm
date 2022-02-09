package com.neueda.atm.controllers;

import com.neueda.atm.models.BalanceCheck;
import com.neueda.atm.models.WithdrawalNotesBalance;
import com.neueda.atm.models.dto.AccountDTO;
import com.neueda.atm.models.dto.WithdrawDTO;
import com.neueda.atm.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<BalanceCheck> getBalance(@RequestBody AccountDTO userDTO) throws ValidationException {
        Optional<BalanceCheck> userBalance = accountService.getBalance(userDTO);
        if(userBalance.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(userBalance.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/withdrawal")
    public ResponseEntity<WithdrawalNotesBalance> withdraw(@RequestBody WithdrawDTO withdrawDTO) throws  ValidationException{
        Optional<WithdrawalNotesBalance> withdrawal = accountService.putWithdraw(withdrawDTO);
        if(withdrawal.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(withdrawal.get(), HttpStatus.OK);
        }
    }



}
