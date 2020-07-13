package mx.uam.tsis.ejemplobackend.negocio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Grupo {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank
	private String clave;
	
	@Builder.Default
	@OneToMany
	private List<Alumno> alumnos=new ArrayList<> ();
	
}
