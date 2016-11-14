package de.ast.strings;

public class WorkWithStrings {

    public void printMessage() {
        
        String hello = "Hello";
        
        byte[] data = hello.getBytes();
        
        String sRequest = new String(data); //will received byte array hello
        String all = sRequest;
        
        System.out.println(all);
        
        all = all.concat("world");
        System.out.println(all);
    }

    public static void main(String[] args) {

        WorkWithStrings wws = new WorkWithStrings();
        wws.printMessage();
    }
}
