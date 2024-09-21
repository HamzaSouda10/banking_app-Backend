package net.javaguides.banking.controller;

import net.javaguides.banking.service.Impl.AccountServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController{
    private AccountServiceImpl accountService;

    public AccountController (AccountServiceImpl accountService){
        this.accountService=accountService;
    }


}
