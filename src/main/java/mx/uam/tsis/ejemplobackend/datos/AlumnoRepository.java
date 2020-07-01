package mx.uam.tsis.ejemplobackend.datos;


import org.springframework.data.repository.CrudRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
/**
 * Clase que se encarga de almacenar y recuperar alumnos
 * 
 * @author Hernan Dominguez
 * **/

public  interface AlumnoRepository extends CrudRepository<Alumno,Integer>{
}
