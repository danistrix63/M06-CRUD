package com.accesadades.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Opciones del menú
        while (true) {
            System.out.println("Seleccione una opción:\n" +
                    "1. Inicializar Base de Datos\n" +
                    "2. Insertar Registro\n" +
                    "3. Mostrar Registros con Paginación\n" +
                    "4. Crear XML\n" +
                    "5. Consultar por ID\n" +
                    "6. Consultar por Descripción\n" +
                    "7. Modificar Descripción\n" +
                    "8. Eliminar Registro\n" +
                    "9. Salir");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Ingrese la ruta del archivo SQL para inicializar la base de datos: ");
                        scanner.nextLine(); // Consumir nueva línea
                        String rutaArchivo = scanner.nextLine();
                        CRUDHR.inicializarBaseDatos(new FileInputStream(rutaArchivo));
                    } catch (FileNotFoundException e) {
                        System.out.println("Archivo no encontrado: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Ingrese descripción: ");
                    scanner.nextLine(); // Consumir nueva línea
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese ID Departamento: ");
                    int idDepartamento = scanner.nextInt();
                    CRUDHR.insertarRegistro(descripcion, idDepartamento);
                    break;
                case 3:
                    System.out.print("Ingrese límite: ");
                    int limit = scanner.nextInt();
                    System.out.print("Ingrese offset: ");
                    int offset = scanner.nextInt();
                    CRUDHR.mostrarRegistros(limit, offset);
                    break;
                case 4:
                    CRUDHR.crearXML();
                    break;
                case 5:
                    System.out.print("Ingrese ID: ");
                    int idConsulta = scanner.nextInt();
                    CRUDHR.consultarPorID(idConsulta);
                    break;
                case 6:
                    System.out.print("Ingrese término de búsqueda: ");
                    scanner.nextLine(); // Consumir nueva línea
                    String termino = scanner.nextLine();
                    CRUDHR.consultarPorDescripcion(termino);
                    break;
                case 7:
                    System.out.print("Ingrese ID: ");
                    int idModificar = scanner.nextInt();
                    System.out.print("Ingrese nueva descripción: ");
                    scanner.nextLine(); // Consumir nueva línea
                    String nuevaDescripcion = scanner.nextLine();
                    CRUDHR.modificarDescripcion(idModificar, nuevaDescripcion);
                    break;
                case 8:
                    System.out.print("Ingrese ID: ");
                    int idEliminar = scanner.nextInt();
                    CRUDHR.eliminarRegistro(idEliminar);
                    break;
                case 9:
                    System.out.println("Saliendo del programa.");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
