package App;

public class ItemPedido {
    private String nombre;
    private int cant;
    private float precio;

    public ItemPedido(String nombre, int cant, float precio){
        this.nombre = nombre;
        this.cant = cant;
        this.precio = precio;
    }

    public float getPrecio()
    {
        return precio;
    }

    public int getCant()
    {
        return cant;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setCant(int cant)
    {
        this.cant = cant;
    }

    public void agregarCantidad()
    {
        this.cant++;
    }
}
