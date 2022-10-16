package Localidad;

import Personas.Persona;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "localidad")
public class Localidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_localidad;

  @Column
  private String nombre;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_ciudad",referencedColumnName = "id_ciudad")
  private Ciudad ciudad;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "localidad")
  private List<Persona> personas = new ArrayList();

  public Localidad(String nombre, Ciudad ciudad) {
    this.nombre = nombre;
    this.ciudad = ciudad;
  }

  public Localidad(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public Ciudad getCiudad() {
    return ciudad;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setCiudad(Ciudad ciudad) {
    this.ciudad = ciudad;
  }
}
