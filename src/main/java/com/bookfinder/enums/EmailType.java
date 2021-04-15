package com.bookfinder.enums;

public enum EmailType {
    PRICE_ALERT("price-alert-email", "Book price changed"),
    STOCK_ALERT("stock-alert-email", "Book stock changed");

    private final String emailTemplateName;
    private final String emailSubject;

    EmailType(String emailTemplateName, String emailSubject){
        this.emailTemplateName = emailTemplateName;
        this.emailSubject = emailSubject;
    }

    public String getEmailSubject(){
        return emailSubject;
    }
    public String getEmailTemplateName(){
        return emailTemplateName;
    }
}
