package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
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
    private Boolean active;
    private Long userId;
    private Account.CurrencyEnum currency;
    private Account.TypeEnum type;
    private Integer balance ;


}
