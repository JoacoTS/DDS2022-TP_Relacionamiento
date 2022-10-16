package Localidad;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Provincia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_provincia;

  private String nombre;

  public Provincia(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
