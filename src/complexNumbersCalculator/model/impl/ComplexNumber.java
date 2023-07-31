package complexNumbersCalculator.model.impl;

import java.util.Objects;

public class ComplexNumber {
    private Double realPart;
    private Double imagePart;
//    public static final Logger LOG = Log.log(ComplexNumber.class.getName());

    public ComplexNumber() {
        this.realPart = (double) 0;
        this.imagePart = (double) 0;
    }

    public ComplexNumber(Double realPart, Double imagePart) {
        this.realPart = realPart;
        this.imagePart = imagePart;
    }

    public Double getRealPart() {
        return realPart;
    }

    public Double getImagePart() {
        return imagePart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexNumber that)) return false;
        return Double.compare(that.getRealPart(), getRealPart()) == 0 && Double.compare(that.getImagePart(), getImagePart()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRealPart(), getImagePart());
    }

    @Override
    public String toString() {
        if (imagePart == 0) {
            return toString(realPart);
        }
        if (realPart == 0) {
            if (imagePart.equals(1.0)) return "i";
            if (imagePart.equals(-1.0)) return "-i";
            if (imagePart > 0) return String.format("%s*i", toString(imagePart));
            if (imagePart < 0) return String.format("-%s*i", toString(imagePart).replace("-", ""));
        }

        if (imagePart.equals(1.0)) return String.format("%s + i", toString(realPart));
        if (imagePart.equals(-1.0)) return  String.format("%s - i", toString(realPart));

            if (imagePart > 0) return String.format("%s + %s*i", toString(realPart), toString(imagePart));
            else return String.format("%s - %s*i", toString(realPart), toString(imagePart).replace("-", ""));
    }
    private String toString (Double num) {
        System.out.printf("Вызываем toString для Double: %f\n", num);
        Double temp = num;
        if (num < 0) temp = -temp;
        Double modInt = temp % 1;
        System.out.printf("Дробная часть = %f", modInt);
        Integer divInt = (int) (temp / 1);
        System.out.printf("Целая часть = %d\n", divInt);
        if (temp.equals((double) divInt)) {
            if (num >= 0) return String.format("%d", divInt);
            else return String.format("%d", -divInt);
        } else {
            System.out.printf("Преобразовали Double к строке: %s\n", num.toString());
            return String.format("%.4f", num);
        }
    }

}
