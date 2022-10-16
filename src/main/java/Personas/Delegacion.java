package Personas;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
@Entity
@Table(name = "delegacion")
public class Delegacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_delegacion;

  @OneToOne(cascade= CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_personaDelegada",referencedColumnName = "id_persona")
  private Persona personaDelegada;

  /*
  @OneToOne(cascade=CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_personaTitular",referencedColumnName = "id_persona")
  private Persona personaTitular;*/

  @Column
  private Boolean aceptada;

  public Delegacion(Persona personaDelegada, Boolean aceptada) {
    this.personaDelegada = personaDelegada;
    //this.personaTitular = personaTitular;
    this.aceptada = aceptada;
  }


  public Boolean validar(){
    this.aceptada = Boolean.TRUE;
    return true;
  }
}
