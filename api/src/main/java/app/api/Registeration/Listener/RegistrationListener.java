package app.api.Registeration.Listener;
//TODO:Have to improve mail sending
import app.api.Persistence.Entity.User;
import app.api.Registeration.OnRegistrationCompleteEvent;
import app.api.Service.IUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Async
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final IUserService service;

    private final MessageSource messages;

    private final JavaMailSender mailSender;



    public RegistrationListener(IUserService service, MessageSource messages, JavaMailSender mailSender) {
        this.service = service;
        this.messages = messages;
        this.mailSender = mailSender;
    }
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {

            this.confirmRegistration(event);
    }
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/registration-confirm?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());


        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + confirmationUrl);
        mailSender.send(email);
    }


}
