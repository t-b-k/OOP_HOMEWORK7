package complexNumbersCalculator.util;

import complexNumbersCalculator.model.impl.ComplexNumber;

import static complexNumbersCalculator.util.Operation.*;

public class Converter {
    public Operation getOperation(String inputSymbol) {
        switch (inputSymbol) {
            case "+" : return TO_ADD;
            case "-" : return TO_SUBTRACT;
            case "*", "x", "X", "х", "Х" : return  TO_MULTIPLY;
            case "/", ":" : return TO_DIVIDE;
            case "Q", "q": return TO_BREAK;
            default : return NONE;
        }
    }
    public String showOperation(Operation op) {
        switch (op) {
            case TO_ADD : return "+";
            case TO_SUBTRACT : return "-";
            case TO_DIVIDE : return "/";
            case TO_MULTIPLY : return "*";
            default: return "";
        }
    }

    public ComplexNumber parseComplexNumber(String string) throws RuntimeException {
//        Integer realWhole = 0, imageWhole = 0;
//        Double realFraction = 0.0, imageFraction = 0.0;
        Double realPart = 0.0, imagePart = 0.0;
        ComplexNumber result;
        if (string == null) throw new RuntimeException("Method parseComplexNumber: String is absent!!!");
        StringBuilder temp = new StringBuilder(string.trim());
        if (temp.isEmpty()) throw new RuntimeException("Method parseComplexNumber: String is empty!!!");
        boolean negative = false;
        if (temp.charAt(0) == '-') {
            System.out.println("Считали минус в начале и удалили его\n");
            temp.deleteCharAt(0);
            negative = true;
        }
        removeBegSpaces(temp);
        System.out.printf("temp = %s\n", temp);
        if (nextIs_i(temp)) {
            System.out.println("Далее идет i\n");
            if (negative) imagePart = -1.0;
            else imagePart = 1.0;
            temp.deleteCharAt(0);
            System.out.printf("Следует ли продолжение? " + containsSecondPart(temp));
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
                if (!temp.isEmpty())
                    throw new RuntimeException("Method parseComplexNumber: This is not a complex number!!!");
                System.out.printf("Image Part = %f", imagePart);
                System.out.printf("Real Part = %f", realPart);
                if (negative) realPart = -realPart;
            }
            result = new ComplexNumber(realPart, imagePart);
            System.out.printf("Число состоит из одного i со знаком: " + result + "\n");
            return (result);
        }
        System.out.println("Дошли до readDouble");
        System.out.println(temp);
        // Пытаемся считать первое вещественное число
        realPart = readDouble(temp);
        System.out.printf("Real part = %f\n\n", realPart);
        if (negative) realPart = -realPart;
        removeBegSpaces(temp);
        System.out.printf("Считали вещественную часть, осталось %s\n", temp);
        if (nextIsAsterix(temp)) { // если за числом следует звездочка, то это мнимая часть и далее должно быть i
            temp.deleteCharAt(0);
            // далее должна идти i
            if (!nextIs_i(temp))
                throw new RuntimeException("Method parseComplexNumber: This is not a complex number!!!");
        }
        if (nextIs_i(temp)) { // Значит, сначала идет мнимая часть
            temp.deleteCharAt(0);
            System.out.println("Далее следует i");
            imagePart = realPart;
            realPart = 0.0;
            // Проверяем, не следует ли за мнимой частью вещественная
            if (containsSecondPart(temp)) {
                if (temp.charAt(0) == '-') {
                    negative = true;
                } else {
                    negative = false;
                }
                temp.deleteCharAt(0);
                removeBegSpaces(temp);
                realPart = readDouble(temp);
                if (negative) realPart = -realPart;
                if (!temp.isEmpty())
                    throw new RuntimeException("Method parseComplexNumber: This is not a complex number!!!");
            }
            return (new ComplexNumber(realPart, imagePart));
        }
        System.out.println("Есть ли мнимая часть? " + containsSecondPart(temp));
        if (containsSecondPart(temp)) {
            if (temp.charAt(0) == '-') {
                negative = true;
            } else {
                negative = false;
            }
            temp.deleteCharAt(0);
            removeBegSpaces(temp);
            System.out.println("убрали знак мнимой части, осталось" + temp + "\n");
            if (nextIs_i(temp)) {
                imagePart = 1.0;
            } else {
                imagePart = readDouble(temp);
            }
            if (negative) imagePart = -imagePart;
            if (!endsAsComplex(temp))
                throw new RuntimeException("Method parseComplexNumber: This is not a complex number!!!");
        }
        result = new ComplexNumber(realPart, imagePart);
        System.out.printf("Число состоит из одного i со знаком: " + result + "\n");
        return (result);
    }

    private Double readDouble(StringBuilder sb) throws RuntimeException {
        Double result = 0.0;
        Integer wholePart = 0;
        Double fractionPart = 0.0;
        removeBegSpaces(sb);
        wholePart = readInt(sb);
        System.out.printf("Whole Part = %d", wholePart);
        if (nextIsSeparator(sb)) {
            sb.deleteCharAt(0);
            fractionPart = readFract(sb);
            System.out.printf("Fraction Part = %f\n", fractionPart);
        }
        return wholePart + fractionPart;
    }
    private int readInt (StringBuilder sb) throws RuntimeException {
        System.out.println("Зашли в readInt");
        System.out.printf("sb = %s\n", sb);
        removeBegSpaces(sb);
        System.out.println("Убрали пробелы");
        System.out.printf("sb = %s\n", sb);
        removeBegZeros(sb);
        System.out.println("Убрали нули");
        System.out.printf("sb = %s\n", sb);
        int result = 0;
        int factor = 10;
        System.out.printf("result = %d, factor = %d, sb = %s \n", result, factor, sb);
        if (nextIsDigit(sb)) System.out.printf("result = %d \n", Integer.parseInt(String.format("%s", sb.charAt(0))));
        else System.out.printf("Цифр нет, есть только %c \n", sb.charAt(0));
        if (nextIsDigit(sb)) {
            while (nextIsDigit(sb)) {
                System.out.printf("sb.charAt(0) = %c\n", sb.charAt(0));
                result = result * factor + Integer.parseInt(String.format("%c", sb.charAt(0)));
                sb.deleteCharAt(0);
                System.out.printf("result = %d\n", result);
                System.out.printf("sb = %s\n", sb);
            }
            System.out.printf("int = %d\n", result);
            return result;
        } else {
            throw new RuntimeException("Method readInt: No Integer at the beginning of the string argument!!!");
        }
    }
    private Double readFract (StringBuilder sb) throws RuntimeException {
        Double result = 0.0;
        Double factor = 0.1;

        if (nextIsDigit(sb)) {
            while (nextIsDigit(sb)) {
                result = result + Integer.parseInt(String.valueOf(sb.charAt(0))) * factor;
                sb.deleteCharAt(0);
                factor = factor / 10;
            }
            return result;
        }
        throw new RuntimeException("Method readInt: No Fractional part after separator!!!");
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
