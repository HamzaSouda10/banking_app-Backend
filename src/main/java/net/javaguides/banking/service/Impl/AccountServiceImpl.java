package net.javaguides.banking.service.Impl;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount =accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);

    }
    @Override
    public AccountDto getAccountById(Long id){
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not existe "));
        return AccountMapper.maptoAccountDto(account);

    }
    @Override
    public AccountDto deposit(Long id,double amount){
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found"));
        account.setBalance( (account.getBalance()+amount));
        Account savedAccount =accountRepository.save(account);
        return AccountMapper.maptoAccountDto(account);

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found"));
        if(amount>account.getBalance()){
            throw new RuntimeException("Insufficient amount");

        }
        account.setBalance(account.getBalance()-amount);
        Account savedAccount =accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }




    @Override
    public void deleteAccount(Long id){
        Account account=accountRepository.findById( id).orElseThrow(()->new RuntimeException("Account not found"));
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.maptoAccountDto(account) ).collect(Collectors.toList());

    }
}
