package complexNumbersCalculator;

import complexNumbersCalculator.controller.Controller;
import complexNumbersCalculator.model.Operations;
import complexNumbersCalculator.model.impl.ComplexNumber;
import complexNumbersCalculator.model.impl.ComplexOperations;
import complexNumbersCalculator.util.Converter;
import complexNumbersCalculator.view.Viewer;
import complexNumbersCalculator.logging.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Log.log(Main.class.getName());

    public static void main(String[] args) {

//        ComplexNumber num1 = new ComplexNumber();
//        ComplexNumber num2 = new ComplexNumber((double) 0, -3.45);
//        ComplexNumber num3 = new ComplexNumber((double) -678, (double) 0);
//        ComplexNumber num4 = new ComplexNumber(5.0, 7.75);
//        ComplexNumber num5 = new ComplexNumber(-13.368, -23.45);
//        ComplexNumber num6 = new ComplexNumber((double) -78, 16.89);
//        ComplexNumber num7 = num6;
//
//        System.out.println(num1);
//        System.out.println(num2);
//        System.out.println(num3);
//        System.out.println(num4);
//        System.out.println(num5);
//        System.out.println(num6);
//        System.out.println(num7.equals(num6));
//        ComplexNumber num8 = new ComplexNumber((double) -678, (double) 0.0);
//        System.out.println(num3.equals(num8));
//        System.out.println(plus(num1,num2));
//        System.out.printf("(%s) * (%s) = (%s)\n", num3, num4, num3.mult(num4));
//        System.out.printf("(%s) / (%s) = (%s)\n", num3, num4, num3.div(num4));
//        System.out.printf("(%s) / (%s) = (%s)\n", num6, num1, num6.div(num1));
        LOG.log(Level.INFO, "ПОДКЛЮЧАЕМ VIEWER: ");
        Viewer userView = new Viewer();
        LOG.log(Level.INFO, "ПОДКЛЮЧАЕМ КАЛЬКУЛЯТОР КОМПЛЕКСНЫХ ЧИСЕЛ: ");
        ComplexOperations usedCalculator = new ComplexOperations();
        LOG.log(Level.INFO, "ПОДКЛЮЧАЕМ КОНВЕРТЕР: ");
        Converter usedConverter = new Converter();
        LOG.log(Level.INFO, "ЗАПУСКАЕМ CONTROLLER: ");
        Controller controller = new Controller(userView, usedCalculator, usedConverter);

        controller.run();
    }
}