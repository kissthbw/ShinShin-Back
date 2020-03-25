package com.bit.service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface CSVExporter {
	void writeCSV( Writer writer, String [] headers, List<List<Object>> rows ) throws IOException;
}
