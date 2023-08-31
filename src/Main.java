import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names =
                Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families =
                Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        /**
         * Заводим переменную тип Collection хранящую значение Person
         * И заполняем рандомными значениями
         */
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        /**
         * Вычислиям количество людей младше 18 лет
         */
        
        long personCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество не совереннолетних " + personCount);
        /**
         * выявляем граждан в возрасте от 18 до 27 мужчин призывного возраста
         * и сортируем по фамилии
         */
        List<String> calledPeople = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() <= 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("Люди подлежащие призыву в армию " + calledPeople);
        /**
         * Выявляем людей с высшим образованием до пенсионного возраста и сортируем по фамилии
         */
        List<String> peopleForWorkHighEducation = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 65 && person.getSex().equals(Sex.MAN)
                        || person.getSex().equals(Sex.WOMAN) && person.getAge() <= 60 && person.getAge() >= 18)
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("People with high education" + peopleForWorkHighEducation);
    }
}

class Person {
    private String name;
    private String family;
    private Integer age;
    private Sex sex;
    private Education education;

    public Person(String name, String family, int age, Sex sex, Education education) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.sex = sex;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public Integer getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public Education getEducation() {
        return education;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", education=" + education +
                '}';
    }
}