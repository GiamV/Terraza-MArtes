package com.idat.laterraza.serviceR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.laterraza.entity.DetalleVenta;
import com.idat.laterraza.repository.IDetalleRepository;

@Service
public class DetaVServiceIm {
	@Autowired
	IDetalleRepository DetalleRepo;
	
	public List<DetalleVenta> findByCaU(Long codcab) {
		return (List<DetalleVenta>) DetalleRepo.findByCabV(codcab);
	}

}
