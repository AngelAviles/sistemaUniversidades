
import dao.AlumnoJpaController;
import dao.CalificacionJpaController;
import dao.MateriaJpaController;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import objetosNegocio.Alumno;
import objetosNegocio.Calificacion;
import objetosNegocio.Materia;
import objetosNegocio.MateriaPlandeestudio;
import objetosNegocio.MateriasSerializacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angel
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Alumno alumno1 = new Alumno(5, "0001", "hola", "fsfsdfsf", new ArrayList<>());
        Alumno alumno2 = new Alumno(6, "0002", "hola", "dvvvdvdvdv", new ArrayList<>());
        Alumno alumno3 = new Alumno(3, "0003", "tres", "sdfsdfdsggvved", new ArrayList<>());
        Alumno alumno4 = new Alumno(3, "0004", "cuatro", "sdfsdfsdfsdfd", new ArrayList<>());
        Alumno alumno5 = new Alumno(3, "0005", "quinto", "fsdfsdfsdfjsdfsdif", new ArrayList<>());
        

        Materia materia1 = new Materia(1, "ffsdf", "algebrea", new ArrayList<Calificacion>(), new ArrayList<MateriaPlandeestudio>(), new ArrayList<MateriasSerializacion>(), new ArrayList<>());
        Materia materia2 = new Materia(2, "sdsd", "programacion1", new ArrayList<Calificacion>(), new ArrayList<MateriaPlandeestudio>(), new ArrayList<MateriasSerializacion>(), new ArrayList<>());

        Calificacion cal1 = new Calificacion(1, materia1, alumno1, 10);
        Calificacion cal2 = new Calificacion(2, materia1, alumno2, 8);
        Calificacion cal3 = new Calificacion(3, materia2, alumno1, 8);
        Calificacion cal4 = new Calificacion(4, materia2, alumno2, 7);
        Calificacion cal5 = new Calificacion(5, materia1, alumno3, 6);
        Calificacion cal6 = new Calificacion(6, materia2, alumno3, 6);
        Calificacion cal7 = new Calificacion(5, materia1, alumno4, 10);
        Calificacion cal8 = new Calificacion(6, materia2, alumno4, 10);
        Calificacion cal9 = new Calificacion(5, materia1, alumno5, 9);
        Calificacion cal10 = new Calificacion(6, materia2, alumno5, 8);
        

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaUniversidadesPU");
        
        AlumnoJpaController jpaAlumno = new AlumnoJpaController(factory);
        MateriaJpaController jpaMateria = new MateriaJpaController(factory);
        CalificacionJpaController jpaCalificacion = new CalificacionJpaController(factory);
        
//        jpaAlumno.create(alumno1);
//        jpaAlumno.create(alumno2);
        jpaAlumno.create(alumno5);
        
//        jpaMateria.create(materia1);
//        jpaMateria.create(materia2);
        
//        jpaCalificacion.create(cal1);
//        jpaCalificacion.create(cal2);
//        jpaCalificacion.create(cal3);
//        jpaCalificacion.create(cal4);
        jpaCalificacion.create(cal9);
        jpaCalificacion.create(cal10);

//        InputStream inputStream = null;
//        JasperPrint jasperPrint = null;
//        
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemauniversidades?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "sesamo");
//        } catch (SQLException ex) {
//            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        try {
//            inputStream = new FileInputStream("src/java/reportes/ReporteAlumno.jrxml");
//        } catch(FileNotFoundException ex) {
//            JOptionPane.showMessageDialog(null, "Error al leer el fichero de carga jasper report: " + ex.getMessage());
//        }
//        
//        Map parametro = new HashMap();
//            parametro.put("matricula", "0001");
//        
//        try {
//            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//            jasperPrint = JasperFillManager.fillReport(jasperReport, parametro, conn);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/java/reportes/reporte.pdf"); 
//        } catch (JRException e) {
//            JOptionPane.showMessageDialog(null, "Error al leer el fichero de carga jasper report: " + e.getMessage());
//        }
    }
    
}
