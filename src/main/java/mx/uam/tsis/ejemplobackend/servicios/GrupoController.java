package mx.uam.tsis.ejemplobackend.servicios;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.GrupoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;


@RestController
@Slf4j
public class GrupoController {

	@Autowired
	private GrupoService grupoService;
	
	@ApiOperation(
			value="crear Grupo",
			notes="Permite crear un nuevo Grupo"
			)
	@PostMapping(path = "/grupos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Grupo nuevoGrupo) {
		
		log.info("Recibí llamada a create con "+nuevoGrupo);
		Grupo grupo=grupoService.create(nuevoGrupo);
		if(grupo!=null){
			return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se pudo crear el alumno");
		}		
	}
	@ApiOperation(
			value="Recupera todos los Grupos",
			notes="Permite recuperar todos los grupos registrados"
			)
	@GetMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		Iterable<Grupo> result=grupoService.retrieveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}
}
