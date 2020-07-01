package mx.uam.tsis.ejemplobackend.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

@Service
@Slf4j
public class AlumnoService{
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	/**
	 * @param nuevoAlumno
	 * @return regresa el alumno si fue creado o null si no fue creado
	 * **/
	public Alumno create(Alumno nuevoAlumno){
		//no se pueden registrar dos alumnos con la misma matricula
		Optional <Alumno> alumnoOpt=alumnoRepository.findById(nuevoAlumno.getMatricula());
		if(!alumnoOpt.isPresent()){
			 return alumnoRepository.save(nuevoAlumno);
		}else{
			return null;
		}
	}
	/**
	 * obtiene todos los alumnos
	 * @return List<Alumno>
	 * **/
	public Iterable<Alumno> retriveAll(){
		return alumnoRepository.findAll();
	}
	/**
	 * obtiene un alumno segun su matricula
	 * @param Integer matricula
	 * @return Alumno
	 * **/
	public Optional<Alumno> retriveById(Integer matricula){
		Optional <Alumno> alumno=alumnoRepository.findById(matricula);
		if(alumno.isPresent()){
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
	public boolean update(Alumno nuevoAlumno){
		Alumno alumno=alumnoRepository.save( nuevoAlumno);
		if(alumno!=null){
			return true;
		}else {
			return false;
		}
	}
	/**
	 * @param Integer matricula
	 * @return boolean
	 * **/
	public boolean delete(Integer matricula){
		try {
			alumnoRepository.deleteById(matricula);
			return true;
		}catch(IllegalArgumentException ex){
			log.info("no se pudo borrar el alumno"+ex.getMessage());
			return false;
		} 
	}
}