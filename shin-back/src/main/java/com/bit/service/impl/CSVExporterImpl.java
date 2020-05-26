package com.bit.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.bit.model.report.ProductoTicketUsuarioReport;
import com.bit.service.CSVExporter;

@Component
public class CSVExporterImpl implements CSVExporter {

	@Override
	public void writeCSV( Writer writer, String [] headers, List<List<Object>> rows ) throws IOException {
		ICsvListWriter w = null;
		try {
			w = new CsvListWriter(writer, //new FileWriter("/Users/juanosorioalvarez/Desktop/test3.csv"),
					CsvPreference.STANDARD_PREFERENCE);

			w.writeHeader( headers );

			for( List<Object> row : rows ) {
				w.write(row);
			}

		} finally {
			if (w != null) {
				w.close();
			}
		}
		
	}
	
	@Override
	public void writeCSVFromListBean(Writer writer, String[] headers, List<ProductoTicketUsuarioReport> rows) throws IOException {
		ICsvBeanWriter w = null;
		
		try {
			w = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			
			w.writeHeader( headers );

			for( ProductoTicketUsuarioReport row : rows ) {
				w.write(row, headers, getProcessors());
			}

		} finally {
			if (w != null) {
				w.close();
			}
		}
		
	}

	private static CellProcessor[] getProcessors() {
        
        final CellProcessor[] processors = new CellProcessor[] { 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(), 
        		new Optional(),
        		new Optional(), 
        		new Optional(),
        		new Optional()
        };
        
        return processors;
	}

}
