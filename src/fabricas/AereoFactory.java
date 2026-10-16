package fabricas;

import modelos.Aereo;
import modelos.Envio;

public class AereoFactory extends EnvioFactory {

    @Override
    public Envio crearEnvio(String codigo, String cliente, double peso, double distancia) {
        return new Aereo(codigo, cliente, peso, distancia);
    }
}
