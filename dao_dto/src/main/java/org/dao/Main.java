package org.dao;
import Formulario.interfaz;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de JFrame (ventana principal) con título "interfaz"
        JFrame frame = new JFrame("interfaz");

        // Establecer el contenido de la ventana como el panel principal de la interfaz
        frame.setContentPane(new interfaz().getPanelPrincipal());

        // Configurar la operación de cierre de la ventana para salir de la aplicación al cerrarla
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajustar automáticamente el tamaño de la ventana según su contenido
        frame.pack();

        // Hacer visible la ventana principal, permitiendo la interacción del usuario
        frame.setVisible(true);
    }
}
