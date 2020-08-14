package io.jacy.drafts;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

/**
 * @author Jacy
 */
public class Java8Function {
    static {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);

        Function<String, Integer> f = o1 -> map.getOrDefault(o1, 0);
        BiFunction<String, String, Integer> bf = (o1, o2) -> map.getOrDefault(o1, 0) - map.getOrDefault(o2, 0);

        Consumer<String> con1 = System.out::println;
        BiConsumer<String, Integer> bc1 = (s, i) -> System.out.println(s + i);

        Predicate<String> p1 = StringUtils::isNotBlank;
        Supplier<String> s1 = () -> "1111";
        IntSupplier is1 = () -> 1;


        ToIntBiFunction<String, Double> tibf1 = (s, d) -> s.hashCode() | d.hashCode();
    }
}
