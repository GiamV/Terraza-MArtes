package com.idat.laterraza.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.laterraza.entity.Usuario;

import com.idat.laterraza.service.IUsuarioService;
import com.idat.laterraza.serviceR.UsuarioServiceIm;

@CrossOrigin(origins= {"http://localhost/4200"})
@RestController
@RequestMapping("/usuario")
public class usuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private UsuarioServiceIm userService;

	
	//LISTAR USUARIOS
	@GetMapping("/usuarios")
	public List<Usuario> listar(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuariosoff")
	public List<Usuario> listaroff(){
		return userService.findByest();
	}
	
	//BUSCAR USUARIO POR ID
	@GetMapping("/usuario/{id}")
	public Usuario usuario (@PathVariable Long id) {
		return usuarioService.findById(id);
	}
	
	@GetMapping("/{user}/{passw}")
	public int verificacion(@PathVariable String user,@PathVariable String passw) {
		if(userService.findByU(user)!=null) {
			if(userService.iniciosesion(user,passw)!=null) {
				return 1;
			}else {
				return 2;
			}
		}else {
			return 3;
		}
	}
	
	@GetMapping("/{user}")
	public Usuario findByUser(@PathVariable String user) {
		return userService.findByU(user);
		
	}
	
	
	//CREAR UN NUEVO USUARIO
	@PostMapping("/usuarionew")
	public Usuario usuarionew (@RequestBody Usuario usuario) {
		usuarioService.save(usuario);
		
		return usuarioService.findById(usuario.getIdUsuario());
	}
	
	//ACTUALIZAR USUARIO
	@PutMapping("/usuarioupdate/{id}")
	public Usuario actualizar(@RequestBody Usuario usuario,@PathVariable Long id) {
		Usuario usuarioActual=usuarioService.findById(id);
		usuarioActual.setApellido(usuario.getApellido());
		usuarioActual.setNombre(usuario.getNombre());
		usuarioActual.setUsuario(usuario.getUsuario());
		usuarioActual.setContrasena(usuario.getContrasena());
		usuarioActual.setSexo(usuario.getSexo());
		usuarioActual.setDireccion(usuario.getDireccion());
		usuarioActual.setTelefono(usuario.getTelefono());
		usuarioActual.setDni(usuario.getDni());
		usuarioActual.setCorreo(usuario.getCorreo());
		usuarioActual.setEstado(usuario.getEstado());
		usuarioService.save(usuarioActual);
		return usuarioService.findById(id);
	}
	
	//ACTUALIZAR USUARIO
		@PutMapping("/usuariopassword/{id}/{pass}")
		public Usuario actualizarpassword(@RequestBody Usuario usuario,@PathVariable Long id,@PathVariable String pass) {
			Usuario usuarioActual=usuarioService.findById(id);
			usuarioActual.setContrasena(pass);
			usuarioService.save(usuarioActual);
			return usuarioService.findById(id);
		}
	
	//ELIMINAR USUARIO
	@DeleteMapping("/usuariodelete/{id}")
	public void delete(@PathVariable Long id) {
		usuarioService.eliminarUsuario(id);
	}
	
	@DeleteMapping("/usuarioestado/{id}")
	public void deleteestado(@PathVariable Long id) {
		Usuario usuarioActual=usuarioService.findById(id);
		if(usuarioActual.getEstado()==1) {
			usuarioActual.setEstado(0);
		}else {
			usuarioActual.setEstado(1);
		}
		usuarioService.save(usuarioActual);
	}
	
	@GetMapping("/usucorreo/{correo}")
	public Usuario recuperar(@PathVariable String correo) {
		Usuario usuarioActual=userService.recuperacino(correo);
		return usuarioActual;
		}
	
	
}
