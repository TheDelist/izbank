package com.bank.izbank.Job;

public class Job {

    private String name;
    private String maxCreditAmount;
    private String maxCreditInstallment;

    public Job(String name, String maxCreditAmount, String maxCreditInstallment) {
        this.name = name;
        this.maxCreditAmount = maxCreditAmount;
        this.maxCreditInstallment = maxCreditInstallment;
    }

    public Job() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxCreditAmount() {
        return maxCreditAmount;
    }

    public void setMaxCreditAmount(String maxCreditAmount) {
        this.maxCreditAmount = maxCreditAmount;
    }

    public String getMaxCreditInstallment() {
        return maxCreditInstallment;
    }

    public void setMaxCreditInstallment(String maxCreditInstallment) {
        this.maxCreditInstallment = maxCreditInstallment;
    }
}
