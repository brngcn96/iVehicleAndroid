package com.example.vehicle.Api.Model;

public class User {
    private int User_ID;
    private String Name;
    private String Surname;
    private String PasswordHash;
    private String PasswordSalt;

    private String Email;
    private String Adress;
    private String ImageURL; //STRING????
    private String CreationDate;    //STRING DEGILDIR.
    private int CreatorIP;
    private String CreatorRole;
    private int UpdaterIP;
    private String UpdaterRole;
    private String Status;

    public void setUser_ID(int User_ID) {this.User_ID = User_ID;}
    public int getUser_ID(){return User_ID;}

    public void setName(String Name) {this.Name = Name;}
    public String getName(){return Name;}

    public void setSurname(String Surname) {this.Surname = Surname;}
    public String getSurname(){return Surname;}

    public void setPasswordHash(String PasswordHash) {this.PasswordHash = PasswordHash;}
    public String getPasswordHash(){return PasswordHash;}

    public void setPasswordSalt(String PasswordSalt) {this.PasswordSalt = PasswordSalt;}
    public String getPasswordSalt(){return PasswordSalt;}

    public void setEmail(String Email) {this.Email = Email;}
    public String getEmail(){return Email;}

    public void setAdress(String Adress) {this.Adress = Adress;}
    public String getAdress(){return Adress;}

    public void setImageURL(String ImageURL) {this.ImageURL = ImageURL;}
    public String getImageURL(){return ImageURL;}

    public void setCreationDate(String CreationDate) {this.CreationDate = CreationDate;}
    public String getCreationDate(){return CreationDate;}

    public void setCreatorIPint(int CreatorIP) {this.CreatorIP = CreatorIP;}
    public int getCreatorIP(){return CreatorIP;}

    public void setCreatorRole(String CreatorRole) {this.CreatorRole = CreatorRole;}
    public String getCreatorRole(){return CreatorRole;}

    public void serUpdaterIP(int UpdaterIP) {this.UpdaterIP = UpdaterIP;}
    public int getUpdaterIP(){return UpdaterIP;}

    public void setUpdaterRole(String UpdaterRole) {this.UpdaterRole = UpdaterRole;}
    public String getUpdaterRole(){return UpdaterRole;}

    public void setStatus(String Status) {this.Status = Status;}
    public String getStatus(){return Status;}

    public String toString(){
        return
                "User{"+
                        "User_ID = '" + User_ID + '\'' +
                        ",Name = '" + Name + '\'' +
                        ",Surname = '" + Surname + '\'' +
                        ",PasswordHash = '" + PasswordHash + '\'' +
                        ",PasswordSalt = '" + PasswordSalt + '\'' +
                        ",Email = '" + Email + '\'' +
                        ",Adress = '" + Adress + '\'' +
                        ",ImageURL = '" + ImageURL + '\'' +
                        ",CreationDate = '" + CreationDate + '\'' +
                        ",CreatorIP = '" + CreatorIP + '\'' +
                        ",CreatorRole = '" + CreatorRole + '\'' +
                        ",UpdaterIP = '" + UpdaterIP + '\'' +
                        ",UpdaterRole = '" + UpdaterRole + '\'' +
                        ",Status = '" + Status + '\'' +
                        "}";
    }
}
