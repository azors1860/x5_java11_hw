package ru.chuvashov.course.lesson11.hw11.account.model.exceptions;

/**
 * @author Chuvashov Sergey
 * Выбрасывается, чтобы указать, что на счёте недостаточно средств или при попытке установить отрицательное значение.
 */
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException(String message, Throwable e) {
        super(message, e);
    }
}
