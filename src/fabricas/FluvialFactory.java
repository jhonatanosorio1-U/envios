package fabricas;

import modelos.Envio;
import modelos.Fluvial;

public class FluvialFactory extends EnvioFactory {

    @Override
    public Envio crearEnvio(String codigo, String cliente, double peso, double distancia) {
        return new Fluvial(codigo, cliente, peso, distancia);
    }
}
