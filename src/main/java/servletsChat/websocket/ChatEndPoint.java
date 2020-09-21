package servletsChat.websocket;

import servletsChat.DAO.Dao;
import servletsChat.model.Message;

import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/chat/{chatId}/{username}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public final class ChatEndPoint {

    private static Logger log = Logger.getLogger(ChatEndPoint.class.getName());

    @OnOpen
    public void onOpen(@PathParam(Constants.USER_NAME_KEY) final String userName,
                       @PathParam(Constants.CHAT_ID) final String paramChatId, final Session session) {

        int chatId = Integer.parseInt(paramChatId);
        log.info("class: " + log.getName() + "   " + "onOpen method started");

        if (Objects.isNull(userName) || userName.isEmpty() || Objects.isNull(chatId) || Objects.isNull(Dao.getChatById(chatId))) {
            log.log(Level.WARNING, "class: " + log.getName() + "   " + "Correct User name and chatId is required. Exception has been thrown");
            throw new RegistrationFailedException("User name and chatId is required");
        } else {
            session.getUserProperties().put(Constants.USER_NAME_KEY, userName);
            session.getUserProperties().put(Constants.CHAT_ID, chatId);
            log.info("class: " + log.getName() + "   " + "session has been registered");
            SessionsManager.addSessionToChat(chatId, session);
        }
    }

    @OnError
    public void onError(final Session session, final Throwable throwable) {
        log.log(Level.WARNING, "class: " + log.getName() + "   " + "onError");
        if (throwable instanceof RegistrationFailedException) {
            SessionsManager.closeSession(session, CloseCodes.VIOLATED_POLICY, throwable.getMessage());
        }
    }

    @OnMessage
    public void onMessage(final Message message, final Session session) {
        SessionsManager.sendMessage(message, session);
        log.info("class: " + log.getName() + "   " + "onMessage:  " + message.getContent());
    }

    @OnClose
    public void onClose(final Session session) {
        log.info("class: " + log.getName() + "   " + "onClose");
        int chatId = (int) session.getUserProperties().get(Constants.CHAT_ID);
        if (SessionsManager.removeSession(chatId, session)) {
            log.info("Session closed for" + session.getUserProperties().get(Constants.USER_NAME_KEY));
            SessionsManager.sendMessage(new Message((String) session.getUserProperties().get(Constants.USER_NAME_KEY), "  left the chat "), session);

        }
    }

    private static final class RegistrationFailedException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public RegistrationFailedException(final String message) {
            super(message);
        }
    }
}
