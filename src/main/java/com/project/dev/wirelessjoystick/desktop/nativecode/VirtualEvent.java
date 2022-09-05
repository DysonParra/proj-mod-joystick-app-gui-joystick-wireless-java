/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.project.dev.wirelessjoystick.desktop.nativecode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * TODO: Definición de {@code VirtualEvent}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class VirtualEvent {

    public static int VK_MOUSE_CLIC_EVENT_LEFT = 1;
    public static int VK_MOUSE_CLIC_EVENT_RIGHT = 2;
    public static int VK_MOUSE_CLIC_EVENT_MIDDLE = 4;
    public static int VK_MOUSE_CLIC_EVENT_LEFT_DOUBLE = 7;

    public static int VK_MOUSE_MOVE_EVENT_LEFT = 10;
    public static int VK_MOUSE_MOVE_EVENT_RIGHT = 20;
    public static int VK_MOUSE_MOVE_EVENT_UP = 30;
    public static int VK_MOUSE_MOVE_EVENT_DOWN = 40;

    private static boolean linkedDll = true;
    private static String linkErrorMessage = "";

    /**
     * TODO: Definición de {@code isLinkedDll}.
     *
     */
    public static boolean isLinkedDll() {
        return linkedDll;
    }

    /**
     * TODO: Definición de {@code getLinkErrorMessage}.
     *
     */
    public static String getLinkErrorMessage() {
        return linkErrorMessage;
    }

    /**
     * FIXME: Definición de {@code runVirtualKeyEvent}.
     * Simula eventos de teclado en C.
     *
     * @param eventCode es el evento de teclado entendido por C.
     */
    public native void runVirtualKeyEvent(int eventCode);

    /**
     * FIXME: Definición de {@code runVirtualMouseClicEvent}.
     * Simula eventos de clic del mouse en C.
     *
     * @param eventCode es el evento de clic del mouse entendido por C.
     */
    public native void runVirtualMouseClicEvent(int eventCode);

    /**
     * FIXME: Definición de {@code runVirtualMouseMoveEvent}.
     * Simula eventos de mover mouse en C.
     *
     * @param eventCode     es el evento de mover mouse entendido por C.
     * @param pixelQuantity es la cantidad de píxeles que se moverá el mouse.
     */
    public native void runVirtualMouseMoveEvent(int eventCode, int pixelQuantity);

    /**
     * Carga la biblioteca de C en memoria.
     */
    static {
        String libName = "VirtualEvent";
        String libFormat = ".dll";
        String tmpDir = "temp";
        try {
            File temp = new File(tmpDir);
            temp.mkdir();
            File tempLib;

            // /assets/com/project/dev/midi/player
            //try ( InputStream in = VirtualEvent.class.getResourceAsStream(libName + libFormat)) {
            try ( InputStream in = VirtualEvent.class.getResourceAsStream("/assets/com/project/dev/wirelessjoystick/desktop/nativecode/" + libName + libFormat)) {
                String tempPath = temp.getCanonicalPath() + "\\";
                byte[] buffer = new byte[1024];
                int read;
                //tempLib = File.createTempFile(libName + "_temp_", libFormat, new File(tempPath));
                tempLib = new File(tempPath, libName + libFormat);

                try ( FileOutputStream fos = new FileOutputStream(tempLib)) {
                    while ((read = in.read(buffer)) != -1)
                        fos.write(buffer, 0, read);
                }
                System.out.println("Temp file: " + tempLib.getCanonicalPath());
            }
            /*
             * -
             * try ( InputStream in = VirtualEvent.class.getResourceAsStream(libName + libFormat)) {
             * Path basedir = FileSystems.getDefault().getPath(tmpDir); Path fileToCreatePath =
             * basedir.resolve(libName + libFormat); byte[] buffer = new byte[in.available()];
             * in.read(buffer); Files.write(fileToCreatePath, buffer); tempLib =
             * fileToCreatePath.toFile(); System.out.println("Temp file: " +
             * tempLib.getCanonicalPath()); }
             */
            tempLib.deleteOnExit();
            System.load(tempLib.getAbsolutePath());

        } catch (Exception | UnsatisfiedLinkError ex) {
            //ex.printStackTrace(System.out);
            linkedDll = false;
            String[] error = ex.getMessage().split(": ");
            if (error.length == 1)
                linkErrorMessage = ex.getMessage();
            else
                linkErrorMessage = error[1];
        }
    }
}
