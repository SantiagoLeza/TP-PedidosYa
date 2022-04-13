package App;

import java.util.Date;

public class Horario {
    private int horaApertura;
    private int minutosApertura;
    private int horaCierre;
    private int minutosCierre;

    public Horario(int horaApertura, int minutosApertura, int horaCierre, int minutosCierre)
    {
        this.horaApertura = horaApertura;
        this.minutosApertura = minutosApertura;
        this.horaCierre = horaCierre;
        this.minutosCierre = minutosCierre;
    }

    public int getHoraApertura()
    {
        return horaApertura;
    }

    public int getMinutosApertura()
    {
        return minutosApertura;
    }

    public int getHoraCierre()
    {
        return horaCierre;
    }

    public int getMinutosCierre()
    {
        return minutosCierre;
    }
}
