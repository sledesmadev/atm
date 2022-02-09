package com.neueda.atm.services;

import com.neueda.atm.models.WithdrawalNotesBalance;
import com.neueda.atm.models.entities.Notes;
import com.neueda.atm.repositories.ATMNotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.Optional;

@Service
@Slf4j
public class ATMNotesServiceImpl implements ATMNotesService{

    @Autowired
    private ATMNotesRepository atmNotesRepository;


    @Override
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = ValidationException.class)
    public WithdrawalNotesBalance updateAndGetTransactionNotes(Double amount) throws ValidationException {
            log.info("Start updateAndGetTransactionNotes");
            WithdrawalNotesBalance withdrawalNotes = new WithdrawalNotesBalance();
            Optional<Notes> atmCurrentNotes = atmNotesRepository.findById(1l);
            if(atmCurrentNotes.isPresent()){
                withdrawalNotes = getNotesQuantity(atmCurrentNotes.get(), amount);
            }
        return withdrawalNotes;
    }

    private WithdrawalNotesBalance getNotesQuantity(Notes currentNotes,  Double amount) throws ValidationException {
        WithdrawalNotesBalance withdrawal = new WithdrawalNotesBalance();

        log.info("Checking 50 notes");
        while(true){
            if(amount >=50 && ((amount - 50) >= 0) && currentNotes.getFiftyNotes() > 0){
                withdrawal.setFiftyNotes(withdrawal.getFiftyNotes()+1);
                currentNotes.setFiftyNotes(currentNotes.getFiftyNotes()-1);
                amount = amount - 50;
            }else{
                break;
            }
        }

        log.info("Checking 20 notes");
        while(true){
            if(amount >=20 && ((amount - 20) >=  0) && currentNotes.getTwentyNotes() > 0){
                withdrawal.setTwentyNotes(withdrawal.getTwentyNotes()+1);
                currentNotes.setTwentyNotes(currentNotes.getTwentyNotes()-1);
                amount = amount - 20;
            }else{
                break;
            }
        }

        log.info("Checking 10 notes");
        while(true){
            if(amount >=10 && ((amount - 10) >=  0) && currentNotes.getTenNotes() > 0){
                withdrawal.setTenNotes(withdrawal.getTenNotes()+1);
                currentNotes.setTenNotes(currentNotes.getTenNotes()-1);
                amount = amount - 10;
            }else{
                break;
            }
        }

        log.info("Checking 5 notes");
        while(true){
            if(amount >=5 && ((amount - 5) >=  0) && currentNotes.getFiveNotes() > 0){
                withdrawal.setFiveNotes(withdrawal.getFiveNotes()+1);
                currentNotes.setFiveNotes(currentNotes.getFiveNotes()-1);
                amount = amount - 5;
            }else{
                break;
            }
        }

        if(amount > 0){
            log.error("There are no enough notes");
            throw new ValidationException("There are no enough notes to supply your requirements");
        }

        return withdrawal;
    }
}
