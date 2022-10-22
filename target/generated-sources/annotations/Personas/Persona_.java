package Personas;

import Localidad.Localidad;
import Usuarios.Usuario;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Persona.class)
public abstract class Persona_ {

	public static volatile SingularAttribute<Persona, Integer> id_persona;
	public static volatile SingularAttribute<Persona, String> foto;
	public static volatile SingularAttribute<Persona, LocalDate> fechaNacimiento;
	public static volatile SingularAttribute<Persona, Delegacion> delegacion;
	public static volatile SingularAttribute<Persona, String> apellido;
	public static volatile SingularAttribute<Persona, Localidad> localidad;
	public static volatile SingularAttribute<Persona, Usuario> usuario;
	public static volatile SingularAttribute<Persona, String> nombre;
	public static volatile SingularAttribute<Persona, String> dni;

	public static final String ID_PERSONA = "id_persona";
	public static final String FOTO = "foto";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String DELEGACION = "delegacion";
	public static final String APELLIDO = "apellido";
	public static final String LOCALIDAD = "localidad";
	public static final String USUARIO = "usuario";
	public static final String NOMBRE = "nombre";
	public static final String DNI = "dni";

}

