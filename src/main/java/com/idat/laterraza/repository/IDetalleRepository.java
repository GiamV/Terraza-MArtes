package com.idat.laterraza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.idat.laterraza.entity.DetalleVenta;


public interface IDetalleRepository extends JpaRepository<DetalleVenta, Long> { 
	@Query(value= "{call consultadetventa(:xcodcab)}",nativeQuery=true)
	List<DetalleVenta> findByCabV(@Param("xcodcab")Long xcodcab);
}
