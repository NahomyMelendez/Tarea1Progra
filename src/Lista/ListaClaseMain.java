package Lista;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Personal
 */
public class ListaClaseMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean salir = false;
        ListaClase lis = new ListaClase();
        HashSet<String> listaClase = lis.cargarListaDesdeArchivo("Programacion II (1).txt");
        List<String> listaOrdenada = lis.ordenarListaAscendente(listaClase);
//        List<String> nombresRepetidos = lis.obtenerNombresRepetidos(listaClase);

        while (!salir) {
            String opcion = JOptionPane.showInputDialog("Menú\n"
                    + "1. determinar si hay nombres completos repetidos.\n"
                    + "2.  mostrar la lista ordenada. .\n"
                    + "3.  Agregar un nuevo estudiante al inicio. \n"
                    + "4. editar Estudiante\n"
                    + "5.buscar un estudiante en particular\n"
                    + "6. Remover estudiante \n"
                    + "7. Salir\n\n"
                    + "Ingrese el número de la opción que desea:");

            switch (opcion) {
                case "1":

                    boolean nombresRepetidos = 
                            ListaClase.verificarNombresRepetidos(listaClase);
                    if (nombresRepetidos) {
                        JOptionPane.showMessageDialog(null,
                                "Se encontraron nombres repetidos en la lista.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se encontraron nombres repetidos en la lista.");
                    }
                    break;

                case "2":
                    
                    HashSet<String> listaActualizada = 
                            lis.cargarListaDesdeArchivo("Programacion II (1).txt");
                 
                    List<String> listaOrdenadaActualizada = 
                            lis.ordenarListaAscendente(listaActualizada);
                    
                    StringBuilder sb = new StringBuilder("Lista actualizada:\n");
                    for (String nombre : listaOrdenadaActualizada) {
                        sb.append(nombre).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());

                    break;

                case "3":

                    String nuevoEstudiante = JOptionPane.showInputDialog(
                            "Ingrese el nombre del estudiante:");
                    if (nuevoEstudiante != null && !nuevoEstudiante.isEmpty()) {
                        lis.agregarEstudiante(nuevoEstudiante,
                                "Programacion II (1).txt");
                    } else {
                        JOptionPane.showMessageDialog(null,
                       "Error: el nombre del estudiante no puede estar vacío.");
                    }
                    break;

                case "4":

                    String nombreAntiguo = JOptionPane.showInputDialog(
                            "Ingrese el nombre del estudiante a editar:");
                    String nuevoNombre = JOptionPane.showInputDialog(
                            "Ingrese el nuevo nombre:");
                    if (nombreAntiguo != null && nuevoNombre != null
                            && !nombreAntiguo.isEmpty() && !nuevoNombre.isEmpty()) {
                        ListaClase.editarEstudiante(nombreAntiguo, nuevoNombre,
                                "Programacion II (1).txt");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error: Los nombres no pueden estar vacíos.");
                    }

                    break;

                case "5":

                    String nombreBuscado = JOptionPane.showInputDialog(
                            "Nota: El nombre debe coincidir exactamente."
                            + "\nIngrese el nombre del estudiante a buscar:");
                    if (nombreBuscado != null && !nombreBuscado.isEmpty()) {
                        String estudianteEncontrado = ListaClase.
                                buscarEstudiante(nombreBuscado,
                                        "Programacion II (1).txt");
                        if (!estudianteEncontrado.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El estudiante"
                                    + " fue encontrado en la lista:   "
                                    + estudianteEncontrado);
                        } else {
                            JOptionPane.showMessageDialog(null, "El estudiante"
                                    + " no fue encontrado en la lista.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: El nombre a"
                                + " buscar no puede estar vacío.");
                    }

                    break;

                case "6":
                    String estudianteRemover = JOptionPane.showInputDialog(
                            "Ingrese el nombre del estudiante a remover:");
                    if (estudianteRemover != null && !estudianteRemover.isEmpty()) {
                        ListaClase.removerEstudiante(estudianteRemover,
                                "Programacion II (1).txt");
                        JOptionPane.showMessageDialog(null,
                                "Estudiante removido exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error: El nombre del estudiante a remover "
                                + "no puede estar vacío.");
                    }
                    break;

                case "7":
                    salir = true;
                    JOptionPane.showMessageDialog(null,
                            "Saliendo del programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida,"
                            + " por favor ingrese un número del 1 al 6");
                    break;
            }
        }

    }

}
