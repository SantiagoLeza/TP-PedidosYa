package App;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class VentanaInicioLocal
{
    JFrame ventana;
    JPanel panel;

    ArchivoHandler hndl = new ArchivoHandler();

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

        JButton registrarse = new JButton("Registrar su local");
        registrarse.setBounds(Math.round(body.getWidth() * 0.05f), Math.round(body.getHeight() * 0.75f), Math.round(body.getWidth() * 0.9f), Math.round(body.getHeight() * 0.1f));
        registrarse.setBackground(new java.awt.Color(150, 150, 150));
        registrarse.addActionListener(e -> {
            ventana.dispose();
            JFrame registro = new JFrame("Registro");
            registro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            registro.setSize(800, 1000);
            registro.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(null);

            JTextField nombreTitulo = new JTextField("Nombre");
            nombreTitulo.setBounds(Math.round(registro.getWidth() * .25f), 100, Math.round(registro.getWidth() * .25f), 30);
            nombreTitulo.setBorder(null);
            nombreTitulo.setEditable(false);

            JTextField nombre = new JTextField();
            nombre.setBounds(Math.round(registro.getWidth() * .5f), 100, Math.round(registro.getWidth() * .25f), 30);

            JTextField correoTitulo = new JTextField("Correo electronico");
            correoTitulo.setBounds(Math.round(registro.getWidth() * .25f), 150, Math.round(registro.getWidth() * .25f), 30);
            correoTitulo.setBorder(null);
            correoTitulo.setEditable(false);

            JTextField correo = new JTextField();
            correo.setBounds(Math.round(registro.getWidth() * .5f), 150, Math.round(registro.getWidth() * .25f), 30);

            JTextField passwordTitulo = new JTextField("Contraseña");
            passwordTitulo.setBounds(Math.round(registro.getWidth() * .25f), 200, Math.round(registro.getWidth() * .25f), 30);
            passwordTitulo.setBorder(null);
            passwordTitulo.setEditable(false);

            JPasswordField passwordT = new JPasswordField();
            passwordT.setBounds(Math.round(registro.getWidth() * .5f), 200, Math.round(registro.getWidth() * .25f), 30);

            JTextField confirmarPasswordTitulo = new JTextField("Confirmar contraseña");
            confirmarPasswordTitulo.setBounds(Math.round(registro.getWidth() * .25f), 250, Math.round(registro.getWidth() * .25f), 30);
            confirmarPasswordTitulo.setBorder(null);
            confirmarPasswordTitulo.setEditable(false);

            JPasswordField confirmarPasswordT = new JPasswordField();
            confirmarPasswordT.setBounds(Math.round(registro.getWidth() * .5f), 250, Math.round(registro.getWidth() * .25f), 30);

            JTextField direccionTitulo = new JTextField("Direccion");
            direccionTitulo.setBounds(Math.round(registro.getWidth() * .25f), 300, Math.round(registro.getWidth() * .25f), 30);
            direccionTitulo.setBorder(null);
            direccionTitulo.setEditable(false);

            JTextField direccion = new JTextField();
            direccion.setBounds(Math.round(registro.getWidth() * .5f), 300, Math.round(registro.getWidth() * .25f), 30);

            JTextField telefonoTitulo = new JTextField("Telefono");
            telefonoTitulo.setBounds(Math.round(registro.getWidth() * .25f), 350, Math.round(registro.getWidth() * .25f), 30);
            telefonoTitulo.setBorder(null);
            telefonoTitulo.setEditable(false);

            JTextField telefono = new JTextField();
            telefono.setBounds(Math.round(registro.getWidth() * .5f), 350, Math.round(registro.getWidth() * .25f), 30);

            JTextField categoriasTitulo = new JTextField("Categorias");
            categoriasTitulo.setBounds(Math.round(registro.getWidth() * .25f), 400, Math.round(registro.getWidth() * .25f), 30);
            categoriasTitulo.setBorder(null);
            categoriasTitulo.setEditable(false);

            JPanel categoriasPanel = new JPanel();
            categoriasPanel.setLayout(new GridLayout(0, 1));
            categoriasPanel.setBounds(Math.round(registro.getWidth() * .25f), 430, Math.round(registro.getWidth() * .5f), 200);
            categoriasPanel.setBorder(BorderFactory.createTitledBorder("Categorias"));

            JTextField[] c = new JTextField[5];

            for (int i = 0; i < c.length; i++) {
                c[i] = new JTextField();
            }
            c[0].setBounds(Math.round(registro.getWidth() * .25f), 430, Math.round(registro.getWidth() * .5f), 40);

            c[1].setBounds(Math.round(registro.getWidth() * .25f), 470, Math.round(registro.getWidth() * .5f), 40);

            c[2].setBounds(Math.round(registro.getWidth() * .25f), 510, Math.round(registro.getWidth() * .5f), 40);

            c[3].setBounds(Math.round(registro.getWidth() * .25f), 550, Math.round(registro.getWidth() * .5f), 40);

            c[4].setBounds(Math.round(registro.getWidth() * .25f), 590, Math.round(registro.getWidth() * .5f), 40);

            JCheckBox takeAway = new JCheckBox("Take Away");
            takeAway.setBounds(Math.round(registro.getWidth() * .25f), 650, Math.round(registro.getWidth() * .5f), 30);

            JCheckBox delivery = new JCheckBox("Delivery");
            delivery.setBounds(Math.round(registro.getWidth() * .25f), 700, Math.round(registro.getWidth() * .5f), 30);

            JTextField demoraTitle = new JTextField("Demora");
            demoraTitle.setBounds(Math.round(registro.getWidth() * .25f), 750, Math.round(registro.getWidth() * .25f), 30);
            demoraTitle.setBorder(null);
            demoraTitle.setEditable(false);

            JTextField demora = new JTextField();
            demora.setBounds(Math.round(registro.getWidth() * .5f), 750, Math.round(registro.getWidth() * .25f), 30);

            JTextField horarioTitle = new JTextField("Horario");
            horarioTitle.setBounds(Math.round(registro.getWidth() * .25f), 800, Math.round(registro.getWidth() * .25f), 30);
            horarioTitle.setBorder(null);
            horarioTitle.setEditable(false);

            JComboBox hA = new JComboBox(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
            hA.setBounds( Math.round(registro.getWidth() * .5f) + (Math.round(registro.getWidth() * .0625f)), 800, Math.round(registro.getWidth() * .0625f), 30);
            JComboBox mA = new JComboBox(new String[]{"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"});
            mA.setBounds( Math.round(registro.getWidth() * .5f) + (Math.round(registro.getWidth() * .0625f) * 2), 800, Math.round(registro.getWidth() * .0625f), 30);
            JComboBox hC = new JComboBox(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
            hC.setBounds( Math.round(registro.getWidth() * .5f) + (Math.round(registro.getWidth() * .0625f) * 3), 800, Math.round(registro.getWidth() * .0625f), 30);
            JComboBox mC = new JComboBox(new String[]{"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"});
            mC.setBounds( Math.round(registro.getWidth() * .5f) + (Math.round(registro.getWidth() * .0625f) * 4), 800, Math.round(registro.getWidth() * .0625f), 30);

            JTextField constoEnvioTitle = new JTextField("Costo Envio");
            constoEnvioTitle.setBounds(Math.round(registro.getWidth() * .25f), 850, Math.round(registro.getWidth() * .25f), 30);
            constoEnvioTitle.setBorder(null);
            constoEnvioTitle.setEditable(false);

            JTextField constoEnvio = new JTextField();
            constoEnvio.setBounds(Math.round(registro.getWidth() * .5f), 850, Math.round(registro.getWidth() * .25f), 30);

            JButton registrar = new JButton("Registrar");
            registrar.setBounds(Math.round(registro.getWidth() * .25f), 900, Math.round(registro.getWidth() * .5f), 30);
            registrar.addActionListener(e1 -> {
                registro.dispose();
                String[] s = new String[5];
                for (int i = 0; i < c.length; i++) {
                    if(!c[i].getText().equals(""))
                    {
                        s[i] = c[i].getText();
                    }
                }

                Horario h = new Horario(
                        Integer.parseInt(hA.getSelectedItem().toString()),
                        Integer.parseInt(mA.getSelectedItem().toString()),
                        Integer.parseInt(hC.getSelectedItem().toString()),
                        Integer.parseInt(mC.getSelectedItem().toString())
                );

                Restaurant r = app.agregarRestaurant(new Restaurant(
                                correo.getText().toLowerCase(Locale.ROOT).trim(),
                                passwordT.getText().trim(),
                                nombre.getText().trim(),
                                direccion.getText().trim(),
                                Integer.parseInt(telefono.getText().trim()),
                                s,
                                takeAway.isSelected(),
                                delivery.isSelected(),
                                Integer.parseInt(demora.getText().trim()),
                                h,
                                Integer.parseInt(constoEnvio.getText().trim())
                        )
                );
                hndl.guardarRestaurant(
                        r.getId(),
                        r.getMail(),
                        r.getContra(),
                        r.getNombre(),
                        r.getDireccion(),
                        r.getTelefono(),
                        r.getCategorias(),
                        r.getPuntuacion(),
                        r.getMenu(),
                        r.isTakeAway(),
                        r.isDelivery(),
                        r.getMediosDePago(),
                        r.getResenas(),
                        r.getPedidosPendientes(),
                        r.getDemora(),
                        r.getHorario(),
                        r.getCostoEnvio()
                );
                VentanaDueñoLocal v = new VentanaDueñoLocal(app, r);
            });

            mainPanel.add(nombreTitulo);
            mainPanel.add(nombre);
            mainPanel.add(direccionTitulo);
            mainPanel.add(direccion);
            mainPanel.add(passwordTitulo);
            mainPanel.add(passwordT);
            mainPanel.add(confirmarPasswordTitulo);
            mainPanel.add(confirmarPasswordT);
            mainPanel.add(telefonoTitulo);
            mainPanel.add(telefono);
            mainPanel.add(correoTitulo);
            mainPanel.add(correo);
            mainPanel.add(takeAway);
            mainPanel.add(delivery);
            mainPanel.add(demoraTitle);
            mainPanel.add(demora);
            mainPanel.add(horarioTitle);
            mainPanel.add(categoriasTitulo);
            mainPanel.add(hA);
            mainPanel.add(mA);
            mainPanel.add(hC);
            mainPanel.add(mC);
            mainPanel.add(constoEnvioTitle);
            mainPanel.add(constoEnvio);
            mainPanel.add(registrar);

            for (int i = 0; i < 5; i++) {
                mainPanel.add(c[i]);
            }

            registro.add(mainPanel);

            registro.setVisible(true);
        });


        body.add(usuarioText);
        body.add(usuario);
        body.add(passwordText);
        body.add(password);
        body.add(login);
        body.add(registrarse);

        panel.add(body);

        ventana.add(panel);

        ventana.setVisible(true);
    }
}
