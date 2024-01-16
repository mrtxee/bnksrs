package org.mrtxee.bnksrs.clientservcie.repository;

import org.mrtxee.bnksrs.clientservcie.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
