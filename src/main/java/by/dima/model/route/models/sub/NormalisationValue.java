package by.dima.model.route.models.sub;

import java.util.List;

public interface NormalisationValue {
    double normalize(Number number);

    void setNumbers(List<Number> numbers);

    List<Double> normalizeAll(List<Number> numbers);
}
