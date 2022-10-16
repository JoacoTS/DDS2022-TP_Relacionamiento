package Personas;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Delegacion.class)
public abstract class Delegacion_ {

	public static volatile SingularAttribute<Delegacion, Integer> id_delegacion;
	public static volatile SingularAttribute<Delegacion, Boolean> aceptada;
	public static volatile SingularAttribute<Delegacion, Persona> personaDelegada;

	public static final String ID_DELEGACION = "id_delegacion";
	public static final String ACEPTADA = "aceptada";
	public static final String PERSONA_DELEGADA = "personaDelegada";

}

