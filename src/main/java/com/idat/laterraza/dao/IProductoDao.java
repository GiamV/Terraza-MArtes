package com.idat.laterraza.dao;

import org.springframework.data.repository.CrudRepository;

import com.idat.laterraza.entity.Producto;



public interface IProductoDao extends CrudRepository<Producto, Long  >{

}
