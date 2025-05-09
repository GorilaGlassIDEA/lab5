package by.dima.model.common.route.sub;

import java.io.Serializable;
import java.util.List;

public interface NormalisationValue extends Serializable {
    double normalize(Number number);

    void setNumbers(List<Number> numbers);

    List<Double> normalizeAll(List<Number> numbers);
}
