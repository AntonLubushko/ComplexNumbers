package entity;

import java.util.Formatter;

public class Complex {
    private double re ;// действительная часть числа
    private double im ;// мнимая часть числа
    private double module ;// модуль числа
    private double argument ;// аргумент числа
    public static final Complex NULL = new Complex(0, 0);
    public static final Complex REAL_ONE = new Complex(1, 0);

    // конструктор по вещественным координатам
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
        makeModule(this);
        makeArgument(this);
    }

    // конструктор по копплексному числу
    public Complex(Complex complex) {
        if (complex != null) {
            re = complex.getRe();
            im = complex.getIm();
            module = complex.getModule();
            argument = complex.getArgument();
        }
    }

    //  по строковому представлению комплексного числа
    public static Complex createComplexByString(String string) throws NoSuchComplexException {
        string = string.replaceAll(" ", "");
        boolean isMatch1 = string
                .matches("^(\\+|\\-)?(\\d)+(\\.(\\d)+)?(\\+|\\-)((\\d)+(\\.(\\d)+)?)?[iI]$");// +2+5i
        boolean isMatch2 = string
                .matches("^(\\+|\\-)?((\\d)+(\\.(\\d)+)?)?[iI](\\+|\\-)(\\d)+(\\.(\\d)+)?$");// +5i+2
        boolean isMatch3 = string
                .matches("^(\\+|\\-)?[iI]((\\d)+(\\.(\\d)+)?)?(\\+|\\-)(\\d)+(\\.(\\d)+)?$");// +i5+2
        boolean isMatch4 = string
                .matches("^(\\+|\\-)?(\\d)+(\\.(\\d)+)?(\\+|\\-)[iI]((\\d)+(\\.(\\d)+)?)?$");// +2+i5
        boolean isMatch5 = string
                .matches("^(\\+|\\-)?((\\d)+(\\.(\\d)+)?)?[iI]$");// +5i
        boolean isMatch6 = string
                .matches("^(\\+|\\-)?[iI]((\\d)+(\\.(\\d)+)?)?$");// +i5
        boolean isMatch7 = string.matches("^(\\+|\\-)?((\\d)+(\\.(\\d)+)?)$");// +2
        Checker checker = new Checker();
        if (isMatch1) {
          return checker.isMatch1(string);
        }
        if (isMatch2) {
           return checker.isMatch2(string);
        }
        if (isMatch3) {
            return  checker.isMatch3(string);
        }
        if (isMatch4) {
            return  checker.isMatch4(string);
        }
        if (isMatch5) {
            return  checker.isMatch5(string);
        }
        if (isMatch6) {
            return checker.isMatch6(string);
        }
        if (isMatch7) {
            return checker.isMatch7(string);
        }

        return NULL;
    }

    // метод создания модуля комплексного числа
    private void makeModule(Complex complex) {
        complex.module = Math.sqrt(complex.re * complex.re + complex.im
                * complex.im);
    }

    // метод создания аргумента комплексного числа
    private void makeArgument(Complex complex) {
        double phi = Math.atan(complex.im / complex.re);
        if (complex.re > 0) {
            complex.argument = phi;
        }
        if (complex.re < 0 && complex.im > 0) {
            complex.argument = Math.PI + phi;
        }
        if (complex.re < 0 && complex.im < 0) {
            complex.argument = -Math.PI + phi;
        }
        if (complex.re == 0 && complex.im > 0) {
            complex.argument = Math.PI / 2;
        }
        if (complex.re == 0 && complex.im < 0) {
            complex.argument = -Math.PI / 2;
        }
        if (complex.re > 0 && complex.im == 0) {
            complex.argument = 0;
        }
        if (complex.re < 0 && complex.im == 0) {
            complex.argument = Math.PI;
        }
    }

    // метод проверяет число на нормальность
    private static void isCorrectNumber(double number)
            throws ArithmeticException {
        if (Double.isInfinite(number) || Double.isNaN(number)) {
            throw new ArithmeticException("Error, number out of range");
        }
    }

    // приватный класс для проверки комплексного числа, заданного строкой, на
    // соответствие
    private static class Checker {
        private Complex isMatch1(String string) {// +2+5i
            String str = "";
            int i = 0;
            do {
                str = str + string.charAt(i++);
            } while (string.charAt(i) != '-' && string.charAt(i) != '+');
            double re = Double.parseDouble(str);
            str = "";
            while (string.charAt(i) != 'i' && string.charAt(i) != 'I') {
                str = str + string.charAt(i++);
            }
            if (str.equals("-") || str.equals("+")) {
                str = str + "1";
            }
            double im = Double.parseDouble(str);
            return new Complex(re,im);
        }

        private Complex isMatch2(String string) {// +5i+2
            String str = "";
            int i = 0;
            while (string.charAt(i) != 'i' && string.charAt(i) != 'I') {
                str = str + string.charAt(i++);
            }
            if (str.equals("-") || str.equals("+") || str.equals("")) {
                str = str + "1";
            }
            double im = Double.parseDouble(str);
            str = "";
            i++;
            while (string.length() > i) {
                str = str + string.charAt(i++);
            }
            double re = Double.parseDouble(str);return new Complex(re,im);
        }

        private Complex isMatch3(String string) {// +i5+2
            String str = "";
            int i = 0;
            if (string.charAt(i) != 'i' && string.charAt(i) != 'I') {
                str = str + string.charAt(i++);
            }
            i++;
            while (string.charAt(i) != '+' && string.charAt(i) != '-') {
                str += string.charAt(i++);
            }
            if (str.equals("-") || str.equals("+") || str.equals("")) {
                str += "1";
            }
            double im = Double.parseDouble(str);
            str = "";
            while (string.length() > i) {
                str = str + string.charAt(i++);
            }
            double re = Double.parseDouble(str);return new Complex(re,im);
        }

        private Complex isMatch4(String string) {// +2+i5
            String str = "";
            str += string.charAt(0);
            int i = 1;
            while (string.charAt(i) != '+' && string.charAt(i) != '-') {
                str += string.charAt(i++);
            }
            double re = Double.parseDouble(str);
            str = "";
            str += string.charAt(i);
            i += 2;
            while (string.length() > i) {
                str = str + string.charAt(i++);
            }
            if (str.equals("-") || str.equals("+")) {
                str += "1";
            }
            double im = Double.parseDouble(str);return new Complex(re,im);
        }

        private Complex isMatch5(String string) {// +5i
            String str = "";
            int i = 0;
            while (string.charAt(i) != 'i' && string.charAt(i) != 'I') {
                str += string.charAt(i++);
            }
            if (str.equals("-") || str.equals("+") || str.equals("")) {
                str += "1";
            }
            double im = Double.parseDouble(str);
            double re = 0;return new Complex(re,im);
        }

        private Complex isMatch6(String string) {// +i5
            String str = "";
            int i = 0;
            while (string.charAt(i) != 'i' && string.charAt(i) != 'I') {
                str += string.charAt(i++);
            }
            i++;
            while (string.length() > i) {
                str = str + string.charAt(i++);
            }
            if (str.equals("-") || str.equals("+") || str.equals("")) {
                str += "1";
            }
            double im = Double.parseDouble(str);
            double re = 0;return new Complex(re,im);
        }

        private Complex isMatch7(String string) {// +2
            String str = "";
            int i = 0;
            while (string.length() > i) {
                str = str + string.charAt(i++);
            }
            double re = Double.parseDouble(str);
            double im = 0;return new Complex(re,im);
        }
    }

    // метод создания комплексного числа по модулю и аргументу
    public static Complex createComplexPolar(double module, double argument)
            throws NoSuchComplexException {
        double re = 0;
        double im = 0;
        if (module < 0) {
            throw new NoSuchComplexException();
        } else {
            re = module * Math.cos(argument);
            im = module * Math.sin(argument);
            isCorrectNumber(re);
            isCorrectNumber(im);
            return new Complex(re, im);
        }
    }

    // метод получение числа, обратного комплексному
    public Complex inverse() {
        Complex inverse = REAL_ONE.divide(this);
        return inverse;
    }

    // метод получения числа, сопряжённого комплексному
    public Complex mated(Complex complex) {
        Complex matedDivider = new Complex(complex.re, -complex.im);
        return matedDivider;
    }


    // метод суммы комплекных чисел
    public Complex sum(Complex addend) {
        double re = this.re + addend.re;
        double im = this.im + addend.im;
        isCorrectNumber(re);
        isCorrectNumber(im);
        Complex sum = new Complex(re, im);
        return sum;
    }

    //  метод разности комплекных чисел
    public Complex subtract(Complex subtrahend) {
        double re = this.re - subtrahend.re;
        double im = this.im - subtrahend.im;
        isCorrectNumber(re);
        isCorrectNumber(im);
        Complex diff = new Complex(re, im);
        return diff;
    }

    // метод произведения комплекных чисел
    public Complex multiply(Complex multiplier) {
        double re = this.re * multiplier.re - this.im * multiplier.im;
        double im = this.re * multiplier.im + this.im * multiplier.re;
        isCorrectNumber(multiplier.re);
        isCorrectNumber(multiplier.im);
        Complex product = new Complex(re, im);
        return product;
    }

    //  метод деления двух комплекных чисел
    public Complex divide(Complex divider)
            throws ArithmeticException {
        if (divider.module == 0) {
            throw new ArithmeticException("Division by zero");
        }
        double squaredDividerModule = divider.module * divider.module;
        double re = (this.re * divider.re - this.im * divider.im) / squaredDividerModule;
        double im = (this.re * divider.im + this.im * divider.re) / squaredDividerModule;
        Complex quotient = new Complex(re, im);
        return quotient;
    }

    // метод возведения комплексного числа в любую целую
    // степень(алгебраическая форма)
    public Complex powAlgebraic(int index)  {
        boolean isNegativeIndex = (index < 0);
        if (isNegativeIndex) {
            throw new ArithmeticException("Index less than zero");
        }
        double re = 1;
        double im = 0;
        for (int i = 0; i < index; i++) {
            double a = this.re * re - this.im * im;
            double b = this.re * im + this.im * re;
            re = a;
            im = b;
        }
        Complex degree = new Complex(re, im);
        return degree;
    }

    // метод возведения комплексного числа в любую целую
    // степень(тригонометрическая форма)
    public Complex powTrigonometric(int index)  {
        boolean isNegativeIndex = (index < 0);
             if (isNegativeIndex ) {
            throw new ArithmeticException("Index less than zero");
        }
        double module = Math.pow(this.module, index);
        double argument = this.argument * index;
        double re = module * Math.cos(argument);
        double im = module * Math.sin(argument);
        return new Complex(re, im);
    }

    //  метод извлечения корня натуральной степени комплексного числа
    // (тригонометрическая форма)
    public Complex[] getRootTrigonometric(int index) {
        if (index <2) {
            throw new ArithmeticException("Index less than 2");
        }
        double module = Math.pow(this.module, 1d / index);
        Complex[] sourses = new Complex[index];
        for (int k = 0; k < index; k++) {
            double argument = (this.argument + (2d * Math.PI * k))/ index;
            double re = module * Math.cos(argument);
            double im = module * Math.sin(argument);
            sourses[k] = new Complex(re, im);
        }
        return sourses;
    }

    // метод равенства комплексных чисел, если действительные и мнимые части
    // соответственно равны, то и комплексные числа равны
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj.getClass() == this.getClass()) {
            Complex c = (Complex) obj;
            if (this.re == c.re && this.im == c.im) {
                return true;
            }
        }
        return false;
    }

    // переопределённый метод для красивого вывода комплексного числа в
    // алгебраическом виде
    @Override
    public String toString() {
        char sign = ' ';
        if (im > 0)
            sign = '+';
        if (im < 0)
            sign = '-';

        String s = "";
        if (re != 0) {
            if ((int) re == re) {
                s += (int) re;
            } else {
                s += re;
            }
        }
        if (Math.abs(im) == 1) {
            if (im == -1) {
                s += "-i";
            }
            if (im == 1) {
                if (re != 0) {
                    s += "+i";
                } else {
                    s += "i";
                }
            }
        } else if (im != 0) {
            String imStr = ((int) im == im) ? String
                    .valueOf(Math.abs((int) im)) : String.valueOf(Math.abs(im));
            if (re != 0) {
                s += String.valueOf(sign) + imStr + "i";
            } else if (sign == '+') {
                s += imStr + "i";
            } else {
                s += String.valueOf(sign) + imStr + "i";
            }
        }
        if (s == "") {
            s += 0;
        }
        return s;
    }

    // метод представления комплексного числа в тригонометрической форме
    public String toTrigonometric() {
        String z = "";
        if (module == (int) module) {
            z = "" + (int) module + "*(cos(" + argument + ")+i*sin(" + argument
                    + "))";
        } else {
            z = "" + module + "*(cos(" + argument + ")+i*sin(" + argument
                    + "))";
        }
        return z;
    }

    // метод представления комплексного числа в показательной форме
    public String toExponent() {
        String z = "";
        if (module == (int) module) {
            z = "" + (int) module + "*e^(" + argument + "*i)";
        } else {
            z = "" + module + "*e^(" + argument + "*i)";
        }
        return z;
    }

    // Форматтер служит для представления числа не более 10 знаками после
    // запятой

    public String toFormatedString() {
        Formatter formatter = new Formatter();
        Formatter formatter2 = new Formatter();
        String strRe = (formatter.format("%f", re).toString());
        strRe = strRe.replace(',', '.');
        String strIm = (formatter2.format("%f", im).toString());
        strIm = strIm.replace(',', '.');
        if (im > 0) {
            return strRe + "+" + strIm + String.valueOf("i");
        } else if (im < 0) {
            return strRe + strIm + String.valueOf("i");
        }
        return strRe;
    }

    // геттеры полей комплексного числа
    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public double getModule() {
        return module;
    }

    public double getArgument() {
        return argument;
    }

	/*// метод отображения комплексного числа на графике
    public static void showOnGraph(entity.Complex complex) {

		try {
			CFrame frame = new CFrame(complex);
			frame.setLocationRelativeTo(null);
			frame.setResizable(true);
			frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// метод отображения корней целой степени комплексного числа на графике
	public static void showSoursesOnGraph(entity.Complex[] complex) {
		try {
			CRFrame frame = new CRFrame(complex);

			frame.setLocationRelativeTo(null);
			frame.setResizable(true);
			frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
