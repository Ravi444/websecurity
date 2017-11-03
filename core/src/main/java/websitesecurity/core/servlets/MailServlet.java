package websitesecurity.core.servlets;

import java.io.IOException;
import java.net.URL;
import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
//import javax.mail.MessagingException;

import org.apache.commons.lang.text.StrLookup;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.mail.MailTemplate;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

@SlingServlet(paths = "/bin/websitesecurity/emailservlet", methods = "POST", metatype = false)
public class MailServlet extends SlingAllMethodsServlet {
}
  /*  private static final long serialVersionUID = 2598426539166789515L;
    
    @Reference
    private MessageGatewayService messageGatewayService;
    
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public MailServlet() {
    }

    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
     
        String toaddress = request.getParameter("toaddress");
		
			try {
				SendHHTMLEmail(request, toaddress);
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
		
		response.getWriter().write("EMAIL GONE"); 
    }

    private void SendHHTMLEmail(SlingHttpServletRequest request, String toaddress) throws IOException, MessagingException {
        
        try {
         
            Map<String, String> myMap = new HashMap<String, String>();
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            myMap.put("time", timeStamp);
            String template = "/etc/notification/email/html/com.day.cq.collab.forum/en.txt";
            Resource templateRsrc = request.getResourceResolver().getResource(template);
            MailTemplate mailTemplate = MailTemplate.create(templateRsrc.getPath(), (Session) templateRsrc.getResourceResolver().adaptTo(Session.class));
            HtmlEmail email = (HtmlEmail) mailTemplate.getEmail(StrLookup.mapLookup(myMap), HtmlEmail.class);
         
            EmailAttachment attachment = new EmailAttachment();
            attachment.setURL(new URL("http://www.bbc.co.uk/webwise/2010/pdf/level-one/scenarios/email/what-is-email.pdf"));
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            
            email.addTo(toaddress);
            email.setSubject("AEM Email Template");
            email.setFrom("vasudevmunagala@gmail.com");
            email.attach(attachment);
            
            MessageGateway<HtmlEmail> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
            messageGateway.send(email);
        } catch (EmailException e) {
            log.info(e.getMessage());
        }
    }

    protected void bindMessageGatewayService(MessageGatewayService paramMessageGatewayService) {
        messageGatewayService = paramMessageGatewayService;
    }

    protected void unbindMessageGatewayService(MessageGatewayService paramMessageGatewayService) {
        if (messageGatewayService == paramMessageGatewayService) {
            messageGatewayService = null;
        }
    }
}*/