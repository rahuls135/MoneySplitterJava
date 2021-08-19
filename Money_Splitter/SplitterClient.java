import java.util.ArrayList;
import java.util.Arrays;

public class SplitterClient {
    
    public static void main(String[] args) {
        
        Graph client = new Graph();
        client.addVertex("Diya");
        client.addVertex("Ish");
        client.addVertex("Krishi");
        client.addVertex("Rahul");
        client.addVertex("Rudra");
        client.addVertex("Sneha");
        client.addVertex("Yash");
        client.addVertex("Zak");

        ArrayList<String> allExceptRahul = client.getNamesExcept("Rahul");
        ArrayList<String> allExceptIsh = client.getNamesExcept("Ish");
        ArrayList<String> rahulYash = client.getNamesExcept(new ArrayList<String>(Arrays.asList("Rahul", "Yash")));
        ArrayList<String> diyaYash = client.getNamesExcept(new ArrayList<String>(Arrays.asList("Diya", "Yash")));
        ArrayList<String> krishiRudra = client.getNamesExcept(new ArrayList<String>(Arrays.asList("Krishi", "Rudra")));

        // Gas - Rahul
        double gasCostTotal = 63.0;
        double gasYash = 3.15;
        double gasRest = (gasCostTotal - gasYash) / 7;
        for (String names : rahulYash) {
            client.addDirectedEdge(names, "Rahul", gasRest);
        }
        client.addDirectedEdge("Yash", "Rahul", 3.15);

        // Alc - Rahul
        double alcCostRahul = 88.0 / 8;
        for (String names : allExceptRahul) {
            client.addDirectedEdge(names, "Rahul", alcCostRahul);
        }

        // Alc and chips - Ish
        double alcAndChips = 53.0 / 8;
        for (String names : allExceptIsh) {
            client.addDirectedEdge(names, "Ish", alcAndChips);
        }

        // Deep eddys - Krishi
        double deepCost = 18.0 / 6;
        for (String names : krishiRudra) {
            client.addDirectedEdge(names, "Krishi", deepCost);
        }

        // Shoprite - Rahul
        double shopriteCost = 62.76;
        double cakeCost = 7.0;
        double shopriteCostMinusCake = (shopriteCost - cakeCost) / 7;
        // groceries except cake
        for (String names : allExceptRahul) {
            client.addDirectedEdge(names, "Rahul", shopriteCostMinusCake);
        }
        // cake
        for (String names : rahulYash) {
            client.addDirectedEdge(names, "Rahul", cakeCost);
        }

        // Ziplining after white water rafting - Rahul
        client.addDirectedEdge("Rudra", "Rahul", 10.0);

        // Chinese food - Diya
        client.addDirectedEdge("Ish", "Diya", 6.22);
        client.addDirectedEdge("Krishi", "Diya", 9.99);
        client.addDirectedEdge("Sneha", "Diya", 9.99);
        client.addDirectedEdge("Rahul", "Diya", 8.94);
        client.addDirectedEdge("Rudra", "Diya", 4.76);
        client.addDirectedEdge("Yash", "Diya", 7.77);
        client.addDirectedEdge("Zak", "Diya", 10.61);

        // Mexican food - Ish
        client.addDirectedEdge("Krishi", "Ish", 16.5);
        client.addDirectedEdge("Sneha", "Ish", 16.5);
        client.addDirectedEdge("Diya", "Ish", 18.0);
        client.addDirectedEdge("Zak", "Ish", 21.0); // - split up yash's food with beef
        client.addDirectedEdge("Rudra", "Ish", 22.0); // - split up yash's food with beef
        client.addDirectedEdge("Rahul", "Ish", 14.75);
        client.addDirectedEdge("Yash", "Ish", 7.0);

        // Tbell
        client.addDirectedEdge("Ish", "Sneha", 8.0);
        // client.addDirectedEdge("Diya", "Krishi", 9.17);

        // Burger king - Sneha
        client.addDirectedEdge("Diya", "Sneha", 1.43);
        client.addDirectedEdge("Ish", "Sneha", 1.42);

        // Gloves for rope course - Diya
        for (String names : diyaYash) {
            client.addDirectedEdge(names, "Diya", 3.5);
        }

        // Bdubs - Krishi
        double tipAndTax = 3.27;
        client.addDirectedEdge("Rahul", "Krishi", 13.49 + tipAndTax);
        client.addDirectedEdge("Rudra", "Krishi", 14.75 + tipAndTax);
        client.addDirectedEdge("Zak", "Krishi", 14.75 + tipAndTax);
        client.addDirectedEdge("Diya", "Krishi", 13.49 + tipAndTax);
        client.addDirectedEdge("Ish", "Krishi", 11.29 + tipAndTax);
        client.addDirectedEdge("Sneha", "Krishi", 8.5 + tipAndTax);


        client.updateGraph();

        System.out.println(client);
    }
}
