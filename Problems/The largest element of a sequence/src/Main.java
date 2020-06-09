import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        int max = value;
        while (value > 0) {
            value = sc.nextInt();
            max = Math.max(max, value);
        }
        System.out.println(max);
    }
}