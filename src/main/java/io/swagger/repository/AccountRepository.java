package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    @Query("SELECT t from Account t where t.iban =:iban")
    Account getAccountByIban(String iban);

    void deleteAccountByIban(String iban);
}
