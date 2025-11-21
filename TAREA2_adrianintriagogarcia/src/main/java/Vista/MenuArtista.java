package Vista;

import java.util.Scanner;

import model.Artista;
import model.Especialidad;
import service.ArtistaService;

public class MenuArtista {

	public static void mostrar() {
        Scanner leer = new Scanner(System.in);
        ArtistaService service = new ArtistaService();

        while (true) {
            System.out.println("---- MENÚ ARTISTAS ----");
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
                    int id = leer.nextInt(); leer.nextLine();
                    System.out.print("Nombre artístico: ");
                    String nom = leer.nextLine();
                    service.save(new Artista());
                }
                case 2 : {
                    System.out.print("ID: ");
                    Artista a = service.getById(leer.nextInt());
                    leer.nextLine();
                    System.out.println(a != null ? a : "No encontrado");
                }
                case 3 : service.getAll().forEach(System.out::println);
                
                case 4 : {
                    System.out.print("ID: ");
                    int id = leer.nextInt(); leer.nextLine();
                    Artista a = service.getById(id);

                    if (a == null) { System.out.println("No existe."); break; }

                    System.out.print("Nuevo nombre artístico: ");
                    a.setApodo(leer.nextLine());
                    service.save(a);
                }
                case 5 : {
                    System.out.print("ID: ");
                    service.delete(leer.nextInt());
                    leer.nextLine();
                }
                case 6 : { return; }
            }
        }
    }
}

