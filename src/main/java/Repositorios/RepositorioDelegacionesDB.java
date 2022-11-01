package Repositorios;

import Personas.Delegacion;

public class RepositorioDelegacionesDB extends Repositorio<Delegacion>{

    public RepositorioDelegacionesDB() {
        super(new DBHibernate<Delegacion>(Delegacion.class));
    }
}
