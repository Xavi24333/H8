import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class SistemaEmergencia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VectorHeap<Paciente> cola = new VectorHeap<>();
        List<String> enfermedades = new ArrayList<>();
        List<Character> prioridades = new ArrayList<>();

        // Leer enfermedades desde Enfermedades.txt
        try (BufferedReader br = new BufferedReader(new FileReader("Enfermedades.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("Enfermedad") || linea.startsWith("-")) continue;

                String[] partes = linea.split("\\s{2,}");
                if (partes.length >= 2) {
                    enfermedades.add(partes[0].trim());
                    prioridades.add(partes[1].trim().charAt(0));
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
            return;
        }

        // === Bucle para registrar múltiples pacientes ===
        boolean continuarRegistro = true;
        while (continuarRegistro) {
            // Solicitar nombre del paciente
            System.out.print("\nIngrese el nombre del paciente: ");
            String nombre = scanner.nextLine();

            // Mostrar lista numerada de enfermedades
            System.out.println("\nSeleccione la enfermedad:");
            for (int i = 0; i < enfermedades.size(); i++) {
                System.out.println((i + 1) + ". " + enfermedades.get(i));
            }

            int seleccion = -1;
            while (seleccion < 1 || seleccion > enfermedades.size()) {
                System.out.print("\nIngrese el número de la enfermedad: ");
                try {
                    seleccion = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido.");
                }
            }

            String enfermedad = enfermedades.get(seleccion - 1);
            char prioridad = prioridades.get(seleccion - 1);
            Paciente nuevo = new Paciente(nombre, enfermedad, prioridad);
            cola.add(nuevo);

            System.out.println("\n Paciente registrado:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Enfermedad: " + enfermedad);
            System.out.println("Prioridad: " + prioridad);

            // Menú de opciones
            int opcion;
            do {
                System.out.println("\n--- MENÚ ---");
                System.out.println("1. Ver pacientes por prioridad");
                System.out.println("2. Salir");
                System.out.println("3. Agregar otro paciente");
                System.out.print("Seleccione una opción: ");
                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }

                if (opcion == 1) {
                    if (cola.isEmpty()) {
                        System.out.println("No hay pacientes registrados.");
                    } else {
                        System.out.println("\nPacientes en orden de prioridad:");
                        VectorHeap<Paciente> copia = new VectorHeap<>();
                        List<Paciente> mostrados = new ArrayList<>();

                        while (!cola.isEmpty()) {
                            Paciente p = cola.remove();
                            System.out.println(p);
                            mostrados.add(p);
                        }

                        for (Paciente p : mostrados) {
                            cola.add(p);
                        }
                    }
                } else if (opcion == 3) {
                    break; // vuelve al inicio del bucle principal
                } else if (opcion == 2) {
                    continuarRegistro = false;
                }

            } while (opcion != 2 && opcion != 3);
        }

        System.out.println("Gracias por usar el sistema.");
        scanner.close();
    }
}
