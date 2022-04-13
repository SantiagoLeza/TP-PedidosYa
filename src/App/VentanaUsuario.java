package App;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class VentanaUsuario
{
    private JFrame ventana;

    public VentanaUsuario(PedidosYa app, Usuario usuario)
    {
        ventana = new JFrame("Pedidos Ya");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1024, 800);
        ventana.setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);

        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 1024, 50);
        header.setBackground(new java.awt.Color(51, 51, 51, 255));

        JTextField txtHeader = new JTextField("Pedidos Ya");
        txtHeader.setEditable(false);
        txtHeader.setForeground(new java.awt.Color(255, 255, 255, 255));
        txtHeader.setFont(new java.awt.Font("Arial", 1, 24));
        txtHeader.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtHeader.setBounds(0, 0, Math.round(header.getWidth()*0.75f), 50);

        JTextField username = new JTextField(usuario.getNombreYAp());
        username.setEditable(false);
        username.setForeground(new java.awt.Color(255, 255, 255, 255));
        username.setBackground(new java.awt.Color(0, 0, 0, 0));
        username.setBounds(Math.round(header.getWidth()*0.75f), 0, Math.round(header.getWidth()*0.15f), 50);


        ImageIcon logoutIcon = new ImageIcon(this.getClass().getResource("../Imagenes/logout.png"));
        JButton btnCerrarSesion = new JButton();
        btnCerrarSesion.setBounds(Math.round(header.getWidth()*0.90f), 0, 50, 50);
        btnCerrarSesion.setIcon(new ImageIcon(logoutIcon.getImage().getScaledInstance(btnCerrarSesion.getWidth(), btnCerrarSesion.getHeight(), java.awt.Image.SCALE_SMOOTH)));
        btnCerrarSesion.setBackground(new java.awt.Color(51, 51, 51, 255));
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.addActionListener(e -> {
            ventana.dispose();
            VentanaInicioSesion ventanaRegistro = new VentanaInicioSesion(app);
        });

        header.add(txtHeader);
        header.add(username);
        header.add(btnCerrarSesion);

        JPanel panelContenido = new JPanel();

        panelContenido.setLayout(new GridLayout(0, 2));

        for (int i = 0; i < app.cantRestaurants(); i++)
        {
            if(app.getRestaurantByIndex(i) != null)
            {
                JPanel panelRestaurante = new JPanel(new GridLayout(2, 1));
                panelRestaurante.setPreferredSize(new Dimension(300, 300));
                panelRestaurante.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                JLabel imgenRestaurante;

                ImageIcon icon;
                try
                {
                    icon = new ImageIcon(this.getClass().getResource("../Imagenes/Locales/" + app.getRestaurantByIndex(i).getNombre() + ".png"));
                }
                catch (Exception e)
                {
                    try
                    {
                        icon = new ImageIcon(this.getClass().getResource("../Imagenes/Locales/" + app.getRestaurantByIndex(i).getNombre() + ".jpg"));
                    }
                    catch (Exception ex)
                    {
                        icon = new ImageIcon(this.getClass().getResource("../Imagenes/default.png"));
                    }
                }
                icon = new ImageIcon(icon.getImage().getScaledInstance(400, 200, java.awt.Image.SCALE_SMOOTH));
                imgenRestaurante = new JLabel(icon);


                JPanel contenido = new JPanel(new GridLayout(2, 1));

                JTextField txtNombreRestaurante = new JTextField(app.getRestaurantByIndex(i).getNombre());
                txtNombreRestaurante.setFont(new java.awt.Font("Arial", Font.BOLD, 24));
                txtNombreRestaurante.setEditable(false);
                txtNombreRestaurante.setHorizontalAlignment(JTextField.CENTER);
                contenido.add(txtNombreRestaurante);

                ImageIcon starIcon = new ImageIcon(this.getClass().getResource("../Imagenes/star.png"));

                JButton btnVerRestaurante = new JButton("Ver");
                int finalI = i;
                btnVerRestaurante.addActionListener(e -> {
                    ventana.dispose();
                    VentanaRestaurante ventanaRestaurante = new VentanaRestaurante(app, app.getRestaurantByIndex(finalI), usuario);
                });

                JPanel panelEstrellas = new JPanel(new GridLayout(1, 6));
                panelEstrellas.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                for (int j = 0; j < 5; j++)
                {
                    JLabel labelEstrella = new JLabel();
                    if(j < app.getRestaurantByIndex(i).getPuntuacion())
                    {
                        labelEstrella.setIcon(new ImageIcon(starIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
                    }
                    panelEstrellas.add(labelEstrella);

                }
                panelEstrellas.add(btnVerRestaurante);

                contenido.add(panelEstrellas);

                panelRestaurante.add(imgenRestaurante);
                panelRestaurante.add(contenido);

                panelContenido.add(panelRestaurante);
            }
        }
        panelContenido.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setBounds(50, 50, ventana.getWidth()-100, ventana.getHeight() - 50);

        panelPrincipal.add(header);
        panelPrincipal.add(scrollPane);

        ventana.add(panelPrincipal);

        ventana.setVisible(true);
    }
}
