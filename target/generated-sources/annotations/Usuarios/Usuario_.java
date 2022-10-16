package Usuarios;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, Integer> id_usuario;
	public static volatile SingularAttribute<Usuario, String> contraHasheada;
	public static volatile SingularAttribute<Usuario, String> username;

	public static final String ID_USUARIO = "id_usuario";
	public static final String CONTRA_HASHEADA = "contraHasheada";
	public static final String USERNAME = "username";

}

