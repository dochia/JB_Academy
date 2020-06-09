import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        List<Integer> arr = readData();
        if (arr.size() == 1) {
            System.out.println("true");
            return;
        }
        int prev = arr.get(0);
        int i = 1;
        while (prev == arr.get(i)) {
            i++;
        }
        
        boolean isAscending = prev < arr.get(i);
        isOrdered(arr, isAscending);
    }
    
    private static List<Integer> readData() {
        Scanner sc = new Scanner(System.in);
        List<Integer> result = new ArrayList<>();
        int value;
        while (sc.hasNext()) {
            value = sc.nextInt();
            if (value == 0) {
                break;
            }
            result.add(value);
        }
        return result;
    }
    private static void isOrdered(List<Integer> a, boolean isAsc) {
        int prev = a.get(0);
        for (int j = 1; j < a.size(); j++) {
            if (isAsc && prev > a.get(j) || !isAsc && prev < a.get(j)) {
                System.out.println("false");
                return;
            }
            prev = a.get(j);
        }
        System.out.println("true");
    }
}
