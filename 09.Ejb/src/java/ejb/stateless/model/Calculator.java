package ejb.stateless.model;

import javax.ejb.Stateless;
import javax.jws.WebService;
	
@Stateless
@WebService
public class Calculator implements ICalculator {
	
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }
}
