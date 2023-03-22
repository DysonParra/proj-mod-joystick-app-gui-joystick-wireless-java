/*
 * @fileoverview    {JavaGraphicJoystick}
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
package com.project.dev.joystick.desktop;

import com.project.dev.joystick.button.GenericButton;
import com.project.dev.joystick.button.GenericButtonGraphicListener;
import com.project.dev.joystick.graphic.GenericImage;
import com.project.dev.joystick.graphic.GraphicJoystick;
import com.project.dev.joystick.name.generic.GenericJoystick;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * TODO: Definición de {@code JavaGraphicJoystick}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class JavaGraphicJoystick extends JLayeredPane implements GraphicJoystick, KeyListener, ActionListener {

    private int width;                                                          // Ancho del joystick.
    private int height;                                                         // Alto del joystick.

    private final GenericJoystick joystick;                                     // Joystick parámetro.

    private final GenericImage backgroundImage;                                 // Imagen de fondo del joystick.
    private final JLabel backgroundComponent;                                   // Componente con el fondo del joystick.

    private final GenericButton[] joystickButtons;                              // Botones del joystick.
    private GenericButton button;                                               // Ccada botón encontrado en el joystick.
    private JLabel buttonComponet;                                              // Cada botón que irá como componente de la vista.

    /**
     * TODO: Definición de {@code JavaGraphicJoystick}.
     *
     * @param genericJoystick es el joystick que tendrá el JLayeredPane.
     */
    public JavaGraphicJoystick(GenericJoystick genericJoystick) {
        joystick = genericJoystick;                                                                         // Obtiene el joystick parámetro.
        joystickButtons = joystick.getButtons();

        backgroundComponent = new JLabel();                                                                 // Inicializa componente que tendrá el fondo del joystick.
        backgroundImage = joystick.getBackground();
        width = backgroundImage.getWidth();                                                                 // Obtiene el ancho de la imagen de fondo.
        height = backgroundImage.getHeight();                                                               // Obtiene el alto de la imagen de fondo.

        backgroundComponent.setIcon((Icon) backgroundImage.getGraphic());                              // Asigna la imagen de fondo del joystick al componente.
        backgroundComponent.setSize(width, height);                                                         // Asigna tamaño al componete con la imagen de fondo.
        backgroundComponent.setLocation(0, 0);
        this.setSize(width, height);                                                                         // Asigna tamaño al componete actual.

        for (int i = 0; i < joystick.getButtonQuantity(); i++) {                                                 // Recorre los botones del joystick.
            button = joystickButtons[i];                                                                    // Obtiene el botón actual.

            buttonComponet = new JLabel();                                                                  // Crea un nuevo componente para ponerlo en la vista.

            if (!joystick.getType().equals(GenericJoystick.JOYSTICK_TYPE_SERVER)) {                          // Si el joystick no es un servidor.
                buttonComponet.addMouseListener(new MouseAdapter() {                                        // Agrega evento de mouse al componente actual.
                    final GenericButton actualButton = button;

                    @Override
                    public void mousePressed(MouseEvent ev) {
                        actualButton.touchButton();
                    }

                    @Override
                    public void mouseReleased(MouseEvent ev) {
                        actualButton.unTouchButton();
                    }
                });
            }

            GenericButtonGraphicListener<JLabel> graphicButtonListener = new GenericButtonGraphicListener<JLabel>(buttonComponet) {
                @Override
                public void onButtonStateChanged(byte newState, GenericImage newImage) {
                    if (newImage != null) {
                        view.setIcon((Icon) newImage.getGraphic());
                        view.setLocation(newImage.getPositionX(), newImage.getPositionY());
                        view.setSize(newImage.getWidth(), newImage.getHeight());
                    }
                }
            };

            button.setOnGraphicListener(graphicButtonListener);
            graphicButtonListener.onButtonStateChanged(BUTTON_RELEASED, button.getButtonImage(BUTTON_RELEASED));

            this.add(buttonComponet, 0);
        }

        this.add(backgroundComponent, joystick.getButtonQuantity());
        joystick.setOnGraphicListener(this);
    }

    /**
     * FIXME: Definición de {@code getJoystick}. Obtiene el joystick del joystick gráfico.
     *
     * @return es el joystick del gráfico.
     */
    @Override
    public final GenericJoystick getJoystick() {
        return joystick;
    }

    /**
     * FIXME: Definición de {@code getJoystickWidth}. Obtiene ancho del joystick asociado al
     * joystick gráfico.
     *
     * @return el ancho del joystick gráfico.
     */
    @Override
    public final int getJoystickWidth() {
        return width;
    }

    /**
     * FIXME: Definición de {@code getJoystickHeight}. Obtiene el alto del joystick asociado al
     * joystick gráfico.
     *
     * @return el alto del joystick gráfico.
     */
    @Override
    public final int getJoystickHeight() {
        return height;
    }

    /**
     * FIXME: Definición de {@code onJoystickScale}. Invocado cuando el joystick escala sus
     * dimensiones.
     */
    @Override
    public final void onJoystickScale() {
        backgroundComponent.setIcon((Icon) backgroundImage.getGraphic());
        width = backgroundImage.getWidth();                                                                   // Obtiene el ancho de la imagen de fondo.
        height = backgroundImage.getHeight();                                                               // Obtiene el alto de la imagen de fondo.
        backgroundComponent.setSize(width, height);
        this.setSize(width, height);
    }

    /**
     * FIXME: Definición de {@code scaleJoystick}. Cambia la resolución de las imágenes de los
     * botones y el joystick.
     *
     * @param percent es el porcentaje de escalado del joystick.
     * @throws java.lang.Exception
     */
    @Override
    public final void scaleJoystick(double percent) throws Exception {
        joystick.scaleImages(percent);
    }

    /**
     * FIXME: Definición de {@code scaleJoystickWidth}. Cambia la resolución de las imágenes de los
     * botones y el joystick.
     *
     * @param newWidth es el nuevo ancho del joystick.
     * @throws java.lang.Exception
     */
    @Override
    public final void scaleJoystickWidth(int newWidth) throws Exception {
        double percent = newWidth / (double) width;
        scaleJoystick(percent);
    }

    /**
     * FIXME: Definición de {@code scaleJoystickHeight}. Cambia la resolución de las imágenes de los
     * botones y el joystick.
     *
     * @param newHeight es el nuevo alto del joystick.
     * @throws java.lang.Exception
     */
    @Override
    public final void scaleJoystickHeight(int newHeight) throws Exception {
        double percent = newHeight / (double) height;
        scaleJoystick(percent);
    }

    /**
     * FIXME: Definición de {@code addButtonKeyEvents}. Asocia teclas del teclado con los botones
     * del joystick.
     *
     * @throws java.lang.Exception
     */
    public final void addButtonKeyEvents() throws Exception {
        if (!joystick.getType().equals(GenericJoystick.JOYSTICK_TYPE_SERVER)) {  // Si el joystick no es un servidor.
            this.setFocusable(true);
            this.addKeyListener(this);
        } else
            throw new Exception("Cannot assign key events for servers");
    }

    /**
     * FIXME: Definición de {@code keyTyped}. Evalúa la tecla acabada de tocar.
     *
     * @param ke es la tecla tocada.
     */
    @Override
    public final void keyTyped(KeyEvent ke) {

    }

    /**
     * FIXME: Definición de {@code keyPressed}. Evalúa la tecla mientras esté oprimida.
     *
     * @param ke es la tecla oprimida.
     */
    @Override
    public final void keyPressed(KeyEvent ke) {
        int c = ke.getKeyChar();
        int key = ke.getKeyCode();
        for (GenericButton joystickButton : joystickButtons)
            if (joystickButton.getKeyEvent() == key)
                joystickButton.touchButton();
    }

    /**
     * FIXME: Definición de {@code keyReleased}. Evalúa la tecla soltada.
     *
     * @param ke es la tecla soltada.
     */
    @Override
    public final void keyReleased(KeyEvent ke) {
        int c = ke.getKeyChar();
        int key = ke.getKeyCode();
        for (GenericButton joystickButton : joystickButtons)
            if (joystickButton.getKeyEvent() == key)
                joystickButton.unTouchButton();
    }

    /**
     * TODO: Definición de {@code actionPerformed}.
     *
     * @param ae
     */
    @Override
    public final void actionPerformed(ActionEvent ae) {

    }
}
