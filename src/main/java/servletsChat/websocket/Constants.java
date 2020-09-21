package servletsChat.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;

final class Constants {

    static final String INSTANTIATION_NOT_ALLOWED = "Instantiation not allowed";
    static final String USER_NAME_KEY = "username";
    static final String CHAT_ID = "chatId";
    static final String MESSAGE_KEY = "content";// message
    static final ObjectMapper MAPPER = new ObjectMapper();

    private Constants() {
        throw new IllegalStateException(INSTANTIATION_NOT_ALLOWED);
    }
}
