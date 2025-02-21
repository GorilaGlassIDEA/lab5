package by.dima.model.service.generate.id;

import junit.framework.TestCase;
import org.junit.Test;

public class IdGenerateMyTest extends TestCase {

    public void testGenerateTest() {
        IdGenerateble generatorId = new IdGenerateMy(10, 2, 4, 1);
        assertEquals(11, generatorId.generateId());

        IdGenerateble generatorId2 = new IdGenerateMy();
        assertEquals(1, generatorId2.generateId());

    }

}