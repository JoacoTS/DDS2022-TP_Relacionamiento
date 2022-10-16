package Localidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provincia")
public class Provincia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_provincia;

  @Column
  private String nombre;

  @OneToMany(cascade=CascadeType.ALL , mappedBy = "provincia")
  private List<Ciudad> ciudades = new ArrayList();

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
