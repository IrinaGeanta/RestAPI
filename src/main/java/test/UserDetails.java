package test;

import lombok.*;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetails {

    public String name;
    public String city;
    public int age;


/*
    public UserDetails(String name, String city, int age){
        this.name = name;
        this.city = city;
        this.age = age;
    }
    public UserDetails(){

    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(int age) {
        this.age = age;
    }

 */
}
