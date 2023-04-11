package com.idat.laterraza.serviceR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.laterraza.entity.DetalleVenta;
import com.idat.laterraza.entity.Usuario;
import com.idat.laterraza.repository.IUsuarioRepository;

@Service
public class UsuarioServiceIm {
	
	@Autowired
	IUsuarioRepository usuarioRepo;
	
	public Usuario iniciosesion(String ida,String idas) {
		return usuarioRepo.inicios(ida,idas);
	}
	
	public Usuario findByU(String ida) {
		return usuarioRepo.findByUser(ida);
	}
	
	public List<Usuario> findByest() {
		return (List<Usuario>) usuarioRepo.findByestado();
	}
	public Usuario recuperacino(String correo) {
		return usuarioRepo.recuperar(correo);
	}
}
