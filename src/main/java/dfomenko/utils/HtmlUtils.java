package dfomenko.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.expression.ThymeleafEvaluationContext;

import java.util.Map;


@Component
public class HtmlUtils {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private ApplicationContext applicationContext;

    public HtmlUtils(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public String makeHtmlFromTemplate(String templateFilePath,
                                       Map<String, Object> parameters) {

        Context templateContext = new Context();

        // Copy all needed variables from thymeleaf environments context to mailTemplateContext
        ThymeleafEvaluationContext thymeleafEvaluationContext = new ThymeleafEvaluationContext(applicationContext, null);
        templateContext.setVariable(ThymeleafEvaluationContext.THYMELEAF_EVALUATION_CONTEXT_CONTEXT_VARIABLE_NAME,
                                    thymeleafEvaluationContext);

        // Copy model variables to mailTemplateContext
        if (parameters != null) {
            templateContext.setVariables(parameters);
        }

        // Build new mail Html
        return templateEngine.process(templateFilePath, templateContext);
    }
}
