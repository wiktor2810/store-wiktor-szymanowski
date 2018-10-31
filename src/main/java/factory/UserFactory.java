package factory;

import org.apache.commons.lang.RandomStringUtils;
import template.User;

public class UserFactory {

    public User randomUser(){
        String firstName = randomStringOfLenght5();
        String lastName = randomStringOfLenght5();
        String address = randomStringOfLenght5();
        String city =randomStringOfLenght5();
        String stateProvince = randomStringOfLenght5();
        int phone = Integer.parseInt(randomIntOfLenght9());
        String email = randomStringOfLenght5() + "@wp.pl";

        return new User(firstName, lastName, address, city, stateProvince, phone, email);
    }

    public String randomStringOfLenght5(){
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomIntOfLenght9(){
        return RandomStringUtils.randomNumeric(9);
    }


}
