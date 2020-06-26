package mx.uam.tsis.ejemplobackend.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

@Service
public class AlumnoService{
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	/**
	 * @param nuevoAlumno
	 * @return regresa el alumno si fue creado o null si no fue creado
	 * **/
	public Alumno create(Alumno nuevoAlumno){
		//no se pueden registrar dos alumnos con la misma matricula
		Alumno alumno=alumnoRepository.findByMatricula(nuevoAlumno.getMatricula());
		if(alumno==null){
			 return alumnoRepository.save(nuevoAlumno);
		}else{
			return null;
		}
	}
	/**
	 * obtiene todos los alumnos
	 * @return List<Alumno>
	 * **/
	public List<Alumno> retriveAll(){
		return alumnoRepository.find();
	}
	/**
	 * obtiene un alumno segun su matricula
	 * @param Integer matricula
	 * @return Alumno
	 * **/
	public Alumno retriveById(Integer matricula){
		Alumno alumno=alumnoRepository.findByMatricula(matricula);
		if(alumno!=null){
			return alumno;
		}else{
			return null;
		}
	}
	/**
	 * @param Integer matricula
	 * @param Alumno nuevoAlumno
	 * @return boolean
	 * **/
	public boolean update(Integer matricula,Alumno nuevoAlumno){
		return alumnoRepository.Update(matricula, nuevoAlumno);
	}
	/**
	 * @param Integer matricula
	 * @return boolean
	 * **/
	public boolean delete(Integer matricula){
		return alumnoRepository.delete(matricula);
	}
}