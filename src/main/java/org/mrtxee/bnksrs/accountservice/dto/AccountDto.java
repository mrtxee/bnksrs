package org.mrtxee.bnksrs.accountservice.dto;

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

    public AccountDto(Long clientId, Double amount) {
        this.clientId = clientId;
        this.amount = amount;
    }
}
