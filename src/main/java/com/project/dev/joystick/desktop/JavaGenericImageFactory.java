/*
 * @fileoverview    {JavaGenericImageFactory} se encarga de realizar tareas específicas.
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

import com.project.dev.joystick.factory.GenericImageAbstractFactory;
import com.project.dev.joystick.graphic.GenericImage;

/**
 * TODO: Definición de {@code JavaGenericImageFactory}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class JavaGenericImageFactory extends GenericImageAbstractFactory {

    /**
     * FIXME: Definición de {@code JavaGenericImageFactory}.
     *
     */
    public JavaGenericImageFactory() {
        super();
    }

    /**
     * FIXME: Definición de {@code makeGenericImage}. Fabrica una imagen genérica con solo la ruta.
     *
     * @param path es la ruta de la imagen.
     * @return una imagen genérica.
     * @throws java.lang.Exception
     */
    @Override
    public GenericImage makeGenericImage(String path) throws Exception {
        return new JavaGenericImage(path);
    }
}
