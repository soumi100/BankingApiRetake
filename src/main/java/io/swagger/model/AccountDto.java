package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
    @Id
    @SequenceGenerator(name = "account_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @JsonProperty("id")
    private Long id = null;

    public Boolean active;
    public Account.CurrencyEnum currency;
    public Account.TypeEnum type;
    public Integer balance;



}
