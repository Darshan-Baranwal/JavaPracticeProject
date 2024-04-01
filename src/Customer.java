public class Customer {
    private String name;
    private int age;

    public Customer(String darshan, int i) {
        this.age = i;
        this.name = darshan;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return name.length()*age;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(obj.getClass().getName());
    }
    // hashcode - > name and age-> 10-20 ->
}
