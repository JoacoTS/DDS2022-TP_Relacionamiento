package Localidad;

import Personas.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Localidad.class)
public abstract class Localidad_ {

	public static volatile SingularAttribute<Localidad, Integer> id_localidad;
	public static volatile SingularAttribute<Localidad, Ciudad> ciudad;
	public static volatile SingularAttribute<Localidad, String> nombre;
	public static volatile ListAttribute<Localidad, Persona> personas;

	public static final String ID_LOCALIDAD = "id_localidad";
	public static final String CIUDAD = "ciudad";
	public static final String NOMBRE = "nombre";
	public static final String PERSONAS = "personas";

}

