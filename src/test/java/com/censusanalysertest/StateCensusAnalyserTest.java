package com.censusanalysertest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.censusanalyser.MyException;
import com.censusanalyser.StateCensusAnalyser;
import com.censusanalyser.CSVStateCensus;

public class StateCensusAnalyserTest {

	@Test
	public void givenCsvFile_with8rowscomparingwith8_returnstrue() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/StateCensusData.csv");
			Assert.assertEquals(8,analyser.readStateRecordDetail());
		} catch (MyException e) {
			System.out.println(e);
		}
	}

	@Test
	public void givenCsvFile_whichIsIncorrect_expectsException() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/StateCensusDataType.csv");
			analyser.readStateRecordDetail();
		} catch (MyException e) {
			Assert.assertEquals("File not found", e.getMessage());
			System.out.println(e);
		}
	}

	@Test
	public void givenCsvFile_whichIsWrongType_returnsFalse() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/AddressBook.txt");
			analyser.readStateRecordDetail();
		} catch (MyException e) {
			Assert.assertEquals("Incorrect Type", e.getMessage());
			System.out.println(e);
		}
	}

	@Test
	public void givenCsvFile_withwrongdelimeter_returnsFalse() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/InvalidDelimiter.csv");
			analyser.readStateRecordDetail();
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Incorrect Delimeter", e.getMessage());
		}
	}

	@Test
	public void givenCsvFile_withwrongdHeader_returnsFalse() {
		try {
			StateCensusAnalyser analyser = new StateCensusAnalyser(
					"/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StateCensusAnalyser/resources/IncorrectHeader.csv");
			analyser.readStateRecordDetail();
		} catch (MyException e) {

			System.out.println(e);
			Assert.assertEquals("Header doesn't match", e.getMessage());
		}
	}
}
