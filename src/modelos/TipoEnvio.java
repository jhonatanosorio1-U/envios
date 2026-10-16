package modelos;

public enum TipoEnvio {
    TERRESTRE("Terrestre"),
    AEREO("Aéreo"),
    FLUVIAL("Fluvial");

    private final String nombre;

    TipoEnvio(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}