package pl.galajus.brokenpediabackend.user.common.mail;

public interface EmailSender {
    void send(String to, String subject, String msg);

}
