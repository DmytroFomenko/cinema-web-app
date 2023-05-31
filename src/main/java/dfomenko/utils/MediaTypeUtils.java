package dfomenko.utils;

import jakarta.servlet.ServletContext;
import org.springframework.http.MediaType;

public class MediaTypeUtils {

    // *.pdf, *.zip..
    public static MediaType getMediaTypeForFileName(String fileName,
                                                    ServletContext servletContext) {

        // image/gif, application/pdf, application/xml...
        String mineType = servletContext.getMimeType(fileName);
        try {
            return MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}