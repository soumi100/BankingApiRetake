package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    void deleteAccountByIban(String iban);

    Account getAccountByIban(String iban);
}
