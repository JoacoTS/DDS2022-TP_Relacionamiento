package Localidad;

import Personas.Persona;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class Localidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_localidad;

  private String nombre;

  private Ciudad ciudad;

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
