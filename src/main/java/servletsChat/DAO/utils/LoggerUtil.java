package servletsChat.DAO.utils;

import servletsChat.websocket.ChatEndPoint;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerUtil {

    private static Logger log = Logger.getLogger(ChatEndPoint.class.getName());

    static {

        try {
            LogManager.getLogManager().readConfiguration(
                    LoggerUtil.class.getClassLoader().getResourceAsStream("logging.properties"));
            log.info("scscscs");
        } catch (
                IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

    }
}
