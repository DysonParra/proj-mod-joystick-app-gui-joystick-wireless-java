/*
 * @fileoverview    {JavaGenericImage}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.joystick.desktop;

import com.project.dev.joystick.graphic.GenericImage;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * TODO: Description of {@code JavaGenericImage}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class JavaGenericImage extends GenericImage {

    protected ImageIcon imageIcon = null;                                                 // Imagen cualquiera.

    /**
     * TODO: Description of {@code JavaGenericImage}.
     *
     * @param path es la ruta de la imagen.
     * @throws java.lang.Exception
     */
    public JavaGenericImage(String path) throws Exception {
        super(path);
        pathChanged();
    }

    /**
     * FIXME: Description of {@code updateGraphic}. Actualiza los datos de la implementación de
     * imagen genérica en java.
     */
    private void updateGraphic() {
        this.graphic = new ImageIcon(this.imageIcon.getImage().getScaledInstance(this.width, this.height, Image.SCALE_DEFAULT));
    }

    /**
     * FIXME: Description of {@code pathChanged}. Invocado cuando cambia la ruta de la implementación
     * de imagen genérica en java.
     *
     * @throws Exception
     */
    private void pathChanged() throws Exception {
        try {
            this.imageIcon = new ImageIcon(GenericImage.class.getResource(this.path));
            this.width = imageIcon.getIconWidth();
            this.height = imageIcon.getIconHeight();
            updateGraphic();
        } catch (Exception e) {
            throw new Exception("Cannot use the image '" + this.path + "' the image exist?");
        }
    }

    /**
     * FIXME: Description of {@code onPathChange}. Invocado cuando se cambia la ruta de la imagen
     * genérica.
     *
     * @throws Exception
     */
    @Override
    public void onPathChange() throws Exception {
        pathChanged();
    }

    /**
     * FIXME: Description of {@code onWidthChange}. Invocado cuando se cambia el ancho de la imagen
     * genérica.
     */
    @Override
    public void onWidthChange() {
        updateGraphic();
    }

    /**
     * FIXME: Description of {@code onHeightChange}. Invocado cuando se cambia el alto de la imagen
     * genérica.
     */
    @Override
    public void onHeightChange() {
        updateGraphic();
    }

    /**
     * FIXME: Description of {@code onPositionXChange}. Invocado cuando se cambian las coordenadas en
     * X de la imagen genérica.
     */
    @Override
    public void onPositionXChange() {

    }

    /**
     * FIXME: Description of {@code onPositionYChange}. Invocado cuando se cambian las coordenadas en
     * Y de la imagen genérica.
     */
    @Override
    public void onPositionYChange() {

    }
}
