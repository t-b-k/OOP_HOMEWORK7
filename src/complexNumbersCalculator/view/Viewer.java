package complexNumbersCalculator.view;

import complexNumbersCalculator.Main;
import complexNumbersCalculator.controller.Controller;
import complexNumbersCalculator.logging.Log;
import complexNumbersCalculator.model.impl.ComplexNumber;
import complexNumbersCalculator.util.Operation;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Viewer {
    private static final Logger VIEWER_LOG = Log.log(Viewer.class.getName());
    public String getUserInput (String promptMessage) {
        System.out.print(promptMessage + "========> ");
        Scanner in = new Scanner(System.in);
        VIEWER_LOG.log(Level.INFO, "Читаем пользовательский ввод: ");
        return in.nextLine();
    }
    public void showResult (ComplexNumber op1, ComplexNumber op2, String op, ComplexNumber result) {
        if (op2.getRealPart() < 0) {
            System.out.println(op1 + " " + op + " (" + op2 + ") = " + result);
        } else {
            System.out.println(op1 + " " + op + " " + op2 + " = " + result);
        }
        if (op2.getRealPart() == 0) {
            if (op2.getImagePart() < 0) {
                System.out.println(op1 + " " + op + " (" + op2 + ") = " + result);
            } else {
                System.out.println(op1 + " " + op + " " + op2 + " = " + result);
            }
        }
    }

}
