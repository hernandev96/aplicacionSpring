package mx.uam.tsis.ejemplobackend.datos;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
/**
 * Clase que se encarga de almacenar y recuperar alumnos
 * 
 * @author Hernan Dominguez
 * **/
@Component
public class AlumnoRepository {
	// La "base de datos"
	private Map <Integer, Alumno> alumnoRepository = new HashMap <>();
	/*
	 * Guarda los alumnos en la base de datos
	 * 
	 * @param Alumno nuevoAlumno
	 * **/
	public Alumno save(Alumno nuevoAlumno){
		alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);
		return nuevoAlumno;
		
	}
	/**
	 * Devuelve un alumno
	 * lo busca por la matricula
	 * @param Integer matricula
	 * @return Alumno 
	 * */
	public Alumno findByMatricula(Integer matricula){
		return alumnoRepository.get(matricula);
		
	}
	/**
	 * Devuelve la lista de todos los alumnos
	 * @return List<Alumno> 
	 * */
	public List<Alumno> find(){
		return new ArrayList<>(alumnoRepository.values());
	}
	/**
	 * Actualiza un alumno 
	 * buscando por la matricula
	 * @param Integer Matricula
	 * @param Alumno alumno
	 * @return boolean
	 * */
	public boolean Update(Integer matricula,Alumno nuevoAlumno){
		Alumno alumno=alumnoRepository.get(matricula);
		if(alumno!=null){
			alumno.setMatricula(nuevoAlumno.getMatricula());
			alumno.setCarrera(nuevoAlumno.getCarrera());
			alumno.setNombre(nuevoAlumno.getNombre());
			alumnoRepository.put(alumno.getMatricula(),alumno);
			return true;
		}
		else{
			return false;
		}
	} 
	
	/**
	 * Elimina un alumno
	 * buscado con una matricula
	 * @param Integer matricula
	 * @return boolean
	 * **/
	public boolean delete(Integer matricula){
		Alumno alumno=alumnoRepository.get(matricula);
		if(alumno!=null){
			alumnoRepository.remove(matricula);
			return true;
		}else{
			return false;
		}
	}
}
