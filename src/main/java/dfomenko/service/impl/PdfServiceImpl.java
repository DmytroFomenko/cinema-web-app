package dfomenko.service.impl;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import dfomenko.service.PdfService;
import dfomenko.utils.HtmlUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;


@Service
public class PdfServiceImpl implements PdfService {

    // final is NOT allowed
    @Autowired
    private HtmlUtils htmlUtils;

    @Override
    public byte[] convertHtmlToPdf(String htmlString) throws IOException {

        Document document = Jsoup.parse(htmlString, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        document.outputSettings().prettyPrint(false);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder pdfRendererBuilder = new PdfRendererBuilder();
            pdfRendererBuilder.toStream(outputStream);
            pdfRendererBuilder.useDefaultPageSize(297, 210, PdfRendererBuilder.PageSizeUnits.MM);
            pdfRendererBuilder.useFont(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("fonts/PTSans-Regular.ttf")).getFile()), "PTSans");
            pdfRendererBuilder.withProducer("FDV Cinema web App");
            pdfRendererBuilder.withW3cDocument(new W3CDom().fromJsoup(document), "/");
            pdfRendererBuilder.run();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new IOException("Error while converting HTML to PDF " + e.getMessage());
        }

    }


    @Override
    public byte[] convertTemplatedHtmlToPdf(String templateFilePath,
                                            Map<String, Object> parameters) throws IOException {
        // Build new mail Html
        String htmlText = htmlUtils.makeHtmlFromTemplate(templateFilePath, parameters);
        return convertHtmlToPdf(htmlText);
    }


}


