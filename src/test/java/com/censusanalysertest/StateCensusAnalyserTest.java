package com.censusanalysertest;

import java.io.IOException;


import org.junit.Assert;
import org.junit.Test;

import com.censusanalyser.MyException;
import com.censusanalyser.StateCensusAnalyser;
import com.censusanalyser.CSVStateCensus;
import com.censusanalyser.CsvStateCode;

public class StateCensusAnalyserTest {

	/**
	 * This test case pass when a file with 8 rows is read
	 */
	@Test
	public void givenCsvFile_with8rowscomparingwith8_returnstrue() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/StateCensusData.csv");
			Assert.assertEquals(8,analyser.readStateRecordDetail(CSVStateCensus.class));
		} catch (MyException e) {
			System.out.println(e);
		}
	}

	/**
	 * If given wrong csv file anticipates exception
	 */
	@Test
	public void givenCsvFile_whichIsIncorrect_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/StateCensusDataType.csv");
			analyser.readStateRecordDetail(CSVStateCensus.class);
		} catch (MyException e) {
			Assert.assertEquals("File not found", e.getMessage());
			System.out.println(e);
		}
	}

	/**
	 * If given file other than csv type, anticipates custom exception
	 */
	@Test
	public void givenCsvFile_whichIsWrongType_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/AddressBook.txt");
			analyser.readStateRecordDetail(CSVStateCensus.class);
		} catch (MyException e) {
			Assert.assertEquals("Incorrect File Type", e.getMessage());
			System.out.println(e);
		}
	}

	/**
	 * If csv file with invalid delimiter if given Anticipates custom exception
	 */
	@Test
	public void givenCsvFile_withwrongdelimiter_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/InvalidDelimiter.csv");
			analyser.readStateRecordDetail(CSVStateCensus.class);
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Incorrect Delimiter", e.getMessage());
		}
	}

	@Test
	public void givenCsvFile_withwrongdHeader_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/IncorrectHeader.csv");
			analyser.readStateRecordDetail(CSVStateCensus.class);
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Header doesn't match", e.getMessage());
		}
	}
	//
	//
	//
	//
	/**
	 * Test case passes when a csv state code file with 3 rows of data is read
	 */
	@Test
	public void givenStateCodeCsvFile_with3rowscomparingwith3_returnstrue() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/stateCode.csv");
			Assert.assertEquals(3, analyser.readStateRecordDetail(CsvStateCode.class));
		} catch (MyException e) {
			System.out.println(e);
		}
	}

	/**
	 * Given wrong csv file Anticipates custom exception
	 */
	@Test
	public void givenStateCodeCsvFile_whichIsIncorrect_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/stateCodewrong.csv");
			analyser.readStateRecordDetail(CsvStateCode.class);
		} catch (MyException e) {
			Assert.assertEquals("File not found", e.getMessage());
			System.out.println(e);
		}

	}
	
	/**
	 * If given file other than csv type. Anticipates custom exception
	 */
	@Test
	public void givenStateCodeCsvFile_whichIsWrongType_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/bookexample.txt");
			analyser.readStateRecordDetail(CsvStateCode.class);
		} catch (MyException e) {
			Assert.assertEquals("Incorrect File Type", e.getMessage());
			System.out.println(e);
		}
	}
	
	/**
	 * If csv file with invalid delimiter if given Anticipates custom exception
	 */
	@Test
	public void givenStateCodeCsvFile_withwrongDelimeter_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/DelimiterCode.csv");
			analyser.readStateRecordDetail(CsvStateCode.class);
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Incorrect Delimiter", e.getMessage());
		}
	}
	
	/**
	 * If csv file with invalid header if given Anticipates custom exception
	 */
	@Test
	public void givenStateCodeCsvFile_withwrongdHeader_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/InvalidHeaderStateCode.csv");
			analyser.readStateRecordDetail(CsvStateCode.class);
		} catch (MyException e) {

			System.out.println(e);
		}
		
	}
}
