package de.ast.numbers;

public class NumberRanges {

    public NumberRanges() {
    }

    static public void printRanges() {
        System.out.println("min byte : " + Byte.MIN_VALUE);
        System.out.println("max byte : " + Byte.MAX_VALUE);
        System.out.println("min short : " + Short.MIN_VALUE);
        System.out.println("max short : " + Short.MAX_VALUE);
        System.out.println("min int : " + Integer.MIN_VALUE);
        System.out.println("max int : " + Integer.MAX_VALUE);
        System.out.println("min long : " + Long.MIN_VALUE);
        System.out.println("max long : " + Long.MAX_VALUE);
        System.out.println("min float : " + Float.MIN_VALUE);
        System.out.println("max float : " + Float.MAX_VALUE);
        System.out.println("min double : " + Double.MIN_VALUE);
        System.out.println("max double : " + Double.MAX_VALUE);
        
        System.out.printf("dexp: %f\n", Float.MIN_VALUE);
        System.out.printf("dexp: %f\n", Float.MAX_VALUE);
        System.out.printf("dexp: %f\n", Double.MIN_VALUE);
        System.out.printf("dexp: %f\n", Double.MAX_VALUE );
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        printRanges();
    }

}
