/*
 * @fileoverview    {JoystickFrame}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementación realizada.
 * @version 2.0     Documentación agregada.
 */
package com.project.dev.wirelessjoystick.desktop.frame;

import com.project.dev.joystick.button.GenericButton;
import com.project.dev.joystick.button.GenericButtonActionListener;
import com.project.dev.joystick.desktop.JavaJoystickViewSetter;
import com.project.dev.joystick.exception.JoystickClientConnectionRefusedException;
import com.project.dev.joystick.exception.UnknownJoystickTypeException;
import com.project.dev.joystick.factory.GraphicJoystickFactory;
import com.project.dev.joystick.graphic.GraphicJoystick;
import com.project.dev.joystick.listener.JoystickServerListener;
import com.project.dev.joystick.name.generic.GenericJoystick;
import com.project.dev.joystick.name.generic.type.GenericJoystickClient;
import com.project.dev.joystick.name.generic.type.GenericJoystickLocal;
import com.project.dev.joystick.name.generic.type.GenericJoystickServer;
import com.project.dev.joystick.name.nintendo.NintendoJoystick;
import com.project.dev.joystick.name.poly.PolyJoystick;
import com.project.dev.tray.setter.TrayIconSetter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import static com.project.dev.joystick.name.generic.GenericJoystick.JOYSTICK_TYPE_CLIENT;
import static com.project.dev.joystick.name.generic.GenericJoystick.JOYSTICK_TYPE_LOCAL;
import static com.project.dev.joystick.name.generic.GenericJoystick.JOYSTICK_TYPE_SERVER;

