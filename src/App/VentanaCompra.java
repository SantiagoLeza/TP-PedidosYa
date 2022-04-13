package App;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class VentanaCompra
{
    public VentanaCompra(PedidosYa app, Restaurant restaurante, Usuario usuario, float subtotal, ItemPedido[] items)
    {
        JFrame ventana = new JFrame("PedidosYa - Compra");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, ventana.getWidth(), 50);
        headerPanel.setBackground(new java.awt.Color(200, 0, 0));

        JTextField headerText = new JTextField("Confirmacion de compra");
        headerText.setEditable(false);
        headerText.setBounds(0, 0, headerPanel.getWidth(), headerPanel.getHeight());
        headerText.setHorizontalAlignment(JTextField.CENTER);
        headerText.setFont(new java.awt.Font("Arial", Font.BOLD, 24));
        headerText.setBackground(new java.awt.Color(0, 0, 0, 0));
        headerText.setBorder(null);

        headerPanel.add(headerText);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(null);
        bodyPanel.setBounds(0, 50, ventana.getWidth(), ventana.getHeight() - 50);

        JTextArea bodyText = new JTextArea();
        bodyText.setEditable(false);
        bodyText.setBounds(Math.round(bodyPanel.getWidth() * 0.25f), Math.round(bodyPanel.getHeight() * 0.25f), Math.round(bodyPanel.getWidth() * 0.5f), Math.round(bodyPanel.getHeight() / 3f));
        bodyText.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        bodyText.setBackground(new java.awt.Color(0, 0, 0, 0));
        bodyText.setBorder(null);
        float total = subtotal + restaurante.getCostoEnvio();
        bodyText.setText("Subtotal: " + String.format("%.2f", subtotal) + "\n" + "Costo de envio: " + String.format("%.2f", restaurante.getCostoEnvio()) + "\n" + "Total: " + String.format("%.2f", total));

        JTextField direccionTitle = new JTextField("Direccion");
        direccionTitle.setEditable(false);
        direccionTitle.setBounds(Math.round(bodyPanel.getWidth() * 0.25f), Math.round(bodyPanel.getHeight() / 3f) + 40, Math.round(bodyPanel.getWidth() * 0.5f), 10);
        direccionTitle.setBorder(null);

        JTextField direccionText = new JTextField(usuario.getDireccion());
        direccionText.setEditable(false);
        direccionText.setBorder(null);
        direccionText.setBounds(Math.round(ventana.getWidth() * 0.25f), Math.round(bodyPanel.getHeight() / 3f) + 50, Math.round(ventana.getWidth() * 0.5f), 30);

        JButton cambiarDireccionButton = new JButton("Cambiar direccion");
        cambiarDireccionButton.setBounds(Math.round(ventana.getWidth() * 0.75f), Math.round(bodyPanel.getHeight() / 3f) + 50, Math.round(ventana.getWidth() * 0.1f), 20);
        AtomicBoolean editando = new AtomicBoolean(false);
        cambiarDireccionButton.addActionListener(e -> {
            editando.set(!editando.get());
            if(editando.get())
            {
                direccionText.setEditable(true);
                direccionText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                direccionText.requestFocus();
                cambiarDireccionButton.setText("Confirmar");
            }
            else
            {
                direccionText.setEditable(false);
                usuario.setDireccion(direccionText.getText());
                direccionText.setBorder(null);
                cambiarDireccionButton.setText("Cambiar direccion");
                app.modificarUsuario(usuario);
            }

        });

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(Math.round(ventana.getWidth() * 0.25f), Math.round(ventana.getHeight() * 0.75f), Math.round(ventana.getWidth() * 0.5f), 30);
        confirmarButton.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        confirmarButton.setBackground(new java.awt.Color(200, 0, 0));
        confirmarButton.setForeground(new java.awt.Color(0, 0, 0));
        confirmarButton.addActionListener(e -> {
            ventana.dispose();
            Pedido p = new Pedido(
                    items,
                    restaurante.getCostoEnvio(),
                    usuario.getDireccion(),
                    total,
                    restaurante.getId(),
                    false
            );
            restaurante.agregarPedidoPendiente(p);
            app.modificarRestaurant(restaurante);

            Pedido[] aux = new Pedido[usuario.getPedidos().length + 1];
            for(int i = 0; i < usuario.getPedidos().length; i++)
            {
                aux[i] = usuario.getPedidos()[i];
            }
            aux[aux.length - 1] = p;
            usuario.setPedidos(aux);
            app.modificarUsuario(usuario);

            VentanaEspera ventanaEspera = new VentanaEspera(app, restaurante, usuario);
        });

        bodyPanel.add(bodyText);
        bodyPanel.add(direccionTitle);
        bodyPanel.add(direccionText);
        bodyPanel.add(cambiarDireccionButton);
        bodyPanel.add(confirmarButton);

        mainPanel.add(headerPanel);
        mainPanel.add(bodyPanel);

        ventana.add(mainPanel);

        ventana.setVisible(true);
    }
}
