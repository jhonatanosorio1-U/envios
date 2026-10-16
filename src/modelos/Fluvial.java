package modelos;

public class Fluvial extends Envio {

    public Fluvial(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (getDistancia() * 800) + (getPeso() * 1000);
    }

    @Override
    public TipoEnvio getTipo() {
        return TipoEnvio.FLUVIAL;
    }
}
