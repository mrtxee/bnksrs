package org.mrtxee.bnksrs.accountservice.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.mrtxee.bnksrs.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountNumber(long account_number);

    boolean existsByClientId(Long clientId);

    Account getFirstByClientId(Long clientId);

    Optional<Account> getFirstByAccountNumber(Long accountNumber);

    List<Account> findByAccountNumberIn(Collection<Long> accountNumbers);

}
