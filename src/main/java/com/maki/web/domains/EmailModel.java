package com.maki.web.domains;

public class EmailModel {
  //  private final long id;
   // private final String content;


    private  String name;
    private  String email;
    private  String phone;
    private  String message;


    /*public EmailModel(long id, String content) {
        this.id = id;
        this.content = content;
    }*/

   // public long getId() {
      //  return id;
    //}

   // public String getContent() {
   //     return content;
   // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
