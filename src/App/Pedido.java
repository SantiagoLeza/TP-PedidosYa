package App;

import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido {
    private String fechaPedido;
    private ItemPedido[] listaDePlatos;
    private float costoDeEnvio;
    private String destino;
    private float precioTotal;
    private int idRestaurant;
    private int estadoDelPedido; //0: pendiente, 1: en proceso, 2: enviado, 3: cancelado
    private boolean pagado;

    public Pedido(ItemPedido[] listaDePlatos, float costoDeEnvio, String destino, float precioTotal, int idRestaurant, boolean pagado)
    {
        Date d = new Date();
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
        fechaPedido = f1.format(d);

        this.listaDePlatos = listaDePlatos;
        this.costoDeEnvio = costoDeEnvio;
        this.destino = destino;
        this.precioTotal = precioTotal;
        this.idRestaurant = idRestaurant;
        estadoDelPedido = 0;
        this.pagado = pagado;
    }

    public Pedido(String fechaPedido, ItemPedido[] listaDePlatos, float costoDeEnvio, String destino, float precioTotal, int idRestaurant,int estadoDelPedido, boolean pagado)
    {
        this.fechaPedido = fechaPedido;
        this.listaDePlatos = listaDePlatos;
        this.costoDeEnvio = costoDeEnvio;
        this.destino = destino;
        this.precioTotal = precioTotal;
        this.idRestaurant = idRestaurant;
        this.estadoDelPedido = estadoDelPedido;
        this.pagado = pagado;
    }

    public String getFechaPedido()
    {
        return fechaPedido;
    }

    public ItemPedido[] getListaDePlatos()
    {
        return listaDePlatos;
    }

    public float getCostoDeEnvio()
    {
        return costoDeEnvio;
    }

    public String getDestino()
    {
        return destino;
    }

    public float getPrecioTotal()
    {
        return precioTotal;
    }

    public int getIdRestaurant()
    {
        return idRestaurant;
    }

    public int getEstadoDelPedido()
    {
        return estadoDelPedido;
    }

    public boolean isPagado()
    {
        return pagado;
    }
}
