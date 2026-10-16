import java.util.EnumMap;
import java.util.Map;

import controladores.EnviosControlador;
import fabricas.AereoFactory;
import fabricas.EnvioFactory;
import fabricas.FluvialFactory;
import fabricas.TerrestreFactory;
import modelos.TipoEnvio;
import servicios.EnvioServicio;
import servicios.IEnvioServicio;
import vistas.EnviosVista;

public class App {
    public static void main(String[] args) throws Exception {Map<TipoEnvio, EnvioFactory> fabricas = new EnumMap<>(TipoEnvio.class);
        fabricas.put(TipoEnvio.TERRESTRE, new TerrestreFactory());
        fabricas.put(TipoEnvio.AEREO, new AereoFactory());
        fabricas.put(TipoEnvio.FLUVIAL, new FluvialFactory());

        IEnvioServicio servicio = new EnvioServicio(fabricas);
        var vista = new EnviosVista();
        new EnviosControlador(vista, servicio);
        vista.setVisible(true);
    }
}
