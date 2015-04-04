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
    
    public double divide (int numerator, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        return numerator / divisor;
    }
}
