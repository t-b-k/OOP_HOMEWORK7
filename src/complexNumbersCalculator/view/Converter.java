package complexNumbersCalculator.view;

import complexNumbersCalculator.logging.Log;
import complexNumbersCalculator.model.impl.ComplexNumber;

import java.util.logging.Level;
import java.util.logging.Logger;

import static complexNumbersCalculator.view.Operation.*;

public class Converter implements Convertarable<ComplexNumber, Operation>{
    private static final Logger CONVERTER_LOG = Log.log(Converter.class.getName());
    public Operation stringToOperation(String inputSymbol) {
        switch (inputSymbol) {
            case "+" : return TO_ADD;
            case "-" : return TO_SUBTRACT;
            case "*", "x", "X", "х", "Х" : return  TO_MULTIPLY;
            case "/", ":" : return TO_DIVIDE;
            case "Q", "q": return TO_BREAK;
            default : return NONE;
        }
    }
    public String operationToString(Operation op) {
        switch (op) {
            case TO_ADD : return "+";
            case TO_SUBTRACT : return "-";
            case TO_DIVIDE : return "/";
            case TO_MULTIPLY : return "*";
            default: return "";
        }
    }

    public ComplexNumber parseNumber(String string) throws RuntimeException {
        CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: parsing complex number... !!!\n");
        double realPart = 0.0, imagePart = 0.0;
        ComplexNumber result;
        if (string == null) {
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: user has input NOTHING !!!\n");
            throw new RuntimeException("RUNTIME EXCEPTION: user has input NOTHING !!!\n");
        }
        StringBuilder temp = new StringBuilder(string.trim());
        if (temp.isEmpty()) {
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: user has input ONLY SPACES !!!\n");
            throw new RuntimeException("RUNTIME EXCEPTION: user has input ONLY SPACES !!!\n");
        }
        boolean negative = false;
        if (temp.charAt(0) == '-') {
            temp.deleteCharAt(0);
            negative = true;
        }
        removeBegSpaces(temp);
        if (nextIs_i(temp)) {
            if (negative) imagePart = -1.0;
            else imagePart = 1.0;
            temp.deleteCharAt(0);
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: ...checking if there is a second part of a number... \n");
            // Проверяем, не следует ли за мнимой единицей вещественная часть
            if (containsSecondPart(temp)) {
                if (temp.charAt(0) == '-') {
                    negative = true;
                } else {
                    negative = false;
                }
                temp.deleteCharAt(0);
                removeBegSpaces(temp);
                realPart = readDouble(temp);
                CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: ...have read the second part of a number... \n");
                if (!temp.isEmpty()){
                    CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: user input is NOT A COMPLEX NUMBER !!!\n");
                    throw new RuntimeException("RUNTIME EXCEPTION: user input is NOT A COMPLEX NUMBER !!!\n");
                }
                if (negative) realPart = -realPart;
            }
            result = new ComplexNumber(realPart, imagePart);
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: user number is (i) or (-i) \n");
            return (result);
        }
        CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: trying to parse first double number ...\n");
        // Пытаемся считать первое вещественное число
        realPart = readDouble(temp);
        if (negative) realPart = -realPart;
        removeBegSpaces(temp);
        CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: have read the real part, continue analyzing the rest of user input...\n");
        if (nextIsAsterix(temp)) { // если за числом следует звездочка, то это мнимая часть и далее должно быть i
            temp.deleteCharAt(0);
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: found asterisk, removed...\n");
            if (!nextIs_i(temp)){
                CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: there is no \"i\" after asterisk!!!\n");
                throw new RuntimeException("RUNTIME EXCEPTION: user input is NOT A COMPLEX NUMBER !!!\n");
            }
        }
        if (nextIs_i(temp)) { // Значит, сначала идет мнимая часть
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: image part goes first...\n");
            temp.deleteCharAt(0);
//            System.out.println("Далее следует i");
            imagePart = realPart;
            realPart = 0.0;
            // Проверяем, не следует ли за мнимой частью вещественная
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: ...checking if there is a second part of a number... \n");
            if (containsSecondPart(temp)) {
                if (temp.charAt(0) == '-') {
                    negative = true;
                } else {
                    negative = false;
                }
                temp.deleteCharAt(0);
                removeBegSpaces(temp);
                CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: trying to parse the second double number ...\n");
                realPart = readDouble(temp);
                if (negative) realPart = -realPart;
                if (!temp.isEmpty()){
                    CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: user input contains illegal characters !!!\n");
                    throw new RuntimeException("RUNTIME EXCEPTION: user input is NOT A COMPLEX NUMBER !!!\n");
                }
            }
            return (new ComplexNumber(realPart, imagePart));
        }
        CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: real part goes first...\n");
        CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: is there an mage part?...\n");
        if (containsSecondPart(temp)) {
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: reading an image part...\n");
            if (temp.charAt(0) == '-') {
                negative = true;
            } else {
                negative = false;
            }
            temp.deleteCharAt(0);
            removeBegSpaces(temp);
//          убрали знак мнимой части, осталось" + temp + "\n");
            if (nextIs_i(temp)) {
                imagePart = 1.0;
            } else {
                CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: reading an image double...\n");
                imagePart = readDouble(temp);
            }
            if (negative) imagePart = -imagePart;
            if (!endsAsComplex(temp)) {
                CONVERTER_LOG.log(Level.INFO, "CONVERTER.parseNumber: user input doesn't finish as a complex one !!!\n");
                throw new RuntimeException("RUNTIME EXCEPTION: user input is NOT A COMPLEX NUMBER !!!\n");
            }
        }
        result = new ComplexNumber(realPart, imagePart);
//        System.out.printf("Число состоит из одного i со знаком: " + result + "\n");
        return (result);
    }

    private Double readDouble(StringBuilder sb) throws RuntimeException {
        CONVERTER_LOG.log(Level.INFO, "CONVERTER.readDouble: parsing double number... !!!\n");
        Double result = 0.0;
        int wholePart = 0;
        Double fractionPart = 0.0;
        removeBegSpaces(sb);
        wholePart = readInt(sb);
        if (nextIsSeparator(sb)) {
            sb.deleteCharAt(0);
            fractionPart = readFract(sb);
        }
        result = wholePart + fractionPart;
        CONVERTER_LOG.log(Level.INFO, "CONVERTER.readDouble: have read" + result + "\n");
        return result;
    }
    private int readInt (StringBuilder sb) throws RuntimeException {
        CONVERTER_LOG.log(Level.INFO, "CONVERTER.readInt: parsing integer number... !!!\n");
        removeBegSpaces(sb);
        removeBegZeros(sb);
        int result = 0;
        int factor = 10;
        if (nextIsDigit(sb)) {
            while (nextIsDigit(sb)) {
                result = result * factor + Integer.parseInt(String.format("%c", sb.charAt(0)));
                sb.deleteCharAt(0);
            }
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.readInt: have read" + result + "\n");
            return result;
        } else {
            CONVERTER_LOG.log(Level.INFO, "CONVERTER.readInt: can't parse Integer from user input !!!\n");
            throw new RuntimeException("RUNTIME EXCEPTION: Method readInt couldn't parse Integer !!!\n");
        }
    }
    private Double readFract (StringBuilder sb) throws RuntimeException {
        double result = 0.0;
        double factor = 0.1;

        if (nextIsDigit(sb)) {
            while (nextIsDigit(sb)) {
                result = result + Integer.parseInt(String.valueOf(sb.charAt(0))) * factor;
                sb.deleteCharAt(0);
                factor = factor / 10;
            }
            return result;
        }
        CONVERTER_LOG.log(Level.INFO, "CONVERTER.readFract: can't parse fractal part from user input !!!\n");
        throw new RuntimeException("RUNTIME EXCEPTION: Method readFract couldn't parse fractal part !!!\n");
    }
    private boolean containsSecondPart (StringBuilder sb) {
        removeBegSpaces(sb);
        if (!sb.isEmpty()) {
            if (nextIsPlusOrMinus(sb)) {
                return true;
            }
        }
        return false;
    }
    private boolean endsAsComplex (StringBuilder sb) {
        removeBegSpaces(sb); ;
        if (nextIsAsterix(sb)) {
            sb.deleteCharAt(0);
        }
        removeBegSpaces(sb);
        if (nextIs_i(sb)) {
            sb.deleteCharAt(0);
            removeBegSpaces(sb);
            if (sb.isEmpty()) return true;
            else return false;
        }
        return false;
    }
    private boolean whole (Double number) {
        return number%1 == 0;
    }
    private void removeBegSpaces (StringBuilder sb) {
        while (nextIsSpace(sb)) sb.deleteCharAt(0);
    }
    private void removeBegZeros (StringBuilder sb) {
        while (nextIsZero(sb)) sb.deleteCharAt(0);
    }
    private boolean nextIsSpace (StringBuilder sb) {
        if (!sb.isEmpty() && sb.charAt(0) == ' ') return true;
        else return false;
    }
    private boolean nextIsZero(StringBuilder sb) {
        if (!sb.isEmpty() && sb.charAt(0) == '0') return true;
        else return false;
    }
    private boolean nextIsDigit (StringBuilder sb) {
        if (!sb.isEmpty() && Character.isDigit(sb.charAt(0))) return true;
        else return false;
    }
    private boolean nextIsAsterix (StringBuilder sb) {
        if (!sb.isEmpty() && sb.charAt(0) == '*') return true;
        else return false;
    }
    private boolean nextIs_i (StringBuilder sb) {
        if (!sb.isEmpty() && sb.charAt(0) == 'i') return true;
        else return false;
    }
    private boolean nextIsSeparator (StringBuilder sb) {
        if (!sb.isEmpty() && (sb.charAt(0) == '.' || sb.charAt(0) == ',')) return true;
        else return false;
    }
    private boolean nextIsPlusOrMinus (StringBuilder sb) {
        if (!sb.isEmpty() && (sb.charAt(0) == '+' || sb.charAt(0) == '-')) return true;
        else return false;
    }

}
