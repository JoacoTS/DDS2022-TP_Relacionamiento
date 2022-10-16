package Localidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ciudad")
public class Ciudad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_ciudad;

  @Column
  private String nombre;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_provincia",referencedColumnName = "id_provincia")
  private Provincia provincia;

  @OneToMany(cascade=CascadeType.ALL , mappedBy = "ciudad")
  private List<Localidad> localidades = new ArrayList();

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
