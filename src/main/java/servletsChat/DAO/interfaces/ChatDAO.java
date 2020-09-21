package servletsChat.DAO.interfaces;

import org.hibernate.Session;
import servletsChat.model.Chat;
import servletsChat.model.User;

import java.util.List;

public interface ChatDAO {
    Chat findById(int id);

    List<Chat> findByUser(String login);

    List<Chat> findByChatName(String name);

    List<Chat> findByChatNameAndUser(String chatName, String login);

    boolean save(Chat chat);

    boolean deleteUserFromChat(User user, Chat chat);

    void setSession(Session session);

}
