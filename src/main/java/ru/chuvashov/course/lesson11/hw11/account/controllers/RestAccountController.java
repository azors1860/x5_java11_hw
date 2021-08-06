package ru.chuvashov.course.lesson11.hw11.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.chuvashov.course.lesson11.hw11.account.model.Account;
import ru.chuvashov.course.lesson11.hw11.account.model.exceptions.UnknownAccountException;
import ru.chuvashov.course.lesson11.hw11.account.repository.Repository;
import ru.chuvashov.course.lesson11.hw11.account.repository.exception.RepositoryException;
import ru.chuvashov.course.lesson11.hw11.account.service.AccountService;

import java.util.List;

/**
 * @author Chuvashov Sergey
 */

@RestController()
public class RestAccountController {

    private final Repository<Account> repository;
    private final AccountService service;

    @Autowired
    public RestAccountController(Repository repository, AccountService service) {
        this.repository = repository;
        this.service = service;
    }

    /**
     * Возвращает аккаунт id которого указано в параметре.
     *
     * @param number - Идентификатор аккаунта.
     * @return ответ для HTTP c указанным в параметре аккаунтом в JSON.
     * @throws RepositoryException    - В случае каких-либо проблем с БД.
     * @throws UnknownAccountException - В случае, если аккаунт с указанным ID не существует.
     */
    @GetMapping("/account/{number}")
    @ResponseBody
    public ResponseEntity<Account>
    getAccount(@PathVariable("number") String number)
            throws RepositoryException, UnknownAccountException {
        Account account = repository.read(Integer.parseInt(number));
        return ResponseEntity.accepted().body(account);
    }


    /**
     * Возвращает все аккаунты.
     *
     * @return ответ для HTTP cо всеми аккаунтами в JSON.
     * @throws RepositoryException    - В случае каких-либо проблем с БД.
     */
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>>
    getAccounts() throws RepositoryException {
        List<Account> accountList = repository.getListAccounts();
        return ResponseEntity.accepted().body(accountList);
    }
}
