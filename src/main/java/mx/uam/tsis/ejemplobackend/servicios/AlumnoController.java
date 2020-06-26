package mx.uam.tsis.ejemplobackend.servicios;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.AlumnoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

/**
 * Controlador para el API rest
 * 
 * @author humbertocervantes
 *
 */
@RestController
@Slf4j
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	
	@PostMapping(path = "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Alumno nuevoAlumno) {
		
		log.info("Recibí llamada a create con "+nuevoAlumno);
		Alumno alumno=alumnoService.create(nuevoAlumno);
		if(alumno!=null){
			return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se pudo crear el alumno");
		}		
	}
	
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		List<Alumno> result=alumnoService.retriveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}

	@GetMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("matricula") Integer matricula) {
		log.info("Buscando al alumno con matricula "+matricula);
		
		Alumno alumno=alumnoService.retriveById(matricula);
		
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	@PutMapping(path="/alumnos/update/{matricula}", consumes=MediaType.APPLICATION_JSON_VALUE)
	
	public  ResponseEntity <?> replaceAlumn(@RequestBody @Valid Alumno nuevoAlumno ,@PathVariable("matricula") Integer matricula) {
		log.info("Buscando alumno a actualizar");
		boolean result=alumnoService.update(matricula, nuevoAlumno);
		if(result){
			return ResponseEntity.status(HttpStatus.OK).body("alumno actualizado");
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se pudo actualizar el alumno");
		}
		
	}
	
	@DeleteMapping(path="/alumnos/delete/{matricula}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("matricula") Integer matricula) {
		boolean result=alumnoService.delete(matricula);
		if(result){
			return ResponseEntity.status(HttpStatus.OK).body("alumno eliminado");
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el alumno no existe");
		}
	}
 
}
