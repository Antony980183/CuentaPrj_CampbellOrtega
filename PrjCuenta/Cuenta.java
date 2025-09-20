import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cuenta{
    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHambiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas = 0;
    
    
    public Cuenta(double pSaldo){
        this("", pSaldo);
    }
    
    
    public Cuenta(String nombreCuentaHambiente, double pSaldo){
        this.nombreCuentaHambiente = nombreCuentaHambiente;
        saldo = pSaldo;
        cantCuentasCreadas++;
        codCuenta += String.valueOf(cantCuentasCreadas);
    }
    
    
    public void setNombreCuentaHambiente(String pNombreCuentaHambiente){
        nombreCuentaHambiente = pNombreCuentaHambiente;    
    }
    
    
    public String getCodCuenta(){
        return codCuenta;
    }
    
    
    public double getSaldo(){
        return saldo;
    }
    
    
    public double depositar(double monto){
        saldo += monto;
        cantDepositosRealizados++;
        return saldo;
    }
    
    
    public double retirar(double monto){
        if(!validarRetiro(monto)){
            return saldo;
        }
        saldo -= monto;
        cantRetirosExitososRealizados++;
        return monto;
    }

    
    public int getCantCuentas(){
        return cantCuentasCreadas;
    }
    

    public String toString(){
        String resultado = "";
        resultado += "Fecha de Creacion de la Cuenta: " + determinarFechaHoraActual() + "\n"; 
        resultado += "Nombre de Cuenta: " + nombreCuentaHambiente + "\n";
        resultado += "Codido de Cuenta: " + codCuenta + "\n";
        resultado += "Cantidad de Depositos Realizados: " + cantDepositosRealizados + "\n";
        resultado += "Cantidad de Retiros Realizados: " + cantRetirosExitososRealizados + "\n";
        resultado += "Saldo Actual: " + saldo + "\n";
        return resultado;
    }
    
    
    private boolean validarRetiro(double monto){
        if((saldo - monto) < 0){
            return false;
        }
        return true;
    }
    

    private String determinarFechaHoraActual() {
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return formatoFecha.format(fecha);
    }
}