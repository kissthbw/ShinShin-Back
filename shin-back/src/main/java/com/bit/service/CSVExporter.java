package com.bit.service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.bit.model.report.ProductoTicketUsuarioReport;

public interface CSVExporter {
	void writeCSV( Writer writer, String [] headers, List<List<Object>> rows ) throws IOException;
	void writeCSVFromListBean( Writer writer, String [] headers, List<ProductoTicketUsuarioReport> rows ) throws IOException;
}
