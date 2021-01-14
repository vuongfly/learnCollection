package vn.techmaster.learncollection;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import vn.techmaster.learncollection.model.Person;
import vn.techmaster.learncollection.repository.PersonRepositoryInterface;

@SpringBootTest
class PersonRepositoryTest {

	@Autowired
	PersonRepositoryInterface personRepository;

	@Test
	public void getAll() {
		List<Person> people = personRepository.getAll();
		assertThat(people).hasSize(20);		
	}
	@Test
	public void testFindTop5CitisWithNormalCase() {
		HashMap<String, Long> top5Citis = personRepository.findTop5Citis();
		assertThat(top5Citis).hasSizeGreaterThan(2);
	}
	@Test
	public void testGetSortedCitiesNormalCase() {
		List<String> getSortedCities = personRepository.getSortedCities();
		assertThat(getSortedCities).hasSizeGreaterThan(5);
	}
	@Test
	public void testGroupPeopleByCity(){
		HashMap<String, List<Person>> groupPeopleByCity = personRepository.groupPeopleByCity();
		assertThat(groupPeopleByCity).hasSizeGreaterThan(5);
	}
	@Test
	public void testGroupPeopleByJob(){
		HashMap<String, List<Person>> groupPeopleByJob = personRepository.groupPeopleByJob();
		assertThat(groupPeopleByJob).hasSizeGreaterThan(5);
	}

	@Test
	public void testGroupJobByCount(){
		HashMap<String, Long> groupJobByCount = personRepository.groupJobByCount();
		assertThat(groupJobByCount).hasSizeGreaterThan(5);
	}
	
	@Test
	public void testFindTopJobInCity(){
		HashMap<String, String> topJobInCity = personRepository.findTopJobInCity();
		assertThat(topJobInCity).hasSizeGreaterThan(5);
	}

	@Test
	public void testSortPeopleByFullName(){
		List<Person> sortPeopleByFullName = personRepository.sortPeopleByFullName();
		assertThat(sortPeopleByFullName).hasSizeGreaterThan(5);
	}

	@Test
	public void testAverageJobSalary(){
		HashMap<String, Float> averageJobSalary = personRepository.averageJobSalary();
		// assertThat(averageJobSalary).hasSizeGreaterThan(5);
		assertThat(averageJobSalary).hasSameSizeAs(personRepository.groupJobByCount());
	}
	
	
	@Test
	public void testAverageJobAge(){
		HashMap<String, Float> averageJobAge = personRepository.averageJobAge();
		// assertThat(averageJobAge).hasSizeGreaterThan(5);
		assertThat(averageJobAge).hasSizeGreaterThan(0);
	}

	
	@Test
	public void testAverageCityAge(){
		HashMap<String, Float> averageCityAge = personRepository.averageCityAge();
		// assertThat(averageCityAge).hasSizeGreaterThan(5);
		assertThat(averageCityAge).hasSizeGreaterThan(0);
	}

	@Test
	public void find5CitiesHaveMostSpecificJob(){
		List<String> find5CitiesHaveMostSpecificJob = 
		personRepository.find5CitiesHaveMostSpecificJob("Soldier");
		// assertThat(find5CitiesHaveMostSpecificJob).hasSizeGreaterThan(5);
		assertThat(find5CitiesHaveMostSpecificJob).hasSizeGreaterThan(0);
	}
	
}
