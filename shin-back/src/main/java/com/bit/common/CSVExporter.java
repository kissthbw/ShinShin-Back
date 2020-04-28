package com.bit.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

@Component
public class CSVExporter {
	
	public void writeCSV( Writer writer, String [] headers, List<List<Object>> rows ) throws IOException {
		ICsvListWriter listWriter = null;
		try {
			listWriter = new CsvListWriter(writer, 
					CsvPreference.STANDARD_PREFERENCE);

			listWriter.writeHeader( headers );

			for( List<Object> row : rows ) {
				listWriter.write(row);
			}

		} finally {
			if (listWriter != null) {
				listWriter.close();
			}
		}
		
	}
	
/*
	public static void main(String[] args) throws IOException {
		// create the customer Lists (CsvListWriter also accepts arrays!)
		final List<Object> john = Arrays
				.asList(new Object[] { "1", "John", "Dunbar", new GregorianCalendar(1945, Calendar.JUNE, 13).getTime(),
						"1600 Amphitheatre Parkway\nMountain View, CA 94043\nUnited States", null, null,
						"\"May the Force be with you.\" - Star Wars", "jdunbar@gmail.com", 0L });

		final List<Object> bob = Arrays.asList(new Object[] { "2", "Bob", "Down",
				new GregorianCalendar(1919, Calendar.FEBRUARY, 25).getTime(),
				"1601 Willow Rd.\nMenlo Park, CA 94025\nUnited States", true, 0,
				"\"Frankly, my dear, I don't give a damn.\" - Gone With The Wind", "bobdown@hotmail.com", 123456L });

		ICsvListWriter listWriter = null;
		try {
			listWriter = new CsvListWriter(new FileWriter("/Users/juanosorioalvarez/Desktop/test2.csv"),
					CsvPreference.STANDARD_PREFERENCE);

			final CellProcessor[] processors = getProcessors();
			final String[] header = new String[] { "customerNo", "firstName", "lastName", "birthDate", "mailingAddress",
					"married", "numberOfKids", "favouriteQuote", "email", "loyaltyPoints" };

			// write the header
			listWriter.writeHeader(header);

			// write the customer lists
			listWriter.write(john);
			listWriter.write(bob);

		} finally {
			if (listWriter != null) {
				listWriter.close();
			}
		}
	}
	
	private static CellProcessor[] getProcessors() {
        
        final CellProcessor[] processors = new CellProcessor[] { 
                new UniqueHashCode(), // customerNo (must be unique)
                new NotNull(), // firstName
                new NotNull(), // lastName
                new FmtDate("dd/MM/yyyy"), // birthDate
                new NotNull(), // mailingAddress
                new Optional(new FmtBool("Y", "N")), // married
                new Optional(), // numberOfKids
                new NotNull(), // favouriteQuote
                new NotNull(), // email
                new LMinMax(0L, LMinMax.MAX_LONG) // loyaltyPoints
        };
        
        return processors;
	}
	*/
}
