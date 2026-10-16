package controladores;

import servicios.EnvioServicio;
import vistas.EnviosVista;

public class EnviosControlador {

    private final EnviosVista vista;

    public EnviosControlador(EnviosVista vista) {
        this.vista = vista;

        this.vista.setGuardarEnvioClick(evento -> agregarEnvio());
        this.vista.setRetirarEnvioClick(evento -> retirarEnvio());

        mostrarEnvios();
    }

    public void mostrarEnvios() {
        vista.mostrarEnvios(EnvioServicio.getDatos(), EnvioServicio.getEncabezados());
    }

    private void agregarEnvio() {
        var tipo = vista.getTipoSeleccionado();
        var codigo = vista.getCodigo();
        var cliente = vista.getCliente();
        var peso = vista.getPeso();
        var distancia = vista.getDistancia();

        var envioAgregado = EnvioServicio.agregar(tipo, codigo, cliente, peso, distancia);
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

        if (EnvioServicio.eliminarPorCodigo(codigo)) {
            mostrarEnvios();
        } else {
            vista.mostrarMensaje("No existe un envío con el código " + codigo);
        }
    }
}