package calculator.model;

import javax.ejb.Stateless;
	
@Stateless
public class Calculator implements ICalculator {
	
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }
}
