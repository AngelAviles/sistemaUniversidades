package objetosNegocio;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import objetosNegocio.Calificacion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-31T22:26:09")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile ListAttribute<Alumno, Calificacion> calificaciones;
    public static volatile SingularAttribute<Alumno, String> matricula;
    public static volatile SingularAttribute<Alumno, Integer> id;
    public static volatile SingularAttribute<Alumno, String> nombre;
    public static volatile SingularAttribute<Alumno, String> curp;

}