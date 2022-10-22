package Localidad;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Provincia.class)
public abstract class Provincia_ {

	public static volatile SingularAttribute<Provincia, Integer> id_provincia;
	public static volatile SingularAttribute<Provincia, String> nombre;
	public static volatile ListAttribute<Provincia, Ciudad> ciudades;

	public static final String ID_PROVINCIA = "id_provincia";
	public static final String NOMBRE = "nombre";
	public static final String CIUDADES = "ciudades";

}

