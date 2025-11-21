package Vista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Coordinador;
import model.Espectaculo;
import model.Numero;
import service.CoordinadorService;
import service.EspectaculoService;

public class MenuEspectaculo {

	 public static void mostrar() {
		 
	 
	        Scanner leer = new Scanner(System.in);
	        
	        EspectaculoService espectaculoService = new EspectaculoService();
	        CoordinadorService coordinadorService = new CoordinadorService();
	        

	        while(true) {
	        	System.out.println("-----------MENU ESPECTACULOS---------");
	        	System.out.println("1.Registrar espectaculo");
	        	System.out.println("2.Buscar por ID");
	        	System.out.println("3.Listar todos");
	        	System.out.println("4.Actualizar espectaculo");
	        	System.out.println("5.Eliminar espectaculo");
	        	System.out.println("6.Volver");
	        	System.out.print("Opcion");
	        	
	        	int op =leer.nextInt();
	        	leer.nextLine();
	        	
	        	switch (op) {

                case 1 : {
                    System.out.print("ID: ");
                    int id = leer.nextInt(); leer.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = leer.nextLine();

                    System.out.print("Fecha inicio (AAAA-MM-DD): ");
                    LocalDate inicio = LocalDate.parse(leer.nextLine());

                    System.out.print("Fecha fin (AAAA-MM-DD): ");
                    LocalDate fin = LocalDate.parse(leer.nextLine());

                    System.out.print("ID del coordinador: ");
                    int idCoord = leer.nextInt(); leer.nextLine();

                    Coordinador coord = coordinadorService.getById(idCoord);
                    if (coord == null) {
                        System.out.println("ERROR: Coordinador no existe.");
                        break;
                    }

                    List<Numero> numeros = new ArrayList<>();

                    Espectaculo e = new Espectaculo(id, nombre, inicio, fin, coord, numeros);
                    espectaculoService.save(e);

                    System.out.println("Espectáculo registrado.");
                }

                case 2 : {
                    System.out.print("ID: ");
                    Espectaculo e = espectaculoService.getById(leer.nextInt());
                    leer.nextLine();

                    System.out.println(e != null ? e : "No encontrado");
                }

                case 3 : espectaculoService.getAll().forEach(System.out::println);

                case 4 : {
                    System.out.print("ID del espectáculo a actualizar: ");
                    int id = leer.nextInt(); leer.nextLine();
                    Espectaculo e = espectaculoService.getById(id);

                    if (e == null) {
                        System.out.println("No existe.");
                        break;
                    }

                    System.out.print("Nuevo nombre: ");
                    e.setNombre(leer.nextLine());

                    System.out.print("Nueva fecha inicio (AAAA-MM-DD): ");
                    e.setFechaInicio(LocalDate.parse(leer.nextLine()));

                    System.out.print("Nueva fecha fin (AAAA-MM-DD): ");
                    e.setFechaFin(LocalDate.parse(leer.nextLine()));

                    System.out.print("Nuevo coordinador ID: ");
                    int idCoord = leer.nextInt(); leer.nextLine();
                    Coordinador coord = coordinadorService.getById(idCoord);

                    if (coord == null) {
                        System.out.println("Coordinador inexistente.");
                        break;
                    }

                    e.setCoordinador(coord);

                    espectaculoService.save(e);
                    System.out.println("Actualizado correctamente.");
                }

                case 5 : {
                    System.out.print("ID a eliminar: ");
                    espectaculoService.delete(leer.nextInt());
                    leer.nextLine();
                    System.out.println("Espectáculo eliminado.");
                }

                case 6 : { return; }

                default : System.out.println("Opción inválida.");
            }
        }
    }

	        }
