/**
 * 	Student Name: Lui, Kin Man (Kelvin)
 *	Instructor: Professor Schatz
 * 	Course:	CS111C-001
 *	Assignment:		1. Fraction
 *	Date: 8/24/2016
 */
public interface Fraction {

    public int getNum();

    public int getDen();

    public void setNum(int t);

    public void setDen(int b);

    public Fraction add(Fraction frac);

    public Fraction subtract(Fraction frac);

    public Fraction multiply(Fraction frac);

    public Fraction divide(Fraction frac);

    public Fraction reciprocal();

    public boolean isEqual(Fraction frac1);

    public Fraction reduce();

    public int gcd(int y, int x);

    public String toString();

}
