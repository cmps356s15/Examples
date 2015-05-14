package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

@Stateful
@StatefulTimeout(value = 20, unit = TimeUnit.MINUTES)
public class ShoppingCartBean implements ShoppingCart {

    private HashMap<String, Integer> cart = new HashMap<String, Integer>();

    public void addItem(String product, int quantity) {
        if (cart.containsKey(product)) {
            int currentQuantity = cart.get(product);
            currentQuantity += quantity;
            cart.put(product, currentQuantity);
        } else {
            cart.put(product, quantity);
        }
    }

    public HashMap<String, Integer> getCartItems() {
        return cart;
    }
    
    public int getCartItemsCount() {
        return cart.size();
    }
        
    @Remove
    public int checkout() {
        System.out.println("\nThank you shopping with us. Enjoy your items:");
        for (Map.Entry<String, Integer> item : cart.entrySet()) {
            System.out.println("Product " + item.getKey() + " - Quantity "
                    + item.getValue());
        }
        
        return ( new Random()).nextInt(10000);
    }
}
