package complexNumbersCalculator.view;

import complexNumbersCalculator.model.impl.ComplexNumber;

public interface Viewerable <NUM> {
    public void showResultOfOperation (NUM op1, NUM op2, Operation op, NUM result);
    public NUM getUserNumber();
    public void showPrompt (String promptMessage);
    public String getUserInput ();
    public Operation getOperation();
}
