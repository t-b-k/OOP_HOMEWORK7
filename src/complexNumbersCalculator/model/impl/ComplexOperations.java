package complexNumbersCalculator.model.impl;

import complexNumbersCalculator.logging.Log;
import complexNumbersCalculator.model.Operations;
import complexNumbersCalculator.view.Converter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ComplexOperations implements Operations<ComplexNumber> {
    private static final Logger COMPLEX_OP_LOG = Log.log(ComplexOperations.class.getName());
    private ComplexNumber result;
    @Override
    public ComplexNumber plus(ComplexNumber arg1, ComplexNumber arg2) {
        COMPLEX_OP_LOG.log(Level.INFO, "Метод plus (arg1, arg2) ...");
        result = new ComplexNumber(arg1.getRealPart() + arg2.getRealPart(),
                arg1.getImagePart() + arg2.getImagePart());
        COMPLEX_OP_LOG.log(Level.INFO, "Результат равен " + result);
        return result;
    }
    @Override
    public ComplexNumber minus(ComplexNumber arg1, ComplexNumber arg2) {
        COMPLEX_OP_LOG.log(Level.INFO, "Метод minus (arg1, arg2) ...");
        result = new ComplexNumber(arg1.getRealPart() - arg2.getRealPart(),
                arg1.getImagePart() - arg2.getImagePart());
        COMPLEX_OP_LOG.log(Level.INFO, "Результат равен " + result);
        return result;
    }
    @Override
    public ComplexNumber multiply(ComplexNumber arg1, ComplexNumber arg2) {
        COMPLEX_OP_LOG.log(Level.INFO, "Метод multiply (arg1, arg2) ...");
        double real = arg1.getRealPart() * arg2.getRealPart() - arg1.getImagePart() * arg2.getImagePart();
        COMPLEX_OP_LOG.log(Level.INFO, "Вещественная часть равна " + real);
        double image = arg1.getRealPart() * arg2.getImagePart() + arg1.getImagePart() * arg2.getRealPart();
        COMPLEX_OP_LOG.log(Level.INFO, "Мнимая часть равна " + image);
        result = new ComplexNumber(real, image);
        COMPLEX_OP_LOG.log(Level.INFO, "Результат равен " + result);
        return result;
    }

    @Override
    public ComplexNumber divide(ComplexNumber arg1, ComplexNumber arg2) throws RuntimeException {
        COMPLEX_OP_LOG.log(Level.INFO, "Метод divide (arg1, arg2) ...");
        double denominator = Math.pow(arg2.getRealPart(), 2.0) + Math.pow(arg2.getImagePart(), 2.0);
        COMPLEX_OP_LOG.log(Level.INFO, "Знаменатель равен " + denominator);
        double resRealPart = 0.0, resImagePart = 0.0;
        try {
            resRealPart = (arg1.getRealPart()  * arg2.getRealPart() + arg1.getImagePart() * arg2.getImagePart()) / denominator;
        } catch (RuntimeException e) {
            COMPLEX_OP_LOG.log(Level.INFO, "RUNTIME EXCEPTION: DIVISION BY ZERO !!!\n");;
            throw new RuntimeException("RUNTIME EXCEPTION: DIVISION BY ZERO!!!\n");
        }
        COMPLEX_OP_LOG.log(Level.INFO, "Вещественная часть равна " + resRealPart);
        resImagePart = (arg1.getImagePart() * arg2.getRealPart() - arg1.getRealPart()  * arg2.getImagePart()) / denominator;
        COMPLEX_OP_LOG.log(Level.INFO, "Мнимая часть равна " + resImagePart);
        result = new ComplexNumber(resRealPart, resImagePart);
        COMPLEX_OP_LOG.log(Level.INFO, "Результат равен " + result);
        return result;
    }
}

