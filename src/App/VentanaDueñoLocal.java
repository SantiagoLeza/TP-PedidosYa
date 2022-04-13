package App;

import javax.swing.*;
import java.awt.*;

public class VentanaDueñoLocal extends JFrame
{
    JFrame ventana;
    JPanel panel;

    public VentanaDueñoLocal(PedidosYa app, Restaurant restaurant)
    {
        ventana = new JFrame("Ventana Dueño Local");
        ventana.setSize(1024, 768);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);

        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 1024, 100);
        header.setBackground(new java.awt.Color(0, 153, 153));

        JTextField restaurante = new JTextField(restaurant.getNombre());
        restaurante.setBounds(0, 0, Math.round(header.getWidth() * 0.75f), header.getHeight());
        restaurante.setBackground(new java.awt.Color(0, 0, 0, 0));
        restaurante.setFont(new java.awt.Font("Arial", Font.BOLD, 50));
        restaurante.setEditable(false);

        ImageIcon logoutIcon = new ImageIcon(this.getClass().getResource("../Imagenes/logout.png"));
        JButton logout = new JButton(new ImageIcon(logoutIcon.getImage().getScaledInstance(Math.round(header.getWidth() * 0.25f), header.getHeight(), java.awt.Image.SCALE_SMOOTH)));
        logout.setBounds(Math.round(header.getWidth() * 0.75f), 0, Math.round(header.getWidth() * 0.25f), header.getHeight());
        logout.setBackground(new java.awt.Color(0, 0, 0, 0));
        logout.setBorder(null);
        logout.setFocusPainted(false);
        logout.setContentAreaFilled(false);

        logout.addActionListener(e -> {
            ventana.dispose();
            VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(app);
        });

        header.add(restaurante);
        header.add(logout);

        panel.add(header);

        JPanel body = new JPanel();
        body.setLayout(null);
        body.setBounds(0, 100, ventana.getWidth(), ventana.getHeight() - 100);

        JPanel opciones = new JPanel();
        opciones.setLayout(new GridLayout(0, 2));
        opciones.setBounds(25, 25, ventana.getWidth() - 50, body.getHeight() - 50);
        opciones.setBackground(new java.awt.Color(200, 200 , 200));

        JButton modificarDatos = new JButton("Modificar Datos");
        modificarDatos.addActionListener(e -> {

            JFrame ventanaModificarNombre = new JFrame("Modificar Datos");
            ventanaModificarNombre.setSize(400, 800);
            ventanaModificarNombre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaModificarNombre.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(null);

            JTextField nombreTitulo = new JTextField("Nombre");
            nombreTitulo.setBounds(0, 5, 195, 20);
            nombreTitulo.setEditable(false);
            nombreTitulo.setHorizontalAlignment(JTextField.RIGHT);

            JTextField nuevoNombre = new JTextField(restaurant.getNombre());
            nuevoNombre.setBounds(200, 5, 175, 20);

            JTextField direccionTitulo = new JTextField("Dirección");
            direccionTitulo.setBounds(0, 30, 195, 20);
            direccionTitulo.setEditable(false);
            direccionTitulo.setHorizontalAlignment(JTextField.RIGHT);

            JTextField nuevoDireccion = new JTextField(restaurant.getDireccion());
            nuevoDireccion.setBounds(200, 30, 175, 20);

            JTextField telefonoTitulo = new JTextField("Teléfono");
            telefonoTitulo.setBounds(0, 55, 195, 20);
            telefonoTitulo.setEditable(false);
            telefonoTitulo.setHorizontalAlignment(JTextField.RIGHT);

            JTextField nuevoTelefono = new JTextField(String.valueOf(restaurant.getTelefono()));
            nuevoTelefono.setBounds(200, 55, 175, 20);

            JTextField correoTitulo = new JTextField("Correo");
            correoTitulo.setBounds(0, 80, 195, 20);
            correoTitulo.setEditable(false);
            correoTitulo.setHorizontalAlignment(JTextField.RIGHT);

            JTextField nuevoCorreo = new JTextField(restaurant.getMail());
            nuevoCorreo.setBounds(200, 80, 175, 20);

            JTextField takeAwayTitulo = new JTextField("Take Away");
            takeAwayTitulo.setBounds(0, 105, 195, 20);
            takeAwayTitulo.setEditable(false);
            takeAwayTitulo.setHorizontalAlignment(JTextField.RIGHT);

            JTextField nuevoTakeAway = new JTextField(restaurant.isTakeAway() ? "Si" : "No");
            nuevoTakeAway.setBounds(200, 105, 175, 20);

            JTextField deliveryTitulo = new JTextField("Delivery");
            deliveryTitulo.setBounds(0, 130, 195, 20);
            deliveryTitulo.setEditable(false);
            deliveryTitulo.setHorizontalAlignment(JTextField.RIGHT);

            JTextField nuevoDelivery = new JTextField(restaurant.isDelivery() ? "Si" : "No");
            nuevoDelivery.setBounds(200, 130, 175, 20);

            JTextField demoraTitulo = new JTextField("Demora");
            demoraTitulo.setBounds(0, 155, 195, 20);
            demoraTitulo.setEditable(false);
            demoraTitulo.setHorizontalAlignment(JTextField.RIGHT);

            JTextField nuevaDemora = new JTextField(String.valueOf(restaurant.getDemora()));
            nuevaDemora.setBounds(200, 155, 175, 20);

            JTextField categoriasTitulo = new JTextField("Categorías");
            categoriasTitulo.setBounds(0, 180, ventana.getWidth(), 20);
            for (int j = 0; j < restaurant.getCategorias().length; j++)
            {
                 if(restaurant.getCategorias()[j] != null)
                 {
                     JPanel categoria = new JPanel(new GridLayout(1, 2));
                     categoria.setBounds(0, 200 + j * 30, 400, 30);
                     JTextField categoriaText = new JTextField(restaurant.getCategorias()[j]);
                     JButton borrarCategoria = new JButton("X");
                     int finalJ = j;
                     borrarCategoria.addActionListener(e1 -> {
                         categoria.setVisible(false);
                         String[] cat = restaurant.getCategorias();
                         cat[finalJ] = null;
                         restaurant.setCategorias(cat);
                     });
                     categoria.add(categoriaText);
                     categoria.add(borrarCategoria);
                     mainPanel.add(categoria);
                 }
                 else if(restaurant.getCategorias()[j] == null)
                 {
                     JButton agregarCategoria = new JButton("Agregar Categoría");
                     agregarCategoria.setBounds(0, 200 + j * 30, 400, 30);
                     int finalJ1 = j;
                     agregarCategoria.addActionListener(e1 -> {
                         JPanel categoria = new JPanel(new GridLayout(1, 2));
                         categoria.setBounds(0, 200 + finalJ1 * 30, 400, 30);
                         agregarCategoria.setVisible(false);
                         JTextField nuevaCategoria = new JTextField();

                         JButton aceptarCategoria = new JButton("Aceptar");
                         aceptarCategoria.addActionListener(e2 -> {
                             categoria.setVisible(false);
                             String[] cat = restaurant.getCategorias();
                             cat[finalJ1] = nuevaCategoria.getText();
                             restaurant.setCategorias(cat);
                             ventana.dispose();
                             ventanaModificarNombre.dispose();
                             VentanaDueñoLocal v = new VentanaDueñoLocal(app, restaurant);
                         });

                         categoria.add(nuevaCategoria);
                         categoria.add(aceptarCategoria);
                         mainPanel.add(categoria);
                     });
                     mainPanel.add(agregarCategoria);
                 }
            }

            JComboBox metodosPago = new JComboBox(new String[]{"Efectivo", "Tarjeta", "Ambos"});
            metodosPago.setBounds(Math.round(ventanaModificarNombre.getWidth() / 3f), 450, Math.round(ventanaModificarNombre.getWidth() / 3f), 30);
            if(restaurant.getMediosDePago().length > 1)
            {
                metodosPago.setSelectedItem("Ambos");
            }
            else
            {
                metodosPago.setSelectedItem(restaurant.getMediosDePago()[0]);
            }

            mainPanel.add(metodosPago);

            JButton aceptar = new JButton("Aceptar");
            aceptar.setBounds(50, 500, 100, 50);
            aceptar.addActionListener(e2 -> {
                if(nuevoNombre.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(ventanaModificarNombre, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(nuevoDireccion.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(ventanaModificarNombre, "La dirección no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(nuevoTelefono.getText().equals("") || !nuevoTelefono.getText().matches("[0-9]+"))
                {
                    JOptionPane.showMessageDialog(ventanaModificarNombre, "El teléfono no puede estar vacío o contener caracteres no numericos", "Error", JOptionPane.ERROR_MESSAGE);

                }
                else if(nuevoCorreo.getText().equals("") || !nuevoCorreo.getText().contains("@") || !nuevoCorreo.getText().contains(".com"))
                {
                    JOptionPane.showMessageDialog(ventanaModificarNombre, "El correo no es valido", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(!nuevoTakeAway.getText().equals("Si") && !nuevoTakeAway.getText().equals("No"))
                {
                    JOptionPane.showMessageDialog(ventanaModificarNombre, "El campo de take away debe ser Si o No", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(!nuevoDelivery.getText().equals("Si") && !nuevoDelivery.getText().equals("No"))
                {
                    JOptionPane.showMessageDialog(ventanaModificarNombre, "El campo de delivery debe ser Si o No", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(nuevaDemora.getText().equals("") || !nuevaDemora.getText().matches("[0-9]+"))
                {
                    JOptionPane.showMessageDialog(ventanaModificarNombre, "La demora debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    restaurant.setNombre(nuevoNombre.getText());
                    restaurant.setDireccion(nuevoDireccion.getText());
                    restaurant.setTelefono(Integer.parseInt(nuevoTelefono.getText()));
                    restaurant.setMail(nuevoCorreo.getText());
                    restaurant.setTakeAway(nuevoTakeAway.getText().equals("Si"));
                    restaurant.setDelivery(nuevoDelivery.getText().equals("Si"));
                    restaurant.setDemora(Integer.parseInt(nuevaDemora.getText()));

                    if(!metodosPago.getSelectedItem().equals("Ambos"))
                    {
                        restaurant.setMediosDePago(new String[]{metodosPago.getSelectedItem().toString()});
                    }
                    else
                    {
                        restaurant.setMediosDePago(new String[]{"Efectivo", "Tarjeta"});
                    }

                    app.modificarRestaurant(restaurant);
                    ventanaModificarNombre.dispose();
                    ventana.dispose();
                    VentanaDueñoLocal ventanaDueñoLocal = new VentanaDueñoLocal(app, restaurant);
                }

            });

            mainPanel.add(nombreTitulo);
            mainPanel.add(nuevoNombre);
            mainPanel.add(direccionTitulo);
            mainPanel.add(nuevoDireccion);
            mainPanel.add(telefonoTitulo);
            mainPanel.add(nuevoTelefono);
            mainPanel.add(correoTitulo);
            mainPanel.add(nuevoCorreo);
            mainPanel.add(takeAwayTitulo);
            mainPanel.add(nuevoTakeAway);
            mainPanel.add(deliveryTitulo);
            mainPanel.add(nuevoDelivery);
            mainPanel.add(demoraTitulo);
            mainPanel.add(nuevaDemora);

            mainPanel.add(aceptar);

            ventanaModificarNombre.add(mainPanel);
            ventanaModificarNombre.setVisible(true);

            app.modificarRestaurant(restaurant);
        });
        opciones.add(modificarDatos);

        JButton administrarProductos = new JButton("Administrar Productos");
        administrarProductos.addActionListener(e -> {
            JFrame ventanaAdministrarProductos = new JFrame("Administrar Productos");
            ventanaAdministrarProductos.setSize(1024, 768);
            ventanaAdministrarProductos.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(null);

            JPanel contenedorProductos = new JPanel(new GridLayout(0, 2));

            for (Plato producto : restaurant.getMenu())
            {
                if(producto != null)
                {
                    JPanel productoPanel = new JPanel(new GridLayout(0, 1));
                    productoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                    productoPanel.setPreferredSize(new Dimension((Math.round(ventanaAdministrarProductos.getWidth() / 2f))-20, 300));

                    JTextField nombre = new JTextField("Nombre: " + producto.getNombrePlato());
                    nombre.setBorder(null);
                    nombre.setEditable(false);

                    JTextField precio = new JTextField("Precio: " + producto.getPrecio());
                    precio.setBorder(null);
                    precio.setEditable(false);

                    JTextField unidad = new JTextField("Tipo de unidad: " + producto.getTipoUnidad());
                    unidad.setBorder(null);
                    unidad.setEditable(false);

                    JTextField stock = new JTextField("Stock: " + producto.getStock());
                    stock.setBorder(null);
                    stock.setEditable(false);

                    JTextField demora = new JTextField("Demora: " + producto.getTiempoDemora());
                    demora.setBorder(null);
                    demora.setEditable(false);

                    productoPanel.add(nombre);
                    productoPanel.add(precio);
                    productoPanel.add(unidad);
                    productoPanel.add(stock);
                    productoPanel.add(demora);

                    JPanel botones = new JPanel(new GridLayout(1, 2));
                    JButton modificar = new JButton("Modificar");
                    modificar.addActionListener(e1 -> {

                        String nombreViejo = producto.getNombrePlato();

                        JFrame ventanaModificarProducto = new JFrame("Modificar Producto");
                        ventanaModificarProducto.setSize(300, 300);
                        ventanaModificarProducto.setLocationRelativeTo(null);

                        JPanel mainPanelModificar = new JPanel();
                        mainPanelModificar.setLayout(null);

                        JTextField tituloNombre = new JTextField("Nombre: ");
                        tituloNombre.setBorder(null);
                        tituloNombre.setEditable(false);
                        tituloNombre.setHorizontalAlignment(JTextField.RIGHT);
                        tituloNombre.setBounds(10, 10, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        JTextField nombreModificar = new JTextField(producto.getNombrePlato());
                        nombreModificar.setEditable(true);
                        nombreModificar.setHorizontalAlignment(JTextField.LEFT);
                        nombreModificar.setBounds(Math.round(ventanaModificarProducto.getWidth()/2f), 10, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        JTextField tituloPrecio = new JTextField("Precio: ");
                        tituloPrecio.setBorder(null);
                        tituloPrecio.setEditable(false);
                        tituloPrecio.setHorizontalAlignment(JTextField.RIGHT);
                        tituloPrecio.setBounds(10, 40, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        JTextField precioModificar = new JTextField(String.valueOf(producto.getPrecio()));
                        precioModificar.setEditable(true);
                        precioModificar.setHorizontalAlignment(JTextField.LEFT);
                        precioModificar.setBounds(Math.round(ventanaModificarProducto.getWidth()/2f), 40, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        JTextField tituloUnidad = new JTextField("Tipo de unidad: ");
                        tituloUnidad.setBorder(null);
                        tituloUnidad.setEditable(false);
                        tituloUnidad.setHorizontalAlignment(JTextField.RIGHT);
                        tituloUnidad.setBounds(10, 70, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        JTextField unidadModificar = new JTextField(producto.getTipoUnidad());
                        unidadModificar.setEditable(true);
                        unidadModificar.setHorizontalAlignment(JTextField.LEFT);
                        unidadModificar.setBounds(Math.round(ventanaModificarProducto.getWidth()/2f), 70, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        JTextField tituloStock = new JTextField("Stock: ");
                        tituloStock.setBorder(null);
                        tituloStock.setEditable(false);
                        tituloStock.setHorizontalAlignment(JTextField.RIGHT);
                        tituloStock.setBounds(10, 100, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        JTextField stockModificar = new JTextField(String.valueOf(producto.getStock()));
                        stockModificar.setEditable(true);
                        stockModificar.setHorizontalAlignment(JTextField.LEFT);
                        stockModificar.setBounds(Math.round(ventanaModificarProducto.getWidth()/2f), 100, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        JTextField tituloDemora = new JTextField("Demora: ");
                        tituloDemora.setBorder(null);
                        tituloDemora.setEditable(false);
                        tituloDemora.setHorizontalAlignment(JTextField.RIGHT);
                        tituloDemora.setBounds(10, 130, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        JTextField demoraModificar = new JTextField(String.valueOf(producto.getTiempoDemora()));
                        demoraModificar.setEditable(true);
                        demoraModificar.setHorizontalAlignment(JTextField.LEFT);
                        demoraModificar.setBounds(Math.round(ventanaModificarProducto.getWidth()/2f), 130, Math.round(ventanaModificarProducto.getWidth()/2f)-20, 20);

                        mainPanelModificar.add(tituloNombre);
                        mainPanelModificar.add(nombreModificar);
                        mainPanelModificar.add(tituloPrecio);
                        mainPanelModificar.add(precioModificar);
                        mainPanelModificar.add(tituloUnidad);
                        mainPanelModificar.add(unidadModificar);
                        mainPanelModificar.add(tituloStock);
                        mainPanelModificar.add(stockModificar);
                        mainPanelModificar.add(tituloDemora);
                        mainPanelModificar.add(demoraModificar);

                        JButton botonModificar = new JButton("Modificar");
                        botonModificar.setBounds(Math.round(ventanaModificarProducto.getWidth()/2f)-100, 160, 100, 20);
                        botonModificar.addActionListener(e2 -> {
                            for (int i = 0; i < restaurant.getMenu().length; i++) {
                                if (restaurant.getMenu()[i] != null)
                                {
                                    if (restaurant.getMenu()[i].getNombrePlato().equals(nombreViejo))
                                    {
                                        restaurant.getMenu()[i].setNombrePlato(nombreModificar.getText());
                                        restaurant.getMenu()[i].setPrecio(Float.parseFloat(precioModificar.getText()));
                                        restaurant.getMenu()[i].setTipoUnidad(unidadModificar.getText());
                                        restaurant.getMenu()[i].setStock(Integer.parseInt(stockModificar.getText()));
                                        restaurant.getMenu()[i].setTiempoDemora(Integer.parseInt(demoraModificar.getText()));

                                        app.modificarRestaurant(restaurant);

                                        ventanaModificarProducto.dispose();
                                        ventanaAdministrarProductos.dispose();
                                        ventana.dispose();
                                        VentanaDueñoLocal a = new VentanaDueñoLocal(app, restaurant);
                                    }
                                }
                            }
                        });

                        mainPanelModificar.add(botonModificar);

                        ventanaModificarProducto.add(mainPanelModificar);
                        ventanaModificarProducto.setVisible(true);
                    });

                    JButton eliminar = new JButton("Eliminar");
                    eliminar.addActionListener(e1 -> {
                        Plato[] menu = restaurant.getMenu();
                        for (int i = 0; i < restaurant.getMenu().length; i++) {
                            if(menu[i] != null)
                            {
                                if(producto.getNombrePlato().equals(menu[i].getNombrePlato()))
                                {
                                    menu[i] = null;
                                    restaurant.setMenu(menu);
                                    app.modificarRestaurant(restaurant);
                                    ventanaAdministrarProductos.dispose();
                                    ventana.dispose();
                                    VentanaDueñoLocal vdl = new VentanaDueñoLocal(app, restaurant);
                                }
                            }
                        }
                    });

                    botones.add(modificar);
                    botones.add(eliminar);

                    productoPanel.add(botones);

                    contenedorProductos.add(productoPanel);
                }
            }

            JButton agregarProducto = new JButton("Agregar Producto");
            agregarProducto.addActionListener(f -> {
                JFrame ventanaAgregarProducto = new JFrame("Agregar Producto");
                ventanaAgregarProducto.setSize(400, 800);
                ventanaAgregarProducto.setLocationRelativeTo(null);

                JPanel mainPanelAgregarProduto = new JPanel();
                mainPanelAgregarProduto.setLayout(null);

                JTextField nombreTitulo = new JTextField("Nombre");
                nombreTitulo.setBounds(10, 10, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);
                nombreTitulo.setHorizontalAlignment(JTextField.RIGHT);
                nombreTitulo.setEditable(false);

                JTextField nuevoNombre = new JTextField();
                nuevoNombre.setBounds(Math.round(ventanaAgregarProducto.getWidth()/2f), 10, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);

                JTextField precioTitulo = new JTextField("Precio");
                precioTitulo.setBounds(10, 40, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);
                precioTitulo.setHorizontalAlignment(JTextField.RIGHT);
                precioTitulo.setEditable(false);

                JTextField nuevoPrecio = new JTextField();
                nuevoPrecio.setBounds(Math.round(ventanaAgregarProducto.getWidth()/2f), 40, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);

                JTextField unidadTitulo = new JTextField("Unidad");
                unidadTitulo.setBounds(10, 70, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);
                unidadTitulo.setHorizontalAlignment(JTextField.RIGHT);
                unidadTitulo.setEditable(false);

                JTextField nuevaUnidad = new JTextField();
                nuevaUnidad.setBounds(Math.round(ventanaAgregarProducto.getWidth()/2f), 70, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);

                JTextField cantidadTitulo = new JTextField("Stock");
                cantidadTitulo.setBounds(10, 100, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);
                cantidadTitulo.setHorizontalAlignment(JTextField.RIGHT);
                cantidadTitulo.setEditable(false);

                JTextField nuevaCantidad = new JTextField();
                nuevaCantidad.setBounds(Math.round(ventanaAgregarProducto.getWidth()/2f), 100, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);

                JTextField tiempoDemoraTitulo = new JTextField("Demora");
                tiempoDemoraTitulo.setBounds(10, 130, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);
                tiempoDemoraTitulo.setHorizontalAlignment(JTextField.RIGHT);
                tiempoDemoraTitulo.setEditable(false);

                JTextField nuevoTiempoDemora = new JTextField();
                nuevoTiempoDemora.setBounds(Math.round(ventanaAgregarProducto.getWidth()/2f), 130, Math.round(ventanaAgregarProducto.getWidth()/2f) - 20, 20);

                JButton aceptar = new JButton("Aceptar");
                aceptar.setBounds((Math.round(ventanaAgregarProducto.getWidth()/2f))-50, 175, 100, 30);
                aceptar.addActionListener(g -> {
                    ventanaAgregarProducto.dispose();

                    restaurant.agregarPlato(
                            new Plato(
                                    nuevoNombre.getText(),
                                    Float.parseFloat(nuevoPrecio.getText()),
                                    nuevaUnidad.getText(),
                                    Integer.parseInt(nuevoTiempoDemora.getText()),
                                    Integer.parseInt(nuevaCantidad.getText())
                            )
                    );

                    app.modificarRestaurant(restaurant);

                    ventana.dispose();
                    ventanaAdministrarProductos.dispose();
                    ventanaAgregarProducto.dispose();
                    VentanaDueñoLocal ventanaDueñoLocal = new VentanaDueñoLocal(app, restaurant);
                });

                mainPanelAgregarProduto.add(nombreTitulo);
                mainPanelAgregarProduto.add(nuevoNombre);
                mainPanelAgregarProduto.add(precioTitulo);
                mainPanelAgregarProduto.add(nuevoPrecio);
                mainPanelAgregarProduto.add(unidadTitulo);
                mainPanelAgregarProduto.add(nuevaUnidad);
                mainPanelAgregarProduto.add(cantidadTitulo);
                mainPanelAgregarProduto.add(nuevaCantidad);
                mainPanelAgregarProduto.add(tiempoDemoraTitulo);
                mainPanelAgregarProduto.add(nuevoTiempoDemora);

                mainPanelAgregarProduto.add(aceptar);

                ventanaAgregarProducto.add(mainPanelAgregarProduto);
                ventanaAgregarProducto.setVisible(true);
            });

            contenedorProductos.add(agregarProducto);

            JButton aceptar = new JButton("Aceptar");
            aceptar.addActionListener(e1 -> {
                ventanaAdministrarProductos.dispose();
                ventana.dispose();

                app.modificarRestaurant(restaurant);

                VentanaDueñoLocal ventanaDueñoLocal = new VentanaDueñoLocal(app, restaurant);
            });
            aceptar.setBounds(Math.round(ventanaAdministrarProductos.getWidth() * 0.5f) - 50, 600, 100, 50);

            JScrollPane scrollPane = new JScrollPane(contenedorProductos);
            scrollPane.setBounds(10, 5, ventanaAdministrarProductos.getWidth()-20, 590);
            mainPanel.add(scrollPane);
            mainPanel.add(aceptar);

            ventanaAdministrarProductos.add(mainPanel);

            ventanaAdministrarProductos.setVisible(true);
        });
        opciones.add(administrarProductos);

        JButton verPedidosPendientes = new JButton("Ver pedidos pendientes");
        verPedidosPendientes.addActionListener(e -> {
            JFrame pedidosPendientes = new JFrame("Pedidos pendientes");
            pedidosPendientes.setSize(750, 500);
            pedidosPendientes.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(null);
            mainPanel.setBackground(Color.WHITE);

            JPanel pedidos = new JPanel(new GridLayout(0,1));

            for( int i = 0; i < restaurant.getPedidosPendientes().length; i++ )
            {
                Pedido pedido = restaurant.getPedidosPendientes()[i];
                if(pedido != null)
                {
                    JPanel pedidoPanel = new JPanel(new GridLayout(1, 0));
                    pedidoPanel.setPreferredSize(new Dimension(pedidos.getWidth(), 50));

                    JTextField destino = new JTextField(pedido.getDestino());
                    destino.setEditable(false);

                    pedidoPanel.add(destino);

                    JPanel cant = new JPanel(new GridLayout(0, 1));
                    for(ItemPedido it : pedido.getListaDePlatos())
                    {
                        if(it != null)
                        {
                            JPanel p = new JPanel(new GridLayout(1, 0));
                            JTextField nombre = new JTextField(it.getNombre());
                            JTextField cantidad = new JTextField(it.getCant() + "");
                            nombre.setEditable(false);
                            cantidad.setEditable(false);
                            p.add(nombre);
                            p.add(cantidad);
                            cant.add(p);
                        }
                    }
                    pedidoPanel.add(cant);

                    JButton aceptar = new JButton("Aceptar");
                    int finalI = i;
                    aceptar.addActionListener(e1 -> {
                        Pedido[] pp = restaurant.getPedidosPendientes();
                        pp[finalI] = null;
                        pedidos.remove(pedidoPanel);
                        pedidos.revalidate();
                    });

                    pedidoPanel.add(aceptar);

                    pedidos.add(pedidoPanel);
                }
            }

            JScrollPane scrollPane = new JScrollPane(pedidos);
            scrollPane.setBounds(25,25,pedidosPendientes.getWidth()-50,pedidosPendientes.getHeight()-100);

            mainPanel.add(scrollPane);
            pedidosPendientes.add(mainPanel);
            pedidosPendientes.setVisible(true);
        });
        opciones.add(verPedidosPendientes);

        body.add(opciones);

        panel.add(body);

        ventana.add(panel);
        ventana.setVisible(true);
    }
}
