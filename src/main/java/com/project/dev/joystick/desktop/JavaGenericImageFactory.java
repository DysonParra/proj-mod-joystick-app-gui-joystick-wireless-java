/*
 * @fileoverview {JavaGenericImageFactory} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {JavaGenericImageFactory} fue realizada el 31/07/2022.
 * @Dev - La primera version de {JavaGenericImageFactory} fue escrita por Dyson A. Parra T.
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
