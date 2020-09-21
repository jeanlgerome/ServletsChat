package servletsChat.DAO.interfaces;

import org.hibernate.Session;
import servletsChat.model.Message;

import java.util.List;

public interface MessageDAO {
    List<Message> getMessagesByChat(int chatId);

    List<Message> getMessagesByChatAndWord(int chatId, String word);

    boolean save(Message message);

    void setSession(Session session);
}
