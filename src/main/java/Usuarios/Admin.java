package Usuarios;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name="id_usuario")
public class Admin extends Usuario {
  //////////////////////////////////  VARIABLES

  //////////////////////////////////  CONSTRUCTORES
  public Admin(String username, String contra) {
    super(username, contra);
  }

  //////////////////////////////////  GETTERS

  //////////////////////////////////  SETTERS


  //////////////////////////////////  INTERFACE


  //TODO faltan los reportes y la autorizacion de Delegacion

}
