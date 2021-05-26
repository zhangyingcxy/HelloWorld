package com.swufe.helloworld;

public class Rate  {
    private String countryName;
    private String rate;

    public Rate(String countryName,String rate){
        this.countryName = countryName;
        this.rate = rate;
    }
    public String getCountryName(){
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
