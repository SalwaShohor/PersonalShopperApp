package com.example.personalshopperapp;



public class PersonalShopper {

    String FirstName, LastName,NRIC, Gender, Age, PhoneNum, Email, Password, Address, CityCode, City, State, Specialists, ImageURL;
    PersonalShopper()
    {

    }
    public PersonalShopper(String FirstName, String LastName,String NRIC, String Gender, String Age, String PhoneNum, String Email, String Password, String Address, String CityCode, String City, String State, String Specialists, String ImageURL) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.NRIC = NRIC;
        this.Gender = Gender;
        this.Age = Age;
        this.PhoneNum = PhoneNum;
        this.Email = Email;
        this.Password = Password;
        this.Address = Address;
        this.CityCode = CityCode;
        this.City = City;
        this.State = State;
        this.Specialists = Specialists;
        this.ImageURL = ImageURL;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getSpecialists() {
        return Specialists;
    }

    public void setSpecialists(String specialists) {
        Specialists = specialists;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}



