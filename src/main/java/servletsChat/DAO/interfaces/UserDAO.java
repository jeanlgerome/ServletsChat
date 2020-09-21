package servletsChat.DAO.interfaces;

import org.hibernate.Session;
import servletsChat.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    User findByLoginAndPassword(String login, String password);

    boolean save(User contact);

    boolean delete(User contact);

    List<User> findByChat(int chatId);

    boolean isUserInChat(int chatId, String login);

    User findByLogin(String login);

    void setSession(Session session);
}