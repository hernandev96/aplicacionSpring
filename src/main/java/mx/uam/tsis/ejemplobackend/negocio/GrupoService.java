package mx.uam.tsis.ejemplobackend.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@Service
@Slf4j
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public Grupo create(Grupo nuevoGrupo){
		return grupoRepository.save(nuevoGrupo);
	}
	
	public Iterable <Grupo> retrieveAll(){
		return grupoRepository.findAll();
	} 
	
	
}
