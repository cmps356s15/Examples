package ejb.mdb.controller;

import ejb.mdb.model.TopUp;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/* 
 * ToDo before running this example:
 * Open the command line then go to C:\glassfish-4.1\bin (or the folder where glassfish is installed) then run the following:
 * asadmin create-jms-resource --restype javax.jms.Queue jms/OoredooQueue
 * asadmin create-jms-resource --restype javax.jms.QueueConnectionFactory jms/OoredooQueueConnectionFactory
 * Note asadmin is located @ ...glassfish-?-?\bin  (...your glassfish installation folder)
 */
@WebServlet("/topup")
public class OoredooTopup extends HttpServlet {

    @Resource(mappedName = "jms/OoredooQueue")
    private Queue queue;
    @Resource(mappedName = "jms/OoredooQueueConnectionFactory")
    private QueueConnectionFactory queueFactory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QueueConnection queueConnection = null;
        try (JMSContext context = queueFactory.createContext()) {

            //1. Send a text message
            String textMessage = "Ooredoo Mabrouk the new name! Although it is quite boring lol!";
            System.out.println("1. Sent TextMessage to the Queue");
            context.createProducer().send(queue, textMessage);

            //2. Sending ObjectMessage to the Queue
            ObjectMessage objectMessage = context.createObjectMessage();
            TopUp topUp = new TopUp("Hala", "55667788", 50);
            objectMessage.setObject(topUp);
            context.createProducer().send(queue, objectMessage);
            System.out.println("2. Sent TopUp ObjectMessage to the Queue");

        } catch (JMSException ex) {
            Logger.getLogger(OoredooTopup.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("confirmationMessage", "Mabrouk TextMessage & TopUp ObjectMessage sent to OoredooQueue");
        request.getRequestDispatcher("topup-confirmation.jsp").forward(request, response);
    }
}
