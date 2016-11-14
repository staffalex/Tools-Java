package de.ast;

public class Primes {

  
  private static boolean isPrime(int number) {
    
    int limit = (int) Math.sqrt(number);
    for(int i = 3; i <= limit ; i += 2) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
    
  }
  
  private static int calcPrimes(int max) {
    
    int counter = 0;
    
    for(int i = 3; i <= max; i += 2) {
      if (isPrime(i)) {
        // System.out.println(i);
        counter++;
      }
    }
    
    return counter;
    
  }
  
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
   
    int max = 50000000;

    int primesCount = Primes.calcPrimes(max);
    
    System.out.println("Calculating " + primesCount + " primes took " + (System.currentTimeMillis() - startTime) + " msecs.");

  }

}
