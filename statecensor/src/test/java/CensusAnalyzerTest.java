import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.CSVStateCensus;
import com.example.CSVStates;
import com.example.StateCensusAnalyzer;
import com.exceptions.*;

public class CensusAnalyzerTest {

    private StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer("src/main/resources/StateCensus.csv");

    @Test
    public void testNumberOfRecords() {
        try {
            int recordsFetched = stateCensusAnalyzer.countRecordsInStateCensusData();
            assertEquals(37, recordsFetched);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }

    @Test
    public void testIncorrectFile() {
        try {
            CSVStateCensus dummy = new CSVStateCensus("src/main/resources/DUMMY_FILE.csv");
            assertThrows(FileReaderException.class, () -> dummy.loadDataFromFile());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }

    @Test
    public void testIncorrectFileType() {
        try {
            CSVStateCensus dummyFileType = new CSVStateCensus("src/main/resources/DUMMY_FILE.txt");
            assertThrows(FileFormatException.class, () -> dummyFileType.loadDataFromFile());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }

    @Test
    public void testIncorrectDelimiter() {
        try {
            CSVStateCensus dummyDelimiter = new CSVStateCensus("src/main/resources/StateCensusTest1.csv");
            assertThrows(DelimiterException.class, () -> dummyDelimiter.loadDataFromFile());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }

    @Test
    public void testIncorrectHeader() {
        try {
            CSVStateCensus dummyHeader = new CSVStateCensus("src/main/resources/StateCensusTest2.csv");
            assertThrows(IncorrectHeaderException.class, () -> dummyHeader.loadDataFromFile());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }

    @Test
    public void testNumberOfStateRecords() {
        try {
            int stateRecordsFetched = stateCensusAnalyzer.countDistinctStateCodes();
            assertEquals(37, stateRecordsFetched);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }

    @Test
    public void testIncorrectFileForStateData() {
        try {
            CSVStates dummy = new CSVStates("src/main/resources/DUMMY_FILE.csv");
            assertThrows(FileReaderException.class, () -> dummy.loadDataFromFile());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }

    @Test
    public void testIncorrectFileTypeForStateData() {
        try {
            CSVStates dummyFileType = new CSVStates("src/main/resources/DUMMY_FILE.txt");
            assertThrows(FileFormatException.class, () -> dummyFileType.loadDataFromFile());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }

    @Test
    public void testIncorrectDelimiterForStateData() {
        try {
            CSVStates dummyDelimiter = new CSVStates("src/main/resources/StateCensusTest1.csv");
            assertThrows(DelimiterException.class, () -> dummyDelimiter.loadDataFromFile());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }

    @Test
    public void testIncorrectHeaderForStateData() {
        try {
            CSVStates dummyHeader = new CSVStates("src/main/resources/StateCensusTest2.csv");
            assertThrows(IncorrectHeaderException.class, () -> dummyHeader.loadDataFromFile());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getClass());
        }
    }
}

