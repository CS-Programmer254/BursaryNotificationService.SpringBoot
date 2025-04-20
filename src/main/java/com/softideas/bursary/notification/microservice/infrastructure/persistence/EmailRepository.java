package com.softideas.bursary.notification.microservice.infrastructure.persistence;

import com.softideas.bursary.notification.microservice.domain.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {

    //Find all emails with PENDING, SENT, FAILED)

    List<Email> findByStatus(String status);

}