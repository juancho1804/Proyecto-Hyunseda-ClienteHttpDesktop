package co.unicauca.microkernel.plugins.pse;

import co.unicauca.microkernel.common.payhuynseda.IPaymentPlugin;
import co.unicauca.microkernel.common.payhuynseda.Payment;
import co.unicauca.microkernel.plugins.accountBankManager.AccountBankManager;

public class PagoPse implements IPaymentPlugin {


    @Override
    public boolean payProcesing(Payment payment) {
        String typePayment = payment.getTypePayment();
        int accountBankNum = payment.getAccountBankNum();
        boolean pagoExitoso = false;
        double charge = payment.getCharge();

        //Esto influye desde donde guarda cada uno al clonar el proyecto
        String path = "D:\\ProyectoHyunseda\\Proyecto-Hyunseda-ClienteHttpDesktop\\Microkernel-pago-pse\\src\\main\\resources";

        System.out.println("Pago exitoso hecho con:  " + typePayment +" | " + path);

        try
        {
            AccountBankManager.init(path);

        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
        AccountBankManager ac = AccountBankManager.getInstance();
        ac.getInformationAccount(Integer.toString(accountBankNum));

        //demas logica para buscar la existencia de la cuenta y la validacion de la compra:(
        if(payment.getAccountBankNum() == 12345 && payment.getCharge() == 1000000){
            return true;

        }else {
            return false;
        }
    }
}
