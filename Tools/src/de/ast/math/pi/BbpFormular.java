package de.ast.math.pi;

import java.math.BigDecimal;


public class BbpFormular {
    
    public static final BigDecimal myPi = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
    
    private static BigDecimal numberOne = new BigDecimal(1.0);
    private static BigDecimal numberTwo = new BigDecimal(2.0);
    private static BigDecimal numberFour = new BigDecimal(4.0);
    private static BigDecimal numberFive = new BigDecimal(5.0);
    private static BigDecimal numberSix = new BigDecimal(6.0);
    private static BigDecimal numberEight = new BigDecimal(8.0);
    private static BigDecimal numberSixteen = new BigDecimal(16.0);
    
    
    public static BigDecimal calculatePi(int start, int count)
    {
        // ab count  > ca. 8   keine Verbesserung der Genauigkeit mehr
        BigDecimal result = new BigDecimal(0.0);
        
        for(int i = start; i < start + count; i++) {
            
            BigDecimal p1 = numberSixteen.pow(-1 * i);
            BigDecimal p2 = numberSixteen.pow(-1 * (i + 1));
            
            
            
            result = result.add(new BigDecimal((1.0 / Math.pow(16, i)) *  (4.0 / (8 * i + 1) - 2.0 / (8 * i + 4) - 1.0 / (8 * i + 5)  - 1.0 / (8 * i + 6) )));
            
            
            
        }
        return result;
    }
    

    public static void main(String[] args) {
        
        BigDecimal calculatedPi1stPart = calculatePi(0, 55);
  //      BigDecimal calculatedPi2ndPart = calculatePi(4, 4);
  //      BigDecimal calculatedPi3rdPart = calculatePi(8, 34);
        
  //      BigDecimal concatenatedPi = calculatedPi1stPart.add(calculatedPi2ndPart).add(calculatedPi3rdPart);
        
        System.out.println("Pi1 : " + calculatedPi1stPart);
  //      System.out.println("Pi2 : " + calculatedPi2ndPart);
  //      System.out.println("Pi3 : " + calculatedPi3rdPart);
        
        System.out.println("Difference of Pi1part1 to const Pi : " + (myPi.subtract(calculatedPi1stPart)));
        
  //      System.out.println("calculatedPi : " + concatenatedPi);
  //      System.out.println("Difference to const Pi : " + (myPi.subtract(concatenatedPi)));
    }

}
