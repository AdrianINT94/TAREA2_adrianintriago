package Vista;

import java.util.Scanner;

import model.Numero;
import service.NumeroService;

public class MenuNumero {
	
	public static void mostrar() {

        Scanner leer = new Scanner(System.in);
        NumeroService service = new NumeroService();

        while (true) {
            System.out.println("---- MENÚ NÚMEROS ----");
            System.out.println("1. Registrar");
            System.out.println("2. Buscar");
            System.out.println("3. Listar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Volver");
            System.out.print("Opción: ");

            int op = leer.nextInt();
            leer.nextLine();

            switch (op) {

                case 1 : {
                    System.out.print("ID: ");
                    int id = leer.nextInt();
                    leer.nextLine();

                    System.out.print("Nombre del número: ");
                    String nombre = leer.nextLine();

                    System.out.print("Duración (ej: 10 min): ");
                    String duracion = leer.nextLine();

                    Numero n = new Numero(id, nombre, duracion);
                    service.save(n);

                    System.out.println("Número registrado correctamente.");
                    break;
                }

                case 2 : {
                    System.out.print("ID: ");
                    Numero n = service.getById(leer.nextInt());
                    leer.nextLine();
                    System.out.println(n != null ? n : "No encontrado");
                    break;
                }

                case 3 : service.getAll().forEach(System.out::println);
                break;

                case 4 : {
                    System.out.print("ID a actualizar: ");
                    int id = leer.nextInt();
                    leer.nextLine();

                    Numero n = service.getById(id);

                    if (n == null) {
                        System.out.println("No existe.");
                        break;
                    }

                    System.out.print("Nuevo nombre: ");
                    n.setNombre(leer.nextLine());

                    System.out.print("Nueva duración: ");
                    n.setDuracion(leer.nextLine());

                    service.save(n);
                    System.out.println("Número actualizado.");
                    break;
                }

                case 5 : {
                    System.out.print("ID a eliminar: ");
                    service.delete(leer.nextInt());
                    leer.nextLine();
                    System.out.println("Número eliminado.");
                    break;
                }

                case 6 : { return; }

                default : System.out.println("Opción inválida");
            }
        }
    }
}

