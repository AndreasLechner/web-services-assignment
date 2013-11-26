import java.util.*;
/**
 *
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrderSystem {


    private static List<Part> parts;
    private static List<Order> orders;


    public static void main (String []args) {

        Random random = new Random();

        parts = new ArrayList<Part>();
        orders = new ArrayList<Order>();

        for (int i = 0; i < 10000; i++) {
            String code;

            if (i < 10) {
                code = "000" + i;
            } else {
                if (i < 100) {
                    code = "00" + i;
                } else {
                    if (i < 1000) {
                        code = "0" + i;
                    } else {
                        code = "" + i;
                    }
                }
            }

            parts.add(new Part("PN" + code, random.nextInt(1000), (random.nextInt(101) > 20 ) ));
        }

        placeOrder(4, "PN2221", "asdasfdasdw");
    }
    
        /**
     * 
     */
    public static void placeOrder (int customerID, String partCode, String email) {
        
        boolean error = false;
        String errorMessage;

        if ( customerID < 2 && customerID > 9 ) {
            error = true; 
            errorMessage = " Customer ID is out of range (1 < customerID < 10) ";
            answer(errorMessage, "error", email);
        }

        if ( partCode.length() != 6 ) {
            error = true;
            errorMessage = " Part code is wrong. Requiered format is PNXXXX ";
            answer(errorMessage, "error", email);
        } else {
            if ( !partCode.startsWith("PN") ) {
                error = true;
                errorMessage = " Part code should start with PN followed by 4 digits";
                answer(errorMessage, "error", email);
            } else {
                try {
                    int numberCode = Integer.parseInt(partCode.substring(2));
                } catch (Exception e) {
                    error = true;
                    answer(" Number part of part code is not valid ", "error", email);
                }

            }
        }

        if ( !error ) {
            orders.add( new Order(email, partCode) );
            answer(" Your order for " + partCode + " was Successful!", "success", email );
        }
    }

    /**
     * 
     */
    private static void answer (String message, String type, String email) {

        String answer = "<rootnode>\n<header>" ;

        if ( type.equals("error") ) {
            answer = answer + "<Title>Error occured: " + message + " . Order was not completed.</Title>\n<Email>" +email +  "</Email>\n</header>\n";
        } else {
            answer = answer + "<Title>Order Successful</Title>\n<Email>" +email +  "</Email>\n</header>\n";
        }
        
        answer = answer +"<body>" + message + "</body>\n </rootnode>";

        System.out.print(answer);

    }

    public static class Part {
        private int price;
        private boolean available;
        private String partCode;
        
        public Part(String pC, int p, boolean aval) {
            price = p;
            available = aval;
            partCode = pC;
        }
        

        public boolean isAvailable() {
            return available;
        }

        public int getPrice() {
            return price;
        }

        public String getPartCode() {
            return partCode;
        }

    }

    public static class Order {
        private static String email;
        private static String partCode;

        public Order(String em, String pC) {
            email = em;
            partCode = pC;
        }
    }
}
