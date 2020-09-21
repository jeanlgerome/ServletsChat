package servletsChat.websocket;

import servletsChat.DAO.Dao;
import servletsChat.model.Message;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SessionsManager {

    private static final Lock LOCK = new ReentrantLock();
    private static final Map<Integer, Set<Session>> SESSIONS = new ConcurrentHashMap<>();

    public static void sendMessage(Message message, Session origin) {
        System.out.println(message);
        int chatId = (int) origin.getUserProperties().get(Constants.CHAT_ID);
        Dao.addMessage(new Message(Dao.getChatById(chatId), message));
        getSessionsByChat(chatId).stream().filter(session -> !session.equals(origin)).forEach(session -> {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }

    public static boolean addSessionToChat(int chatId, Session session) {
        boolean result = false;
        try {
            LOCK.lock();
            if (containsChat(chatId)) {
                SESSIONS.get(chatId).add(session);
            } else {
                Set<Session> sessionsSet = new CopyOnWriteArraySet<>();
                sessionsSet.add(session);
                SESSIONS.put(chatId, sessionsSet);
                result = true;
            }
        } finally {
            LOCK.unlock();
        }
        return result;
    }

    public static void closeSession(final Session session, final CloseReason.CloseCodes closeCode, final String message) {
        assert !Objects.isNull(session) && !Objects.isNull(closeCode);

        try {
            session.close(new CloseReason(closeCode, message));
        } catch (IOException e) {
            throw new RuntimeException("Unable to close session", e);
        }
    }

    public static Set<Session> getSessionsByChat(int chatId) {
        return SESSIONS.get(chatId);
    }

    public static boolean containsChat(int chatId) {
        return SESSIONS.containsKey(chatId);
    }

    public static boolean containsSession(int chatId, Session session) {
        return SESSIONS.get(chatId).contains(session);
    }

    public static boolean removeSession(int chatId, Session session) {
        return SESSIONS.get(chatId).remove(session);
    }

    public static void clear() {
        SESSIONS.clear();
    }

}
