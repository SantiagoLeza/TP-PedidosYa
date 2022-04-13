package App;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Restaurant
{
    private int id;
    private String mail;
    private String contra;
    private String nombre;
    private String direccion;
    private int telefono;
    private String[] categorias;
    private int puntuacion;
    private Plato[] menu;
    private boolean takeAway;
    private boolean delivery;
    private String[] mediosDePago;
    private Puntuacion[] resenas;
    private Pedido[] pedidosPendientes;
    private int demora;
    private Horario horario;
    private float costoEnvio;

    private static int ultimoId = 0;

    public Restaurant(String mail, String contra, String nombre, String direccion, int telefono, String[] categorias, boolean takeAway, boolean delivery, int demora, Horario horario, float costoEnvio)
    {
        this.id = ultimoId;
        ultimoId++;
        this.mail = mail;
        this.contra = contra;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.categorias = categorias;
        puntuacion = 0;
        menu = new Plato[25];
        this.takeAway = takeAway;
        this.delivery = delivery;
        mediosDePago = new String[5];
        resenas = new Puntuacion[100];
        pedidosPendientes = new Pedido[50];
        this.demora = demora;
        this.horario = horario;
        this.costoEnvio = costoEnvio;

    }

    public Restaurant(int id, String mail, String contra, String nombre, String direccion, int telefono, String[] categorias, int puntuacion, Plato[] menu, boolean takeAway, boolean delivery, String[] mediosDePago, Puntuacion[] resenas, Pedido[] pedidosPendientes, int demora, Horario horario, float costoEnvio)
    {
        this.id = id;
        ultimoId = id;
        this.mail = mail;
        this.contra = contra;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.categorias = categorias;
        this.puntuacion = puntuacion;
        this.menu = menu;
        this.takeAway = takeAway;
        this.delivery = delivery;
        this.mediosDePago = mediosDePago;
        this.resenas = resenas;
        this.pedidosPendientes = pedidosPendientes;
        this.demora = demora;
        this.horario = horario;
        this.costoEnvio = costoEnvio;
    }

    public int getId()
    {
        return id;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getContra()
    {
        return contra;
    }

    public void setContra()
    {
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setMenu(Plato[] menu)
    {
        this.menu = menu;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public boolean isTakeAway() {
        return takeAway;
    }

    public void setTakeAway(boolean takeAway) {
        this.takeAway = takeAway;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String[] getMediosDePago() {
        return mediosDePago;
    }

    public void setMediosDePago(String[] mediosDePago) {
        this.mediosDePago = mediosDePago;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getDemora() {
        return demora;
    }

    public void setDemora(int demora) {
        this.demora = demora;
    }

    public Puntuacion[] getResenas() {
        return resenas;
    }

    public Plato[] getMenu() {
        return menu;
    }

    public Pedido[] getPedidosPendientes()
    {
        return pedidosPendientes;
    }

    public void setCostoEnvio(float costoEnvio)
    {
        this.costoEnvio = costoEnvio;
    }

    public float getCostoEnvio()
    {
        return costoEnvio;
    }

    public void agregarPlato(Plato plato)
    {
        Plato[] nuevoMenu = new Plato[menu.length + 1];
        System.arraycopy(menu, 0, nuevoMenu, 0, menu.length);
        nuevoMenu[menu.length] = plato;
        menu = nuevoMenu;
    }

    public JSONObject toJsonObject()
    {
        JSONArray categoriasJson = new JSONArray();

        if(categorias != null)
        {
            for (String categoria : categorias)
            {
                if(categoria != null)
                {
                    categoriasJson.add(categoria);
                }
            }
        }

        JSONArray menuJson = new JSONArray();
        for (Plato plato : menu)
        {
            if (plato != null)
            {
                JSONObject platoJson = new JSONObject();
                platoJson.put("nombre", plato.getNombrePlato());
                platoJson.put("precio", plato.getPrecio());
                platoJson.put("tipoUnidad", plato.getTipoUnidad());
                platoJson.put("demora", plato.getTiempoDemora());
                platoJson.put("stock", plato.getStock());

                menuJson.add(platoJson);
            }
        }

        JSONArray mediosDePagoJson = new JSONArray();

        for (String medioDePago : mediosDePago)
        {
            if (medioDePago != null)
            {
                mediosDePagoJson.add(medioDePago);
            }
        }

        JSONArray resenasJson = new JSONArray();

        for (Puntuacion reseña : resenas)
        {
            if(reseña != null)
            {
                JSONObject resenaJson = new JSONObject();
                resenaJson.put("id", reseña.getId());
                resenaJson.put("usuario", reseña.getUsuario());
                resenaJson.put("puntuacion", reseña.getCantEstrllas());
                resenaJson.put("comentario", reseña.getComentario());
                resenaJson.put("fecha", reseña.getFecha().toString());
                resenaJson.put("nombreProducto", reseña.getNombreProducto());

                resenasJson.add(resenaJson);
            }
        }

        JSONArray pedidosPendientesJson = new JSONArray();

        for (Pedido pedido : pedidosPendientes)
        {
            if(pedido != null)
            {
                JSONObject pedidoJson = new JSONObject();
                pedidoJson.put("fechaPedido", pedido.getFechaPedido().toString());

                JSONArray platosJson = new JSONArray();
                for (ItemPedido plato : pedido.getListaDePlatos())
                {
                    if(plato != null)
                    {
                        JSONObject itemPedidoJson = new JSONObject();
                        itemPedidoJson.put("nombre", plato.getNombre());
                        itemPedidoJson.put("cant", plato.getCant());
                        itemPedidoJson.put("precio", plato.getPrecio());

                        platosJson.add(itemPedidoJson);
                    }
                }

                pedidoJson.put("platos", platosJson);
                pedidoJson.put("costoDeEnvio", pedido.getCostoDeEnvio());
                pedidoJson.put("destino", pedido.getDestino());
                pedidoJson.put("precioTotal", pedido.getPrecioTotal());
                pedidoJson.put("idRestaurant", pedido.getIdRestaurant());
                pedidoJson.put("estado", pedido.getEstadoDelPedido());
                pedidoJson.put("pagado", pedido.isPagado());

                pedidosPendientesJson.add(pedidoJson);
            }
        }

        JSONObject horarioJson = new JSONObject();

        JSONObject aperturaJson = new JSONObject();
        aperturaJson.put("hora", horario.getHoraApertura());
        aperturaJson.put("minuto", horario.getMinutosApertura());

        JSONObject cierreJson = new JSONObject();
        cierreJson.put("hora", horario.getHoraCierre());
        cierreJson.put("minuto", horario.getMinutosCierre());

        horarioJson.put("apertura", aperturaJson);
        horarioJson.put("cierre", cierreJson);

        JSONObject restaurantJson = new JSONObject();

        restaurantJson.put("id", id);
        restaurantJson.put("mail", mail);
        restaurantJson.put("contraseña", contra);
        restaurantJson.put("nombre", nombre);
        restaurantJson.put("direccion", direccion);
        restaurantJson.put("telefono", telefono);
        restaurantJson.put("categorias", categoriasJson);
        restaurantJson.put("puntuacion", puntuacion);
        restaurantJson.put("menu", menuJson);
        restaurantJson.put("takeAway", takeAway);
        restaurantJson.put("delivery", delivery);
        restaurantJson.put("mediosDePago", mediosDePagoJson);
        restaurantJson.put("resenas", resenasJson);
        restaurantJson.put("pedidosPendientes", pedidosPendientesJson);
        restaurantJson.put("demora", demora);
        restaurantJson.put("horario", horarioJson);
        restaurantJson.put("costoEnvio", costoEnvio);

        return restaurantJson;
    }

    public void agregarReseña(Puntuacion reseña)
    {
        Puntuacion[] r = new Puntuacion[resenas.length + 1];
        for (int i = 0; i < resenas.length; i++)
        {
            r[i] = resenas[i];
        }
        r[resenas.length] = reseña;
        resenas = r;
    }

    public void agregarPedidoPendiente(Pedido pedido)
    {
        Pedido[] p = new Pedido[pedidosPendientes.length + 1];
        for (int i = 0; i < pedidosPendientes.length; i++)
        {
            p[i] = pedidosPendientes[i];
        }
        p[pedidosPendientes.length] = pedido;
        pedidosPendientes = p;
    }
}
