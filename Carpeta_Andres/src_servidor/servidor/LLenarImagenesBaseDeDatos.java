package servidor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.hibernate.Session;

public class LLenarImagenesBaseDeDatos {
	public LLenarImagenesBaseDeDatos() throws Exception{
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		
		Publicidad publicidad1 = new Publicidad();
		File file = new File("/root/workspace/ProyectoEntrega/Publicidad/publi1.png");
		FileInputStream fis = new FileInputStream(file);
	    BufferedInputStream bis = new BufferedInputStream(fis);
	    byte[] buf = new byte[(int) file.length()];
	    bis.read(buf);
	    publicidad1.setImagen(buf);
	    publicidad1.setNombreArchivo("/root/workspace/ProyectoEntrega/Publicidad/publi1.png");
	    
	    File file1 = new File("/root/workspace/ProyectoEntrega/Publicidad/publi2.png");
		FileInputStream fis1 = new FileInputStream(file1);
	    BufferedInputStream bis1 = new BufferedInputStream(fis1);
	    byte[] buf1 = new byte[(int) file1.length()];
	    bis1.read(buf1);
	    Publicidad publicidad2 = new Publicidad();
	    publicidad2.setImagen(buf1);
	    publicidad2.setNombreArchivo("/root/workspace/ProyectoEntrega/Publicidad/publi2.png");
	    
	    File file2 = new File("/root/workspace/ProyectoEntrega/Publicidad/publi3.jpg");
		FileInputStream fis2 = new FileInputStream(file2);
	    BufferedInputStream bis2 = new BufferedInputStream(fis2);
	    byte[] buf2 = new byte[(int) file2.length()];
	    bis2.read(buf2);
	    Publicidad publicidad3 = new Publicidad();
	    publicidad3.setImagen(buf2);
	    publicidad3.setNombreArchivo("/root/workspace/ProyectoEntrega/Publicidad/publi3.jpg");
	    
	    File file3 = new File("/root/workspace/ProyectoEntrega/Publicidad/publi4.jpg");
		FileInputStream fis3 = new FileInputStream(file3);
	    BufferedInputStream bis3 = new BufferedInputStream(fis3);
	    byte[] buf3 = new byte[(int) file3.length()];
	    bis3.read(buf3);
	    Publicidad publicidad4 = new Publicidad();
	    publicidad4.setImagen(buf3);
	    publicidad4.setNombreArchivo("/root/workspace/ProyectoEntrega/Publicidad/publi4.jpg");
	    
	    File file4 = new File("/root/workspace/ProyectoEntrega/Publicidad/publi5.png");
		FileInputStream fis4 = new FileInputStream(file4);
	    BufferedInputStream bis4 = new BufferedInputStream(fis4);
	    byte[] buf4 = new byte[(int) file4.length()];
	    bis4.read(buf4);
	    Publicidad publicidad5 = new Publicidad();
	    publicidad5.setImagen(buf4);
	    publicidad5.setNombreArchivo("/root/workspace/ProyectoEntrega/Publicidad/publi5.png");

	    
	    
	    
	    
	    
	    session.save(publicidad1);
	    session.save(publicidad2);
	    session.save(publicidad3);
	    session.save(publicidad4);
	    session.save(publicidad5);
	    session.getTransaction().commit();
	    session.close();
	 
	}
	public static void main(String[] args) {
		try {
			LLenarImagenesBaseDeDatos main = new LLenarImagenesBaseDeDatos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
