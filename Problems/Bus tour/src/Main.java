import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int number = sc.nextInt();
        int current;
        boolean noCrashes = true;
        for (int i = 0; i < number; i++) {
            current = sc.nextInt();
            if (current <= height) {
                System.out.printf("Will crash on bridge %d\n", i + 1);
                noCrashes = false;
                break;
            }
        }
        if (noCrashes) {
            System.out.println("Will not crash");
        }
    }
}