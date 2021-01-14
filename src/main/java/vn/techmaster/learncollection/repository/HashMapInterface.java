package vn.techmaster.learncollection.repository;

import java.util.HashMap;

public interface HashMapInterface {
    HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm);
    HashMap<String, Integer> sortByKey(HashMap<String, Integer> hm);
    String getKeyOfMaxValueOfMap(HashMap<String, Integer> hm);
}
