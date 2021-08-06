package ru.chuvashov.course.lesson11.hw11.account.repository;

import ru.chuvashov.course.lesson11.hw11.account.model.exceptions.UnknownAccountException;
import ru.chuvashov.course.lesson11.hw11.account.repository.exception.RepositoryException;

import java.util.List;

/**
 * @param <T> - POJO объект.
 * @author Chuvashov Sergey
 */
public interface Repository<T> {

    /**
     * Создаёт новый объект
     *
     * @param item - Объект который должен быть создан.
     * @throws RepositoryException - В случае, если возникнут проблемы с чтением информации из
     *                              БД, либо при записи информации в БД.
     */
    void create(T item) throws RepositoryException;

    /**
     * Обновляет (изменяет) существующий объект в базе.
     *
     * @param item - Объект который должен быть изменен.
     * @throws UnknownAccountException Если аккаунт с идентификатором из параметра не найден в списке.
     */
    void update(T item) throws RepositoryException, UnknownAccountException;

    /**
     * Возвращает объект с соответствующим идентификатором из параметра (из БД).
     *
     * @param id - Идентификартор объекта.
     * @return - Объект аккаунта с идентификатором указанный в параметре метода.
     * @throws UnknownAccountException Если аккаунт с идентификатором из параметра не найден в списке,
     *                                 либо если параметр имеет отризательное значение.
     * @throws RepositoryException    - В случае, если возникнут проблемы с чтением информации из
     *                                 БД, либо при записи информации в БД.
     */
    T read(int id) throws UnknownAccountException, RepositoryException;

    /**
     * Удаляет переданный объект из БД.
     *
     * @param item - Объект, который должен быть удалён из базы.
     * @throws UnknownAccountException Если аккаунт с идентификатором из параметра не найден в списке.
     * @throws RepositoryException    - В случае, если возникнут проблемы с чтением информации из
     *                                 БД, либо при записи информации в БД.
     */
    void delete(T item) throws UnknownAccountException, RepositoryException;

    /**
     * Метод для получения всех объектов, имеющихся в БД.
     *
     * @return - Лист со всеми аккаунтами.
     * @throws RepositoryException - В случае, если возникнут проблемы с чтением информации из
     *                              БД, либо при записи информации в БД.
     */
    List<T> getListAccounts() throws RepositoryException;
}
