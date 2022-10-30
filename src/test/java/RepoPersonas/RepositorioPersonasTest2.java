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

public class RepositorioPersonasTest2 {
    public static void main(String[] args) {
        RepositorioPersonasDB repositorioPersonasDB = new RepositorioPersonasDB();
        Persona personaDelegada = repositorioPersonasDB.mostrarPersonaDelegacion("usuario2");

        Usuario usuarioDelegado = personaDelegada.getUsuario();
        Delegacion delegacion = repositorioPersonasDB.mostrarDelegacion("usuario2");

        Assertions.assertEquals(personaDelegada.getDelegacion(), delegacion);
    }
}
