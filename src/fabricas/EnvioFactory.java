package fabricas;

import modelos.Envio;

public abstract class EnvioFactory {
    public abstract Envio crearEnvio(String codigo, String cliente, double peso, double distancia);
}
