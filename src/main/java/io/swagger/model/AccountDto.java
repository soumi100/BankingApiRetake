package io.swagger.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
    public Boolean active;
    public Account.CurrencyEnum currency;
    public Account.TypeEnum type;
    // balance is changed via  transactions


}
