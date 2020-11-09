package thinking_in_java.utils;

public class Print {
    public static void print(String str)
    {
        System.out.println(str);
    }

    public static void printBinaryInt(String s, int i) {
        print(s + ", int: " + i + ", binary: \n " + Integer.toBinaryString(i));
    }

    public static void printBinaryLong(String s, long l) {
        print(s + ", long: " + l + ", binary: \n " + Long.toBinaryString(l));
    }
}
