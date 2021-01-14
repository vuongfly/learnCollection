package vn.techmaster.learncollection.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;



import lombok.Data;

@Data
public class Person {
  private int id;
  private String fullname;
  private String job;
  private String gender;
  private String city;
  private int salary;
  private Date birthday;
  private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");

  public int getAge(){
    Date safeDate = new Date(birthday.getTime());
    LocalDate birthDayInLocalDate = safeDate.toInstant()
    .atZone(ZoneId.systemDefault())
    .toLocalDate();
    return Period.between(birthDayInLocalDate, LocalDate.now()).getYears();
  }

  public void setBirthday(String birthday) {
    try {
      this.birthday = dateFormatter.parse(birthday);
    } catch (ParseException e) {      
      e.printStackTrace();
    }
  }
  
}