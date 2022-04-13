package App;

import javax.swing.*;
import java.awt.*;

public class VentanaInicioSesion
{
    public VentanaInicioSesion(PedidosYa app)
    {
        JFrame ventanaLogin = new JFrame("Login");
        ventanaLogin.setSize(1024, 720);

        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(null);

        JTextField titluloLogin = new JTextField("Inicio de sesion");
        titluloLogin.setBounds(Math.round(ventanaLogin.getWidth()*0.25f), 100, Math.round(ventanaLogin.getWidth()*0.5f), 50);
        titluloLogin.setEditable(false);
        titluloLogin.setHorizontalAlignment(JTextField.CENTER);
        titluloLogin.setFont(new Font("Arial", Font.BOLD, 20));
        panelLogin.add(titluloLogin);

        JTextField usuarioTitulo = new JTextField("E-mail");
        usuarioTitulo.setBounds(Math.round(ventanaLogin.getWidth()*0.25f), 250, Math.round(ventanaLogin.getWidth()*0.5f), 30);
        usuarioTitulo.setEditable(false);
        panelLogin.add(usuarioTitulo);

        JTextField usuario = new JTextField();
        usuario.setBounds(Math.round(ventanaLogin.getWidth()*0.25f), 280, Math.round(ventanaLogin.getWidth()*0.5f), 30);
        panelLogin.add(usuario);

        JTextField contrasenaTitulo = new JTextField("Contraseña");
        contrasenaTitulo.setBounds(Math.round(ventanaLogin.getWidth()*0.25f), 320, Math.round(ventanaLogin.getWidth()*0.5f), 30);
        contrasenaTitulo.setEditable(false);
        panelLogin.add(contrasenaTitulo);

        JTextField contrasena = new JTextField();
        contrasena.setBounds(Math.round(ventanaLogin.getWidth()*0.25f), 350, Math.round(ventanaLogin.getWidth()*0.5f), 30);
        panelLogin.add(contrasena);

        JButton botonLogin = new JButton("Login");
        botonLogin.setBounds(Math.round((ventanaLogin.getWidth()*0.75f)/2), 400, Math.round(ventanaLogin.getWidth()*0.25f), 30);
        botonLogin.addActionListener(e -> {

            Usuario user = app.iniciarSesion(usuario.getText(), contrasena.getText());
            if( user != null)
            {
                ventanaLogin.dispose();
                VentanaUsuario ventanaUsuario = new VentanaUsuario(app, user);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }

        });
        panelLogin.add(botonLogin);

        JButton botonRegistro = new JButton("Registrarse");
        botonRegistro.setBounds(Math.round((ventanaLogin.getWidth()*0.75f)/2), 450, Math.round(ventanaLogin.getWidth()*0.25f), 30);
        botonRegistro.addActionListener(e -> {
            ventanaLogin.dispose();
            VentanaRegistro ventanaRegistro = new VentanaRegistro(app);
        });
        panelLogin.add(botonRegistro);

        JButton botonSesionRestaurante = new JButton("Tiene un local? Inicie sesion aqui");
        botonSesionRestaurante.setBounds(Math.round((ventanaLogin.getWidth()*0.75f)/2), 600, Math.round(ventanaLogin.getWidth()*0.25f), 30);
        botonSesionRestaurante.addActionListener(e -> {

            ventanaLogin.dispose();
            VentanaInicioLocal ventanaInicioLocal = new VentanaInicioLocal(app);

        });
        panelLogin.add(botonSesionRestaurante);

        ventanaLogin.add(panelLogin);

        ventanaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaLogin.setLocationRelativeTo(null);
        ventanaLogin.setVisible(true);
    }
}
