package vn.techmaster.learncollection.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import vn.techmaster.learncollection.repository.HashMapInterface;

@Service
public class HashMapService implements HashMapInterface {

    

	@Override
    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // This func from https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }

    @Override
    public HashMap<String, Integer> sortByKey(HashMap<String, Integer> hm) {
        // This func from https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getKey()).compareTo(o2.getKey()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }

    @Override
    public String getKeyOfMaxValueOfMap(HashMap<String, Integer> hm) {
        return Collections.max(hm.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    
    
}
