import java.util.Scanner;

public class Main {

    public static boolean isVowel(char ch) {
        char a = Character.toUpperCase(ch);
        return a == 65 || a == 69 || a == 73 || a == 79 || a == 85;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char letter = scanner.nextLine().charAt(0);
        System.out.println(isVowel(letter) ? "YES" : "NO");
    }
}
