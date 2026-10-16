package servicios;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import fabricas.AereoFactory;
import fabricas.EnvioFactory;
import fabricas.FluvialFactory;
import fabricas.TerrestreFactory;
import modelos.Envio;
import modelos.TipoEnvio;

public class EnvioServicio {

    private static final List<Envio> envios = new ArrayList<>();

    private static final Map<TipoEnvio, EnvioFactory> fabricas = new EnumMap<>(TipoEnvio.class);

    static {
        fabricas.put(TipoEnvio.TERRESTRE, new TerrestreFactory());
        fabricas.put(TipoEnvio.AEREO, new AereoFactory());
        fabricas.put(TipoEnvio.FLUVIAL, new FluvialFactory());
    }

    public static Envio agregar(TipoEnvio tipo, String codigo, String cliente, double peso, double distancia) {
        if (codigo == null || codigo.isBlank() || cliente == null || cliente.isBlank()) {
            return null;
        }
        if (peso <= 0 || distancia <= 0) {
            return null;
        }
        if (buscarPorCodigo(codigo) != null) {
            return null;
        }

        EnvioFactory fabrica = fabricas.get(tipo);
        if (fabrica == null) {
            return null;
        }

        Envio envio = fabrica.crearEnvio(codigo, cliente, peso, distancia);
        envios.add(envio);
        return envio;
    }

    public static boolean eliminarPorCodigo(String codigo) {
        Envio envio = buscarPorCodigo(codigo);
        if (envio == null) {
            return false;
        }
        return envios.remove(envio);
    }

    public static Envio buscarPorCodigo(String codigo) {
        for (Envio envio : envios) {
            if (envio.getCodigo().equals(codigo)) {
                return envio;
            }
        }
        return null;
    }

    private static String[] encabezados = new String[]{"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};

    public static String[] getEncabezados() {
        return encabezados;
    }

    public static String[][] getDatos() {
        String[][] datos = new String[envios.size()][encabezados.length];
        int fila = 0;
        for (Envio envio : envios) {
            datos[fila][0] = envio.getTipo().toString();
            datos[fila][1] = envio.getCodigo();
            datos[fila][2] = envio.getCliente();
            datos[fila][3] = String.valueOf(envio.getPeso());
            datos[fila][4] = String.valueOf(envio.getDistancia());
            datos[fila][5] = String.valueOf(envio.calcularTarifa());
            fila++;
        }
        return datos;
    }
}
