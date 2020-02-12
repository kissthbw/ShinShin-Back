package com.bit.service;

import java.util.HashMap;
import java.util.List;

import com.bit.model.dto.Item;

public interface ReportService {
	List<Item> getBonificacionesGeneralInfo();
	HashMap<String, List<Item>> getBonificacionesRecargasInfo();
}
