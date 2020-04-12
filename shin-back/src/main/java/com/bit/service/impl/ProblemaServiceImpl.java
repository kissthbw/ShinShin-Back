package com.bit.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bit.communication.CloundinaryService;
import com.bit.dao.CatalogoTipoProductoDAO;
import com.bit.model.Problema;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.ProblemaService;
import com.bit.dao.ProblemaDAO;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ProblemaServiceImpl implements ProblemaService{
	@Autowired
	private ProblemaDAO problemaDAO;
		
	@Override
	@Transactional
	public String guardaProblemas(Problema problema) {
		problemaDAO.save(problema);
		return "OK";
	}

}
