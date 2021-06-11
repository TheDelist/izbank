package com.bank.izbank.Job;

public class Farmer extends Job{
    private final String name="Farmer";
    private final String maxCreditAmount="300000";
    private final String maxCreditInstallment ="40";

    public Farmer() {
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
