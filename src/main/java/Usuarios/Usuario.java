package Usuarios;

import Usuarios.Excepciones.ContraseniaEsInvalidaException;
import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Entity
@Table(name = "usuario")
public class Usuario {
    //////////////////////////////////  VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_usuario;
    @Column
    private String username;
    @Column
    private String contraHasheada;



    @Transient
    private ArrayList<CriterioValidacion> validadoresContrasenia;

    @Transient
    private final String salt = "+@351";

    //////////////////////////////////  CONSTRUCTORES

    public Usuario(String username, String contra) {
      if(!isContraseniaValida(contra)){
        throw new ContraseniaEsInvalidaException("no pasa por alguna de las validaciones de seguridad");
      }
      this.contraHasheada = generateHash(contra);
      this.username = username;
    }

  public Usuario() {
  }

  //////////////////////////////////  GETTERS

    public String getUsername() {
      return this.username;
    }


    public void setUsername(String username) {
      this.username = username;
    }

    public void setContraHasheada(String contraHasheada) {
      this.contraHasheada = contraHasheada;
    }

    //////////////////////////////////  SETTERS




    //////////////////////////////////  INTERFACE
    public boolean isColision(String contraAProbar){
      if(this.contraHasheada == null){
        //todo buscar contrasenia em DB si el usuario no tiene la contra

      }
      String contraHasheada = generateHash(contraAProbar);
      return this.contraHasheada.equals(contraHasheada);
    }

    private boolean isContraseniaValida(String contra){

      this.validadoresContrasenia = new ArrayList<>();

      this.validadoresContrasenia.add(new Peores10KContra());
      this.validadoresContrasenia.add(new CriterioLongitud(8,80));

      for (CriterioValidacion criterioValidacion : this.validadoresContrasenia) {
        if (!criterioValidacion.validarContrasenia(contra))
          return false;
      }
      return true;
    }


    private String generateHash(String contrasenia) {
      MessageDigest digest;
      try {
        digest = MessageDigest.getInstance("SHA-256");
      }catch (NoSuchAlgorithmException e) {
        throw new ContraseniaEsInvalidaException("La contrasenia no pudo ser hasheada");
      }
      digest.reset();
      digest.update(this.salt.getBytes(StandardCharsets.UTF_8));
      byte[] hash = digest.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();

      for (byte b : hash) {
        sb.append(String.format("%02x", b));
      }

      return sb.toString();
    }


    public static String toString(Usuario usuario) {
      if(usuario == null) return "el usuario es nulo";

      return "Usuario{" +
          "id_usuario=" + usuario.id_usuario + '\'' +'\n'+
          ", username='" + usuario.username + '\'' +'\n'+
          ", contraHasheada='" + usuario.contraHasheada + '\'' +'\n'+
          '}';
    }

    public void logout (){
      try{
        this.finalize();
      }
      catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }

}
