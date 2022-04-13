package App;

public class Plato {
    private String nombrePlato;
    private float precio;
    private String tipoUnidad;
    private int tiempoDemora;
    private int stock;

    public Plato(String nombrePlato, float precio, String tipoUnidad, int tiempoDemora, int stock){
        this.nombrePlato = nombrePlato;
        this.precio = precio;
        this.tipoUnidad = tipoUnidad;
        this.tiempoDemora = tiempoDemora;
        this.stock = stock;
    }

    public void setNombrePlato(String nombrePlato)
    {
        this.nombrePlato = nombrePlato;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public void setTipoUnidad(String tipoUnidad)
    {
        this.tipoUnidad = tipoUnidad;
    }

    public void setPrecio(float precio){
        this.precio = precio;
    }

    public void setTiempoDemora(int tiempoDemora){
        this.tiempoDemora = tiempoDemora;
    }

    public String getNombrePlato()
    {
        return nombrePlato;
    }

    public float getPrecio()
    {
        return precio;
    }

    public String getTipoUnidad()
    {
        return tipoUnidad;
    }

    public int getTiempoDemora()
    {
        return tiempoDemora;
    }

    public int getStock()
    {
        return stock;
    }

    public boolean hayStock()
    {
        return stock > 0;
    }

    public void restarStock()
    {
        stock--;
    }

}
