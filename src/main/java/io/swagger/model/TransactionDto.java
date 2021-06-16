package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    @Id
    @SequenceGenerator(name = "dto_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dto_seq")
    private Long id = null;

    private String accountFrom;
    private String accountTo;
    private Double amount;
    private String description;
}
