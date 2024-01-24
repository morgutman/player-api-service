package com.example.playerapiservice.utils;

import com.example.playerapiservice.AbstractPlayerApiServiceTest;
import com.example.playerapiservice.entities.Player;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class CsvUtilTest<T> extends AbstractPlayerApiServiceTest {
    @Mock
    private CSVReader csvReader;
    @Mock
    private CsvToBean<T> csvToBean;
    @InjectMocks
    private CsvUtil<T> csvUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReadCsv_InvalidPath() throws FileNotFoundException {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> csvUtil.readCsv("", (Class<T>) Object.class));
    }

    @Test
    public void testReadCsv_FileNotFound() throws FileNotFoundException {
        // Arrange
        String csvFilePath = "/path/nonexistent.csv";

        // Act and Assert
        assertThrows(FileNotFoundException.class, () -> csvUtil.readCsv(csvFilePath, (Class<T>) Object.class));
    }


    @Test
    public void testReadCsv_Success() throws FileNotFoundException {
        // Arrange
        String csvFilePath = "src/test/resources/data/player_test.csv";

        // Act
        List<Player> players = (List<Player>) csvUtil.readCsv(csvFilePath, (Class<T>) Player.class);

        // Assert
        assertNotNull(players);
        assertEquals(1, players.size());
    }
}
