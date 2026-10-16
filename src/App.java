import controladores.EnviosControlador;
import vistas.EnviosVista;

public class App {
    public static void main(String[] args) throws Exception {
        var vista = new EnviosVista();
        new EnviosControlador(vista);
        vista.setVisible(true);
    }
}
