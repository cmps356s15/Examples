package qu.ejb.asynchronous;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Stateless
public class OrderEJB {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Resource
    SessionContext ctx;

    // ======================================
    // =           Public Methods           =
    // ======================================

    @Asynchronous
    public void sendEmailOrderComplete(Order order, Customer customer) {
        // Very Long task
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
        }
    }

    @Asynchronous
    public void printOrder(Order order) {
        // Very Long task
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
        }
    }

    @Asynchronous
    public Future<Integer> sendOrderToWorkflow(Order order) {
        Integer status = 0;
        // processing
        status = 1;
        if (ctx.wasCancelCalled()) {
            return new AsyncResult<Integer>(2);
        }
        // processing
        return new AsyncResult<Integer>(status);
    }
}