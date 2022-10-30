package Personas;

import Localidad.Localidad;
import Usuarios.Usuario;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persona")
public class Persona {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_persona;

  @Column
  private LocalDate fechaNacimiento;

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad")
  private Localidad localidad;

  @Column
  private String foto;

  @Column
  private String dni;

  @Column
  private String nombre;

  @Column
  private String apellido;

  @OneToOne(cascade=CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_delegacion",referencedColumnName = "id_delegacion")
  private Delegacion delegacion;

  @OneToOne(cascade=CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name="id_usuario",referencedColumnName = "id_usuario")
  private Usuario usuario;


  public Persona() {
  }

  public Persona(LocalDate fechaNacimiento, Localidad localidad, String foto, String dni, String nombre, String apellido) {
    this.fechaNacimiento = fechaNacimiento;
    this.localidad = localidad;
    this.foto = foto;
    this.dni = dni;
    this.nombre = nombre;
    this.apellido = apellido;
  }

  public Persona(LocalDate fechaNacimiento, String foto, String dni, String nombre, String apellido) {
    this.fechaNacimiento = fechaNacimiento;
    this.foto = foto;
    this.dni = dni;
    this.nombre = nombre;
    this.apellido = apellido;
  }



  //GETTERS


  public int getId_persona() {
    return id_persona;
  }

  public Delegacion getDelegacion() {
    return delegacion;
  }

  public void setDelegacion(Delegacion delegacion) {
    this.delegacion = delegacion;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public Localidad getLocalidad() {
    return localidad;
  }

  public String getFoto() {
    return foto;
  }

  public String getDni() {
    return dni;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  // SETTERS
  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public void setLocalidad(Localidad localidad) {
    this.localidad = localidad;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  // INTERFACE

  //TODO para el JSON
  public Boolean registrar(){
    return true;
  }
}
