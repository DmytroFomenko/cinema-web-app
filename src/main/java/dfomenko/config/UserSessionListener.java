package dfomenko.config;


import dfomenko.config.interfaces.SessionEventHandler;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Component
public class UserSessionListener implements HttpSessionListener {

    private SessionEventHandler sessionCreatedHandler;
    private SessionEventHandler sessionDestroyedHandler;

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        if (sessionCreatedHandler != null) {
            sessionCreatedHandler.handleSessionEvent(sessionEvent.getSession());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        if (sessionDestroyedHandler != null) {
            sessionDestroyedHandler.handleSessionEvent(sessionEvent.getSession());
        }
    }

}
