package RepoPersonas;

import Personas.Delegacion;
import Personas.Persona;
import Repositorios.RepositorioDelegacionesDB;
import Repositorios.RepositorioPersonasDB;
import Usuarios.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

public class RepositorioPersonasTest2 {
    public static void main(String[] args) {

        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Optional<Persona> personaDelegada;

        personaDelegada = repositorioPersonasDB.mostrarPersonaDelegacion("tomas");


        Optional<Usuario> usuarioDelegado = personaDelegada.map(Persona::getUsuario);

        Optional<Delegacion> delegacion = repositorioPersonasDB.mostrarDelegacion("tomas");

        usuarioDelegado.ifPresent(value -> System.out.println("el valor de usuario delegado " + value));

        delegacion.ifPresent(value -> System.out.println("el valor de delegacion " + value));

        delegacion.ifPresent(value -> System.out.println("el valor de delegacion aceptada " + value.isAceptada()));



    }
}
