package org.mrtxee.bnksrs.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long rec;
    private Long clientId;
    private Long accountNumber;
    private Double amount;
}
