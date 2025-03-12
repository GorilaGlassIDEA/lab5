package by.dima.model.data;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectionControllerTest {

    @InjectMocks
    CollectionController collectionController;

    @Mock
    Models models;

    @Mock
    WriteableFile writeableFile;

    @Mock
    ParserToJson parser;

    @Mock
    Route route1;

    @Mock
    Route route2;

    @Mock
    Route route3;

    Map<Long, Route> routeMap;

    @BeforeEach
    void mockInit() {
        MockitoAnnotations.openMocks(this);
        routeMap = new HashMap<>();
        when(route1.getId()).thenReturn(1L);
        when(route2.getId()).thenReturn(2L);
        // route3 - clean var
        when(route3.getId()).thenReturn(3L);

        routeMap.put(route1.getId(), route1);
        routeMap.put(route2.getId(), route2);
        when(models.getRoutesMap()).thenReturn(routeMap);

    }

    @Test
    void addCollectionTest() {
        collectionController.addElem(route3);
        routeMap.put(route3.getId(), route3);
        assertEquals(models, collectionController.getModels());
    }
}