package io.swagger;

import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.threeten.bp.LocalDate;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" ,"io.swagger.repository"  ,"io.swagger.configuration"})
public class Swagger2SpringBoot implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... arg0) throws Exception {
        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account(500l,Account.TypeEnum.CURRENT,Account.CurrencyEnum.EUR,true,"NL01INHO00000000010",9989);
        Account account2 = new Account(400l,Account.TypeEnum.SAVINGS,Account.CurrencyEnum.EUR,true,"NL01INHO00000000080",500);
        Account  account3= new Account(300l,Account.TypeEnum.CURRENT,Account.CurrencyEnum.EUR,true,"NL01INHO00000000090",8887);

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        User user =new User();
        user.setId(1L);
        user.setActive(true);
        user.setUsername("prinsalvino");
        user.setPassword(passwordEncoder.encode("test123"));
        user.setFirstName("Prins");
        user.lastName("Alvino");
        user.setEmail("prinsalvino@gmail.com");
        user.setBirthdate(LocalDate.now());
        user.setAddress("Kets");
        user.setPostalcode("1156AX");
        user.setCity("Marken");
        user.setPhoneNumber("0855");
        user.setType(User.TypeEnum.EMPLOYEE);
        userRepository.save(user);

        accounts.forEach(accountRepository::save);
        accountRepository.findAll().forEach(System.out::println);
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
