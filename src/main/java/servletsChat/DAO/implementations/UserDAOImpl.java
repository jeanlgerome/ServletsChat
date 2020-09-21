package servletsChat.DAO.implementations;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import servletsChat.DAO.Dao;
import servletsChat.DAO.interfaces.UserDAO;
import servletsChat.model.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

    private static Logger log = Logger.getLogger(Dao.class.getName());
    private Session session;

    public List<User> findAll() {
        return session.createQuery("from Users").list();
    }

    public User findByLoginAndPassword(String login, String password) {
        Query query = session.createQuery("from User where login = :paramName and password = :paramPassword");
        query.setParameter("paramName", login);
        query.setParameter("paramPassword", password);
        List<User> list = query.list();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public boolean save(User user) {
        boolean successful = false;
        Transaction transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.saveOrUpdate(user);
            transaction.commit();
            successful = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.log(Level.WARNING, "userDao save() operation exception", e);
        }
        return successful;
    }

    public boolean delete(User user) {
        boolean successful = false;
        Transaction transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.delete(user);
            transaction.commit();
            successful = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.log(Level.WARNING, "userDao delete() operation exception", e);
        }
        return successful;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public List<User> findByChat(int chatId) {
        Query query = session.createQuery("select u from User u join u.chats c where c.id=:chatId");
        query.setParameter("chatId", chatId);
        List<User> users = query.list();
        return users;
    }

    public boolean isUserInChat(int chatId, String login) {
        Query query = session.createQuery("select u from User u join u.chats c where c.id=:chatId and u.login=:login");
        query.setParameter("chatId", chatId);
        query.setParameter("login", login);
        List<User> users = query.list();
        if (users.size() > 0) {
            return true;
        }
        return false;
    }

    public User findByLogin(String login) {
        User user = null;
        try {
            Criteria criteria = session.createCriteria(User.class);
            user = (User) criteria.add(Restrictions.eq("login", login))
                    .uniqueResult();
        } catch (Exception e) {
            log.log(Level.WARNING, "ChatDAO findById exception", e);
        }
        return user;
    }
}
