package com.meditrack.accounts.adapter.out.db.repo;

import com.meditrack.accounts.adapter.out.db.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsEntity, Long> {

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    AccountsEntity findByUserNameIgnoreCase(String userName);

    boolean existsByIdAndUserName(Long id, String userName);
}
