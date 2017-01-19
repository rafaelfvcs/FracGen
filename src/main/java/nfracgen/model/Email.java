package nfracgen.model;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    
    private final static Properties prop = new Properties();
    
    private final static String GMAIL_HOST = "smtp.gmail.com";
    private final static String GMAIL_PORT = "587";
    
    private final String ourMail = "contact@nfracgen.com";
    private final String subject  = "nFracGen User Contact";
    
    private final String name;
    private final String email;
    private final String prof;
    private final String company;
    private final String country;
    //private final String comments;
    
    private final Date date = new Date();
    private final String header = "nFracGen mail";
    private final String footer = "nFracGen Software";        
    
    public Email(String name, String email, String prof, String comp,
            String country){
        this.name = name;
        this.email = email;
        this.prof = prof;
        this.company = comp;
        this.country  = country;
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", GMAIL_HOST);
        prop.put("mail.smtp.port", GMAIL_PORT);
    }
    
    public void sendMessage() throws AddressException, MessagingException{
        Session session = Session.getDefaultInstance(prop, null);
        session.setDebug(true);
        Message msg = new MimeMessage(session);
        InternetAddress adress = new InternetAddress(this.email);
        msg.setFrom(adress);
        
        InternetAddress addressTo = new InternetAddress(ourMail);
        msg.setRecipient(Message.RecipientType.TO, addressTo);
                
        msg.setSubject(subject);
        
        String content = this.header+" - "+this.date+
                "<br><br><b>"+this.name+"</b>"
                + "<br>"+this.prof+"<br>"+
                this.company+"<br>"+
                this.country+"<br><br>"+                
                this.footer;                
        msg.setContent(content,"text/html");
        Transport.send(msg);
    }
    
}
