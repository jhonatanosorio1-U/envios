package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fabricas.EnvioFactory;
import modelos.Envio;
import modelos.TipoEnvio;

public class EnvioServicio implements IEnvioServicio {

    private final List<Envio> envios = new ArrayList<>();
    private final Map<TipoEnvio, EnvioFactory> fabricas;

    public EnvioServicio(Map<TipoEnvio, EnvioFactory> fabricas) {
        this.fabricas = fabricas;
    }

    @Override
    public Envio agregar(TipoEnvio tipo, String codigo, String cliente, double peso, double distancia) {
        if (codigo == null || codigo.isBlank() || cliente == null || cliente.isBlank()) return null;
        if (peso <= 0 || distancia <= 0) return null;
        codigo = codigo.trim();
        if (buscarPorCodigo(codigo) != null) return null;

        EnvioFactory fabrica = fabricas.get(tipo);
        if (fabrica == null) return null;

        Envio envio = fabrica.crearEnvio(codigo, cliente, peso, distancia);
        envios.add(envio);
        return envio;
    }

    @Override
    public boolean eliminarPorCodigo(String codigo) {
        Envio envio = buscarPorCodigo(codigo.trim());
        return envio != null && envios.remove(envio);
    }

    @Override
    public List<Envio> listar() {
        return List.copyOf(envios);
    }

    private Envio buscarPorCodigo(String codigo) {
        for (Envio envio : envios) {
            if (envio.getCodigo().equals(codigo)) return envio;
        }
        return null;
    }
}