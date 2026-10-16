package fabricas;

import modelos.Envio;
import modelos.Terrestre;

public class TerrestreFactory extends EnvioFactory {

    @Override
    public Envio crearEnvio(String codigo, String cliente, double peso, double distancia) {
        return new Terrestre(codigo, cliente, peso, distancia);
    }
}
