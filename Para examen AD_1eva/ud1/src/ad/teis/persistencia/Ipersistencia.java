package ad.teis.persistencia;

import ad.teis.model.Persona;

public interface Ipersistencia {

    void escribirPersona(Persona persona, String ruta);

    Persona leerDatos(String ruta);

     Persona leerPersona(int posicion, String ruta);
}
