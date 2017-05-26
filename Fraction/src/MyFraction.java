/**
 * 	Student Name: Lui, Kin Man (Kelvin)
 *	Instructor: Professor Schatz
 * 	Course:	CS111C-001
 *	Assignment:	1. Fraction
 *	Date:	8//24/2016
 */
public class MyFraction implements Fraction{

    private int num, den;

    public MyFraction (int t, int b){

        if(b != 0) {
            num = t;
            den = b;
        }
        else throw new IllegalArgumentException ("Argument 'denominator' is 0");    //can not be divided by zero
    }

    public int getNum() {
        return num;
    }

    public int getDen() {
        return den;
    }

    public void setNum(int t) {
        num = t;
    }

    public void setDen(int b) {
        den = b;
    }

    public Fraction add(Fraction frac) {

        int comDen = den * frac.getDen();	                    //Finds the common denominator of two fractions.
        int newNum = num * frac.getDen() + den * frac.getNum();	//It takes the form x1y2 + x2y1.

        num = newNum;					                       	//Assigns the new values into the new fraction
        den = comDen;

        return reduce();                            	//reduce or simplify the fraction before the method returns it
    }

    public Fraction subtract(Fraction frac) {

        int comDen = den * frac.getDen();	//Finds the common denominator of two fractions.
        int newNum = num * frac.getDen() - den * frac.getNum();	//It takes the form x1y2 - x2y1

        num = newNum;	//Assigns the new values into the new fraction
        den = comDen;

        return reduce();	//reduce or simplify the fraction before the method returns it
    }

    public Fraction multiply(Fraction frac) {

        num *= frac.getNum();
        den *= frac.getDen();

        return reduce();	//reduce or simplify the fraction before the method returns it
    }

    public Fraction divide(Fraction frac) {

        num *= frac.getDen();
        den *= frac.getNum();

        return reduce();	//reduce or simplify the fraction before the method returns it
    }

    public Fraction reciprocal() {

        int temp = num;			//Swaps the value of denominator and numerator.
        num = den;
        den = temp;

        return reduce();		//reduce or simplify the fraction before the method returns it
    }

    public boolean isEqual(Fraction frac) {

        frac.reduce();			//reduce or simplify the fraction before comparison.
        reduce();

        return num == frac.getNum() && den == frac.getDen();
    }

    public int gcd(int y, int x){	//Calculate the greatest common divider.

        if (x == 0)
            return y;
        else
            return gcd(x, y%x);		//This uses recursion to find the gcd.

    }

    public Fraction reduce(){	//reduce or simplify the fraction by dividing by its greatest common divider

        int tempNum = getNum();
        int tempDen = getDen();

        num /= gcd(tempNum, tempDen);	//Divides denominator and numerator by the gcd to simplify the fraction.
        den /= gcd(tempNum, tempDen);

        if(num < 0 && den < 0){	//Eliminate the negative sides if both denominator and numerator are negative
            num = -num;
            den = -den;
        }

        return this;            //return the fraction after simplification

    }

    public String toString() {

        return "Fraction: " + getNum() + "/" + getDen();

    }
}
