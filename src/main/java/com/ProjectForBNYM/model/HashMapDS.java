package com.ProjectForBNYM.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDS {
    public static void main(String[] args) {
        //Data Structure that allows us to store and retrieve it in a constant time provided we know the key {key, value}.
        HashMap<Integer, String> map = new HashMap<Integer, String>();

        map.put(120, " James Avery");
        map.put(21, " Covington Rich");
        map.put(12, " Dakota Jones");
        map.put(13, " Reece Alexander");

        System.out.println(map.get(120));

        //listing all key/value pairs and retrieving the set of entries
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        for(Map.Entry<Integer, String> entry  : entrySet)
        {
            System.out.println(entry.getKey() + entry.getValue());
        }

        //Transferring data between multiple hashmap structures
        //To transfer the data from your previous set to the new one by using putAll() method
        HashMap<Integer, String> map1 = new HashMap<Integer, String>();
        map1.put(1999, " Scott Donald");
        map1.putAll(map);

        Set<Map.Entry<Integer, String>> entrySet1 = map1.entrySet();
        for(Map.Entry<Integer, String> entry  : entrySet1)
        {
            System.out.println(entry.getKey() + entry.getValue());
        }

        //retrieving only keys from HashMap data structure use keySet() method.
        Set<Integer> keySet = map1.keySet();
        for(Integer key  : keySet)
        {
            System.out.println(key);
        }

        //To see if a specific key or value exists use containsKey() or containsValue(). Will return a boolean value (T/F)
        System.out.println(map1.containsKey(20)); //will return false, there is no 20 key
        System.out.println(map1.containsValue(" Dakota Jones")); //will return true, there is " Dakota Jones" value.




    }
}
