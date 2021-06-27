package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")

@Entity
@Data

public class Transaction {
    @Id
    @SequenceGenerator(name = "trans_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_seq")

    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("timestamp")
    private OffsetDateTime timestamp = null;


    @JsonProperty("accountFrom")
    private String accountFrom = null;

    @JsonProperty("accountTo")
    private String accountTo = null;

    @JsonProperty("amount")
    private Double amount = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("userPerformingId")
    private Long userPerformingId = null;
    @JsonProperty("transactionType")
    private TransactionTypeEnum transactionType = null;

    public Transaction(Long id, OffsetDateTime timestamp, String accountFrom, String accountTo, Double amount, String description, Long userPerformingId, TransactionTypeEnum transactionType) {
        this.id = id;
        this.timestamp = timestamp;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.description = description;
        this.userPerformingId = userPerformingId;
        this.transactionType = transactionType;
    }

    public Transaction(OffsetDateTime timestamp, String accountFrom, String accountTo, Double amount, String description, Long userPerformingId, TransactionTypeEnum transactionType) {
        this.timestamp = timestamp;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.description = description;
        this.userPerformingId = userPerformingId;
        this.transactionType = transactionType;
    }

    public Transaction() {
    }

    public Transaction(String accountFrom, String accountTo, Double amount, String description, Long userPerformingId, TransactionTypeEnum transactionType) {
        setTimestamp(OffsetDateTime.now());
        setAccountFrom(accountFrom);
        setAccountTo(accountTo);
        setAmount(amount);
        setDescription(description);
        setUserPerformingId(userPerformingId);
        setTransactionType(transactionType);
    }

    public Transaction id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @Schema(example = "10000000001", description = "")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction timestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     *
     * @return timestamp
     **/
    @Schema(example = "2021-05-07T12:32:28Z", required = true, description = "")
    @NotNull

    @Valid
    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Transaction accountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
        return this;
    }

    /**
     * Get accountFrom
     *
     * @return accountFrom
     **/
    @Schema(example = "NL01INHO0000000000", description = "")

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Transaction accountTo(String accountTo) {
        this.accountTo = accountTo;
        return this;
    }

    /**
     * Get accountTo
     *
     * @return accountTo
     **/
    @Schema(example = "NL01INHO0000000000", description = "")

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public Transaction amount(Double amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Get amount
     *
     * @return amount
     **/
    @Schema(example = "100", required = true, description = "")
    @NotNull

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Transaction description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/
    @Schema(example = "Money for your new RB-17", description = "")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Transaction userPerformingId(Long userPerformingId) {
        this.userPerformingId = userPerformingId;
        return this;
    }

    /**
     * Get userPerformingId
     *
     * @return userPerformingId
     **/
    @Schema(example = "10000000001", required = true, description = "")
    @NotNull

    public Long getUserPerformingId() {
        return userPerformingId;
    }

    public void setUserPerformingId(Long userPerformingId) {
        this.userPerformingId = userPerformingId;
    }

    public Transaction transactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    /**
     * Get transactionType
     *
     * @return transactionType
     **/
    @Schema(example = "Deposit", required = true, description = "")
    @NotNull

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        return Objects.equals(this.id, transaction.id) &&
                Objects.equals(this.timestamp, transaction.timestamp) &&
                Objects.equals(this.accountFrom, transaction.accountFrom) &&
                Objects.equals(this.accountTo, transaction.accountTo) &&
                Objects.equals(this.amount, transaction.amount) &&
                Objects.equals(this.description, transaction.description) &&
                Objects.equals(this.userPerformingId, transaction.userPerformingId) &&
                Objects.equals(this.transactionType, transaction.transactionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, accountFrom, accountTo, amount, description, userPerformingId, transactionType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Transaction {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    accountFrom: ").append(toIndentedString(accountFrom)).append("\n");
        sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    userPerformingId: ").append(toIndentedString(userPerformingId)).append("\n");
        sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    /**
     * Gets or Sets transactionType
     */
    public enum TransactionTypeEnum {
        DEPOSIT("Deposit"),

        WITHDRAWAL("Withdrawal"),

        TRANSFER("Transfer");

        private String value;

        TransactionTypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static TransactionTypeEnum fromValue(String text) {
            for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }
    }
}