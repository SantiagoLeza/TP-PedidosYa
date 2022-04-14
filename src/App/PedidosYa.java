package App;

public class PedidosYa {
    private Usuario[] listaUsuarios;
    private Restaurant[] listaRestaurants;

    private int ultimoUserRegistrado;
    private int ultimoRestaurantRegistrado;

    private ArchivoHandler hndl;

    public PedidosYa()
    {
        hndl = new ArchivoHandler();
        listaUsuarios = new Usuario[50];
        listaRestaurants = new Restaurant[20];
        ultimoUserRegistrado = -1;
        ultimoRestaurantRegistrado = -1;
    }

    //retorna null si el user no existe, el user si existe
    public Usuario iniciarSesion(String mail, String contra)
    {
        boolean flag = false;
        int i = 0;
        while(!flag&& i <= ultimoUserRegistrado)
        {
            if(listaUsuarios[i] != null)
            {
                if(listaUsuarios[i].getEmail().equals(mail))
                {
                    flag = true;
                    if(listaUsuarios[i].getContra().equals(contra))
                    {
                        return listaUsuarios[i];
                    }
                }
            }
            i++;
        }
        return null;
    }

    public boolean existeUsuario(String mail)
    {
        int i = 0;
        while(i <= ultimoUserRegistrado)
        {
            if(listaUsuarios[i] != null)
            {
                if(listaUsuarios[i].getEmail().equals(mail))
                {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    //retorna null si el mail ya existe, el usuario si es correcto
    public Usuario registrarUsuario(String direccion, String mail, String contra, String nombreYAp, int numTel)
    {
        if(existeUsuario(mail))
        {
            return null;
        }
        listaUsuarios[ultimoUserRegistrado+1] = new Usuario(direccion, mail, contra, nombreYAp, numTel);
        ultimoUserRegistrado++;

        hndl.guardarUsuario(listaUsuarios[ultimoUserRegistrado]);

        return listaUsuarios[ultimoUserRegistrado];
    }

    public int cantUsusarios()
    {
        return ultimoUserRegistrado+1;
    }

    public boolean existeRestaurante(String nombre, String direccion)
    {
        int i = 0;
        while(i <= ultimoRestaurantRegistrado)
        {
           if(listaRestaurants[i] != null)
           {
               if(listaRestaurants[i].getNombre().equalsIgnoreCase(nombre.trim()) && listaRestaurants[i].getDireccion().equalsIgnoreCase(direccion.trim()))
               {
                   return true;
               }
           }
            i++;
        }
        return false;
    }

    //retora null si el restaurant ya existe, el objeto si lo crea
    public Restaurant agregarRestaurant(Restaurant restaurant)
    {
         if(existeRestaurante(restaurant.getNombre(), restaurant.getDireccion()))
         {
              return null;
         }
         if(listaRestaurants.length == 0)
         {
             listaRestaurants = new Restaurant[1];
             listaRestaurants[0] = restaurant;
             ultimoRestaurantRegistrado = 0;
         }
         else
         {
             Restaurant[] aux = new Restaurant[listaRestaurants.length + 1];
             for(int i = 0; i < listaRestaurants.length; i++)
             {
                 aux[i] = listaRestaurants[i];
             }
             aux[aux.length-1] = restaurant;
             listaRestaurants = aux;
             ultimoRestaurantRegistrado = aux.length-1;
         }

         hndl.guardarRestaurant(
                 restaurant.getId(),
                 restaurant.getMail(),
                 restaurant.getContra(),
                 restaurant.getNombre(),
                 restaurant.getDireccion(),
                 restaurant.getTelefono(),
                 restaurant.getCategorias(),
                 restaurant.getPuntuacion(),
                 restaurant.getMenu(),
                 restaurant.isTakeAway(),
                 restaurant.isDelivery(),
                 restaurant.getMediosDePago(),
                 restaurant.getResenas(),
                 restaurant.getPedidosPendientes(),
                 restaurant.getDemora(),
                 restaurant.getHorario(),
                 restaurant.getCostoEnvio()
         );

         return restaurant;
    }

    public void modificarRestaurant(Restaurant restaurant)
    {
        if(restaurant != null)
        {
            listaRestaurants[restaurant.getId()] = restaurant;
            hndl.modificarRestaurant(restaurant);
        }
    }

    public void modificarUsuario(Usuario usuario)
    {
        if(usuario != null)
        {
            for (int i = 0; i < listaUsuarios.length; i++)
            {
                if(listaUsuarios[i] != null)
                {
                    if (listaUsuarios[i].getEmail().equals(usuario.getEmail()))
                    {
                        listaUsuarios[i] = usuario;
                        hndl.modificarUsuario(usuario);
                    }
                }
            }
        }
    }

    public void agregarUsersYRestaurants()
    {
        ArchivoHandler handler = new ArchivoHandler();

        listaRestaurants = handler.volcarArchiRestaurants();
        for(Restaurant r : listaRestaurants)
        {
            if(r != null)
            {
                ultimoRestaurantRegistrado++;
            }
        }

        listaUsuarios = handler.volcarArchiUsers();
        for (Usuario usuario: listaUsuarios)
        {
            if (usuario != null)
            {
                ultimoUserRegistrado++;
            }
        }

    }

    public int cantRestaurants()
    {
        return ultimoRestaurantRegistrado+1;
    }

    public Restaurant getRestaurantByIndex(int i)
    {
        return listaRestaurants[i];
    }

    public Restaurant iniciarSesionRestaurant(String mail, String contra)
    {
        for (Restaurant r : listaRestaurants)
        {
            if (r != null)
            {
                if (r.getMail().equals(mail) && r.getContra().equals(contra))
                {
                    return r;
                }
            }
        }
        return null;
    }

}
