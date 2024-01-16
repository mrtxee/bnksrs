package org.mrtxee.bnksrs.accountservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts", schema="public")
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="client_id", nullable=false)
    private Long clientId;
    @Column(name="account_number", nullable=false)
    private Long accountNumber;
    private Double amount;
}
