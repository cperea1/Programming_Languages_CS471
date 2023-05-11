import java.util.*;

class pa8 {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int[] freq = new int[11];
        int limit_1, limit_2, index;
        int new_grade;

        for (;;) {
            new_grade = s.nextInt();
            try {
                if (new_grade < 0) {
                    throw new ArithmeticException();
                }
            } catch (ArithmeticException e) {
                break;
            }
            index = new_grade / 10 + 1;
            try {
                if (new_grade >= 0) {
                    throw new ArithmeticException();
                }
            } catch (ArithmeticException e) {
                if (new_grade < 100) {
                    freq[index] = freq[index] + 1;
                }
                if (new_grade == 100) {
                    freq[10] = freq[10] + 1;
                }
                if (new_grade > 100) {
                    System.out.println("Error -- new grade: " + new_grade + " is out of range");
                }
            }
        }
        System.out.println("Limits Frequency");
        System.out.println("");

        for (index = 0; index < 10; index++) {
            limit_1 = 10 * index;
            limit_2 = limit_1 + 9;
            if (index == 9) {
                limit_2 = 100;
            }
            System.out.print(limit_1 + " ");
            System.out.print(limit_2 + " ");
            System.out.print(freq[index + 1]);
            System.out.println("");
        }
        s.close();
    }
}

