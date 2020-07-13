package mx.uam.tsis.ejemplobackend.servicios;

import java.util.Optional;

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

import io.swagger.annotations.ApiOperation;
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
	
	@ApiOperation(
			value="crear alumno",
			notes="Permite crear un nuevo alumno,la matricula debe ser unica"
			)
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
	@ApiOperation(
			value="Recupera todos los alumnos",
			notes="Permite recuperar todos los alumnos registrados"
			)
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		Iterable<Alumno> result=alumnoService.retriveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}
	@ApiOperation(
			value="recupera un alumno",
			notes="Permite recuperar un alumno mediante la matricula"
			)
	@GetMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("matricula") Integer matricula) {
		log.info("Buscando al alumno con matricula "+matricula);
		
		Optional <Alumno> alumno=alumnoService.retriveById(matricula);
		
		if(alumno.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	@ApiOperation(
			value="Actualiza un alumno",
			notes="Permite actualizar un alumno pasandole los nuevos valores,la matricula no cambia"
			)
	@PutMapping(path="/alumnos/update/{matricula}", consumes=MediaType.APPLICATION_JSON_VALUE)
	
	public  ResponseEntity <?> replaceAlumn(@RequestBody @Valid Alumno nuevoAlumno ,@PathVariable("matricula") Integer matricula) {
		log.info("Buscando alumno a actualizar");
		boolean result=alumnoService.update(nuevoAlumno);
		
		if(result){
			Optional <Alumno> alumno=alumnoService.retriveById(matricula);
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se pudo actualizar el alumno");
		}
		
	}
	@ApiOperation(
			value="Eliminar alumno",
			notes="Permite eliminar un alumno mediante su matricula"
			)
	@DeleteMapping(path="/alumnos/delete/{matricula}")
	public ResponseEntity<?> delete(@PathVariable("matricula") Integer matricula) {
		boolean result=alumnoService.delete(matricula);
		if(result){
			return ResponseEntity.status(HttpStatus.OK).body("alumno eliminado");
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el alumno no existe");
		}
	}
 
}
