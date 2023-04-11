package com.idat.laterraza.correo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.laterraza.entity.CabeceraVenta;
import com.idat.laterraza.entity.DetalleVenta;
import com.idat.laterraza.entity.Usuario;
import com.idat.laterraza.service.ICabeceraVentaService;
import com.idat.laterraza.service.IDetalleVentaService;
import com.idat.laterraza.service.IUsuarioService;
import com.idat.laterraza.serviceR.CabeVServiceIm;
import com.idat.laterraza.serviceR.UsuarioServiceIm;


@CrossOrigin(origins= {"http://localhost/4200"})
@RestController
@RequestMapping("/correo")
public class EnviarCorreo {
	
	@Autowired
	private JavaMailSender mail;
	@Autowired
	private IDetalleVentaService detalleService;
	
	@Autowired
	private ICabeceraVentaService cabeceraService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private UsuarioServiceIm userService;
	

	
	@GetMapping("/enviar/{idUsuario}/{correo}")
	public ResponseEntity<?> enviar_correo(@PathVariable Long idUsuario,@PathVariable String correo){
		SimpleMailMessage email = new SimpleMailMessage();

		
		List<DetalleVenta> listaDetalle=detalleService.findAll();
		ArrayList<DetalleVenta> ListaCarrito= new ArrayList<DetalleVenta>();
		
		
		for(int i=0;i<listaDetalle.size();i++) {
			
			
			//cabeceraService.findById(listaDetalle.get(i).getCabecera().getIdCabecera());
			if ((cabeceraService.findById(listaDetalle.get(i).getCabecera().getIdCabecera())).getTipoCabecera().equals("Carrito")&&
			(usuarioService.findById(cabeceraService.findById(listaDetalle.get(i).getCabecera().getIdCabecera()).getUsuario().getIdUsuario()).getIdUsuario().equals(idUsuario))) {
				ListaCarrito.add(listaDetalle.get(i));
			}
		}
		
		email.setTo(correo);
		email.setFrom("ln72885231@idat.edu.pe");
		email.setSubject("FACTURACIÓN LA TERRAZA");
		String Mensaje="Gracias por su compra\nEstos son los detalles de su compra:\n";
		Double total=0.0;
		
		for(int i=0;i<ListaCarrito.size();i++) {
			Mensaje = Mensaje + "\n"+ListaCarrito.get(i).getProducto().getProducto()  + " | CANTIDAD: "+ ListaCarrito.get(i).getCantidad() + " | PRECIO: "+ListaCarrito.get(i).getPrecio() + " | SUBTOTAL: "+ ListaCarrito.get(i).getCantidad() * ListaCarrito.get(i).getPrecio();
			total = total + ListaCarrito.get(i).getCantidad() * ListaCarrito.get(i).getPrecio();
		}
		Mensaje=Mensaje+"\nTOTAL PAGADO: "+(total+(total*0.18)) +"\nGracias por la preferencia!\nSu pedido estara Listo Pronto";
		email.setText(Mensaje);
		
		mail.send(email);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@GetMapping("/{correo}")
	public String recuperar(@PathVariable String correo) {
		SimpleMailMessage email = new SimpleMailMessage();
		Usuario usuarioActual=userService.recuperacino(correo);
		
		if(usuarioActual==null) {
			return "1";
		}else {
			int min = 100000;
	        int max = 999999;
	        Random random = new Random();
	        int randomNumber = random.nextInt(max - min + 1) + min;
			
			email.setTo(correo);
			email.setFrom("ln72885231@idat.edu.pe");
			email.setSubject("RECUPERACION DE CONTRASEÑA");
			email.setText("Se Solicito la recuperación de contraseña\nDigite este codigo:"+randomNumber+"");
			mail.send(email);
			return randomNumber+"" ;
		}
		
		
		
		
	}
	

}
