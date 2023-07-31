package complexNumbersCalculator.model.impl;

import complexNumbersCalculator.model.Operations;

public class ComplexOperations implements Operations<ComplexNumber> {
    @Override
    public ComplexNumber plus(ComplexNumber arg1, ComplexNumber arg2) {
        return new ComplexNumber(arg1.getRealPart() + arg2.getRealPart(),
                arg1.getImagePart() + arg2.getImagePart());
    }
    @Override
    public ComplexNumber minus(ComplexNumber arg1, ComplexNumber arg2) {
        return new ComplexNumber(arg1.getRealPart() - arg2.getRealPart(),
                arg1.getImagePart() - arg2.getImagePart());
    }
    @Override
    public ComplexNumber multiply(ComplexNumber arg1, ComplexNumber arg2) {
        System.out.println("Вошли в метод multiply");
//        System.out.printf("Вещественная часть 1-го аргумента: " + arg1.getRealPart() + "\n");
//        System.out.printf("Вещественная часть 2-го аргумента: " + arg2.getRealPart() + "\n");
//        Double resRealPart = arg1.getRealPart() * arg2.getRealPart() - arg1.getImagePart() * arg2.getImagePart();
//        System.out.printf("Вещественная часть результата" + resRealPart + "\n");
//        Double resImagePart = arg1.getRealPart() * arg2.getImagePart() + arg1.getImagePart() * arg2.getRealPart();
//        System.out.printf("Мнимая часть результата" + resImagePart + "\n");
        return new ComplexNumber(arg1.getRealPart() * arg2.getRealPart() - arg1.getImagePart() * arg2.getImagePart(), arg1.getRealPart() * arg2.getImagePart() + arg1.getImagePart() * arg2.getRealPart());
    }

    @Override
    public ComplexNumber divide(ComplexNumber arg1, ComplexNumber arg2) {
        System.out.println("Вошли в метод ComplexOperations.divide(op1, op2) :" + arg1 +", " + arg2 + "\n");
        ComplexNumber result = new ComplexNumber();
        double denominator = Math.pow(arg2.getRealPart(), 2.0) + Math.pow(arg2.getImagePart(), 2.0);
        System.out.println("Denominator = " + denominator);
        double resRealPart = 0.0, resImagePart = 0.0;
        try {
            resRealPart = (arg1.getRealPart()  * arg2.getRealPart() + arg1.getImagePart() * arg2.getImagePart()) / denominator;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        resImagePart = (arg1.getImagePart() * arg2.getRealPart() - arg1.getRealPart()  * arg2.getImagePart()) / denominator;
        return new ComplexNumber(resRealPart, resImagePart);
    }
}

