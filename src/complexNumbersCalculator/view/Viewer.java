package complexNumbersCalculator.view;

import complexNumbersCalculator.logging.Log;
import complexNumbersCalculator.model.impl.ComplexNumber;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Viewer implements Viewerable<ComplexNumber> {
    private final Converter converter = new Converter();

    public String getUserInput () {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public void showPrompt (String promptMessage) {
        System.out.print(promptMessage + "========> ");
    }
    public Operation getOperation () {
       return converter.stringToOperation(getUserInput());
    }
    public void showResultOfOperation (ComplexNumber op1, ComplexNumber op2, Operation op, ComplexNumber result) {
            System.out.println("(" + op1 + ") "+ converter.operationToString(op) + " (" + op2 + ") = (" + result + ")");
    }
    public ComplexNumber getUserNumber () throws RuntimeException {
        String userInput = getUserInput();
        ComplexNumber result = converter.parseNumber(userInput);
        return result;
    }

}
