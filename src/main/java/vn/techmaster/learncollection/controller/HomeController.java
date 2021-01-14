package vn.techmaster.learncollection.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.learncollection.model.Person;
import vn.techmaster.learncollection.repository.PersonRepositoryInterface;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private PersonRepositoryInterface personRepository;

    @GetMapping
    public String getHome(Model model) {
        return "home";
    }

    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<Person> listPerson = personRepository.getAll();
        model.addAttribute("list", listPerson);
        return "listPerson";
    }


    @GetMapping("/getSortedJobs")
    public String getSortedJobs(Model model) {
        List<String> listJobs = personRepository.getSortedJobs();
        model.addAttribute("list", listJobs);
        return "listString";
    }

    @GetMapping("/getSortedCities")
    public String getSortedCities(Model model) {
        List<String> listCities = personRepository.getSortedCities();
        model.addAttribute("list", listCities);
        return "listString";
    }
    
    @GetMapping("/groupPeopleByCity")
    public String groupPeopleByCity(Model model) {
        HashMap<String, List<Person>> mapPeopleByCity = personRepository.groupPeopleByCity();
        model.addAttribute("map", mapPeopleByCity);
        return "mapStringList";
    }

    @GetMapping("/groupJobByCount")
    public String groupJobByCount(Model model) {
        HashMap<String, Long> mapJobByCount = personRepository.groupJobByCount();
        model.addAttribute("map", mapJobByCount);
        return "mapStringLong";
    }

    @GetMapping("/findTop5Jobs")
    public String findTop5Jobs(Model model) {
        HashMap<String, Long> mapTop5Jobs = personRepository.findTop5Jobs();
        model.addAttribute("map", mapTop5Jobs);
        return "mapStringLong";
    }

    @GetMapping("/findTop5Cities")
    public String findTop5Cities(Model model) {
        HashMap<String, Long> mapTop5Cities = personRepository.findTop5Citis();
        model.addAttribute("map", mapTop5Cities);
        return "mapStringLong";
    }

    @GetMapping("/findTopJobInCity")
    public String findTopJobInCity(Model model) {
        HashMap<String, String> mapTopJobInCity = personRepository.findTopJobInCity();
        model.addAttribute("map", mapTopJobInCity);
        return "mapStringLong";
    }
    
    @GetMapping("/averageJobSalary")
    public String averageJobSalary(Model model) {
        HashMap<String, Double> mapTopJobInCity = personRepository.averageJobSalary();
        model.addAttribute("map", mapTopJobInCity);
        return "mapStringDouble";
    }

    @GetMapping("/top5HighestSalaryCities")
    public String top5HighestSalaryCities(Model model) {
        HashMap<String, Double> mapTop5HighestSalaryCities = personRepository.top5HighestSalaryCities();
        model.addAttribute("map", mapTop5HighestSalaryCities);
        return "mapStringDouble";
    }

    @GetMapping("/averageJobAge")
    public String averageJobAge(Model model) {
        HashMap<String, Double> mapAverageJobAge = personRepository.averageJobAge();
        model.addAttribute("map", mapAverageJobAge);
        return "mapStringDouble";
    }

    @GetMapping("/averageCityAge")
    public String averageCityAge(Model model) {
        HashMap<String, Double> mapAverageCityAge = personRepository.averageCityAge();
        model.addAttribute("map", mapAverageCityAge);
        return "mapStringDouble";
    }

    @GetMapping("/find5CitiesHaveMostSpecificJob")
    public String find5CitiesHaveMostSpecificJobGet(Model model) {
        return "specificJob";
    }

    @PostMapping("/find5CitiesHaveMostSpecificJob")
    public String find5CitiesHaveMostSpecificJobPost(@RequestParam("job") String job, Model model) {
        List<String> list5CitiesHaveMostSpecificJob = personRepository.find5CitiesHaveMostSpecificJob(job);
        model.addAttribute("job", job);
        model.addAttribute("list", list5CitiesHaveMostSpecificJob);
        return "listString";
    }
}

