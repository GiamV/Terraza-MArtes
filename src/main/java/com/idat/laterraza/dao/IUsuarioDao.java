package com.idat.laterraza.dao;

import org.springframework.data.repository.CrudRepository;


import com.idat.laterraza.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long  > {

}
