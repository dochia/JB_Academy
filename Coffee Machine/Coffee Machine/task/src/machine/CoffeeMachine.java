package machine;
import java.util.Scanner;

public class CoffeeMachine {
    final static Scanner sc = new Scanner(System.in);
    private static void makeCoffee(){
        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");
    }

    private static void calculateIngredients(int n){
        System.out.printf("For %d cups of coffee you will need\n", n);
        System.out.printf("%d ml of water\n", n * 200);
        System.out.printf("%d ml of milk\n", n * 50);
        System.out.printf("%d g of coffee beans\n", n * 15);
    }

    private static int[] readIngredients() {
        int[] result = new int[3];
        System.out.println("Write how many ml of water the coffee machine has: ");
        result[0] = sc.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        result[1] = sc.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        result[2] = sc.nextInt();
        return result;
    }

    private static int enoughIngredientsFor(int[] ingredients, int cups){
        int water = ingredients[0] / 200;
        int milk = ingredients[1] / 50;
        int beans = ingredients[2] / 15;
        int min = Math.min(water, Math.min(milk, beans));
        if ( min >= cups){
            System.out.print("Yes, I can make that amount of coffee");
            if (min > cups) {
                System.out.printf("(and even %d more than that)\n", min - cups);
            }
            return min;
        }
        System.out.printf("No, I can only make %d cup(s) of coffee\n", min);
        return min;
    }

    public static void main(String[] args) {
        int[] values = readIngredients();
        System.out.println("Write how many cups of coffee you will need");
        int cups = sc.nextInt();
        System.out.println(enoughIngredientsFor(values, cups));
    }
}