/**
 * TODO: Definición de {@code JoystickFrame}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class JoystickFrame extends javax.swing.JFrame {

    public static String JOYSTICK_FORM_NORMAL = "Normal";
    public static String JOYSTICK_FORM_NATIVE = "Native";

    /*
     * Variables locales.
     */
    private String joystickType = "";                                   // Indica el tipo de joystick a utilizar.
    private String joystickName = "";                                   // Indica el nombre del joystick a utilizar.
    private String joystickForm = "";                                   // Indica la forma del joystick a utilizar.
    private String ipAddress = "";                                      // Indica la ip del servidor.
    private int serverPort = 0;                                         // Indica el puerto de conexión al servidor.
    private GraphicJoystick graphic = null;                             // Indica el joystick grafico asociado a la ventana.
    private final JFrame frame = this;                                  // Referencia a la ventana.

    /**
     * TODO: Definición de {@code getJoystickType}.
     *
     * @return
     */
    public String getJoystickType() {
        return joystickType;
    }

    /**
     * TODO: Definición de {@code setJoystickType}.
     *
     * @param joystickType
     */
    public void setJoystickType(String joystickType) {
        this.joystickType = joystickType;
    }

    /**
     * TODO: Definición de {@code getJoystickName}.
     *
     * @return
     */
    public String getJoystickName() {
        return joystickName;
    }

    /**
     * TODO: Definición de {@code setJoystickName}.
     *
     * @param joystickName
     */
    public void setJoystickName(String joystickName) {
        this.joystickName = joystickName;
    }

    /**
     * TODO: Definición de {@code getJoystickForm}.
     *
     * @return
     */
    public String getJoystickForm() {
        return joystickForm;
    }

    /**
     * TODO: Definición de {@code setJoystickForm}.
     *
     * @param joystickForm
     */
    public void setJoystickForm(String joystickForm) {
        this.joystickForm = joystickForm;
    }

    /**
     * TODO: Definición de {@code getIpAddress}.
     *
     * @return
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * TODO: Definición de {@code setIpAddress}.
     *
     * @param ipAddress
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * TODO: Definición de {@code getServerPort}.
     *
     * @return
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * TODO: Definición de {@code setServerPort}.
     *
     * @param serverPort
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * TODO: Definición de {@code JoystickFrame}.
     *
     */
    public JoystickFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * FIXME: Definición de {@code startFrame}. Inicializa el frame con la información obtenida del
     * frame anterior.
     */
    public void startFrame() {
        JavaJoystickViewSetter viewSetter = new JavaJoystickViewSetter(this);
        GraphicJoystickFactory graphicFactory = new GraphicJoystickFactory(viewSetter);

        try {
            graphic = graphicFactory.makeGraphicJoystick(joystickType, joystickName, ipAddress, serverPort);
            this.setIconImage(((ImageIcon) (graphic.getJoystick().getIconImage().getGraphic())).getImage());

            GenericJoystick joystick = graphic.getJoystick();
            switch (joystickType) {                                                     // Evalúa el tipo de joystick.
                case JOYSTICK_TYPE_CLIENT:                                              // Cliente.
                    final GenericJoystickClient client = (GenericJoystickClient) joystick;
                    client.removeButtonActionListeners();
                    client.setOnJoystickClientListener(() -> {
                        new Thread(() -> {
                            JOptionPane.showMessageDialog(null, "No es posible comunicarse con el servidor.");
                        }).start();
                    });
                    this.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            client.disconnectToServer();
                        }
                    });
                    break;                                                              // Sale del case.

                case JOYSTICK_TYPE_SERVER:                                              // Servidor.
                    final GenericJoystickServer server = (GenericJoystickServer) joystick;
                    server.removeButtonActionListeners();
                    server.setOnJoystickServerListener(new JoystickServerListener() {
                        @Override
                        public void onClientConnected() {
                            new Thread(() -> {
                                JOptionPane.showMessageDialog(frame, "Se ha conectado un cliente.");
                            }).start();
                        }

                        @Override
                        public void onClientDisconnected() {
                            new Thread(() -> {
                                JOptionPane.showMessageDialog(frame, "Se ha desconectado un cliente.");
                            }).start();
                        }
                    });
                    this.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            server.stopServer();
                        }
                    });
                    // Si se indicó servidor con forma nativa.
                    if (joystickForm.equals(JOYSTICK_FORM_NATIVE))
                        for (final GenericButton button : server.getButtons()) {
                            JLabel buttonView = (JLabel) button.getOnGraphicListener().getView();
                            buttonView.addMouseListener(new MouseAdapter() {
                                final GenericButton actualButton = button;

                                @Override
                                public void mousePressed(MouseEvent ev) {
                                    ButtonActionSetterFrame buttonActionSetterFrame = new ButtonActionSetterFrame();
                                    buttonActionSetterFrame.setButton(button);
                                    buttonActionSetterFrame.startFrame();
                                    buttonActionSetterFrame.setVisible(true);
                                }

                                @Override
                                public void mouseReleased(MouseEvent ev) {

                                }
                            });
                        }
                    break;                                                              // Sale del case.

                case JOYSTICK_TYPE_LOCAL:                                               // Local.
                    GenericJoystickLocal local = (GenericJoystickLocal) joystick;

                    switch (local.getName()) {
                        case PolyJoystick.JOYSTICK_NAME:
                            local.getButton(PolyJoystick.BUTTON_CIRCLE_NAME).setOnActionListener(new GenericButtonActionListener() {
                                String name = "◯";

                                @Override
                                public void onReleased() {
                                    System.out.println(name + " Released");
                                }

                                @Override
                                public void onTyped() {
                                    System.out.println(name + " Typed");
                                }

                                @Override
                                public void onPressed() {
                                    System.out.println(name + " Pressed");
                                }

                                @Override
                                public void onUnpressed() {
                                    System.out.println(name + "Unpressed");
                                }
                            });

                            break;

                        case NintendoJoystick.JOYSTICK_NAME:
                            break;
                    }

                    break;                                                              // Sale del case.
            }

            // Agrega la forma al titulo del joystick.
            this.setTitle(this.getTitle() + " (" + joystickForm + ")");

            // Agrega trayIcon al frame.
            TrayIconSetter.setTrayIconToFrame(frame);

        } catch (JoystickClientConnectionRefusedException ex) {
            dispose();
        } catch (UnknownJoystickTypeException ex) {
            Logger.getLogger(WirelessJoystickFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(WirelessJoystickFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * Entrada principal del sistema.
     *
     * @param args argumentos de la linea de comandos.
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (javax.swing.UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JoystickFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new JoystickFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
