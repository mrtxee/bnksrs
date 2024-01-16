package org.mrtxee.bnksrs.accountservice.repository;

import org.mrtxee.bnksrs.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountNumber(long account_number);

    boolean existsByClientId(Long client_id);
//    boolean existsByDishHourAndRestId(int hour, long restId);

    Account getFirstByClientId(Long client_id);

}
