package io.swagger.repository;

import io.swagger.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository <Transaction, Long> {
    public List<Transaction> findByUserPerforming_Id(Long id);
}
