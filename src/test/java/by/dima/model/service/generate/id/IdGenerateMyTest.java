package by.dima.model.service.generate.id;

import by.dima.model.data.CollectionController;
import by.dima.model.data.route.model.main.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IdGenerateMyTest {


    @Mock
    private Route mockRoute;
    @Mock
    private CollectionController collectionController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void generateIdTest() {
        Long id = 10L;
        Map<Long, Route> routeMap = new HashMap<>();
        routeMap.put(id, mockRoute);
        Mockito.when(mockRoute.getId()).thenReturn(id);
        Mockito.when(collectionController.getCollectionForControl()).thenReturn(routeMap);
        IdGenerateMy idGenerateMy = new IdGenerateMy(collectionController);
        assertEquals(id + 1, idGenerateMy.generateId());
    }
}