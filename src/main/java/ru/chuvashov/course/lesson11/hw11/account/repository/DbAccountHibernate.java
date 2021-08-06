package ru.chuvashov.course.lesson11.hw11.account.repository;

import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.chuvashov.course.lesson11.hw11.account.model.Account;
import ru.chuvashov.course.lesson11.hw11.account.model.exceptions.UnknownAccountException;
import ru.chuvashov.course.lesson11.hw11.account.repository.exception.RepositoryException;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Chuvashov Sergey
 */
@org.springframework.stereotype.Repository
public class DbAccountHibernate implements Repository<Account> {

    private SessionFactory sessionFactory;

    @Override
    public void create(@NonNull Account item) throws RepositoryException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RepositoryException("Error creating the accounts", e);
        }
    }

    @Override
    public void update(@NonNull Account item) throws RepositoryException, UnknownAccountException {
        if (read(item.getId()) != null) {
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.update(item);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                throw new RepositoryException("Error updating the accounts", e);
            }
        }
    }

    @Override
    public Account read(int id) throws RepositoryException, UnknownAccountException {

        if (id < 1) {
            throw new UnknownAccountException("Некорректный идентификатор акаауета на входе: " + id);
        }
        Account result;
        try {
            Session session = sessionFactory.openSession();
            result = session.get(Account.class, id);
            session.close();
        } catch (Exception e) {
            throw new RepositoryException("Error reading the accounts", e);
        }
        if (result == null) {
            throw new UnknownAccountException("Произошла ошибка при получении объекта с указанным id: " + id);
        }
        return result;
    }

    @Override
    public void delete(@NonNull Account item) throws RepositoryException, UnknownAccountException {

        if (read(item.getId()) != null) {
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.delete(item);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                throw new RepositoryException("Error reading the accounts", e);
            }
        }
    }

    @Override
    public List<Account> getListAccounts() throws RepositoryException {
        List<Account> accounts;
        try {
            Session session = sessionFactory.openSession();
            accounts = (List<Account>) session.createQuery("From Account").list();
            session.close();
        } catch (Exception e) {
            throw new RepositoryException("Error reading the accounts", e);
        }
        if (accounts == null) {
            throw new RepositoryException("Error reading the accounts");
        } else {
            return accounts;
        }
    }

    /**
     * Инициализирует поле 'sessionFactory'.
     */
    @PostConstruct
    private void initialization() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Account.class);
            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
    }
}


