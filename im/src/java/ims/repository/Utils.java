package ims.repository;

import java.net.URL;
import java.util.Scanner;

public class Utils {

    public static String readUrl(String urlString) {
        String content = "";
        try {
            //What is "\\A"? => read https://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner.html
            content = new Scanner(new URL(urlString).openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return content;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
