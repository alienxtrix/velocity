package com.example.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.example.model.Persona;

public class VelocityMain {

	public static void main(String[] args) {
		// Inicialización del motor de velocity
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		engine.init();

		// Creación y llenado de lista de personas
		List<Persona> personas = new ArrayList<Persona>();
		for (int i = 0; i < 10; i++) {
			Persona persona = new Persona();
			persona.setNombre("Persona " + i);
			persona.setEdad(20+i);
			personas.add(persona);
		}

		// Inicialización del contexto de velocity
		VelocityContext context = new VelocityContext();
		context.put("title", "pagina de prueba");
		context.put("personas", personas);

		// Recuperar plantilla
		Template template = engine.getTemplate("templates/prueba.vm");

		// Obtener resultado
//		StringWriter stringWriter = new StringWriter();
//		template.merge(context, stringWriter);
//		
//		System.out.println(stringWriter.toString());
		try {
			FileWriter fileWriter = new FileWriter(new File("src/main/resources/outputs/result.html"));
			template.merge(context, fileWriter);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
