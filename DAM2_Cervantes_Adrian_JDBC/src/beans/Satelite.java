package beans;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Satelite {
    private int id;
    private String nombre;
    private String orbita;
    private BigDecimal peso;
    private BigDecimal coste;
    private boolean activo;
    private LocalDate fechaLanzamiento;
    private Agencia agencia;
    private DetalleSatelite detalle;
    private String autorExamen;

    public Satelite() {
    }

    public Satelite(int id, String nombre, String orbita, BigDecimal peso, BigDecimal coste, boolean activo,
                    LocalDate fechaLanzamiento, Agencia agencia, DetalleSatelite detalle, String autorExamen) {
        this.id = id;
        this.nombre = nombre;
        this.orbita = orbita;
        this.peso = peso;
        this.coste = coste;
        this.activo = activo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.agencia = agencia;
        this.detalle = detalle;
        this.autorExamen = autorExamen;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getOrbita() { return orbita; }

    public void setOrbita(String orbita) { this.orbita = orbita; }

    public BigDecimal getPeso() { return peso; }

    public void setPeso(BigDecimal peso) { this.peso = peso; }

    public BigDecimal getCoste() { return coste; }

    public void setCoste(BigDecimal coste) { this.coste = coste; }

    public boolean isActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    public LocalDate getFechaLanzamiento() { return fechaLanzamiento; }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }

    public Agencia getAgencia() { return agencia; }

    public void setAgencia(Agencia agencia) { this.agencia = agencia; }

    public DetalleSatelite getDetalle() { return detalle; }

    public void setDetalle(DetalleSatelite detalle) { this.detalle = detalle; }

    public String getAutorExamen() { return autorExamen; }

    public void setAutorExamen(String autorExamen) { this.autorExamen = autorExamen; }

    @Override
    public String toString() {
        return "Satelite{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", orbita='" + orbita + '\'' +
                ", peso=" + peso +
                ", coste=" + coste +
                ", activo=" + activo +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", agencia=" + agencia +
                ", detalle=" + detalle +
                '}';
    }
}
