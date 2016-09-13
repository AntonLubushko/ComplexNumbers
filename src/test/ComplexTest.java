package test;

import entity.Complex;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Антон on 14.09.2016.
 */
public class ComplexTest {

    @Test
    public void createComplexByString() throws Exception {
        Complex complex = Complex.createComplexByString("7.8+i9.0");
        System.out.println(complex);
        boolean correct=(7.8d==complex.getRe())&&(9d==complex.getIm());
        Assert.assertTrue(correct);
    }

    @Test
    public void createComplexPolar() throws Exception {
        double module = 10;
        double argument = 10;
        Complex complex = Complex.createComplexPolar(module, argument);
        boolean correctArgument = complex.getArgument() >= -Math.PI && complex.getArgument() <= Math.PI;
        Assert.assertTrue(correctArgument);
    }

    @Test
    public void inverse() throws Exception {
Complex complex=new Complex(11,46);
        Complex inversed=complex.inverse();
        System.out.println(inversed);
    }

    @Test
    public void mated() throws Exception {

    }

    @Test
    public void sum() throws Exception {
        Complex complex1 = new Complex(1, 1);
        Complex complex2 = new Complex(10, 11);
        Complex sum1 = complex1.sum(complex2);
        Complex sum2 = complex2.sum(complex1);
        Assert.assertEquals(new Complex(11,12),sum1);
        Assert.assertEquals(new Complex(11,12),sum2);
    }

    @Test
    public void subtract() throws Exception {

    }

    @Test
    public void multiply() throws Exception {

    }

    @Test
    public void divide() throws Exception {

    }

    @Test
    public void getDegreeAlgebraic() throws Exception {

    }

    @Test
    public void powTrigonometric() throws Exception {

    }

    @Test
    public void getRootTrigonometric() throws Exception {

    }

    @Test
    public void trigonometric() throws Exception {

    }

    @Test
    public void exponent() throws Exception {

    }

    @Test
    public void toFormatedString() throws Exception {

    }

    @Test
    public void getRe() throws Exception {

    }

    @Test
    public void getIm() throws Exception {

    }

    @Test
    public void getModule() throws Exception {

    }

    @Test
    public void getArgument() throws Exception {

    }

}