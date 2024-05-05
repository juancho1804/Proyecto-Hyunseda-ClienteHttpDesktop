package co.unicauca.microkernel.common.entities;

public class Delivery {
    private double amount;
    private String PaymentMethod;
    private double valor;


    public Delivery( double amount, String PaymentMethod,double valor) {
        this.valor=valor;
        this.amount = amount;
        this.PaymentMethod = PaymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCountryCode() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
