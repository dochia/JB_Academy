import java.util.Scanner;

class Main {
    final static String[] directions = new String[] {"do not move", "up", "down", "left", "right"};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        switch (value) {
            case 0 :
                sb.append(directions[value]);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                sb.append("move ");
                sb.append(directions[value]);
                break;
            default:
                sb.append("error!");
        }
        System.out.println(sb.toString());
    }
}
