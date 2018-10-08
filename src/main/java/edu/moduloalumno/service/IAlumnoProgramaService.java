package edu.moduloalumno.service;

import java.util.List;

import edu.moduloalumno.entity.AlumnoPrograma;

public interface IAlumnoProgramaService {
	
	List<AlumnoPrograma> getAllAlumnoProgramas();
	
	List<AlumnoPrograma> getAlumnoProgramasIdByNombresApellidos( String nombresApellidos);
	
	List<AlumnoPrograma> getAlumnoProgramasIdByNombresApellidosRestringido( String nombresApellidos);

	AlumnoPrograma getAlumnoProgramaById(String codAlumno);
	
	List<AlumnoPrograma> getAlumnoProgramaByDni(String dni);

	void addAlumnoPrograma(AlumnoPrograma alumnoPrograma);

	void updateAlumnoPrograma(AlumnoPrograma alumnoPrograma);

	void deleteAlumnoPrograma(String codAlumno);
}