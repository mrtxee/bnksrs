package org.mrtxee.bnksrs.clientservcie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long rec;
    private String first_name;
    private String last_name;
    private String patronymic;

}
