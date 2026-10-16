package controladores;

import java.util.List;

import modelos.Envio;
import servicios.IEnvioServicio;
import vistas.EnviosVista;

public class EnviosControlador {

    private static final String[] ENCABEZADOS =
            {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};

    private final EnviosVista vista;
    private final IEnvioServicio servicio;
 public EnviosControlador(EnviosVista vista, IEnvioServicio servicio) {
        this.vista = vista;
        this.servicio = servicio;

        this.vista.setGuardarEnvioClick(evento -> agregarEnvio());
        this.vista.setRetirarEnvioClick(evento -> retirarEnvio());

        mostrarEnvios();
    }
    

    public EnviosControlador(EnviosVista vista) {
        this(vista, null);
    }

    public void mostrarEnvios() {
        vista.mostrarEnvios(construirFilas(), ENCABEZADOS);
    }

    

    private String[][] construirFilas() {
        List<Envio> envios = servicio.listar();
        String[][] filas = new String[envios.size()][];
        for (int i = 0; i < envios.size(); i++) {
            Envio envio = envios.get(i);
            filas[i] = new String[] {
                envio.getTipo().toString(),
                envio.getCodigo(),
                envio.getCliente(),
                String.valueOf(envio.getPeso()),
                String.valueOf(envio.getDistancia()),
                String.format("%.0f", envio.calcularTarifa())
            };
        }
        return filas;
    }

    private void agregarEnvio() {
        var tipo = vista.getTipoSeleccionado();
        var codigo = vista.getCodigo();
        var cliente = vista.getCliente();
        var peso = vista.getPeso();
        var distancia = vista.getDistancia();

        var envioAgregado = servicio.agregar(tipo, codigo, cliente, peso, distancia);
        if (envioAgregado != null) {
            mostrarEnvios();
            vista.ocultarEdicionEnvio();
        } else {
            vista.mostrarMensaje("El envío no pudo ser agregado");
        }
    }

    private void retirarEnvio() {
        String codigo = vista.pedirCodigoRetiro();
        if (codigo == null || codigo.isBlank()) {
            return; // el usuario cerró o canceló el diálogo
        }

        if (servicio.eliminarPorCodigo(codigo)) {
            mostrarEnvios();
        } else {
            vista.mostrarMensaje("No existe un envío con el código " + codigo);
        }
    }
}