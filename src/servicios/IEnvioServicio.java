package servicios;

import java.util.List;
import modelos.Envio;
import modelos.TipoEnvio;

public interface IEnvioServicio {
    Envio agregar(TipoEnvio tipo, String codigo, String cliente, double peso, double distancia);
    boolean eliminarPorCodigo(String codigo);
    List<Envio> listar();
}
