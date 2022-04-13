package App;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Usuario {
    private String direccion;
    private String email;
    private String contra;
    private String nombreYAp;
    private int numTelefono;
    private Pedido[] pedidos;
    private int ultimoPedidoIndex;
    private boolean activo;

    public Usuario(String direccion, String email, String contra, String nombreYAp, int numTelefono)
    {
        this.direccion = direccion;
        this.email = email;
        this.contra = contra;
        this.nombreYAp = nombreYAp;
        this.numTelefono = numTelefono;
        ultimoPedidoIndex = 0;
        pedidos = new Pedido[50];
        activo = true;
    }

    public Usuario(String direccion, String email, String contra, String nombreYAp, int numTelefono, Pedido[] pedidos, boolean activo)
    {
        this.direccion = direccion;
        this.email = email;
        this.contra = contra;
        this.nombreYAp = nombreYAp;
        this.numTelefono = numTelefono;
        this.pedidos = pedidos;
        this.ultimoPedidoIndex = pedidos.length;
        this.activo = activo;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getContra()
    {
        return contra;
    }

    public void setContra(String contra)
    {
        this.contra = contra;
    }

    public String getNombreYAp()
    {
        return nombreYAp;
    }

    public void setNombreYAp(String nombreYAp)
    {
        this.nombreYAp = nombreYAp;
    }

    public int getNumTelefono()
    {
        return numTelefono;
    }

    public void setNumTelefono(int numTelefono)
    {
        this.numTelefono = numTelefono;
    }

    public Pedido[] getPedidos()
    {
        return pedidos;
    }

    public void setPedidos(Pedido[] pedidos)
    {
        this.pedidos = pedidos;
    }

    public int getUltimoPedidoIndex()
    {
        return ultimoPedidoIndex;
    }

    public void setUltimoPedidoIndex(int ultimoPedidoIndex)
    {
        this.ultimoPedidoIndex = ultimoPedidoIndex;
    }

    public boolean isActivo()
    {
        return activo;
    }

    public void setActivo(boolean activo)
    {
        this.activo = activo;
    }

    public JSONObject toJsonObject()
    {
        JSONObject usuarioJson = new JSONObject();
        usuarioJson.put("direccion", getDireccion());
        usuarioJson.put("correo", getEmail());
        usuarioJson.put("contrasena", getContra());
        usuarioJson.put("nombre", getNombreYAp());
        usuarioJson.put("telefono", getNumTelefono());

        JSONArray pedidosJson = new JSONArray();
        for (int i = 0; i < getPedidos().length; i++)
        {
            if(getPedidos()[i] != null)
            {
                JSONObject pedidoJson = new JSONObject();
                pedidoJson.put("fecha", getPedidos()[i].getFechaPedido());

                JSONArray platosJson = new JSONArray();
                for (int j = 0; j < getPedidos()[i].getListaDePlatos().length; j++) {
                    if(getPedidos()[i].getListaDePlatos()[j] != null)
                    {
                        JSONObject platoJson = new JSONObject();
                        platoJson.put("nombre", getPedidos()[i].getListaDePlatos()[j].getNombre());
                        platoJson.put("cantidad", getPedidos()[i].getListaDePlatos()[j].getCant());
                        platoJson.put("precio", getPedidos()[i].getListaDePlatos()[j].getPrecio());
                        platosJson.add(platoJson);
                    }
                }

                pedidoJson.put("platos", platosJson);

                pedidoJson.put("costoDeEnvio", getPedidos()[i].getCostoDeEnvio());
                pedidoJson.put("destino", getPedidos()[i].getDestino());
                pedidoJson.put("precioTotal", getPedidos()[i].getPrecioTotal());
                pedidoJson.put("idRestaurant", getPedidos()[i].getIdRestaurant());
                pedidoJson.put("estadoDelPedido", getPedidos()[i].getEstadoDelPedido());
                pedidoJson.put("pagado", getPedidos()[i].isPagado());

                pedidosJson.add(pedidoJson);
            }
        }

        usuarioJson.put("pedidos", pedidosJson);

        usuarioJson.put("activo", isActivo());

        return usuarioJson;
    }
}
