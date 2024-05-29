package org.librarySystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Members {
     String fullName;

     String idNumber;
     String address;
     @Id
     String email;
     int phoneNumber;
     int age;



     public String getFullName() {
          return fullName;
     }

     public void setFullName(String fullName) {
          this.fullName = fullName;
     }



     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public int getPhoneNumber() {
          return phoneNumber;
     }

     public void setPhoneNumber(int phoneNumber) {
          this.phoneNumber = phoneNumber;
     }

     public int getAge() {
          return age;
     }

     public void setAge(int age) {
          this.age = age;
     }
     public String getIdNumber() {
          return idNumber;
     }

     public void setIdNumber(String idNumber) {
          this.idNumber = idNumber;
     }
}
