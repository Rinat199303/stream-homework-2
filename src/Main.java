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
                .filter(person -> person.getSex()== Sex.MAN)
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
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getSex() == Sex.MAN ? person.getAge() <= 65
                        : person.getAge() <= 60)
                .sorted(Comparator.comparing(person ->  person.getFamily()))
                .map(person -> person.getFamily() )
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("People with high education" + peopleForWorkHighEducation);
    }
}

