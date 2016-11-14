package de.ast.bigdecimals;

import static org.junit.Assert.*;

import java.math.*;
import java.text.*;
import java.util.*;

import org.junit.*;

public class BigDecimalsTest {

  protected static BigInteger BigIntegerZERO      = BigInteger.ZERO;
  protected static BigInteger BigIntegerONE       = BigInteger.ONE;
  protected static BigInteger BigIntegerTWO       = BigInteger.valueOf(2);
  protected static BigInteger BigIntegerTHREE     = BigInteger.valueOf(3);
  protected static BigInteger FactorialBreakpoint = BigInteger.valueOf(96);
  protected static BigDecimal BigDecimalZERO      = BigDecimal.ZERO;
  protected static BigDecimal BigDecimalONE       = BigDecimal.ONE;
  protected static BigDecimal BigDecimalTWO       = new BigDecimal(2);
  protected static BigDecimal BigDecimalFOUR      = new BigDecimal(4);

  @Test
  public void testForSignumNull1() {

    int count = 10;

    List<BigDecimal> bdList = getNumbersList(count);
    BigDecimal bdSum = new BigDecimal(0);

    for (int i = 0; i < count; i++) {
      bdSum = bdSum.add(bdList.get(i));
    }

    BigDecimal bdNull = bdSum.multiply(new BigDecimal(1.0));

    for (int i = 0; i < count; i++) {
      bdNull = bdNull.subtract(bdList.get(i));
    }

    assertTrue(bdNull.signum() == 0);

  }

  public static boolean isDivisibleBy(BigInteger isThisNumberDivisible, Long byThisNumber) {
    return isDivisibleBy(isThisNumberDivisible, BigInteger.valueOf(byThisNumber));
  }

  public static boolean isDivisibleBy(BigInteger isThisNumberDivisible, BigInteger byThisNumber) {
    return isThisNumberDivisible.mod(byThisNumber).equals(BigIntegerZERO);
  }

  @Test
  public void testForSignumNull2() {

    int count = 10;

    List<BigDecimal> bdList = getNumbersList(count);
    BigDecimal bdSum1 = new BigDecimal(0);

    for (int i = 0; i < count; i++) {
      bdSum1 = bdSum1.add(bdList.get(i));
    }

    BigDecimal bdSum2 = new BigDecimal(0.0);

    for (int i = 0; i < count; i++) {
      bdSum2 = bdSum2.add(bdList.get(i));
    }

    assertTrue(bdSum2.subtract(bdSum1).signum() == 0);

  }

  @Test
  public void testForSignumNull3() {

    int count = 10;

    List<BigDecimal> bdList = getOtherNumbersList(10);
    BigDecimal bdSum1 = new BigDecimal(0);
    BigDecimal bd;

    for (int i = 0; i < bdList.size(); i++) {
      bd = bdList.get(i).setScale(2);
      bdSum1 = bdSum1.add(bd);
    }

    bdList = getYetAnotherNumbersList(count);
    BigDecimal bdSum2 = new BigDecimal(0);

    for (int i = 0; i < bdList.size(); i++) {
      bdSum2 = bdSum2.add(bdList.get(i));
    }

    bdSum1.setScale(2, RoundingMode.HALF_UP);
    bdSum2.setScale(2, RoundingMode.HALF_UP);

    System.out.println(bdSum1);
    System.out.println(bdSum2);

    assertTrue(bdSum2.subtract(bdSum1).signum() == 0);

  }

  @Test
  public void testForIMS335() throws Exception {
    List<String> values = new ArrayList<String>();
    DecimalFormat df = new DecimalFormat("##0.00#");

    values.add("10.0");
    values.add("10.5");
    values.add("10");
    values.add("10.01");
    values.add("10.05");
    values.add("10.00");
    values.add("10.11");
    values.add("10.15");
    values.add("10.50");
    values.add("10.51");
    values.add("10.55");
    values.add("10.50");

    for (String perc : values) {
      BigDecimal percN = new BigDecimal(perc);

      // round to one digit after comma
      BigDecimal percRounded = percN.setScale(1, BigDecimal.ROUND_HALF_EVEN);

      String percFormatted = df.parse(percRounded.toString()).toString();

      System.out.println("Value : " + perc + ", percN : " + percN + ", percRounded : " + percRounded
          + " percFormatted : " + percFormatted);
    }

  }

  private List<BigDecimal> getNumbersList(int count) {
    List<BigDecimal> doublemyListsList = new ArrayList<BigDecimal>(count);

    Random rnd = new Random();

    for (int i = 0; i < count; i++) {
      double d = rnd.nextDouble();
      doublemyListsList.add(new BigDecimal(d));
    }

    return doublemyListsList;
  }

  private List<BigDecimal> getOtherNumbersList(int count) {
    List<BigDecimal> myList = new ArrayList<BigDecimal>(count);

    double d = 2.45;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 3.65;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 2.67;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 9.68;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 12.77;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 111.92;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 0.33;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 0.20;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 4.3;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 2;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 0.03;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    return myList;
  }

  private List<BigDecimal> getYetAnotherNumbersList(int count) {
    List<BigDecimal> myList = new ArrayList<BigDecimal>(count);

    double d = 100;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 33.00;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    d = 17.00;
    System.out.println(d);
    myList.add(new BigDecimal(d));

    return myList;
  }

}
