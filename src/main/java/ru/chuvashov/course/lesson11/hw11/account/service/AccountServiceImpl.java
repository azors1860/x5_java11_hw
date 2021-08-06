package ru.chuvashov.course.lesson11.hw11.account.service;

import ru.chuvashov.course.lesson11.hw11.account.model.Account;
import ru.chuvashov.course.lesson11.hw11.account.model.exceptions.NotEnoughMoneyException;
import ru.chuvashov.course.lesson11.hw11.account.model.exceptions.UnknownAccountException;
import ru.chuvashov.course.lesson11.hw11.account.repository.Repository;
import ru.chuvashov.course.lesson11.hw11.account.repository.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Chuvashov Sergey
 */
@Component
public class AccountServiceImpl implements AccountService {

    private final Repository<Account> accountRepository;

    @Autowired
    public AccountServiceImpl(Repository repository) {
        accountRepository = repository;
    }

    @Override
    public void withDraw(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, RepositoryException {

        if (amount < 0) {
            throw new NotEnoughMoneyException("Значение 'amount' не может быть отрицательеым: " + amount);
        }
        if (accountId < 0) {
            throw new UnknownAccountException("Значение 'accountId' не может быть отрицательеым: " + accountId);
        }

        Account account = accountRepository.read(accountId);
        account.setAmount(account.getAmount() - amount);
        accountRepository.update(account);
    }

    @Override
    public int getBalance(int accountId) throws UnknownAccountException, RepositoryException {

        if (accountId < 0) {
            throw new UnknownAccountException("Значение 'accountId' не может быть отрицательеым: " + accountId);
        }

        Account account = accountRepository.read(accountId);
        return account.getAmount();
    }

    @Override
    public void deposit(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, RepositoryException {

        if (amount < 0) {
            throw new NotEnoughMoneyException("Значение 'amount' не может быть отрицательеым: " + amount);
        }
        if (accountId < 0) {
            throw new UnknownAccountException("Значение 'accountId' не может быть отрицательеым: " + accountId);
        }
        Account tmp = accountRepository.read(accountId);
        tmp.setAmount(tmp.getAmount() + amount);
        accountRepository.update(tmp);
    }

    @Override
    public void transfer(int from, int to, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, RepositoryException {

        if (amount < 0) {
            throw new NotEnoughMoneyException("Значение 'amount' не может быть отрицательеым: " + amount);
        }
        if (from < 0) {
            throw new UnknownAccountException("Значение 'from' не может быть отрицательеым: " + from);
        }
        if (to < 0) {
            throw new UnknownAccountException("Значение 'accountId' не может быть отрицательеым: " + to);
        }

        Account accountFrom = accountRepository.read(from);
        Account accountTo = accountRepository.read(to);
        accountFrom.setAmount(accountFrom.getAmount() - amount);
        accountTo.setAmount(accountTo.getAmount() + amount);
        accountRepository.update(accountFrom);
        accountRepository.update(accountTo);
    }
}
