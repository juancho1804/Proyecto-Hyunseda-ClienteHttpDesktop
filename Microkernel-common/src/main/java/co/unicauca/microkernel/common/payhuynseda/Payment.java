package co.unicauca.microkernel.common.payhuynseda;

public class Payment {
    private String typePayment;
    private int accountBankNum;
    private double charge;

    public Payment(String typePayment, int accountBankNum, double charge) {
        this.typePayment = typePayment;
        this.accountBankNum = accountBankNum;
        this.charge = charge;
    }

    /**
     * @return the typePayment
     */
    public String getTypePayment() {
        return typePayment;
    }

    /**
     * @param typePayment the typePayment to set
     */
    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    /**
     * @return the charge
     */
    public double getCharge() {
        return charge;
    }

    /**
     * @param charge the charge to set
     */
    public void setCharge(double charge) {
        this.charge = charge;
    }

    /**
     * @return the accountBankNum
     */
    public int getAccountBankNum() {
        return accountBankNum;
    }

    /**
     * @param accountBankNum the accountBankNum to set
     */
    public void setAccountBankNum(int accountBankNum) {
        this.accountBankNum = accountBankNum;
    }
}
