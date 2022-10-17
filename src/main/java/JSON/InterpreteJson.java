package JSON;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Localidad.*;
import Personas.Persona;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class InterpreteJson {

  public static void guardarPersonasJson(List<Persona> personas){

    JSONArray listaPersonasJson = new JSONArray();


    for (Persona persona: personas) {

      JSONObject personaJson = new JSONObject();
      personaJson.put("nombre",persona.getNombre());
      personaJson.put("apellido",persona.getApellido());
      personaJson.put("fechaNacimiento",persona.getFechaNacimiento().toString());
      personaJson.put("dni",persona.getDni());

      if (persona.getLocalidad() != null){
        JSONObject localidadJson = new JSONObject();
        localidadJson.put("nombre",persona.getLocalidad().getNombre());

        JSONObject ciudadJson = new JSONObject();
        ciudadJson.put("nombre",persona.getLocalidad().getCiudad().getNombre());

        JSONObject provinciaJson = new JSONObject();
        provinciaJson.put("nombre",persona.getLocalidad().getCiudad().getProvincia().getNombre());

        ciudadJson.put("provincia",provinciaJson);

        localidadJson.put("ciudad",ciudadJson);

        personaJson.put("Localidad",localidadJson);
      }

      JSONObject datosPersona = new JSONObject();
      datosPersona.put("persona",personaJson);

      listaPersonasJson.add(datosPersona);
    }

    try(FileWriter file = new FileWriter("personas.json")) {
      file.write(listaPersonasJson.toJSONString());
      file.flush();
    }
    catch (IOException e){
      e.printStackTrace();
    }

  }

  public static List<Persona> leerPersonasJson(String ruta){

    JSONParser jsonParser = new JSONParser();
    List<Persona> personas = new ArrayList<Persona>();

    try {
      FileReader reader = new FileReader(ruta);
      Object obj = jsonParser.parse(reader);

      JSONArray personasJson = (JSONArray) obj;

      for(Object personaJson : personasJson){
        listarPersonas(personas,(JSONObject) personaJson);
      }

    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
    catch (IOException e){
      e.printStackTrace();
    }
    catch (ParseException e){
      e.printStackTrace();
    }


    return personas;
  }

  private static void listarPersonas(List<Persona> personas, JSONObject personaJson) {
      JSONObject persona = (JSONObject) personaJson.get("persona");

      if(persona != null){

        String personaNombre = (String) persona.get("nombre");

        String personaApellido = (String) persona.get("apellido");

        String personaFechaNacimiento = (String) persona.get("fechaNacimiento");

        String personaDni = (String) persona.get("dni");

        JSONObject localidad = (JSONObject) persona.get("localidad");

        Persona persona1 = new Persona(LocalDate.parse(personaFechaNacimiento), null, personaDni, personaNombre, personaApellido);

        if(localidad != null){

          JSONObject ciudad = (JSONObject) localidad.get("ciudad");

          JSONObject provincia = (JSONObject) ciudad.get("provincia");

          String localidadNombre = (String) localidad.get("nombre");

          String ciudadNombre = (String) ciudad.get("nombre");

          String provinciaNombre = (String) provincia.get("nombre");

          Localidad localidad1 = new Localidad(localidadNombre,new Ciudad(ciudadNombre,new Provincia(provinciaNombre)));


          persona1.setLocalidad(localidad1);
        }

        personas.add(persona1);
      }




  }
}
