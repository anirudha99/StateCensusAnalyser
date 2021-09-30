package com.censusanalyser;

import java.io.IOException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	private String Sample_CSV_File_Path = "";

	public StateCensusAnalyser() {

	}

	public StateCensusAnalyser(String Sample_CSV_File_Path) {
		this.Sample_CSV_File_Path = Sample_CSV_File_Path;
	}

	/**
	 * @param <T>
	 * @param className - Name of the class to which we wanna map csv file data to
	 * @return number of data read from csv file
	 * @throws MyException
	 */
	public <T> int readStateRecordDetail(Class<T> className) throws MyException {
		int count = 0;

		try {
			Reader reader = Files.newBufferedReader(Paths.get(Sample_CSV_File_Path));
			@SuppressWarnings("unchecked")
			CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader).withType(className).withIgnoreLeadingWhiteSpace(true)
			.build();
			Iterator<T> csvUserIterator = csvToBean.iterator();
			while (csvUserIterator.hasNext()) {
				T state = csvUserIterator.next();
				count++;
				if (state instanceof CSVStateCensus) { // comparing if read file is of CsvStateCensus class
					if (((CSVStateCensus) state).getState() == null || ((CSVStateCensus) state).getPopulation() == 0
							|| ((CSVStateCensus) state).getAreaInSqKm() == 0) {
						throw new MyException(MyException.ExceptionType.INCORRECT_HEADER, "Header doesn't match");
					}
				}
				if (state instanceof CsvStateCode) { // comparing if read file is of CsvStateCode class
					if (((CsvStateCode) state).getSrNo() == 0 || ((CsvStateCode) state).getStateName() == null
							|| ((CsvStateCode) state).getStateCode() == null) {

						throw new MyException(MyException.ExceptionType.INCORRECT_HEADER, "Header doesn't match");
					}
				}
			}
		} catch (NoSuchFileException e) {
			if (Sample_CSV_File_Path.contains(".csv")) {
				throw new MyException(MyException.ExceptionType.FILE_NOT_FOUND, "File not found");
			}
			throw new MyException(MyException.ExceptionType.INCORRECT_TYPE, "Incorrect File Type");

		} catch (RuntimeException e) {
			throw new MyException(MyException.ExceptionType.DELIMITER_NOT_FOUND, "Incorrect Delimiter");
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println(count);
		return count;
	}
}
