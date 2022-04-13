package App;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Puntuacion {
    private int id;
    private String usuario;
    private int cantEstrllas;
    private String comentario;
    private String fecha;
    private String nombreProducto;

    private static int ultimoIdReseña = 0;

    public Puntuacion( String usuario, int cantEstrllas, String comentario, String nombreProducto)
    {
        id = ultimoIdReseña+1;
        ultimoIdReseña++;

        this.usuario = usuario;
        this.cantEstrllas = cantEstrllas;
        this.comentario = comentario;

        Date d = new Date();
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
        fecha = f1.format(d);

        this.nombreProducto = nombreProducto;
    }

    public Puntuacion(int id, String usuario, int cantEstrllas, String comentario, String fecha, String nombreProducto)
    {
        this.id = id;
        this.usuario = usuario;
        this.cantEstrllas = cantEstrllas;
        this.comentario = comentario;
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;

        ultimoIdReseña = id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public int getCantEstrllas()
    {
        return cantEstrllas;
    }

    public void setCantEstrllas(int cantEstrllas)
    {
        this.cantEstrllas = cantEstrllas;
    }

    public String getComentario()
    {
        return comentario;
    }

    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }

    public String getFecha()
    {
        return fecha.toString();
    }

    public String getNombreProducto()
    {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto)
    {
        this.nombreProducto = nombreProducto;
    }
}
