package beans;


import java.math.BigDecimal;

public class DetalleSatelite {
    private int id;
    private BigDecimal velocidadMaxima;
    private String combustible;
    private int vidaUtil;
    private BigDecimal temperaturaMaxima;
    private String autorExamen;

    public DetalleSatelite() {
    }

    public DetalleSatelite(int id, BigDecimal velocidadMaxima, String combustible, int vidaUtil,
                           BigDecimal temperaturaMaxima, String autorExamen) {
        this.id = id;
        this.velocidadMaxima = velocidadMaxima;
        this.combustible = combustible;
        this.vidaUtil = vidaUtil;
        this.temperaturaMaxima = temperaturaMaxima;
        this.autorExamen = autorExamen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(BigDecimal velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public BigDecimal getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(BigDecimal temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public String getAutorExamen() {
        return autorExamen;
    }

    public void setAutorExamen(String autorExamen) {
        this.autorExamen = autorExamen;
    }

    @Override
    public String toString() {
        return "DetalleSatelite{" +
                "id=" + id +
                ", velocidadMaxima=" + velocidadMaxima +
                ", combustible='" + combustible + '\'' +
                ", vidaUtil=" + vidaUtil +
                ", temperaturaMaxima=" + temperaturaMaxima +
                '}';
    }
}
