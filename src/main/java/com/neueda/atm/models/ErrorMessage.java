package com.neueda.atm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String errorMessage;
}
