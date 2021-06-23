package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    void deleteAccountByIban(String iban);
    Account getAccountByIban(String iban);
    // get accounts of the current user
    List<Account> getAccountsByUserId(Long userId);
}
