package io.jacy.drafts;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.*;

/**
 * TreeMap 比较器使用
 *
 * @author Jacy
 */
public class TreeMapComparator {
    public static void main(String[] args) {
        Map<String, Integer> priorityMap = new HashMap<>();
        priorityMap.put("1", 1);
        priorityMap.put("2", 2);
        priorityMap.put("3", 2);
        priorityMap.put("4", 1);
        priorityMap.put("5", 3);
        priorityMap.put("6", 3);


        Comparator<String> c = Comparator.comparingInt(o -> priorityMap.getOrDefault(o, 0));
        c.thenComparing(String::compareTo);


        Comparator<String> c1 = Comparator
                .<String, Integer>comparing(s -> priorityMap.getOrDefault(s, 0))
                .thenComparing(String::compareTo);

//        Comparable<String> cb = o -> o.compareTo();
//        List<CusVo> vos = new ArrayList<CusVo>() {
//            {
//                add(new CusVo("1"));
//                add(new CusVo("3"));
//                add(new CusVo("2"));
//                add(new CusVo("6"));
//            }
//        };
//        vos.sort(c);
//        System.out.println(vos);

        Map<String, String> result = new TreeMap<>(c1);
        result.put("1", "1");
        result.put("3", "3");
        result.put("2", "2");
        result.put("6", "6");
//        Iterator<Map.Entry<CusVo, Integer>> iterator = priorityMap.entrySet().iterator();
//        int i = 0;
//        while (iterator.hasNext()) {
//            result.put((i++) + "", iterator.next();
//        }
//        for (int i = 0; i < set.size(); i++) {
//            result.putAll();
//        }
//
//        System.out.println(new ToStringBuilder(result).toString());
        System.out.println(ToStringBuilder.reflectionToString(result));
    }

    static class CusVo {
        private String name;

        public CusVo(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
