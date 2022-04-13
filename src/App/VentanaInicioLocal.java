package App;

import javax.swing.*;

public class VentanaInicioLocal
{
    JFrame ventana;
    JPanel panel;

    public VentanaInicioLocal(PedidosYa app)
    {
        ventana = new JFrame("Inicio de sesion de local");
        ventana.setSize(400, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);

        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, ventana.getWidth(), 50);
        header.setBackground(new java.awt.Color(150, 150, 150));

        JTextField titulo = new JTextField("Inicio de sesion de local");
        titulo.setEditable(false);
        titulo.setBounds(0, 0, Math.round(header.getWidth() * 0.75f), header.getHeight());
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setBackground(new java.awt.Color(0, 0, 0, 0));

        ImageIcon logoutIcon = new ImageIcon(this.getClass().getResource("../Imagenes/logout.png"));
        JButton logout = new JButton(new ImageIcon(logoutIcon.getImage().getScaledInstance(Math.round(header.getHeight() * 0.75f), header.getHeight(), java.awt.Image.SCALE_SMOOTH)));
        logout.setBounds(Math.round(header.getWidth() * 0.75f), 0, Math.round(header.getWidth() * 0.25f), header.getHeight());
        logout.setBackground(new java.awt.Color(255, 255, 255, 0));
        logout.setBorder(null);
        logout.setFocusPainted(false);
        logout.setContentAreaFilled(false);
        logout.addActionListener(e -> {
            ventana.dispose();
            VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(app);
        });

        header.add(titulo);
        header.add(logout);

        panel.add(header);

        JPanel body = new JPanel();
        body.setLayout(null);
        body.setBounds(0, 50, ventana.getWidth(), ventana.getHeight() - 50);
        body.setBackground(new java.awt.Color(255, 255, 255));

        JTextField usuarioText = new JTextField("Usuario");
        usuarioText.setBounds(Math.round(body.getWidth() * 0.05f), Math.round(body.getHeight() * 0.05f), Math.round(body.getWidth() * 0.9f), Math.round(body.getHeight() * 0.1f));
        usuarioText.setBorder(null);
        usuarioText.setEditable(false);

        JTextField usuario = new JTextField();
        usuario.setBounds(Math.round(body.getWidth() * 0.05f), Math.round(body.getHeight() * 0.15f), Math.round(body.getWidth() * 0.9f), Math.round(body.getHeight() * 0.1f));

        JTextField passwordText = new JTextField("Contraseña");
        passwordText.setBounds(Math.round(body.getWidth() * 0.05f), Math.round(body.getHeight() * 0.25f), Math.round(body.getWidth() * 0.9f), Math.round(body.getHeight() * 0.1f));
        passwordText.setBorder(null);
        passwordText.setEditable(false);

        JPasswordField password = new JPasswordField();
        password.setBounds(Math.round(body.getWidth() * 0.05f), Math.round(body.getHeight() * 0.35f), Math.round(body.getWidth() * 0.9f), Math.round(body.getHeight() * 0.1f));

        JButton login = new JButton("Iniciar sesion");
        login.setBounds(Math.round(body.getWidth() * 0.05f), Math.round(body.getHeight() * 0.55f), Math.round(body.getWidth() * 0.9f), Math.round(body.getHeight() * 0.1f));
        login.setBackground(new java.awt.Color(150, 150, 150));
        login.addActionListener(e -> {
            if (usuario.getText().equals("") || password.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String usuarioIngresado = usuario.getText();
                String passwordIngresado = password.getText();

                if(!usuarioIngresado.contains("@"))
                {
                    JOptionPane.showMessageDialog(null, "El usuario debe ser un correo electronico", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Restaurant restaurant = app.iniciarSesionRestaurant(usuarioIngresado, passwordIngresado);
                    if( restaurant != null )
                    {
                        ventana.dispose();
                        VentanaDueñoLocal ventanaDueñoLocal = new VentanaDueñoLocal(app, restaurant);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        body.add(usuarioText);
        body.add(usuario);
        body.add(passwordText);
        body.add(password);
        body.add(login);

        panel.add(body);

        ventana.add(panel);

        ventana.setVisible(true);
    }
}
