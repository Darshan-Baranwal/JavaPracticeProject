package CompanyWiseQuestions.BlackRock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CityInfo {
    private String city;
    private String state;

    public CityInfo(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static void  main(String[] args){
        List<CityInfo> cities = new ArrayList<>();
        cities.add(new CityInfo("Noida", "UP"));
        cities.add(new CityInfo("Gurgaon", "HR"));
        cities.add(new CityInfo("Mumbai", "MH"));
        cities.add(new CityInfo("Nagpur", "MH"));
        cities.add(new CityInfo("Lucknow", "UP"));
        // get the last city of each state. output like {HR=Gurgaon, MH=Nagpur, UP=Lucknow}
        Map<String, String> result = cities.stream()
                .collect(Collectors.toMap(CityInfo::getState, CityInfo::getCity, (o,n)-> n, LinkedHashMap::new));
        System.out.println(result);


    }

}



