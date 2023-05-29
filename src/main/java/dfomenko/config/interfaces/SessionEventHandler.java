package dfomenko.config.interfaces;

import jakarta.servlet.http.HttpSession;

@FunctionalInterface
public interface SessionEventHandler {
    void handleSessionEvent(HttpSession session);
}
