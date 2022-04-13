package App;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ArchivoHandler
{

    JSONArray listaRestaurantes;
    JSONArray listaUsuarios;

    public ArchivoHandler()
    {
        try(FileReader reader = new FileReader("restaurants.json"))
        {
            Object obj = new JSONParser().parse(reader);
            listaRestaurantes = (JSONArray) obj;
        }
        catch (FileNotFoundException e)
        {
            listaRestaurantes = new JSONArray();
        }
        catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }

        try(FileReader reader = new FileReader("usuarios.json"))
        {
            Object obj = new JSONParser().parse(reader);
            listaUsuarios = (JSONArray) obj;
        }
        catch (FileNotFoundException e)
        {
            listaUsuarios = new JSONArray();
        }
        catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }
    }

    public void guardarRestaurant(int id, String mail, String contra, String nombre, String direccion, int telefono, String[] categorias, int puntuacion, Plato[] menu, boolean takeAway, boolean delivery, String[] mediosDePago, Puntuacion[] resenas, Pedido[] pedidosPendientes, int demora, Horario horario, float costoEnvio)
    {

        JSONArray categoriasJson = new JSONArray();

        for (String categoria : categorias)
        {
            categoriasJson.add(categoria);
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
                    JSONObject itemPedidoJson = new JSONObject();
                    itemPedidoJson.put("nombre", plato.getNombre());
                    itemPedidoJson.put("cant", plato.getCant());
                    itemPedidoJson.put("precio", plato.getPrecio());

                    platosJson.add(itemPedidoJson);
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


        listaRestaurantes.add(restaurantJson);

        try
        {
            FileWriter archivoRestaurants = new FileWriter("restaurants.json");
            archivoRestaurants.write(listaRestaurantes.toJSONString());
            archivoRestaurants.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Restaurant[] volcarArchiRestaurants()
    {
        Restaurant[] restaurantes = null;

        try (FileReader reader = new FileReader("restaurants.json")) {
            Object obj = new JSONParser().parse(reader);
            listaRestaurantes = (JSONArray) obj;
            restaurantes = new Restaurant[listaRestaurantes.size() * 5];
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < listaRestaurantes.size(); i++) {
            JSONObject restaurantJson = (JSONObject) listaRestaurantes.get(i);

            int id = Integer.parseInt(restaurantJson.get("id").toString());
            String mail = restaurantJson.get("mail").toString();
            String contra = restaurantJson.get("contraseña").toString();
            String nombre = restaurantJson.get("nombre").toString();
            String direccion = restaurantJson.get("direccion").toString();
            int telefono = Integer.parseInt(restaurantJson.get("telefono").toString());

            JSONArray categoriasJson = (JSONArray) restaurantJson.get("categorias");
            String[] categorias = new String[categoriasJson.size() + 5];
            for (int j = 0; j < categoriasJson.size(); j++)
            {
                categorias[j] = categoriasJson.get(j).toString();
            }

            int puntuacion = Integer.parseInt(restaurantJson.get("puntuacion").toString());

            Plato[] menu;
            JSONArray menuJson = (JSONArray) restaurantJson.get("menu");
            menu = new Plato[menuJson.size() * 5];
            for (int j = 0; j < menuJson.size(); j++) {
                JSONObject platoJson = (JSONObject) menuJson.get(j);
                menu[j] = new Plato(platoJson.get("nombre").toString(), Float.parseFloat(platoJson.get("precio").toString()), platoJson.get("tipoUnidad").toString(), Integer.parseInt(platoJson.get("demora").toString()), Integer.parseInt(platoJson.get("stock").toString()));
            }

            boolean takeAway = Boolean.parseBoolean(restaurantJson.get("takeAway").toString());
            boolean delivery = Boolean.parseBoolean(restaurantJson.get("delivery").toString());


            JSONArray mediosDePagoJson = (JSONArray) restaurantJson.get("mediosDePago");
            String[] mediosDePago = new String[mediosDePagoJson.size() + 5];
            for (int j = 0; j < mediosDePagoJson.size(); j++)
            {
                mediosDePago[j] = mediosDePagoJson.get(j).toString();
            }

            Puntuacion[] resenas;
            JSONArray resenasJson = (JSONArray) restaurantJson.get("resenas");
            resenas = new Puntuacion[resenasJson.size() * 5];
            for (int j = 0; j < resenasJson.size(); j++) {
                JSONObject resenaJson = (JSONObject) resenasJson.get(j);
                resenas[j] = new Puntuacion(
                        Integer.parseInt(resenaJson.get("id").toString()),
                        resenaJson.get("usuario").toString(),
                        Integer.parseInt(resenaJson.get("puntuacion").toString()),
                        resenaJson.get("comentario").toString(),
                        resenaJson.get("fecha").toString(),
                        resenaJson.get("nombreProducto").toString()
                );
            }

            Pedido[] pedidosPendientes;
            JSONArray pedidosPendientesJson = (JSONArray) restaurantJson.get("pedidosPendientes");
            pedidosPendientes = new Pedido[pedidosPendientesJson.size() * 5];
            for (int j = 0; j < pedidosPendientesJson.size(); j++) {
                JSONObject pedidoJson = (JSONObject) pedidosPendientesJson.get(j);

                String fechaPedido = pedidoJson.get("fechaPedido").toString();

                JSONArray listaPedidosJson = (JSONArray) pedidoJson.get("platos");
                ItemPedido[] listaDePlatos = new ItemPedido[listaPedidosJson.size() + 5];

                for (int k = 0; k < listaPedidosJson.size(); k++) {
                    JSONObject platoJson = (JSONObject) listaPedidosJson.get(k);
                    listaDePlatos[k] = new ItemPedido(
                            platoJson.get("nombre").toString(),
                            Integer.parseInt(platoJson.get("cant").toString()),
                            Float.parseFloat(platoJson.get("precio").toString())
                    );
                }


                float costoDeEnvio = Float.parseFloat(pedidoJson.get("costoDeEnvio").toString());
                String destino = pedidoJson.get("destino").toString();
                float precioTotal = Float.parseFloat(pedidoJson.get("precioTotal").toString());
                int idRestaurant = Integer.parseInt(pedidoJson.get("idRestaurant").toString());
                int estadoDelPedido = Integer.parseInt(pedidoJson.get("estado").toString());
                boolean pagado = Boolean.parseBoolean(pedidoJson.get("pagado").toString());



                pedidosPendientes[j] = new Pedido(
                        fechaPedido,
                        listaDePlatos,
                        costoDeEnvio,
                        destino,
                        precioTotal,
                        idRestaurant,
                        estadoDelPedido,
                        pagado
                );
            }

            int demora = Integer.parseInt(restaurantJson.get("demora").toString());

            Horario horario;
            JSONObject horarioJson = (JSONObject) restaurantJson.get("horario");
            JSONObject aperturaJson = (JSONObject) horarioJson.get("apertura");
            JSONObject cierreJson = (JSONObject) horarioJson.get("cierre");

            horario = new Horario(Integer.parseInt(aperturaJson.get("hora").toString()), Integer.parseInt(aperturaJson.get("minuto").toString()), Integer.parseInt(cierreJson.get("hora").toString()), Integer.parseInt(cierreJson.get("minuto").toString()));

            float costoEnvio = Float.parseFloat(restaurantJson.get("costoEnvio").toString());

            restaurantes[i] = new Restaurant(id, mail, contra, nombre, direccion, telefono, categorias, puntuacion, menu, takeAway, delivery, mediosDePago, resenas, pedidosPendientes, demora, horario, costoEnvio);

        }



        return restaurantes;
    }

    public void modificarRestaurant(Restaurant restaurant)
    {
        Restaurant[] restaurantes = null;

        restaurantes = volcarArchiRestaurants();

        for(Restaurant r : restaurantes)
        {
            if(r != null)
            {
                if(restaurant.getId() == r.getId())
                {
                    restaurantes[r.getId()] = restaurant;
                }
            }
        }

        listaRestaurantes = new JSONArray();
        for(Restaurant restaurante : restaurantes)
        {
            if(restaurante != null)
            {
                listaRestaurantes.add(restaurante.toJsonObject());
            }
        }

        try
        {
            FileWriter archivoRestaurants = new FileWriter("restaurants.json");
            archivoRestaurants.write(listaRestaurantes.toJSONString());
            archivoRestaurants.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void modificarUsuario(Usuario usuario)
    {
        Usuario[] usuarios = null;

        usuarios = volcarArchiUsers();

        for (int i = 0; i < usuarios.length; i++)
        {
            Usuario u = usuarios[i];
            if (u != null)
            {
                if (u.getEmail().equals(usuario.getEmail()))
                {
                    usuarios[i] = usuario;
                }
            }
        }

        listaUsuarios = new JSONArray();
        for(Usuario u : usuarios)
        {
            if(u != null)
            {
                listaUsuarios.add(u.toJsonObject());
            }
        }

        try
        {
            FileWriter archivoUsuarios = new FileWriter("usuarios.json");
            archivoUsuarios.write(listaUsuarios.toJSONString());
            archivoUsuarios.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void guardarUsuario(Usuario usuario)
    {
        JSONObject usuarioJson = new JSONObject();
        usuarioJson.put("direccion", usuario.getDireccion());
        usuarioJson.put("correo", usuario.getEmail());
        usuarioJson.put("contrasena", usuario.getContra());
        usuarioJson.put("nombre", usuario.getNombreYAp());
        usuarioJson.put("telefono", usuario.getNumTelefono());

        JSONArray pedidosJson = new JSONArray();
        for (int i = 0; i < usuario.getPedidos().length; i++)
        {
            if(usuario.getPedidos()[i] != null)
            {
                JSONObject pedidoJson = new JSONObject();
                pedidoJson.put("fecha", usuario.getPedidos()[i].getFechaPedido());

                JSONArray platosJson = new JSONArray();
                for (int j = 0; j < usuario.getPedidos()[i].getListaDePlatos().length; j++) {
                    if(usuario.getPedidos()[i].getListaDePlatos()[j] != null)
                    {
                        JSONObject platoJson = new JSONObject();
                        platoJson.put("nombre", usuario.getPedidos()[i].getListaDePlatos()[j].getNombre());
                        platoJson.put("cantidad", usuario.getPedidos()[i].getListaDePlatos()[j].getCant());
                        platoJson.put("precio", usuario.getPedidos()[i].getListaDePlatos()[j].getPrecio());
                        platosJson.add(platoJson);
                    }
                }

                pedidoJson.put("platos", platosJson);

                pedidoJson.put("costoDeEnvio", usuario.getPedidos()[i].getCostoDeEnvio());
                pedidoJson.put("destino", usuario.getPedidos()[i].getDestino());
                pedidoJson.put("precioTotal", usuario.getPedidos()[i].getPrecioTotal());
                pedidoJson.put("idRestaurant", usuario.getPedidos()[i].getIdRestaurant());
                pedidoJson.put("estadoDelPedido", usuario.getPedidos()[i].getEstadoDelPedido());
                pedidoJson.put("pagado", usuario.getPedidos()[i].isPagado());

                pedidosJson.add(pedidoJson);
            }
        }

        usuarioJson.put("pedidos", pedidosJson);

        usuarioJson.put("activo", usuario.isActivo());

        listaUsuarios.add(usuarioJson);

        try
        {
            FileWriter archivoUsers = new FileWriter("usuarios.json");
            archivoUsers.write(listaUsuarios.toJSONString());
            archivoUsers.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Usuario[] volcarArchiUsers()
    {
        Usuario[] usuarios = null;

        try(FileReader reader = new FileReader("usuarios.json"))
        {
            Object obj = new JSONParser().parse(reader);
            listaUsuarios = (JSONArray) obj;
            usuarios = new Usuario[listaUsuarios.size() * 5];
        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < listaUsuarios.size(); i++)
        {
            JSONObject usuarioJson = (JSONObject) listaUsuarios.get(i);

            String direccion = usuarioJson.get("direccion").toString();
            String email = usuarioJson.get("correo").toString();
            String contra = usuarioJson.get("contrasena").toString();
            String nombreYAp = usuarioJson.get("nombre").toString();
            int numTelefono = Integer.parseInt(usuarioJson.get("telefono").toString());

            JSONArray pedidosJson = (JSONArray) usuarioJson.get("pedidos");
            Pedido[] pedidos = new Pedido[pedidosJson.size() + 5];
            for (int j = 0; j < pedidosJson.size(); j++)
            {
                JSONObject pedidoJson = (JSONObject) pedidosJson.get(j);

                JSONArray platosPedidosJson = (JSONArray) pedidoJson.get("platos");
                ItemPedido[] platosPedidos = new ItemPedido[platosPedidosJson.size() + 5];
                for (int k = 0; k < platosPedidosJson.size(); k++)
                {
                    JSONObject platoJson = (JSONObject) platosPedidosJson.get(k);
                    platosPedidos[k] = new ItemPedido(
                            platoJson.get("nombre").toString(),
                            Integer.parseInt(platoJson.get("cantidad").toString()),
                            Float.parseFloat(platoJson.get("precio").toString())
                    );
                }

                pedidos[j] = new Pedido(
                        pedidoJson.get("fecha").toString(),
                        platosPedidos,
                        Float.parseFloat(pedidoJson.get("costoDeEnvio").toString()),
                        pedidoJson.get("destino").toString(),
                        Float.parseFloat(pedidoJson.get("precioTotal").toString()),
                        Integer.parseInt(pedidoJson.get("idRestaurant").toString()),
                        Integer.parseInt(pedidoJson.get("estadoDelPedido").toString()),
                        Boolean.parseBoolean(pedidoJson.get("pagado").toString())
                );
            }

            boolean activo = Boolean.parseBoolean(usuarioJson.get("activo").toString());

            usuarios[i] = new Usuario(direccion, email, contra, nombreYAp, numTelefono, pedidos, activo);
        }

        return usuarios;
    }
}
