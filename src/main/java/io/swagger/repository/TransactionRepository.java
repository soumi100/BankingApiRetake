package io.swagger.repository;

import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.threeten.bp.OffsetDateTime;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> getAllByUserPerformingId(double value);

    Transaction getById(Long value);

    List<Transaction> getByAccountFromOrAccountToOrderByTimestampDesc(String value1,String value2);

    @Query("select count(t.id) from Transaction t where (t.accountFrom = ?1 or (t.accountTo = ?1 and t.transactionType = 'DEPOSIT')) and t.timestamp >= ?2")
    int getTransactionsToday(String iban, OffsetDateTime date);

}

