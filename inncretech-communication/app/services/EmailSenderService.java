package services;

import models.Communication;
import models.EventType;
import models.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateService templateService;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(final Communication comm) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                Template template = Template.find.where().eq("name", EventType.getById(comm.commType)).findList().get(0);
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(comm.contactInfo));
                mimeMessage.setFrom(new InternetAddress("info@incontrolads.com"));
                mimeMessage.setSubject(template.getSubject());
                mimeMessage.setContent(templateService.createCommunicationBody(comm, template), "text/html");
            }
        };
        mailSender.send(preparator);
        Communication.find.byId(comm.id).markAsSent();
    }
}