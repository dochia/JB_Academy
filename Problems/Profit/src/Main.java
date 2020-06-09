import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double m = sc.nextInt();
        int p = sc.nextInt();
        double k = sc.nextInt();
        int count = 0;
        while (m < k) {
            count++;
            m += m * p / 100;
        }
        System.out.println(count);
    }
}
