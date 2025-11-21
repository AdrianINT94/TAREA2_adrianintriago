package Vista;

import java.util.Scanner;

import model.Credencial;
import service.CredencialService;

public class MenuCredencial {
	
	public static void mostrar() {
		
		Scanner leer = new Scanner(System.in);
        CredencialService service = new CredencialService();

        while (true) {
            System.out.println("---- MENÚ CREDENCIALES ----");
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
            
            case 1:
                System.out.print("Usuario: ");
                String u = leer.nextLine();
                System.out.print("Password: ");
                String p = leer.nextLine();
                System.out.print("Rol: ");
                String r = leer.nextLine();

                service.save(new Credencial(u, p, r));
                break;

            case 2:
                System.out.print("Usuario: ");
                u = leer.nextLine();
                Credencial c = service.getByUsername(u);
                System.out.println(c != null ? c : "No encontrado");
                break;

            case 3:
                service.getAll().forEach(System.out::println);
                break;

            case 4:
                System.out.print("Usuario a actualizar: ");
                u = leer.nextLine();
                c = service.getByUsername(u);

                if (c == null) {
                    System.out.println("No existe.");
                    break;
                }

                System.out.print("Nuevo password: ");
                c.setPassword(leer.nextLine());
                System.out.print("Nuevo rol: ");
                c.setRol(leer.nextLine());
                service.save(c);

                break;

            case 5:
                System.out.print("Usuario a eliminar: ");
                service.delete(leer.nextLine());
                break;

            case 6:
                return;

            default:
                System.out.println("Opción inválida");
            }
    }

	}
}
