package servletsChat.DAO.implementations;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import servletsChat.DAO.Dao;
import servletsChat.DAO.interfaces.ChatDAO;
import servletsChat.model.Chat;
import servletsChat.model.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatDAOImpl implements ChatDAO {

    private static Logger log = Logger.getLogger(Dao.class.getName());
    private Session session;

    public Chat findById(int chatId) {
        Chat chat = null;
        try {
            chat = session.load(Chat.class, chatId);
            Hibernate.initialize(chat);
        } catch (Exception e) {
            log.log(Level.WARNING, "ChatDAO findById exception", e);
        }
        return chat;
    }

    public List<Chat> findByUser(String login) {
        Query query = session.createQuery("select c from Chat c join c.users u where u.login=:login");
        query.setParameter("login", login);
        List<Chat> chatsList = query.list();
        return chatsList;
    }

    public List<Chat> findByChatName(String chatName) {
        Query query = session.createQuery("from Chat where name like concat('%',:chatName,'%')");
        query.setParameter("chatName", chatName);
        List<Chat> list = query.list();
        return list;
    }

    public List<Chat> findByChatNameAndUser(String chatName, String login) {
        Query query = session.createQuery("select c from Chat c join c.users u where u.login=:login and c.name like concat('%',:chatName,'%')");
        query.setParameter("chatName", chatName);
        query.setParameter("login", login);
        List<Chat> list = query.list();
        return list;
    }

    public boolean save(Chat chat) {
        boolean successful = false;
        Transaction transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.saveOrUpdate(chat);
            transaction.commit();
            successful = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.log(Level.WARNING, "chatDao save() operation exception", e);
        }
        return successful;
    }

    public boolean deleteUserFromChat(User user, Chat chat) {
        boolean successful = false;
        Transaction transaction = session.beginTransaction();
        try {
            transaction.begin();
            chat.deleteUser(user);
            session.saveOrUpdate(chat);
            transaction.commit();
            successful = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.log(Level.WARNING, "chatDao deleteUserFromChat() operation exception", e);
        }
        return successful;

    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
