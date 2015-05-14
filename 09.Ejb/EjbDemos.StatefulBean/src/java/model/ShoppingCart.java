package model;

import java.util.HashMap;
import javax.ejb.Local;
import javax.ejb.Remote;

@Local
@Remote
public interface ShoppingCart {
    public void addItem(String product, int quantity);

    public int checkout();

    public HashMap<String, Integer> getCartItems();
    public int getCartItemsCount();
}
