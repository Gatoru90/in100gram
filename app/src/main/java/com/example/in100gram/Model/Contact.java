package com.example.in100gram.Model;

public class Contact {
    private String ContactName;
    private String ContactAvatarSource;

    public Contact(String contactName, String contactAvatarSource){
        this.ContactName = contactName;
        this.ContactAvatarSource = contactAvatarSource;
    }

    public String getContactName() {return ContactName ;}

    public void setContactName(String contactName) {this.ContactName = contactName;}

    public String getContactAvatarSource() {return ContactAvatarSource;}

    public void setContactAvatarSource(String contactAvatarSource) {this.ContactAvatarSource = contactAvatarSource;}
}
