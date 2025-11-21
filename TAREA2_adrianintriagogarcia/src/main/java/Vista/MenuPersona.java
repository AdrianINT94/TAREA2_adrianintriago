package Vista;

import java.util.Scanner;

import model.Persona;
import service.PersonaService;

public class MenuPersona {
	
	 public static void mostrar() {
	        Scanner leer = new Scanner(System.in);
	        PersonaService service = new PersonaService();

	        while (true) {
	            System.out.println("---- MENÚ PERSONAS ----");
	            System.out.println("1. Registrar");
	            System.out.println("2. Buscar por ID");
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
	                    System.out.print("Nombre: ");
	                    String nom = leer.nextLine();
	                    System.out.print("Email: ");
	                    String email = leer.nextLine();
	                    System.out.print("Nacionalidad: ");
	                    String nac = leer.nextLine();

	                    service.save(new Persona(id, nom, email, nac));
	                }
	                case 2 : {
	                    System.out.print("ID: ");
	                    Persona p = service.getById(leer.nextInt());
	                    leer.nextLine();
	                    System.out.println(p != null ? p : "No encontrado");
	                }
	                case 3 : service.getAll().forEach(System.out::println);
	                case 4 : {
	                    System.out.print("ID a actualizar: ");
	                    int id = leer.nextInt(); leer.nextLine();

	                    Persona p = service.getById(id);
	                    if (p == null) { System.out.println("No existe."); break; }

	                    System.out.print("Nuevo nombre: ");
	                    p.setNombre(leer.nextLine());
	                    System.out.print("Nuevo email: ");
	                    p.setEmail(leer.nextLine());
	                    System.out.print("Nueva nacionalidad: ");
	                    p.setNacionalidad(leer.nextLine());

	                    service.save(p);
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

