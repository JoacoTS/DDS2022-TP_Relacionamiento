package Localidad;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Ciudad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_ciudad;

  private String nombre;

  private Provincia provincia;

  public Ciudad(String nombre, Provincia provincia) {
    this.nombre = nombre;
    this.provincia = provincia;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Provincia getProvincia() {
    return provincia;
  }

  public void setProvincia(Provincia provincia) {
    this.provincia = provincia;
  }
}
