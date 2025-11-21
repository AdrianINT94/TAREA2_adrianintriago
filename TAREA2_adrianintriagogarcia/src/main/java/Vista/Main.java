package Vista;

import java.util.Scanner;

import java.util.List;

//Me da un error al principio del pom.xml que no consegui solucionar. 

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner leer = new Scanner(System.in);
       // Se crea objeto Scanner para leer las entras del usuario 
        while (true) {
            System.out.println("======== MENÚ PRINCIPAL ========");
            System.out.println("1. Gestión de Credenciales");
            System.out.println("2. Gestión de Personas");
            System.out.println("3. Gestión de Artistas");
            System.out.println("4. Gestión de Coordinadores");
            System.out.println("5. Gestión de Números");
            System.out.println("6. Gestión de Espectáculos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            //Bucle principal del menu 
            int opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) { // depende de la opcion un menu o otro 
                case 1 : MenuCredencial.mostrar();
                case 2 : MenuPersona.mostrar();
                case 3 : MenuArtista.mostrar();
                case 4 : MenuCoordinador.mostrar();
                case 5 : MenuNumero.mostrar();
                case 6 : MenuEspectaculo.mostrar();
                case 7 : {
                    System.out.println("Saliendo");
                    
                }
                default : System.out.println("Opción no válida.");
            }
        }
    }
}
			
		

