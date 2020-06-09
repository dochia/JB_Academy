import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        int sum = 0;
        while (value > 0) {
            sum += value;
            value = sc.nextInt();
            if (value == 0) {
                break;
            }
        }
        System.out.println(sum);
    }
}
