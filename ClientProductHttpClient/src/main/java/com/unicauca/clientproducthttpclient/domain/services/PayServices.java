package com.unicauca.clientproducthttpclient.domain.services;

import co.unicauca.microkernel.common.payhuynseda.IPaymentPlugin;
import co.unicauca.microkernel.common.payhuynseda.Payment;
import unicauca.microkernel.plugin.manager.DeliveryPluginManager;

public class PayServices {
    //verifica si el metodo de pago es correcto o si la cuenta tiene saldo sugiciente para el cargo que se le va a realizar
    public boolean verifyPayment(Payment payment) throws Exception {
        String typePayment = payment.getTypePayment();
        DeliveryPluginManager manager = DeliveryPluginManager.getInstance();
        IPaymentPlugin plugin = manager.getPaymentPlugin(typePayment);

        if (plugin == null) {
            throw new Exception("No hay un plugin disponible para el metodo de pago escogido: " + typePayment);
        }

        return plugin.payProcesing(payment);


    }
}
