package Vista;

import java.util.Scanner;

import model.Coordinador;
import service.CoordinadorService;

public class MenuCoordinador {
	public static void mostrar() {

        Scanner leer = new Scanner(System.in);
        CoordinadorService service = new CoordinadorService();

        while (true) {
            System.out.println("---- MENÚ COORDINADORES ----");
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
                    Coordinador c = new Coordinador();

                    System.out.print("ID: ");
                    c.setId(leer.nextInt());
                    leer.nextLine();

                    System.out.print("Nombre: ");
                    c.setNombre(leer.nextLine());

                    System.out.print("Email: ");
                    c.setEmail(leer.nextLine());

                    service.save(c);
                }

                case 2 : {
                    System.out.print("ID: ");
                    Coordinador c = service.getById(leer.nextInt());
                    leer.nextLine();

                    System.out.println(c != null ? c : "No encontrado");
                }

                case 3 : service.getAll().forEach(System.out::println);

                case 4 : {
                    System.out.print("ID a actualizar: ");
                    int id = leer.nextInt(); 
                    leer.nextLine();

                    Coordinador c = service.getById(id);

                    if (c == null) {
                        System.out.println("No existe.");
                        break;
                    }

                    System.out.print("Nuevo nombre: ");
                    c.setNombre(leer.nextLine());

                    System.out.print("Nuevo email: ");
                    c.setEmail(leer.nextLine());

                    service.save(c);
                }

                case 5 : {
                    System.out.print("ID a eliminar: ");
                    service.delete(leer.nextInt());
                    leer.nextLine();
                }

                case 6 : { return; }

                default : System.out.println("Opción inválida");
            }
        }
    }
}

