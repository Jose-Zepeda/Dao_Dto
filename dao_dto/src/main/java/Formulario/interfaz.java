package Formulario;

import conexion.clsConexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class interfaz {

    private JPanel panelPrincipal;
    private JTextField textUsuario;
    private JTextField textContraseña;
    private JButton iniciarSesionButton;
    private JLabel validar;
    private StringBuilder contraseña = new StringBuilder();
    private String contra = "";
    private String usuario = "";
    clsConexion conexion = new clsConexion();

    public interfaz() {
        // Agrega un KeyListener para el campo de contraseña
        textContraseña.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                contra += e.getKeyChar();
                if (c == '\b') { // Comprueba si es la tecla de retroceso (backspace)
                    if (contraseña.length() > 0) {
                        // Elimina el último carácter de la contraseña si la longitud es mayor que cero
                        contraseña.deleteCharAt(contraseña.length() - 1);
                    }
                } else {
                    // Agrega el carácter ingresado a la contraseña
                    contraseña.append(c);
                }

                // Enmascara la contraseña y actualiza el campo de contraseña
                textContraseña.setText(encryptPassword(contraseña.toString()));

                e.consume(); // Evita que el carácter se muestre en el campo
            }
        });

        // Agrega un ActionListener para el botón "Iniciar Sesión"
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario = textUsuario.getText();
                String resultado = "";
                try {
                    resultado = conexion.asignarValores(usuario, contra);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                // Compara el resultado y muestra un mensaje en el JLabel "validar"
                if ("true".equals(resultado)) {
                    validar.setText("Bienvenido al sistema " + usuario);
                } else {
                    validar.setText("Usuario o contraseña incorrectos");
                }
            }
        });
    }

    // Método para enmascarar la contraseña con asteriscos
    private String encryptPassword(String password) {
        int length = password.length();
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // Crea una cadena de asteriscos del mismo tamaño que la contraseña
            encrypted.append("*");
        }
        return encrypted.toString();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}
