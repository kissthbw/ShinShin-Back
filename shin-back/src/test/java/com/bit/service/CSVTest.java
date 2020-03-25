package com.bit.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bit.service.impl.CSVExporterImpl;

public class CSVTest {
	public static void main(String[] args) {
		CSVExporter csv = new CSVExporterImpl();
		
		depositosGeneral();
		
		String [] headers = {"ID", "Nombre", "Cuenta"};
		
		List<List<Object>> rows = new ArrayList<>();
		rows.add( Arrays.asList( new Object[] { "1", "Juan Osorio", "2102" } ) );
		rows.add( Arrays.asList( new Object[] { "2", "Paola Sanchez Pardo", "2102" } ) );
		rows.add( Arrays.asList( new Object[] { "3", "Joshua Sanchez", "2102" } ) );
		try {
			csv.writeCSV(new FileWriter("/Users/juanosorioalvarez/Desktop/test3.csv"), headers, rows);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void depositosGeneral() {
		CSVExporter csv = new CSVExporterImpl();
		
		String [] headers = {"", ""};
		
		List<List<Object>> rows = new ArrayList<>();
		rows.add( Arrays.asList( new Object[] { "Dia", "" } ) );
		rows.add( Arrays.asList( new Object[] { "D24", "2" } ) );
		rows.add( Arrays.asList( new Object[] { "Semanal", "" } ) );
		rows.add( Arrays.asList( new Object[] { "26-01-2020 - 01-02-2020", "4", } ) );
		rows.add( Arrays.asList( new Object[] { "Mensual", "" } ) );
		rows.add( Arrays.asList( new Object[] { "Enero", "2",  } ) );
		rows.add( Arrays.asList( new Object[] { "Febrero", "1",  } ) );
		try {
			csv.writeCSV(new FileWriter("/Users/juanosorioalvarez/Desktop/general.csv"), headers, rows);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
