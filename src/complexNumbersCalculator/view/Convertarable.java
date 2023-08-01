package complexNumbersCalculator.view;

public interface Convertarable<N, O> {
    public O stringToOperation (String str);
    public String operationToString(O op);
    N parseNumber(String string);
}
