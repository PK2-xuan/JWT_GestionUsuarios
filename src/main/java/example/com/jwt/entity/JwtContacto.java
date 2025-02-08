package example.com.jwt.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity

@Table(name = "contacto")
public class JwtContacto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idcontacto")

private Integer	id;
private String nombre;
 
@Column (name = "fechanac")
private LocalDate fechaNacimiento;

private String celular;
private String email;


public JwtContacto() {
	super();
}


public JwtContacto(Integer id, String nombre, LocalDate fechaNacimiento, String celular, String email) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.fechaNacimiento = fechaNacimiento;
	this.celular = celular;
	this.email = email;
}


public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}


public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public LocalDate getFechaNacimiento() {
	return fechaNacimiento;
}


public void setFechaNacimiento(LocalDate fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
}


public String getCelular() {
	return celular;
}


public void setCelular(String celular) {
	this.celular = celular;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}



}
