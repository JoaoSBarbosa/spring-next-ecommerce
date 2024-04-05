package br.com.joaosbarbosa.backend.services;

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

import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public String sendEmail(Email email) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(email.getDestination());
            simpleMailMessage.setSubject(email.getTitle());
            simpleMailMessage.setText(email.getMessage());

            javaMailSender.send(simpleMailMessage);

            System.out.println("EMAIL ENVIADO PARA: " + email.getDestination());
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


//    public String sendEmailMime(String destination, String recipientName, String title) {
//        try {
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom(from);
//            helper.setTo(destination);
//            helper.setSubject(title);
//
//            // Construa o corpo do email em HTML
//            String htmlContent = "<html>" +
//                    "<head>" +
//                    "<style>" +
//                    "body {" +
//                    "font-family: Arial, sans-serif;" +
//                    "background-color: #f4f4f4;" +
//                    "margin: 0;" +
//                    "padding: 0;" +
//                    "}" +
//                    ".container {" +
//                    "max-width: 600px;" +
//                    "margin: 20px auto;" +
//                    "padding: 20px;" +
//                    "background-color: #fff;" +
//                    "border-radius: 10px;" +
//                    "box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
//                    "}" +
//                    "h1 {" +
//                    "color: #333;" +
//                    "}" +
//                    "p {" +
//                    "color: #666;" +
//                    "}" +
//                    "</style>" +
//                    "</head>" +
//                    "<body>" +
//                    "<div class='container'>" +
//                    "<h1>Seja bem-vindo, " + recipientName + "!</h1>" +
//                    "<p>Seu cadastro foi realizado com sucesso. Em breve você recebera sua senha de acesso pelo e-mail.</p>" +
//                    "<img src='cid:image' style='max-width: 100%; height: auto;' />" +
//                    "</div>" +
//                    "</body>" +
//                    "</html>";
//
//            helper.setText(htmlContent, true);
//
//            helper.addInline("image", new ClassPathResource("static/images/wellcome.jpeg"), "image/jpeg");
//            javaMailSender.send(message);
//
//            System.out.println("EMAIL ENVIADO PARA: " + destination);
//            return "Email enviado com sucesso!";
//        } catch (Exception e) {
//            System.out.println("EMAIL não ENVIADO. ERRO: " + e);
//            return "Erro ao enviar o email: " + e;
//        }
//    }

}