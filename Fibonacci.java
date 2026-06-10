public class Fibonacci {
    public static void main(String[] args) {
        long a= 0;
        long b= 1;

        System.out.println(a);

        for (int i = 1; i < 50; i++) {
            System.out.println(b);
            long suma = a + b;
            a = b;
            b= suma;
        }
    }
}