package Practice;

import java.util.*;

public class MyMain {
    public static void main(String[] args) {

        // Map<String, String> countryCapitalMap=new HashMap<>();
        // countryCapitalMap.put("india","Delhi");
        // countryCapitalMap.put("japan","Tokyo");
        // countryCapitalMap.put("france","Paris");
        // countryCapitalMap.put("russia","Moscow");
        // System.out.println(countryCapitalMap);
        // countryCapitalMap.entrySet().stream()
        // .sorted((e1,e2) -> e1.getValue().compareTo(e2.getValue()))
        // .collect(Collectors.toMap(k-> k, v->v, (v1,v2) -> v1, LinkedHashMap::new))
        // .forEach((k,v) -> System.out.println("Country: "+k+" Capitol: "+v));

        // List<Observer> obs = new ArrayList<>();
        // obs.add(new Observer1());
        // obs.add(new Observer2());
        // Observable obj = new Observable(obs);
        // obj.notifyAllObservers();

//        System.out.println(SingletonClass.getInstance());
//        System.out.println(SingletonClass.getInstance());
    }
}



// Observable ->

class Observable {
    public List<Observer> observers= new ArrayList<>();
    public Observable(List<Observer> observers) {
        this.observers = observers;
    }
    public void notifyAllObservers() {
        for(Observer observer: observers) {
            observer.received("Received: " + observer.getName());
        }
    }
}

interface Observer {
    String getName();
    void received(String name);
}

class Observer1 implements Observer {
    @Override
    public String getName() {
        return "Observer1";
    }
    @Override
    public void received(String name) {
        System.out.println(name);
    }
}
class Observer2 implements Observer {
    @Override
    public String getName() {
        return "Observer2";
    }
    @Override
    public void received(String name) {
        System.out.println(name);
    }
}

