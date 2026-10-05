package org.example.transfferingapp.repo;

import jakarta.persistence.LockModeType;
import org.example.transfferingapp.user.Account;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByName(String name);
    Optional<Account> findByEmail(String email);
    boolean existsAccountByAccountName(String accountName);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findById(@Param("id") String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.accountName = :accountName")
    Optional<Account> findByAccountName(@Param("accountName") String accountName);
}
