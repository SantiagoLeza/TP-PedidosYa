package App;

import javax.swing.*;

public class VentanaRegistro extends JFrame
{
    private PedidosYa app;

    private JFrame ventanaLogIn;
    private JPanel panelPrincipal;
    private JTextField tituloVentana;
    private JTextField direccionTitulo;
    private JTextField direccion;
    private JTextField emailTitulo;
    private JTextField email;
    private JTextField contrasenaTitulo;
    private JPasswordField contrasena;
    private JTextField confirmarContrasenaTitulo;
    private JPasswordField confirmarContrasena;
    private JTextField nombreYApellidoTitulo;
    private JTextField nombreYApellido;
    private JTextField numeroTelefonoTitulo;
    private JTextField numeroTelefono;

    private JButton loginButton;

    private Usuario nuevo;

    public VentanaRegistro(PedidosYa app)
    {
        this.app = app;

        ventanaLogIn = new JFrame("Registro");
        panelPrincipal = new JPanel(null);

        ventanaLogIn.setSize(500, 720);
        ventanaLogIn.setLocationRelativeTo(null);
        ventanaLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelPrincipal.setLayout(null);

        tituloVentana = new JTextField("Ingrese sus datos");
        tituloVentana.setHorizontalAlignment(JTextField.CENTER);
        tituloVentana.setEditable(false);
        tituloVentana.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 100, Math.round(ventanaLogIn.getWidth()*0.5f), 30);

        direccionTitulo = new JTextField("Direccion");
        direccionTitulo.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 150, Math.round(ventanaLogIn.getWidth()*0.5f), 30);
        direccion = new JTextField();
        direccion.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 170, Math.round(ventanaLogIn.getWidth()*0.5f), 30);

        emailTitulo = new JTextField("Email");
        emailTitulo.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 200, Math.round(ventanaLogIn.getWidth()*0.5f), 30);
        email = new JTextField();
        email.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 220, Math.round(ventanaLogIn.getWidth()*0.5f), 30);

        contrasenaTitulo = new JTextField("Contrasena");
        contrasenaTitulo.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 250, Math.round(ventanaLogIn.getWidth()*0.5f), 30);
        contrasena = new JPasswordField();
        contrasena.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 270, Math.round(ventanaLogIn.getWidth()*0.5f), 30);

        confirmarContrasenaTitulo = new JTextField("Confirmar contrasena");
        confirmarContrasenaTitulo.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 300, Math.round(ventanaLogIn.getWidth()*0.5f), 30);
        confirmarContrasena = new JPasswordField();
        confirmarContrasena.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 320, Math.round(ventanaLogIn.getWidth()*0.5f), 30);

        nombreYApellidoTitulo = new JTextField("Nombre y Apellido");
        nombreYApellidoTitulo.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 350, Math.round(ventanaLogIn.getWidth()*0.5f), 30);
        nombreYApellido = new JTextField();
        nombreYApellido.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 370, Math.round(ventanaLogIn.getWidth()*0.5f), 30);

        numeroTelefonoTitulo = new JTextField("Numero de Telefono");
        numeroTelefonoTitulo.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 400, Math.round(ventanaLogIn.getWidth()*0.5f), 30);
        numeroTelefono = new JTextField();
        numeroTelefono.setBounds(Math.round(ventanaLogIn.getWidth()*0.25f), 420, Math.round(ventanaLogIn.getWidth()*0.5f), 30);

        direccionTitulo.setEditable(false);
        emailTitulo.setEditable(false);
        contrasenaTitulo.setEditable(false);
        confirmarContrasenaTitulo.setEditable(false);
        nombreYApellidoTitulo.setEditable(false);
        numeroTelefonoTitulo.setEditable(false);

        loginButton = new JButton("Registrar");
        loginButton.setBounds(Math.round(ventanaLogIn.getWidth()*(0.75f/2)), 500, Math.round(ventanaLogIn.getWidth()*0.25f), 30);
        loginButton.addActionListener(e -> {
            if(!email.getText().contains("@") || !email.getText().contains("."))
            {
                JOptionPane.showMessageDialog(null, "El email ingresado no es valido");
            }
            else
            {
                if(!contrasena.getText().equals(confirmarContrasena.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden");
                }
                else
                {
                    if(!numeroTelefono.getText().matches("[0-9]+"))
                    {
                        JOptionPane.showMessageDialog(null, "El numero de telefono ingresado no es valido");
                    }
                    else
                    {
                        if(!nombreYApellido.getText().matches("[a-zA-Z]+") && !nombreYApellido.getText().contains(" "))
                        {
                            JOptionPane.showMessageDialog(null, "El nombre y apellido ingresado no es valido");
                        }
                        else
                        {
                            int numerodeTelefono = 0;
                            try
                            {
                                numerodeTelefono = Integer.parseInt(numeroTelefono.getText());
                            }
                            catch (NumberFormatException e1)
                            {
                                JOptionPane.showMessageDialog(null, "Error al convertir el numero de telefono");
                                this.dispose();
                            }
                            nuevo = app.registrarUsuario(direccion.getText().trim(), email.getText().trim(), contrasena.getText().trim(), nombreYApellido.getText().trim(), numerodeTelefono);
                            ventanaLogIn.dispose();
                            VentanaUsuario vu = new VentanaUsuario(app, nuevo);
                        }
                    }
                }
            }

        });

        panelPrincipal.add(tituloVentana);
        panelPrincipal.add(direccionTitulo);
        panelPrincipal.add(direccion);
        panelPrincipal.add(emailTitulo);
        panelPrincipal.add(email);
        panelPrincipal.add(contrasenaTitulo);
        panelPrincipal.add(contrasena);
        panelPrincipal.add(confirmarContrasenaTitulo);
        panelPrincipal.add(confirmarContrasena);
        panelPrincipal.add(nombreYApellidoTitulo);
        panelPrincipal.add(nombreYApellido);
        panelPrincipal.add(numeroTelefonoTitulo);
        panelPrincipal.add(numeroTelefono);
        panelPrincipal.add(loginButton);

        ventanaLogIn.add(panelPrincipal);

        ventanaLogIn.setVisible(true);

    }

    public Usuario getNuevo()
    {
        return nuevo;
    }
}
