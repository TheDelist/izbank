package com.bank.izbank.Job;

public class Contractor extends Job {

    private final String name="Contractor";
    private final String maxCreditAmount="300000";
    private final String maxCreditInstallment ="40";

    public Contractor() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMaxCreditAmount() {
        return maxCreditAmount;
    }

    @Override
    public String getMaxCreditInstallment() {
        return maxCreditInstallment;
    }
}
