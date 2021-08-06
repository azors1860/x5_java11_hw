package ru.chuvashov.course.lesson11.hw11.account.model.exceptions;

/**
 * @author Chuvashov Sergey
 * Выбрасывается, чтобы указать, что аккаунт не найден.
 */
public class UnknownAccountException extends Exception {
    public UnknownAccountException(String message) {
        super(message);
    }

    public UnknownAccountException(String message, Throwable e) {
        super(message, e);
    }
}
