package me.chosi.restapi.configs;

import me.chosi.restapi.accounts.Account;
import me.chosi.restapi.accounts.AccountRepository;
import me.chosi.restapi.accounts.AccountRole;
import me.chosi.restapi.accounts.AccountService;
import me.chosi.restapi.common.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            AccountService accountService;

            @Autowired
            AccountRepository accountRepository;

            @Autowired
            AppProperties appProperties;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                this.accountRepository.deleteAll();

                Set<AccountRole> adminRoles = new HashSet<>();
                adminRoles.add(AccountRole.ADMIN);
                adminRoles.add(AccountRole.USER);

                Set<AccountRole> userRoles = new HashSet<>();
                userRoles.add(AccountRole.ADMIN);
                userRoles.add(AccountRole.USER);

                Account admin = Account.builder()
                        .email(appProperties.getAdminUsername())
                        .password(appProperties.getAdminPassword())
                        .roles(adminRoles)
                        .build();
                accountService.saveAccount(admin);


                Account user = Account.builder()
                        .email(appProperties.getUserUsername())
                        .password(appProperties.getUserPassword())
                        .roles(userRoles)
                        .build();
                accountService.saveAccount(user);
            }
        };


    }

}
