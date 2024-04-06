package br.com.joaosbarbosa.backend.modal;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Service
public class EmailTemplateLoader {

    private final freemarker.template.Configuration configuration;

    public EmailTemplateLoader(FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean) {
        this.configuration = freeMarkerConfigurationFactoryBean.getObject();
    }

    public String getTemplate(String templateName, Map<String, Object> model) {
        try {
            Template template = configuration.getTemplate(templateName + ".ftl");
            StringWriter writer = new StringWriter();
            template.process(model, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException("Erro ao carregar o template Freemarker: " + e.getMessage(), e);
        }
    }
}
