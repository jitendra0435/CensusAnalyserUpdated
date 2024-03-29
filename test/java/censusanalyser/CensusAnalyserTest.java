package censusanalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIAN_STATE_CSV_PATH = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String INDIAN_STATE_CSV_PATH_DUMMY = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCensusDataInCorrectHeader.csv";
    private static final String INDIAN_STATECODE_CSV_PATH_DUMMY = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCensusDataInCorrectHeader.csv";
    private static final String INDIAN_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String US_CENSUS_CSV_PATH="/home/admin1/Desktop/CensusAnalyser/src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser indiaCensusAdapter = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            Map<String, CensusDAO> numOfRecords = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords.size());
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser indiaCensusAdapter = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianSateCSV_ShouldReturnExactCount() {
        try {
            CensusAnalyser indiaCensusAdapter = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            Map<String, CensusDAO> numOfRecords = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIAN_STATE_CSV_PATH);
            Assert.assertEquals(37, numOfRecords.size());
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCodeCSVFile_withWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser indiaCensusAdapter = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIAN_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCencusData_whenContainInCorrectHeader() {
        try {
            CensusAnalyser indiaCensusAdapter = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIAN_STATECODE_CSV_PATH_DUMMY);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_WHILE_LOADING, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVfile_whenContainInCorrectHeader() {
        try {
            CensusAnalyser indiaCensusAdapter = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIAN_STATE_CSV_PATH_DUMMY);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_WHILE_LOADING, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVfile_whenContainInCorrectDelimeter() {
        try {
            CensusAnalyser indiaCensusAdapter = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIAN_STATECODE_CSV_PATH_DUMMY);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_WHILE_LOADING, e.type);
        }
    }

    @Test
    public void givenMethodIf_PassesTheemptyFile_ThrowException(){
        try {
            CensusAnalyser indiaCensusAdapter = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,"");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_WHILE_LOADING, e.type);
        }

    }

    @Test
    public void givenIndianCensusCodeData_whenContainInCorrectDelimeter() {
        try {
            CensusAnalyser indiaCensusAdapter = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIAN_STATE_CSV_PATH_DUMMY);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_WHILE_LOADING, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.STATE);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.DENSITY_PER_SQ_KM);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Bihar", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnAreaInSqKm_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.AREAIN_SQKM);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Rajasthan", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.USA);
            Map<String, CensusDAO> censusDatacount = censusAnalyser.loadCensusData(CensusAnalyser.Country.USA,US_CENSUS_CSV_PATH);
            Assert.assertEquals(51, censusDatacount.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.USA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.USA,US_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.STATE);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Alabama", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.USA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.USA,US_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("California", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.USA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.USA,US_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.DENSITY_PER_SQ_KM);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("District of Columbia", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnAreaInSqKm_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.USA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.USA,US_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.AREAIN_SQKM);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCensusDataByDesending_whenSortedPopulationWithDensity_shouldReturnResult()
    {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        try {
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.POPULUSSTATEWITH_DENSITY);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("West Bengal", censusCSV[censusCSV.length-1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSStateCensusDataByDesending_whenSortedPopulationWithDensity_shouldReturnResult()
    {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.USA);
        try {
            censusAnalyser.loadCensusData(CensusAnalyser.Country.USA,US_CENSUS_CSV_PATH);
            String sortedCensusData = censusAnalyser.genericSort(StateCensusColumnsName.POPULUSSTATEWITH_DENSITY);
            USCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Connecticut", censusCSV[censusCSV.length-1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
