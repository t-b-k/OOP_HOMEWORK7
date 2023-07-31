package complexNumbersCalculator.controller;

import complexNumbersCalculator.model.Operations;
import complexNumbersCalculator.model.impl.ComplexNumber;
import complexNumbersCalculator.model.impl.ComplexNumber.*;
import complexNumbersCalculator.model.impl.ComplexOperations;
import complexNumbersCalculator.util.Converter;
import complexNumbersCalculator.util.Operation;
import complexNumbersCalculator.view.Viewer;

import java.util.List;

public class Controller {
    private Viewer usedViewer;
    private ComplexOperations calculator;
    private Converter converter;
    private final Operation operation;

    public Controller(Viewer usedViewer, ComplexOperations calculator, Converter converter) {
        this.usedViewer = usedViewer;
        this.calculator = calculator;
        this.converter = converter;
        this.operation = null;
    }

    public void run() {
        ComplexNumber op1;
        ComplexNumber op2;
        while (true) {
            Operation command = converter.getOperation(usedViewer.getUserInput("Введите операцию: +/-/*/:/Q(для выхода) "));
            ComplexNumber res;
            switch (command) {
                case TO_ADD:
                    op1 = converter.parseComplexNumber(usedViewer.getUserInput("Введите первый комплексный операнд: "));
                    op2 = converter.parseComplexNumber(usedViewer.getUserInput("Введите второй комплексный операнд: "));
                    System.out.printf("Первый операнд равен " + op1 + "\n");
                    System.out.printf("Второй операнд равен " + op2 + "\n");
                    res = calculator.plus(op1, op2);
                    usedViewer.showResult(op1, op2, converter.showOperation(Operation.TO_ADD), res);
                    break;

                case TO_SUBTRACT:
                    op1 = converter.parseComplexNumber(usedViewer.getUserInput("Введите первый комплексный операнд: "));
                    op2 = converter.parseComplexNumber(usedViewer.getUserInput("Введите второй комплексный операнд: "));
                    System.out.printf("Первый операнд равен " + op1 + "\n");
                    System.out.printf("Второй операнд равен " + op2 + "\n");
                    res = calculator.minus(op1, op2);
                    usedViewer.showResult(op1, op2, converter.showOperation(Operation.TO_SUBTRACT), res);
                    break;

                case TO_MULTIPLY:
                    op1 = converter.parseComplexNumber(usedViewer.getUserInput("Введите первый комплексный операнд: "));
                    op2 = converter.parseComplexNumber(usedViewer.getUserInput("Введите второй комплексный операнд: "));
                    System.out.printf("Первый операнд равен " + op1 + "\n");
                    System.out.printf("Второй операнд равен " + op2 + "\n");
                    res = calculator.multiply(op1, op2);
                    System.out.println(res);
                    usedViewer.showResult(op1, op2, converter.showOperation(Operation.TO_MULTIPLY), res);
                    break;

                case TO_DIVIDE:
                    op1 = converter.parseComplexNumber(usedViewer.getUserInput("Введите первый комплексный операнд: "));
                    op2 = converter.parseComplexNumber(usedViewer.getUserInput("Введите второй комплексный операнд: "));
                    System.out.printf("Первый операнд равен " + op1 + "\n");
                    System.out.printf("Второй операнд равен " + op2 + "\n");
                    res = calculator.divide(op1, op2);
                    usedViewer.showResult(op1, op2, converter.showOperation(Operation.TO_DIVIDE), res);
                    break;

                case TO_BREAK:
                    return;

                default:
                    break;
            }
        }
    }
    public boolean getTwoOperands(ComplexNumber op1, ComplexNumber op2) {
        try {
            op1 = converter.parseComplexNumber(usedViewer.getUserInput("Введите 1-е комплексное число "));
            System.out.printf("Первый операнд равен " + op1);
        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            op2 = converter.parseComplexNumber(usedViewer.getUserInput("Введите 2-е комплексное число "));
            System.out.printf("Второй операнд равен " + op2);
        } catch (Exception e) {
            System.err.println(e);
        }
        return true;
    }
}

