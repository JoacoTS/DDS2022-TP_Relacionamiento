package Localidad;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ciudad.class)
public abstract class Ciudad_ {

	public static volatile SingularAttribute<Ciudad, Integer> id_ciudad;
	public static volatile SingularAttribute<Ciudad, Provincia> provincia;
	public static volatile SingularAttribute<Ciudad, String> nombre;
	public static volatile ListAttribute<Ciudad, Localidad> localidades;

	public static final String ID_CIUDAD = "id_ciudad";
	public static final String PROVINCIA = "provincia";
	public static final String NOMBRE = "nombre";
	public static final String LOCALIDADES = "localidades";

}

