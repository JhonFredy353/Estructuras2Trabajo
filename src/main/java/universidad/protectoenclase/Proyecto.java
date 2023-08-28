/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license

*/


package universidad.protectoenclase;

/**
 *
 *@author Fredy
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Proyecto {
    private static String archivoEstudiantes;
    private static ArrayList <Alumno> alumnos=new ArrayList<Alumno>();
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        archivoEstudiantes = "./data/reporte.txt";
        File archivoAcargar = new File(archivoEstudiantes);
        if (archivoAcargar.length() != 0) {
            try (BufferedReader lector = new BufferedReader(new FileReader(archivoAcargar))) {
                String linea;
                String nombre = null, apellido = null, correo = null, semestre = null;
                int cedula = 0, telefono = 0;
                while ((linea = lector.readLine()) != null) {
                    if (linea.startsWith("nombre:")) {
                        nombre = linea.substring(linea.indexOf(":") + 2);
                    } else if (linea.startsWith("apellido:")) {
                        apellido = linea.substring(linea.indexOf(":") + 2);
                    } else if (linea.startsWith("cedula:")) {
                        cedula = Integer.parseInt(linea.substring(linea.indexOf(":") + 2));
                    } else if (linea.startsWith("correo:")) {
                        correo = linea.substring(linea.indexOf(":") + 2);
                    } else if (linea.startsWith("telefono:")) {
                        telefono = Integer.parseInt(linea.substring(linea.indexOf(":") + 2));
                    } else if (linea.startsWith("semetre:")) {
                        semestre = linea.substring(linea.indexOf(":") + 2);

                        Alumno nuevoAlumno = new Alumno(nombre, apellido, cedula, correo, telefono, semestre);
                        alumnos.add(nuevoAlumno);

                        // Restablecer valores temporales para el próximo alumno
                        nombre = null; apellido = null; correo = null; semestre = null;
                        cedula = 0; telefono = 0;
                    }
                }
            }
        } else {
            alumnos = new ArrayList<>();
        }

        
        Scanner lector = new Scanner(System.in);
        
       
        //se crea una variable para controlar el ciclo 
        boolean activo=true;
        do {
            System.out.println("--------------------------------------------");
            System.out.println("¿que operaciones quieres hacer?");
            System.out.println("1.- Insertar alumno");
            System.out.println("2.- Eliminar alumno ");
            System.out.println("3.- Modificar alumno");
            System.out.println("4.- Consultar alumno");
            System.out.println("5.- generar  reporte por semestre ");
            System.out.println("6.- Terminar programa");
            System.out.println("-------------------------------------------");

            int opcion=lector.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("ingresa el nombre del alumno");
                    String nombre=lector.next();
                
                    System.out.println("ingresa el apellido del alumno");
                    String apellido=lector.next();

                    System.out.println("ingresa la cedula del alumno");
                    int cedula=lector.nextInt();

                    System.out.println("ingresa el correo del alumno");
                    String correo=lector.next();

                    System.out.println("ingresa el telefono del alumno");
                    int telefono=lector.nextInt();

                    System.out.println("ingresa el semestre del alumno");
                    String semestre=lector.next();     

                    //creacion del objeto alumno 
                    Alumno nuevoAlumno= new Alumno(nombre, apellido, cedula, correo, telefono, semestre);

                    //almacenando alumnos en un ArrayList
                    alumnos.add(nuevoAlumno);
                    break;
                case 2:
                    System.out.println("Introduce la cedula del alumno que quieres eliminar");
                    int eliminar = lector.nextInt();
                    for (Alumno alumno : alumnos) {
                        if (alumno.getCedula()==eliminar) {
                            alumnos.remove(alumno);
                            System.out.println("alumno eliminado exitosamente");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Introduce la cedula del alumno que quieres modificar");
                    int cedula2 = lector.nextInt();
                    System.out.println("Introduce el nuevo nombre del alumno");
                    String nombre2 = lector.next();
                    System.out.println("Introduce el nuevo apellido del alumno");
                    String apellido2 = lector.next();
                    System.out.println("Introduce el nuevo semestre del alumno");
                    String semestre2 = lector.next();
                    for (Alumno a : alumnos) {
                        if(cedula2==a.getCedula()){
                            a.setNombre(nombre2);
                            a.setApellido(apellido2);
                            a.setApellido(semestre2);
                        }
                    }
                    
                    break;

                case 4:
                    for (Alumno alumno : alumnos) {
                        System.out.println("nombre : "+alumno.getNombre());
                        System.out.println("apellido : "+alumno.getApellido());
                        System.out.println("cedula : "+alumno.getCedula());
                        System.out.println("correo : "+alumno.getCorreo());
                        System.out.println("telefono : "+alumno.getTelefono());
                        System.out.println("semestre : "+alumno.getSemestre());
                    }
                    break;

                case 5:
                    System.out.println("ingresa el semestre para generar el reporte");
                    String reporte=lector.next();
                    File archivo= new File("./data/reporte.txt");
                    PrintWriter pluma=new PrintWriter(archivo);
                    for (Alumno alumno : alumnos) {
            
                        if (alumno.getSemestre().equals(reporte) ){

                            pluma.println("nombre: "+ alumno.getNombre());
                            pluma.println("apellido: "+ alumno.getApellido());
                            pluma.println("cedula: "+ alumno.getCedula());
                            pluma.println("correo: "+ alumno.getCorreo());
                            pluma.println("telefono: "+ alumno.getTelefono()); 
                            pluma.println("semetre: "+alumno.getSemestre());             

                        }
                    }
                        pluma.close();
                    break;
                case 6:
                    activo=false;
                default:
                    System.out.println("Ingrese un valor valido");
                    break;
            }
             
        } while (activo);
    }
}
