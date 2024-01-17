package org.mrtxee.bnksrs.accountservice.repository;

import java.util.Optional;
import org.mrtxee.bnksrs.accountservice.model.Account;
import org.mrtxee.bnksrs.clientservcie.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountNumber(long account_number);

    boolean existsByClientId(Long clientId);
//    boolean existsByDishHourAndRestId(int hour, long restId);

    Account getFirstByClientId(Long clientId);

    Optional<Account> getFirstByAccountNumber(Long accountNumber);

}
