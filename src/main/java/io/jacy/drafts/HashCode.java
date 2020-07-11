package io.jacy.drafts;

import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;

/**
 * HashCode 相关
 *
 * @author Jacy
 */
public class HashCode {

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equal(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name, age);
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("111", 100);
        Person p2 = new Person("111", 100);

        Map<Person, Integer> map = new HashMap<>(4, 1f);

        map.put(p1, 1);
        map.put(p2, 1);


        System.out.println(map.size());
    }
}
