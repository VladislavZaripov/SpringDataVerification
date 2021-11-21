package entity;

import service.CheckCountrySinger;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@CheckCountrySinger
public class SingerThree {

    @NotNull
    @Size(min = 2, max = 60, message = "---@Size---")
    private String firstName;

    private String lastName;

    @NotNull(message = "---@NotNull---")
    private Genre genre;

    private Gender gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @AssertTrue(message = "---@AssertTrue--- Error! Individual customer should have gender and last name defined")
    public boolean isCountrySinger() {
        return genre == Genre.COUNTRY;
    }

    @Override
    public String toString() {
        return "SingerThree{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genre=" + genre +
                ", gender=" + gender +
                '}';
    }
}