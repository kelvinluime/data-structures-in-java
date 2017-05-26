/**
 * 	Student Name: Lui, Kin Man (Kelvin)
 *	Instructor: Professor Schatz
 * 	Course:	CS111C-001
 *	Assignment:	1. Fraction
 *	Date: 8/24/2016
 */
public class Tester {

    public static void main(String[] args){

        MyFraction frac = new MyFraction(3, 6);

        System.out.println(frac);

        System.out.println("gcd: " + frac.gcd(frac.getNum(), frac.getDen())); //tests gcd method.

        System.out.println("reduce: " + frac.reduce());							//tests reduce method.

        System.out.println("multiply2/3: " + frac.multiply(new MyFraction(2, 3)));	//tests multiply method.

        System.out.println("divide1/3: " + frac.divide(new MyFraction(1, 3)));		//tests divide method

        System.out.println("subtract1/3: " + frac.subtract(new MyFraction(1, 3)));	//tests subtract method

        System.out.println("reciprocal: " + frac.reciprocal());

        System.out.println("add1/3: " + frac.add(new MyFraction(1,3)));		//tests add method

        System.out.println("is equal-1/-1: " + frac.isEqual(new MyFraction(-1, -1)));	//tests the equal method

    }


}
