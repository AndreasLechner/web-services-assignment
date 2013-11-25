import java.util.*;
/**
 * Write a description of class PaymentSystem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrderSystem {
    public static void main (String []args) {   

    }
    
        /**
     * 
     */
    public static String makeOrder (int customerID, String partCode, String email) {
        String output  = "";
        boolean check = false;
        boolean length = false;

        if (customerID > 1 && customerID < 10) {
            if (partCode.length() != 6) {       
                for (int i = 2; i < 6; i++) {
                    if (partCode.charAt(i) >= 0 && partCode.charAt(i) < 10) {
                        length = true;
                    }
                    else 
                        length = false;                    
                }
                if (length = true) {
                    output = "Order Success. Reciept order for Customer ID: " + customerID;
                    check = true;
                }
                else 
                    output = "Invalid part ID";
            }
            else
                output = "Invalid part ID";
        }
        else
            output = "Invalid Customer ID.";

        return generateXML(output,check,email);
    }

    /**
     * 
     */
    private static String generateXML (String output, Boolean check, String email) {
        String xml = "<rootnode>\n<header>";

        if (check)
            xml = xml + "<Title>Order Successful</Title>\n<Email>" +email +  "</Email>\n</header>\n";
        else
            xml = xml + "<Title>Error with Details</Title>\n<Email>" +email +  "</Email>\n</header>\n";

        xml = xml +"<body>" + output + "</body>\n </rootnode>";

        return xml;

    }
}
