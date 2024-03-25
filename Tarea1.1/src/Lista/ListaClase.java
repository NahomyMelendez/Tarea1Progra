package Lista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Personal
 */
public class ListaClase {
    
/**
     * Método para cargar la lista de nombres desde un archivo y almacenarla en un HashSet.
     *
     * @param nombreArchivo El nombre del archivo desde donde se cargará la lista.
     * @return Un HashSet que contiene los nombres cargados desde el archivo.
     */
    static HashSet<String> cargarListaDesdeArchivo(String nombreArchivo) {
        HashSet<String> lista = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(normalizarNombre(linea));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
  /**
     * Método para normalizar un nombre, eliminando diacríticos y caracteres especiales.
     *
     * @param nombre El nombre a normalizar.
     * @return El nombre normalizado sin diacríticos ni caracteres especiales.
     */
    private static String normalizarNombre(String nombre) {
        return Normalizer.normalize(nombre.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{Alnum}\\s]", "")
                .toLowerCase();
    }

   /**
     * Método para ordenar la lista de nombres de forma ascendente.
     *
     * @param lista La lista de nombres a ordenar.
     * @return Una lista de nombres ordenada de forma ascendente.
     */
    static List<String> ordenarListaAscendente(HashSet<String> lista) {
        List<String> listaOrdenada = new ArrayList<>(lista);
        Collections.sort(listaOrdenada);
        return listaOrdenada;
    }

    /**
     * Método para mostrar los nombres de la lista en un cuadro de diálogo.
     *
     * @param lista La lista de nombres a mostrar.
     */
    static void mostrarNombresLista(HashSet<String> lista) {
        StringBuilder sb = new StringBuilder("Lista actualizada:\n");
        for (String nombre : lista) {
            sb.append(nombre).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }


    
/**
     * Método para verificar si hay nombres repetidos en la lista.
     *
     * @param lista La lista de nombres a verificar.
     * @return true si hay nombres repetidos, false si no los hay.
     */
    static boolean verificarNombresRepetidos(HashSet<String> lista) {
    HashSet<String> nombres = new HashSet<>();
    HashSet<String> nombresRepetidos = new HashSet<>();
    
    for (String nombreCompleto : lista) {
        String nombre = obtenerPrimerNombre(nombreCompleto); 
        if (!nombres.add(nombre)) {
            nombresRepetidos.add(nombre);
        }
    }
    
    if (!nombresRepetidos.isEmpty()) {
        StringBuilder sb = new StringBuilder("Nombres repetidos:\n");
        for (String nombre : nombresRepetidos) {
            sb.append(nombre).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
        return true;
    } else {
        JOptionPane.showMessageDialog(null, "No hay nombres repetidos en la lista.");
        return false;
    }
}
    

     /**
     * Método auxiliar para obtener el primer nombre de un nombre completo.
     *
     * @param nombreCompleto El nombre completo del estudiante.
     * @return El primer nombre del estudiante.
     */
    private static String obtenerPrimerNombre(String nombreCompleto) {
        String[] partes = nombreCompleto.split(" ");
        if (partes.length > 0) {
            return partes[0];
        }
        return "";
    }
     /**
     * Método para agregar un estudiante a la lista y guardarla en un archivo.
     *
     * @param nombreEstudiante El nombre del estudiante a agregar.
     * @param nombreArchivo    El nombre del archivo donde se guardará la lista.
     */
    static void agregarEstudiante(String nombreEstudiante, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            // Normalizar el nombre del estudiante antes de agregarlo
            String nombreNormalizado = normalizarNombre(nombreEstudiante);
            writer.write(nombreNormalizado);
            writer.newLine();

            // Después de agregar el estudiante, actualizamos la lista y la mostramos
            HashSet<String> listaActualizada = cargarListaDesdeArchivo(nombreArchivo);
            mostrarNombresLista(listaActualizada);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar el estudiante al archivo.");
        }
    }

    /**
     * Método para editar el nombre de un estudiante en la lista y guardarla en un archivo.
     *
     * @param nombreAntiguo El nombre antiguo del estudiante.
     * @param nuevoNombre   El nuevo nombre del estudiante.
     * @param nombreArchivo El nombre del archivo donde se guarda la lista.
     */
    static void editarEstudiante(String nombreAntiguo, String nuevoNombre, String nombreArchivo) {
        HashSet<String> lista = cargarListaDesdeArchivo(nombreArchivo);

        String antiguoNormalizado = normalizarNombre(nombreAntiguo);
        String nuevoNormalizado = normalizarNombre(nuevoNombre);

        if (lista.contains(antiguoNormalizado)) {
            lista.remove(antiguoNormalizado);
            lista.add(nuevoNormalizado);
         
            guardarListaEnArchivo(lista, nombreArchivo);
            JOptionPane.showMessageDialog(null, "Estudiante editado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El estudiante a editar no se encuentra en la lista.");
        }
    }
/**
     * Método para remover un estudiante de la lista y guardarla en un archivo.
     *
     * @param estudianteRemover El nombre del estudiante a remover.
     * @param nombreArchivo      El nombre del archivo donde se guarda la lista.
     */
 static void removerEstudiante(String estudianteRemover, String nombreArchivo) {
        
        HashSet<String> lista = cargarListaDesdeArchivo(nombreArchivo);

       
        estudianteRemover = normalizarNombre(estudianteRemover);

        if (lista.contains(estudianteRemover)) {
            lista.remove(estudianteRemover);
            
            guardarListaEnArchivo(lista, nombreArchivo);
            JOptionPane.showMessageDialog(null, "Estudiante removido exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El estudiante a remover no se encuentra en la lista.");
        }
    }
/**
     * Método para guardar la lista de nombres en un archivo.
     *
     * @param lista         La lista de nombres a guardar.
     * @param nombreArchivo El nombre del archivo donde se guardará la lista.
     */
     static void guardarListaEnArchivo(HashSet<String> lista, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String estudiante : lista) {
                writer.write(estudiante);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la lista en el archivo.");
        }
    }
 /**
     * Método para buscar un estudiante en la lista.
     *
     * @param nombreEstudiante El nombre del estudiante a buscar.
     * @param nombreArchivo     El nombre del archivo donde se encuentra la lista.
     * @return El nombre del estudiante si se encuentra en la lista, o una cadena vacía si no.
     */
   static String buscarEstudiante(String nombreEstudiante, String nombreArchivo) {
        // Cargar la lista desde el archivo
        HashSet<String> lista = cargarListaDesdeArchivo(nombreArchivo);

        // Normalizar el nombre del estudiante a buscar
        String nombreBuscado = normalizarNombre(nombreEstudiante);

        if (lista.contains(nombreBuscado)) {
            return nombreBuscado;
        } else {
            return ""; // Retorna cadena vacía si no se encuentra el estudiante
        }
    }
    /**
     * Método para mostrar la lista de nombres en un cuadro de diálogo.
     *
     * @param lista La lista de nombres a mostrar.
     */
     static void mostrarLista(HashSet<String> lista) {
        StringBuilder sb = new StringBuilder("Lista actualizada:\n");
        for (String nombre : lista) {
            sb.append(nombre).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
    
}
