package servidor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;



@Entity
@Table(name = "t_publicidad")
@Proxy(lazy = false)
public class Publicidad {
	private int id;
	private byte[] imagen;
	private String nombreArchivo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	
		@Lob
	  @Column(length = 70000)
	  public byte[] getImagen() {
	    return imagen;
	  }

	  public void setImagen(byte[] imagen) {
	    this.imagen = imagen;
	  }
	  
	 
	  
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}

}
