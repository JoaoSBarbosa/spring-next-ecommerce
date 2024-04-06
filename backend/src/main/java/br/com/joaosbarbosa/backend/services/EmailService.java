package br.com.joaosbarbosa.backend.services;

import br.com.joaosbarbosa.backend.modal.EmailTemplateLoader;
import br.com.joaosbarbosa.backend.modal.email.Email;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailTemplateLoader emailTemplateLoader;

    @Value("${spring.mail.username}")
    private String from;

    public String sendEmail(String destine, String title, String message) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(destine);
            simpleMailMessage.setSubject(title);
            simpleMailMessage.setText(message);

            javaMailSender.send(simpleMailMessage);

            System.out.println("EMAIL ENVIADO PARA: " + destine);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            System.out.println("EMAIL não ENVIADO. ERRO: " + e);

            return "Erro ao enviar o email: " + e;
        }
    }

    public String sendEmailMime(String destination, String recipientName, String title) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(destination);
            helper.setSubject(title);

            // Construa o corpo do email em HTML
            String htmlContent = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body {" +
                    "font-family: Arial, sans-serif;" +
                    "background-color: #f4f4f4;" +
                    "margin: 0;" +
                    "padding: 0;" +
                    "}" +
                    ".container {" +
                    "max-width: 600px;" +
                    "margin: 20px auto;" +
                    "padding: 20px;" +
                    "background-color: #ffffff;" +
                    "border-radius: 10px;" +
                    "box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
                    "}" +
                    "h1 {" +
                    "color: #333333;" +
                    "}" +
                    "p {" +
                    "color: #666666;" +
                    "}" +
                    ".logo {" +
                    "text-align: center;" +
                    "}" +
                    ".logo img {" +
                    "max-width: auto;" +
                    "max-height: 80px;" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<div class='logo'>" +
                    "<img src='cid:logo' alt='Wolf E-Commerce Logo'>" +
                    "</div>" +
                    "<h1>Olá, " + recipientName + "!</h1>" +
                    "<p>Parabéns pelo seu cadastro no Wolf E-Commerce!</p>" +
                    "<p>Você está prestes a entrar em uma nova experiência de compras online. Em nosso site, você encontrará uma ampla variedade de produtos, ótimos preços e um excelente atendimento ao cliente.</p>" +
                    "<p>Fique ligado em nossas promoções exclusivas e novos lançamentos.</p>" +
                    "<img src='cid:image' style='max-width: 100%; height: auto;' />"+
                    "<p>Acesse nosso site e comece a explorar agora mesmo: <a href='https://www.wolfecommerce.com'>www.wolfecommerce.com</a></p>" +
                    "<p>Atenciosamente,<br/>Equipe Wolf E-Commerce</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true);

            // Anexar a imagem do logo
            helper.addInline("logo", new ClassPathResource("static/images/barbosa.png"), "image/png");
            helper.addInline("image", new ClassPathResource("static/images/wellcome.jpeg"), "image/jpeg");

            javaMailSender.send(message);

            System.out.println("EMAIL ENVIADO PARA: " + destination);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            System.out.println("EMAIL não ENVIADO. ERRO: " + e);
            return "Erro ao enviar o email: " + e;
        }
    }


    public String sendEmailChangePassword(String destination, String recipientName, String title) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(destination);
            helper.setSubject("Senha Alterada");

            Map<String, Object> model = new HashMap<>();
            model.put("recipientName", recipientName);

            String htmlContent = emailTemplateLoader.getTemplate("password-changed", model);
            helper.setText(htmlContent, true);

            // Anexar a imagem do logo
            helper.addInline("logo", new ClassPathResource("static/images/barbosa.png"), "image/png");
            helper.addInline("image", new ClassPathResource("static/images/change_password.jpeg"), "image/jpeg");

            javaMailSender.send(message);

            System.out.println("EMAIL ENVIADO PARA: " + destination);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            System.out.println("EMAIL não ENVIADO. ERRO: " + e);
            return "Erro ao enviar o email: " + e;
        }
    }

    public String sendEmailExpiradCode(String destination, String recipientName, String title) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(destination);
            helper.setSubject("Código expirado");


            // Carregar o template Freemarker
            Map<String, Object> model = new HashMap<>();
            model.put("recipientName", recipientName);
            model.put("recipientName", recipientName);

            String htmlContent = emailTemplateLoader.getTemplate("expired-code", model);
            helper.setText(htmlContent, true);

            // Anexar a imagem do logo
            helper.addInline("logo", new ClassPathResource("static/images/barbosa.png"), "image/png");
            helper.addInline("image", new ClassPathResource("static/images/404.jpeg"), "image/jpeg");

            javaMailSender.send(message);

            System.out.println("EMAIL ENVIADO PARA: " + destination);
            return "Email enviado com sucesso!";

        } catch (Exception e) {
            System.out.println("EMAIL não ENVIADO. ERRO: " + e);
            return "Erro ao enviar o email: " + e;
        }
    }

    public String sendPasswordResetCode2(String destination, String recipientName, String code) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(destination);
            helper.setSubject("Seu código de alteração de senha");

            // Carregar o template Freemarker
            Map<String, Object> model = new HashMap<>();
            model.put("recipientName", recipientName);
            model.put("code", code);

            String htmlContent = emailTemplateLoader.getTemplate("password-reset-code", model);
            helper.setText(htmlContent, true);

            // Anexar a imagem do logo
            helper.addInline("logo", new ClassPathResource("static/images/barbosa.png"), "image/png");
            helper.addInline("image", new ClassPathResource("static/images/getCode.jpg"), "image/jpg");

            javaMailSender.send(message);

            System.out.println("EMAIL ENVIADO PARA: " + destination);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            System.out.println("EMAIL não ENVIADO. ERRO: " + e);
            return "Erro ao enviar o email: " + e;
        }
    }
}