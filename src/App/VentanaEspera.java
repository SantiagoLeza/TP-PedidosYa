package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class VentanaEspera
{
    public VentanaEspera(PedidosYa app, Restaurant restaurant, Usuario usuario)
    {
        JFrame frame = new JFrame("Enviando");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JTextField t1 = new JTextField("El repartidor esta a:");
        t1.setBounds(Math.round(frame.getWidth() / 3f), 150, Math.round(frame.getWidth() / 3f), 50);
        t1.setBorder(null);
        t1.setHorizontalAlignment(JTextField.CENTER);
        t1.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        t1.setEditable(false);

        JTextField t2 = new JTextField("minutos");
        t2.setBounds(Math.round(frame.getWidth() / 3f), 250, Math.round(frame.getWidth() / 3f), 50);
        t2.setBorder(null);
        t2.setHorizontalAlignment(JTextField.CENTER);
        t2.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        t2.setEditable(false);

        JButton btn = new JButton("Aceptar");
        btn.setBounds(Math.round(frame.getWidth() / 3f), 350, Math.round(frame.getWidth() / 3f), 50);
        btn.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        btn.addActionListener(e -> {
            frame.dispose();
        });
        btn.setVisible(false);

        JButton btn2 = new JButton("Reseñar");
        btn2.setBounds(Math.round(frame.getWidth() / 3f), 425, Math.round(frame.getWidth() / 3f), 50);
        btn2.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        btn2.addActionListener(e -> {
            frame.dispose();
            JFrame reseña = new JFrame("Reseña");
            reseña.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            reseña.setSize(400, 600);
            reseña.setLocationRelativeTo(null);

            JPanel mainPanel2 = new JPanel();
            mainPanel2.setLayout(null);

            JTextField textArea = new JTextField();
            textArea.setBounds(25, 100, reseña.getWidth()-50, 200);

            mainPanel2.add(textArea);

            JButton[] estrellas = new JButton[5];

            ImageIcon estrellaNegra = new ImageIcon(getClass().getResource("../Imagenes/star.png"));
            estrellaNegra = new ImageIcon(estrellaNegra.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

            ImageIcon estrellaAmarilla = new ImageIcon(getClass().getResource("../Imagenes/yellowstar.png"));
            estrellaAmarilla = new ImageIcon(estrellaAmarilla.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

            AtomicInteger p = new AtomicInteger();
            for (int i = 0; i < estrellas.length; i++)
            {
                estrellas[i] = new JButton();
                estrellas[i].setBackground(new Color(0, 0, 0, 0));
                estrellas[i].setBorder(null);
                estrellas[i].setBounds(75 + (i * 50), 300, 50, 50);
                estrellas[i].setIcon(estrellaNegra);
                ImageIcon finalEstrellaAmarilla = estrellaAmarilla;
                ImageIcon finalEstrellaNegra = estrellaNegra;
                int finalI = i;
                estrellas[i].addActionListener(e2 -> {
                    p.set(finalI + 1);
                    for (int j = 0; j < 5; j++)
                    {
                        if(j <= finalI)
                        {
                            estrellas[j].setIcon(finalEstrellaAmarilla);
                        }
                        else
                        {
                            estrellas[j].setIcon(finalEstrellaNegra);
                        }
                    }
                });
                mainPanel2.add(estrellas[i]);
            }

            JButton btn3 = new JButton("Enviar");
            btn3.setBounds(25, 350, reseña.getWidth()-50, 50);
            btn3.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
            btn3.addActionListener(e2 -> {
                reseña.dispose();
                Puntuacion puntuacion = new Puntuacion(
                        usuario.getNombreYAp(),
                        p.get(),
                        textArea.getText(),
                        ""
                );
                restaurant.agregarReseña(puntuacion);
                app.modificarRestaurant(restaurant);
            });
            mainPanel2.add(btn3);

            reseña.add(mainPanel2);
            reseña.setVisible(true);
        });
        btn2.setVisible(false);

        mainPanel.add(t1);
        mainPanel.add(t2);
        mainPanel.add(btn);
        mainPanel.add(btn2);

        JTextField textField = new JTextField();
        textField.setText(String.valueOf(restaurant.getDemora()));
        textField.setBounds(0, 200, frame.getWidth(), 50);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(null);
        textField.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        textField.setEditable(false);

        mainPanel.add(textField);
        frame.add(mainPanel);

        Timer cuentaAtras = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(textField.getText().equals("0") || textField.getText().equals("El repartidor esta en la puerta"))
                {
                    textField.setText("El repartidor esta en la puerta");
                    t1.setVisible(false);
                    t2.setVisible(false);
                    btn.setVisible(true);
                    btn2.setVisible(true);
                }
                else
                {
                    textField.setText(String.valueOf(Integer.parseInt(textField.getText()) - 1));
                }
            }
        });

        cuentaAtras.start();
        frame.setVisible(true);
    }
}
