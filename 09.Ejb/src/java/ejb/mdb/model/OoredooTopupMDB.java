package ejb.mdb.model;

import java.util.Date;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/OoredooQueue",
    activationConfig = {
       @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class OoredooTopupMDB implements MessageListener {

    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                System.out.println("\nQueue: I received a TextMessage at " + new Date());
                TextMessage msg = (TextMessage) message;
                System.out.println("Message is : " + msg.getText());
            } else if (message instanceof ObjectMessage) {
                System.out.println("Queue: I received an ObjectMessage "
                        + " at " + new Date());
                ObjectMessage msg = (ObjectMessage) message;
                TopUp topUp = (TopUp) msg.getObject();
                System.out.println("Received TopUp Details: ");
                System.out.println(topUp.toString());
            } else {
                System.out.println("Not valid message for this Queue MDB");
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}