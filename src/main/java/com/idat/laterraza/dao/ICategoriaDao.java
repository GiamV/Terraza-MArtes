package com.idat.laterraza.dao;

import org.springframework.data.repository.CrudRepository;

import com.idat.laterraza.entity.Categoria;


public interface ICategoriaDao extends CrudRepository<Categoria, Long  > {

}
