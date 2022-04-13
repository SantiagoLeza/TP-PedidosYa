package App;

import javax.swing.*;
import java.awt.*;

public class VentanaRestaurante
{
    Plato[] carrito;

    public VentanaRestaurante(PedidosYa app, Restaurant restaurant, Usuario usuario)
    {
        carrito = new Plato[0];

        JFrame frame = new JFrame("PedidosYa");
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, frame.getWidth(), 50);
        header.setBackground(Color.red);

        JTextField titulo = new JTextField(restaurant.getNombre());
        titulo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, .4f)));
        titulo.setEditable(false);
        titulo.setBounds(0, 0, Math.round(frame.getWidth() * .65f), 50);
        titulo.setBackground(new Color(0, 0, 0, 0));
        titulo.setFont(new Font("Arial", Font.BOLD, 30));

        header.add(titulo);

        JTextField direccion = new JTextField(usuario.getDireccion());
        direccion.setHorizontalAlignment(JTextField.CENTER);
        direccion.setEditable(false);
        direccion.setBounds(Math.round(frame.getWidth() * .65f), 0, Math.round(frame.getWidth() * .25f), 50);
        direccion.setBackground(new Color(0, 0, 0, 0));
        direccion.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, .4f)));

        header.add(direccion);

        ImageIcon carritoIcono = new ImageIcon(getClass().getResource("/Imagenes/carrito.png"));
        carritoIcono = new ImageIcon(carritoIcono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton carritoBoton = new JButton(carritoIcono);

        carritoBoton.setBounds(Math.round(frame.getWidth() * .90f), 0, Math.round(frame.getWidth() * .1f), 50);
        carritoBoton.setBackground(new Color(0, 0, 0, 0));
        carritoBoton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, .4f)));
        carritoBoton.setFocusPainted(false);
        carritoBoton.setContentAreaFilled(false);

        carritoBoton.addActionListener(e -> {

            if(carrito.length == 0)
            {
                JOptionPane.showMessageDialog(null, "No hay nada en el carrito");
            }
            else
            {
                float subtotal = 0;

                JFrame carritoFrame = new JFrame("Carrito");
                carritoFrame.setSize(400, 500);
                carritoFrame.setLocationRelativeTo(null);
                carritoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel carritoPanel = new JPanel();
                carritoPanel.setLayout(null);

                JPanel items = new JPanel(new GridLayout(0, 1));

                JScrollPane itemsScroll = new JScrollPane(items);
                itemsScroll.setBounds(25, 10, carritoFrame.getWidth()-50, carritoFrame.getHeight() - 150);

                ItemPedido[] itemsPedidos = new ItemPedido[0];

                for (Plato plato : carrito)
                {
                    if (plato != null)
                    {
                        if(itemsPedidos == null)
                        {
                            itemsPedidos = new ItemPedido[1];
                            itemsPedidos[0] = new ItemPedido(plato.getNombrePlato(), 1, plato.getPrecio());
                        }
                        else
                        {
                            boolean encontrado = false;
                            for (ItemPedido i : itemsPedidos)
                            {
                                if (i.getNombre().equals(plato.getNombrePlato()))
                                {
                                    i.setCant(i.getCant() + 1);
                                    encontrado = true;
                                }
                            }

                            if(!encontrado)
                            {
                                ItemPedido[] aux = new ItemPedido[itemsPedidos.length + 1];
                                for (int i = 0; i < itemsPedidos.length; i++)
                                {
                                    aux[i] = itemsPedidos[i];
                                }
                                aux[aux.length - 1] = new ItemPedido(plato.getNombrePlato(), 1, plato.getPrecio());
                                itemsPedidos = aux;
                            }
                        }
                    }
                }

                for (int j = 0; j < itemsPedidos.length; j++){
                    subtotal += itemsPedidos[j].getPrecio() * itemsPedidos[j].getCant();

                    JPanel item = new JPanel();
                    item.setPreferredSize(new Dimension(itemsScroll.getWidth()-20, 50));
                    item.setLayout(new GridLayout(1, 3));

                    JTextField nombre = new JTextField(itemsPedidos[j].getNombre());
                    nombre.setHorizontalAlignment(JTextField.LEFT);
                    nombre.setEditable(false);

                    JTextField cantidad = new JTextField(String.valueOf(itemsPedidos[j].getCant()));
                    cantidad.setHorizontalAlignment(JTextField.CENTER);
                    cantidad.setEditable(false);

                    JTextField precio = new JTextField("$" + itemsPedidos[j].getPrecio() * itemsPedidos[j].getCant());
                    precio.setHorizontalAlignment(JTextField.RIGHT);
                    precio.setEditable(false);

                    item.add(nombre);
                    item.add(cantidad);
                    item.add(precio);

                    items.add(item);
                }

                carritoPanel.add(itemsScroll);

                JPanel subtotalPanel = new JPanel();
                subtotalPanel.setLayout(new GridLayout(1, 2));
                subtotalPanel.setBounds(25, carritoFrame.getHeight() - 150, carritoFrame.getWidth()-50, 50);

                JLabel subtotalLabel = new JLabel("Subtotal:  ");
                subtotalLabel.setHorizontalAlignment(JLabel.RIGHT);
                subtotalLabel.setFont(new Font("Arial", Font.BOLD, 15));
                subtotalPanel.add(subtotalLabel);

                JTextField subtotalText = new JTextField("$" + subtotal);
                subtotalText.setHorizontalAlignment(JTextField.RIGHT);
                subtotalText.setEditable(false);
                subtotalText.setBorder(null);
                subtotalText.setFont(new Font("Arial", Font.BOLD, 15));
                subtotalPanel.add(subtotalText);

                carritoPanel.add(subtotalPanel);

                JButton comprar = new JButton("Comprar");
                comprar.setBounds(Math.round(carritoFrame.getWidth() / 3f), carritoFrame.getHeight() - 100, Math.round(carritoFrame.getWidth() / 3f), 25);
                float finalSubtotal = subtotal;
                ItemPedido[] finalItemsPedidos = itemsPedidos;
                comprar.addActionListener(e2 -> {
                    carritoFrame.dispose();
                    for(Plato p : carrito)
                    {
                        p.setStock(p.getStock() - 1);
                    }
                    VentanaCompra vc = new VentanaCompra(app, restaurant, usuario, finalSubtotal, finalItemsPedidos);
                    frame.dispose();
                    carritoFrame.dispose();
                });

                carritoPanel.add(comprar);

                carritoFrame.add(carritoPanel);
                carritoFrame.setVisible(true);
            }
        });

        header.add(carritoBoton);

        JPanel body = new JPanel();
        body.setBounds(0, 50, frame.getWidth(), frame.getHeight() - 50);

        JPanel panelItems = new JPanel(new GridLayout(0, 1));

        for (Plato p : restaurant.getMenu())
        {
            if(p != null)
            {
                JPanel panelItem = new JPanel(new GridLayout(1, 3));
                panelItem.setBorder(BorderFactory.createLineBorder(Color.black));
                panelItem.setPreferredSize(new Dimension(Math.round(frame.getWidth() * .75f), 100));

                JTextField nombre = new JTextField(p.getNombrePlato());
                nombre.setBorder(BorderFactory.createLineBorder(Color.gray));
                nombre.setEditable(false);

                JPanel infoItem = new JPanel(new GridLayout(1, 2));
                infoItem.setBorder(null);

                JTextField precio = new JTextField("$" + p.getPrecio());
                precio.setHorizontalAlignment(JTextField.CENTER);
                precio.setBorder(null);
                precio.setEditable(false);

                JTextField demora = new JTextField("Demora: " + (p.getTiempoDemora() + restaurant.getDemora() )+ " minutos");
                demora.setHorizontalAlignment(JTextField.RIGHT);
                demora.setBorder(null);
                demora.setEditable(false);

                infoItem.add(precio);
                infoItem.add(demora);

                JPanel botonPanel = new JPanel(new GridLayout(1, 3));
                botonPanel.setBorder(null);

                JButton boton = new JButton("+");
                boton.addActionListener(e -> {
                    Plato[] nuevoCarrito = new Plato[carrito.length + 1];
                    System.arraycopy(carrito, 0, nuevoCarrito, 0, carrito.length);
                    nuevoCarrito[carrito.length] = p;
                    carrito = nuevoCarrito;
                });

                botonPanel.add(new JLabel(""));
                botonPanel.add(new JLabel(""));
                botonPanel.add(boton);

                panelItem.add(nombre);
                panelItem.add(infoItem);
                panelItem.add(botonPanel);

                panelItems.add(panelItem);
            }
        }

        JScrollPane items = new JScrollPane(panelItems);
        items.setBounds(0, 50, frame.getWidth(), frame.getHeight() - 50);
        body.add(items);

        mainPanel.add(header);
        mainPanel.add(body);

        frame.add(mainPanel);

        frame.setVisible(true);
    }
}
