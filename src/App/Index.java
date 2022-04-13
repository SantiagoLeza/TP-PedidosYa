package App;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Index
{
    static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);//

        PedidosYa app = new PedidosYa();
        app.agregarUsersYRestaurants();

        VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(app);

        scanner.close();

    }
}
