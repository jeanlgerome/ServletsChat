package servletsChat.websocket;

import servletsChat.model.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public final class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public void destroy() {
    }

    @Override
    public void init(final EndpointConfig arg0) {
    }

    @Override
    public Message decode(final String message) throws DecodeException {
        try {
            return Constants.MAPPER.readValue(message, Message.class);
        } catch (IOException e) {
            throw new DecodeException(message, "Unable to decode text to Message", e);
        }
    }

    @Override
    public boolean willDecode(final String message) {
        boolean result = message.contains(Constants.USER_NAME_KEY) && message.contains(Constants.MESSAGE_KEY);
        return result;
    }
}
