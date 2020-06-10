import java.util.Scanner;

class Main {
    final static Scanner sc = new Scanner(System.in);

    private static double[] readForm(int dimensions){
        double[] result = new double[dimensions];
        for (int i = 0; i < dimensions; i++) {
            result[i] = sc.nextDouble();
        }
        return result;
    }

    private static double calculateArea(double[] a){
        switch (a.length) {
            case 1:
                return 3.14 * a[0] * a[0];
            case 2:
                return a[0] * a[1];
            default:
                double perimeter = (a[0] + a[1] + a[2]) / 2;
                double result = perimeter;
                for (int i = 0; i < 3; i++) {
                    result *= (perimeter - a[i]);
                }
                return Math.sqrt(result);
        }
    }

    public static void main(String[] args) {
        String type = sc.next();
        double[] values = new double[3];
        switch (type) {
            case "triangle":
                values = readForm(3);
                break;
            case "rectangle":
                values = readForm(2);
                break;
            case "circle":
                values = readForm(1);
                break;
            default:
                throw new UnsupportedOperationException("This is not supported yet");
        }
        System.out.println(calculateArea(values));
    }
}