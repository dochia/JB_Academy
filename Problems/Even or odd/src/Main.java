import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = sc.nextInt(); ; i = sc.nextInt()) {
            if (i == 0) {
                break;
            }
            System.out.println(i % 2 == 0 ? "even" : "odd");
        }
    }
}