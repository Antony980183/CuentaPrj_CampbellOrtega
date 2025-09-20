import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

public class PrincipalCuenta {
    static Scanner scanner = new Scanner(System.in);
    static List<Cuenta> listaCuentas = new ArrayList<>();
    public static void main(String[] args) {
        int actual = 0;
        boolean salir = false;
        while(!salir) { 
            System.out.println("\nMenú principal"); 
            System.out.println("1) Crear Cuenta"); 
            System.out.println("2) Conocer la cantidad de Cuentas Creadas"); 
            System.out.println("3) Listar Cuentas"); 
            System.out.println("4) Seleccionar Cuenta actual"); 
            System.out.println("5) Depositar"); 
            System.out.println("6) Retirar"); 
            System.out.println("7) Consultar Saldo"); 
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("0) Salir");        
            System.out.println("");
            System.out.print("--> Ingrese la opcion que desea realizar: ");
            String opcion = scanner.nextLine().trim();
            System.out.println("");
            switch(opcion) {
                case "1": {
                    listaCuentas.add(crearCuenta());
                    break;
                }
                case "2": {
                    if(listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas!");
                    }else {
                        System.out.println("La cantidad de cuentas creadas es de: " + listaCuentas.size());
                    }
                    System.out.println("");
                    break;
                }
                case "3": {
                    if(listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas!");
                    }else {
                        for(Cuenta cu : listaCuentas) {
                            String usuario = cu.toString();
                            System.out.println(usuario);
                            System.out.println("");
                        }
                    }
                    System.out.println("");
                    break;
                }
                case "4": {
                    if(listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas!");
                    }else {
                        actual = getIndice();
                        System.out.println("Se ha seleccionado la cuenta de indice: " + actual);
                    }
                    System.out.println("");
                    break;
                }
                case "5": {
                    if(listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas!");
                    }else {
                        double monto = getMonto();
                        listaCuentas.get(actual).depositar(monto);
                    } 
                    System.out.println("");
                    break;
                }
                case "6": {
                    if(listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas!");
                    }else {
                        double monto = getMonto();
                        listaCuentas.get(actual).retirar(monto);
                    } 
                    System.out.println("");
                    break;
                }
                case "7": {
                    if(listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas!");
                    }else {
                        double saldo = listaCuentas.get(actual).getSaldo();
                        System.out.println("El saldo actual es de: " + saldo);
                    } 
                    System.out.println("");
                    break;
                }
                case "8": {
                    if(listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas!");
                    }else {
                        System.out.println("El estado actual de la cuenta es: ");
                        String estado = listaCuentas.get(actual).toString();
                        System.out.println(estado);
                    } 
                    System.out.println("");
                    break;
                }
                case "0": {
                    salir = true;
                    System.out.println("");
                    break;
                }
                default:
                    System.out.println("Error: La Opcion ingresada no existe!");
                    System.out.println("");
            }
        }    
        System.out.println("Muchas gracias por utilizar mi programa :D!");
    }
    
    
    public static double getSaldoInicial() {
        boolean salir = false;
        double saldo = 0;
        
        while(!salir) {
            try {
                System.out.print("--> Ingrese el saldo inicial: ");
                saldo = scanner.nextDouble();
                scanner.nextLine();
                salir = true;
            }catch(InputMismatchException e) {
                System.out.println("Error: No debe ingresar letras!");
                System.out.println("");
                scanner.nextLine();
            }
        }
        System.out.println("");
        return saldo;
    }


    public static String getNombreCuenta() {
        boolean salir = false;
        String nombreCuenta = "";
        while(!salir) {
            System.out.print("--> Ingrese el nombre de la cuenta: ");
            nombreCuenta = scanner.nextLine();
            
            if(nombreCuenta.isEmpty()) {
                System.out.println("Error: Debe ingresar un nombre!");
                System.out.println("");
            }else {
                salir = true;
            }
        }
        System.out.println("");
        return nombreCuenta;
    }
    
    
    public static Cuenta crearCuenta() {
        Cuenta cuenta = null;
        boolean salir = false;
        while(!salir) {
            System.out.println("1.Crear Cuenta con Monto");
            System.out.println("2.Crear Cuenta con Monto y Nombre");
            System.out.println("");
            System.out.print("--> Ingrese la opcion que desea realizar: ");
            String opcion = scanner.nextLine().trim();
            System.out.println("");
            switch(opcion) {
                case "1": {
                    double saldo = getSaldoInicial();
                    cuenta = new Cuenta(saldo);
                    String nombreCuenta = getNombreCuenta();
                    cuenta.setNombreCuentaHambiente(nombreCuenta);
                    salir = true;
                    break;
                }
                case "2": {
                    String nombreCuenta = getNombreCuenta();
                    double saldo = getSaldoInicial();
                    cuenta = new Cuenta(nombreCuenta,saldo);
                    salir = true;
                    break;
                }
                default:
                    System.out.println("Error: La opcion ingresada no existe!");
                    System.out.println("");
            }
        }
        return cuenta;
    }
    
    
    static int getIndice() {
        boolean salir = false;
        int indice = 0;
        
        while(!salir) {
            try {
                System.out.print("--> Ingrese el indice de la cuenta a utilizar: ");
                indice = scanner.nextInt();
                scanner.nextLine();
                if(indice >= 0 && indice < listaCuentas.size()) {
                    salir = true;
                }else {
                    System.out.println("Error: Indice ingresado no existe!");
                }
            }catch(InputMismatchException e) {
                System.out.println("Error: No debe ingresar letras!");
                scanner.nextLine();
            }
        }
        return indice;   
    }
    
    
    static double getMonto() {
        boolean salir = false;
        double monto = 0;
        
        while(!salir) {
            try {
                System.out.print("--> Ingrese el monto: ");
                monto = scanner.nextDouble();
                scanner.nextLine();
                if(monto > 0) {
                    salir = true;
                }else {
                    System.out.println("Error: No debe ingresar numeros negativos!");
                }
            }catch(InputMismatchException e) {
                System.out.println("Error: No debe ingresar letras!");
                scanner.nextLine();
            }
        }
        System.out.println("");
        return monto;    
    }
}