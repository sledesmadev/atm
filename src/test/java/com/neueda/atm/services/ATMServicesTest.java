package com.neueda.atm.services;

import com.neueda.atm.models.entities.Account;
import com.neueda.atm.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
@DataJpaTest
public class ATMServicesTest {

    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void test() {

        Account account = new Account();
        account.setNumber(123456789l);
        account.setPin(1234l);
        account.setOpeningBalance(800d);
        account.setOverdraft(200d);

        Account account2 = new Account();
        account2.setNumber(987654321l);
        account2.setPin(4321l);
        account2.setOpeningBalance(1230d);
        account2.setOverdraft(150d);

        List<Account> expectedResult = new ArrayList<Account>();
        expectedResult.add(account);
        expectedResult.add(account2);

        List<Account> actualResult = StreamSupport
                .stream(accountRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(expectedResult, actualResult);
    }


}
