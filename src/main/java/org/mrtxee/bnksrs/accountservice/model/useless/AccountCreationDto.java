package org.mrtxee.bnksrs.accountservice.model.useless;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationDto {
    private Long clientId;
    private Double amount;
}
