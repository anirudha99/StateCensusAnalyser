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

	public int readStateRecordDetail() throws MyException {
		int count = 0;

		try {
			Reader reader = Files.newBufferedReader(Paths.get(Sample_CSV_File_Path));
			@SuppressWarnings("unchecked")
			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader).withType(CSVStateCensus.class)
			.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<CSVStateCensus> csvUserIterator = csvToBean.iterator();
			while (csvUserIterator.hasNext()) {
				CSVStateCensus state = csvUserIterator.next();
				count++;
				if (state.getState() == null || state.getPopulation() == 0 || state.getAreaInSqKm() == 0) {
					throw new MyException(MyException.ExceptionType.INCORRECT_HEADER, "Header doesn't match");
				}
			}
		}
		catch (NoSuchFileException e) {
			if (Sample_CSV_File_Path.contains(".csv")) {
				throw new MyException(MyException.ExceptionType.FILE_NOT_FOUND, "File not found");
			}
			throw new MyException(MyException.ExceptionType.INCORRECT_TYPE, "Incorrect Type");

		} catch (RuntimeException e) {
			throw new MyException(MyException.ExceptionType.DELIMITER_NOT_FOUND, "Incorrect Delimiter");
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println(count);
		return count;
	}
}
