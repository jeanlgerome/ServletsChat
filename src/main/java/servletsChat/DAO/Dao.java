package servletsChat.DAO;

import org.hibernate.Session;
import servletsChat.DAO.implementations.ChatDAOImpl;
import servletsChat.DAO.implementations.MessageDAOImpl;
import servletsChat.DAO.implementations.UserDAOImpl;
import servletsChat.DAO.interfaces.ChatDAO;
import servletsChat.DAO.interfaces.MessageDAO;
import servletsChat.DAO.interfaces.UserDAO;
import servletsChat.DAO.utils.HibernateSessionFactory;
import servletsChat.model.Chat;
import servletsChat.model.Message;
import servletsChat.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Dao {
    private static Logger log = Logger.getLogger(Dao.class.getName());

    public static List<Chat> getChatsByUser(String login) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ChatDAO chatDAO = new ChatDAOImpl();
        chatDAO.setSession(session);
        List<Chat> chats = chatDAO.findByUser(login);
        session.close();
        return chats;
    }

    public static List<Message> getMessages(int chatId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        MessageDAO messageDAO = new MessageDAOImpl();
        messageDAO.setSession(session);
        List<Message> messages = messageDAO.getMessagesByChat(chatId);
        session.close();
        return messages;
    }

    public static List<User> getUsersByChat(int chatId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        UserDAO userDAO = new UserDAOImpl();
        userDAO.setSession(session);
        List<User> users = userDAO.findByChat(chatId);
        session.close();
        return users;
    }

    public static List<Chat> getChats() {
        return null;
    }

    public static Chat getChatById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ChatDAO chatDAO = new ChatDAOImpl();
        chatDAO.setSession(session);
        Chat chat = chatDAO.findById(id);
        session.close();
        return chat;
    }

    public static List<Chat> getChatsByChatName(String chatName) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ChatDAO chatDAO = new ChatDAOImpl();
        chatDAO.setSession(session);
        List<Chat> chatList = chatDAO.findByChatName(chatName);
        session.close();
        return chatList;
    }

    public static List<Chat> getChatsByChatNameAndUSer(String chatName, String login) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ChatDAO chatDAO = new ChatDAOImpl();
        chatDAO.setSession(session);
        List<Chat> chatList = chatDAO.findByChatNameAndUser(chatName, login);
        session.close();
        return chatList;
    }

    public static User getUser(String login) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        UserDAO userDAO = new UserDAOImpl();
        userDAO.setSession(session);
        User user = userDAO.findByLogin(login);
        session.close();
        return user;
    }

    public static User getUser(String login, String password) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        UserDAO userDAO = new UserDAOImpl();
        userDAO.setSession(session);
        User user = userDAO.findByLoginAndPassword(login, password);
        session.close();
        return user;

    }

    public static void addChat(Chat chat) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ChatDAO chatDAO = new ChatDAOImpl();
        chatDAO.setSession(session);
        chatDAO.save(chat);
        session.close();
    }

    public static boolean addUser(User user) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        UserDAO userDAO = new UserDAOImpl();
        userDAO.setSession(session);
        boolean successful = userDAO.save(user);
        session.close();
        return successful;
    }

    public static void addMessage(Message message) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        MessageDAO messageDAO = new MessageDAOImpl();

        messageDAO.setSession(session);
        messageDAO.save(message);
        session.close();
    }

    public static void addUserToChat(int chatId, String login) {
        Session sessionUser = HibernateSessionFactory.getSessionFactory().openSession();
        Session sessionChat = HibernateSessionFactory.getSessionFactory().openSession();
        UserDAO userDAO = new UserDAOImpl();
        ChatDAO chatDAO = new ChatDAOImpl();
        userDAO.setSession(sessionUser);
        chatDAO.setSession(sessionChat);
        User user = userDAO.findByLogin(login);
        Chat chat = chatDAO.findById(chatId);
        sessionUser.close();
        chat.addUser(user);
        chatDAO.save(chat);
        sessionChat.close();
    }

    public static void deleteUserFromChat(int chatId, String login) {
        Session sessionUser = HibernateSessionFactory.getSessionFactory().openSession();
        Session sessionChat = HibernateSessionFactory.getSessionFactory().openSession();
        ChatDAO chatDAO = new ChatDAOImpl();
        UserDAO userDAO = new UserDAOImpl();
        userDAO.setSession(sessionUser);
        chatDAO.setSession(sessionChat);
        Chat chat = chatDAO.findById(chatId);
        User user = userDAO.findByLogin(login);
        sessionUser.close();
        chat.deleteUser(user);
        chatDAO.save(chat);
        sessionChat.close();
    }

    public static boolean isUserInChat(int chatId, String login) {
        boolean result;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        UserDAO userDAO = new UserDAOImpl();
        userDAO.setSession(session);
        result = userDAO.isUserInChat(chatId, login);
        session.close();
        return result;
    }

    public static List<User> getUsers() {
        return null;
    }

    public static void setUsers(ArrayList<User> users) {

    }
}
