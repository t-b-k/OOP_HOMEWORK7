package complexNumbersCalculator.controller;

import complexNumbersCalculator.logging.Log;
import complexNumbersCalculator.model.impl.ComplexNumber;
import complexNumbersCalculator.model.impl.ComplexOperations;
import complexNumbersCalculator.view.Converter;
import complexNumbersCalculator.view.Operation;
import complexNumbersCalculator.view.Viewer;

import java.util.logging.Level;
import java.util.logging.Logger;

import static complexNumbersCalculator.view.Operation.*;

public class Controller {
    private Viewer usedViewer;
    private final ComplexOperations calculator = new ComplexOperations();
//    private Converter converter;
    private final Operation operation;
    private static final Logger CONTROLLER_LOG = Log.log(Controller.class.getName());


    public Controller(Viewer usedViewer) {
        this.usedViewer = usedViewer;
        this.operation = null;
    }

    public void run() throws RuntimeException {
        ComplexNumber op1 = new ComplexNumber();
        ComplexNumber op2 = new ComplexNumber();
        while (true) {
            usedViewer.showPrompt("Введите первый комплексный операнд: ");
            try {
                op1 =  usedViewer.getUserNumber();
            } catch (Exception e) {
                CONTROLLER_LOG.log(Level.INFO, "USER INPUT EXCEPTION: wrong input data format !!!\n");;
                throw new RuntimeException("USER INPUT EXCEPTION: wrong input data format !!!\n");
            }
            CONTROLLER_LOG.log(Level.INFO, String.format("Первый операнд равен " + op1 + "\n"));
            usedViewer.showPrompt("Введите второй комплексный операнд: ");
            try {
                op2 = usedViewer.getUserNumber();
            } catch (Exception e) {
                CONTROLLER_LOG.log(Level.INFO, "USER INPUT EXCEPTION: wrong input data format !!!\n");;
                throw new RuntimeException("USER INPUT EXCEPTION: wrong input data format !!!\n");
            }
            CONTROLLER_LOG.log(Level.INFO, String.format("Второй операнд равен " + op2 + "\n"));
            usedViewer.showPrompt("Введите операцию: +/-/*/:/Q(для выхода) ");
            Operation command = usedViewer.getOperation();
            ComplexNumber res;
            switch (command) {
                case TO_ADD:
                    res = calculator.plus(op1, op2);
                    usedViewer.showResultOfOperation(op1, op2, command, res);
                    break;

                case TO_SUBTRACT:
                    res = calculator.minus(op1, op2);
                    usedViewer.showResultOfOperation(op1, op2, command, res);
                    break;

                case TO_MULTIPLY:
                    res = calculator.multiply(op1, op2);
                    usedViewer.showResultOfOperation(op1, op2, command, res);
                    break;

                case TO_DIVIDE:
                    res = calculator.divide(op1, op2);
                    usedViewer.showResultOfOperation(op1, op2, command, res);
                    break;

                case TO_BREAK:
                    return;

                default:
                    break;
            }
        }
    }
}

