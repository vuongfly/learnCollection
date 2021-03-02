package vn.techmaster.learncollection.repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import vn.techmaster.learncollection.model.Person;
import vn.techmaster.learncollection.service.HashMapService;

@Repository
public class PersonRepositoryCSV implements PersonRepositoryInterface {
  private ArrayList<Person> people;
  @Autowired
  HashMapService hashMapService;

  public PersonRepositoryCSV() {
    people = new ArrayList<>();
    loadData("personsmall.csv");
  }

  private void loadData(String csvFile) {
    try {
      File file = ResourceUtils.getFile("classpath:static/" + csvFile);
      // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
      CsvMapper mapper = new CsvMapper();
      // Dòng đầu tiên sử dụng làm Header
      CsvSchema schema = CsvSchema.emptySchema().withHeader();
      // Cấu hình bộ đọc CSV phù hợp với kiểu
      ObjectReader oReader = mapper.readerFor(Person.class).with(schema);
      Reader reader = new FileReader(file);
      // Iterator đọc từng dòng trong file
      MappingIterator<Person> mi = oReader.readValues(reader);
      while (mi.hasNext()) {
        Person person = mi.next();
        people.add(person);
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  @Override
  public List<Person> getAll() {
    System.out.println(people.toString());
    return people;
  }

  @Override
  public HashMap<String, Long> findTop5Citis() {
    Map<String, Long> mapCityAndCountPeople = people.stream()
        .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));

    LinkedHashMap<String, Long> top5Cities = mapCityAndCountPeople.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(5)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> {
          throw new IllegalStateException();
        }, LinkedHashMap::new));
    return top5Cities;
  }

  @Override
  public HashMap<String, Long> findTop5Jobs() {
    LinkedHashMap<String, Long> top5Jobs = people.stream()
        .collect(Collectors.groupingBy(Person::getJob, Collectors.counting())).entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(5)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> {
          throw new IllegalStateException();
        }, LinkedHashMap::new));
    return top5Jobs;
  }

  @Override
  public HashMap<String, String> findTopJobInCity() {
    return (HashMap<String, String>) people.stream()
        .collect(Collectors.groupingBy(Person::getCity,
            Collectors.collectingAndThen(Collectors.groupingBy(Person::getJob, Collectors.counting()),
                map -> map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey())));
  }

  @Override
  public List<String> getSortedCities() {
    return people.stream().map(Person::getCity).sorted().distinct().collect(Collectors.toList());
  }

  @Override
  public List<String> getSortedJobs() {
    return people.stream().map(Person::getJob).sorted().distinct().collect(Collectors.toList());
  }

  @Override
  public List<String> find5CitiesHaveMostSpecificJob(String job) {

    return people.stream().filter(e -> e.getJob().contains(job))
        .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()))
        // tra ve list gom (city, count)
        .entrySet().stream()
        // lay ra set value va duyet
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        // sap xep theo chieu giam dan
        .limit(5)
        // lay gioi han la 5 phan tu dau tien
        .map(e -> e.getKey())
        // tu top 5 value tra ve top 5 key tuong ung voi value do
        .collect(Collectors.toList());
    // convert thanh list

  }

  @Override
  public List<Person> sortPeopleByFullName() {
    return people.stream().sorted(Comparator.comparing(Person::getFullname).reversed()).collect(Collectors.toList());
  }

  @Override
  public HashMap<String, List<Person>> groupPeopleByCity() {
    Map<String, List<Person>> mapCityAndPeople = people.stream().collect(Collectors.groupingBy(Person::getCity));
    return (HashMap<String, List<Person>>) mapCityAndPeople;
  }

  @Override
  public HashMap<String, List<Person>> groupPeopleByJob() {
    Map<String, List<Person>> mapJobAndPeople = people.stream().collect(Collectors.groupingBy(Person::getJob));
    return (HashMap<String, List<Person>>) mapJobAndPeople;
  }

  @Override
  public HashMap<String, Long> groupJobByCount() {
    Map<String, Long> mapJobAndCountPeople = people.stream().collect(Collectors.groupingBy(Person::getJob, Collectors.counting()));
    return (HashMap<String, Long>) mapJobAndCountPeople;
  }

  @Override
  public HashMap<String, Double> averageJobSalary() {
    Map<String, Double> mapJobAndAVGSalary = people.stream().collect(Collectors.groupingBy(Person::getJob, Collectors.averagingDouble(Person::getSalary)));
    return (HashMap<String, Double>) mapJobAndAVGSalary;
  }

  @Override
  public HashMap<String, Double> top5HighestSalaryCities() {
    Map<String, Double> top5SalaryCities = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.averagingDouble(Person::getSalary)))
    .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
    .limit(5)
    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    return (HashMap<String, Double>) top5SalaryCities;
  }

  @Override
  public HashMap<String, Double> averageJobAge() {
    Map<String, Double> mapJobAndAVGAge = people.stream().collect(Collectors.groupingBy(Person::getJob, Collectors.averagingDouble(Person::getAge)));
    return (HashMap<String, Double>) mapJobAndAVGAge;
  }

  @Override
  public HashMap<String, Double> averageCityAge() {
    Map<String, Double> mapCityAndAVGAge = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.averagingDouble(Person::getAge)));
    return (HashMap<String, Double>) mapCityAndAVGAge;
  }

}
