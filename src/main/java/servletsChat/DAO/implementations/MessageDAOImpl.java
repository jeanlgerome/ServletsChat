package servletsChat.DAO.implementations;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import servletsChat.DAO.Dao;
import servletsChat.DAO.interfaces.MessageDAO;
import servletsChat.model.Message;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageDAOImpl implements MessageDAO {

    private static Logger log = Logger.getLogger(Dao.class.getName());
    private Session session;

    public List<Message> getMessagesByChat(int chatId) {
        Query query = session.createQuery(" select m from Message m join m.chat c where c.id=:chatId");
        query.setParameter("chatId", chatId);
        List<Message> messagesList = query.list();
        if (messagesList.size() == 0) {
            return null;
        }
        return messagesList;
    }

    public List<Message> getMessagesByChatAndWord(int chatId, String word) {
        Query query = session.createQuery("select m from Message m join m.chat c where c.id=:chatId and m.content  like concat('%',:word,'%')");
        query.setParameter("chatId", chatId);
        query.setParameter("word", word);
        List<Message> messagesList = query.list();
        if (messagesList.size() == 0) {
            return null;
        }
        return messagesList;
    }

    public boolean save(Message message) {
        boolean successful = false;
        Transaction transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.saveOrUpdate(message);
            transaction.commit();
            successful = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.log(Level.WARNING, "messageDao save() operation exception", e);
        }
        return successful;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
