package io.jacy.drafts;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Java基础测试类
 *
 * @author Jacy
 */
public class JavaBasic {
    private int a;

    public JavaBasic() {
    }

    public JavaBasic(int a) {
        // 如果要调用this(), 则必须放在有参构造方法的第1行调用
        this();

        this.a = a;
    }

    /**
     * 测试强转与取模
     * == from jack
     */
    void testMod() {
        long temp = (int) 3.9;
        temp %= 2;
        System.out.println(temp);
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(nullEqNull());
//        bitMove(321);

//        testMapInitParams();

//        stream();
//        completeFuture();

//        testCglib();
//        maxIntVal();
        threadState();
    }


    static boolean nullEqNull() {
        return null == null;
    }

    static void testMapInitParams() {
        int size = 128;
        Map<String, Object> map1 = new HashMap<>(size, 1f);
        Map<String, Object> map2 = new HashMap<>();
        String[] strs = new String[size];
        for (int i = 0; i < size; i++) {
            strs[i] = i + "";
        }
        map1.put(strs[0], strs[0]);
        map2.put(strs[0], strs[0]);

        long start = System.nanoTime();
        for (String str : strs) {
            map1.put(str, str);
        }
        System.out.println("1: " + (System.nanoTime() - start));
        start = System.nanoTime();
        for (String str : strs) {
            map2.put(str, str);
        }
        System.out.println("2: " + (System.nanoTime() - start));
        System.out.println(map1.size());
        System.out.println(map2.size());
    }

    static void bitMove(int cap) {
        System.out.println(-3 >> 16);
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(Integer.toBinaryString(-3 >>> 16));
        System.out.println(Integer.toBinaryString(-3 >> 16));
        System.out.println(Integer.toBinaryString(27));


        int n = cap - 1;
        System.out.println("1---: " + n);
        n |= n >>> 1;
        System.out.println("2---: " + n);
        n |= n >>> 2;
        System.out.println("3---: " + n);
        n |= n >>> 4;
        System.out.println("4---: " + n);
        n |= n >>> 8;
        System.out.println("5---: " + n);
        n |= n >>> 16;
        System.out.println("6---: " + n);
    }

    static void sleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(100);
    }

    static void system() {
        System.identityHashCode(new Object());
    }

    static void stream() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("1");
        objects.add("2");
        objects.add("3");
        objects.add("4");
        objects.add("1");
        objects.add("2");
        objects.add("3");
        objects.add("4");

        Stream<Object> objectStream = objects.parallelStream();
//        objectStream.forEach(o -> System.out.println(Thread.currentThread().getName()));
//        System.out.println(objectStream.isParallel());
//        System.out.println("==================");

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
//        threadPoolExecutor.submit(() -> {
//            objects.parallelStream().forEach(o -> System.out.println(Thread.currentThread().getName()));
//        });


        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            integers.add(i);
        }

        List<String> strings = integers.parallelStream().collect(ArrayList::new, (arrayList, i) -> {
            System.out.println(Thread.currentThread().getName());
            arrayList.add(i.toString());
        }, List::addAll);

        System.out.println(strings);
    }

    static void completeFuture() throws Exception {
//        CompletableFuture<Object> objectCompletableFuture = new CompletableFuture<>();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        });
        stringCompletableFuture.thenAccept(s -> {
            System.out.println("3333333: " + s);
        });
        String s = stringCompletableFuture.get();
        System.out.println("=============");

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("222");
            System.out.println("223");
        });
        Void unused = voidCompletableFuture.get();
        System.out.println(unused);

    }

    static class S {
        void a() {
            System.out.println("aaaa");
        }

        void b() {
            System.out.println("bbbb");
        }
    }

    static class Mmi implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("-------1 " + method.getName() + "-------");
            methodProxy.invokeSuper(o, objects);
            System.out.println("-------2 " + methodProxy.getSuperName() + "-------");
            return null;
        }
    }

    static void testCglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(S.class);

        Mmi mmi = new Mmi();
        enhancer.setCallback(mmi);
        S s = (S) enhancer.create();
        System.out.println("=====3 " + s.getClass().getName());
        s.a();
    }

    static void maxIntVal() {
        System.out.println(Integer.toBinaryString(1 << 30));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    }

    static void threadState() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1--- " + thread.getState());
        thread.interrupt();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("2--- " + thread.getState());

        thread.start();
        System.out.println("3--- " + thread.getState());
    }
}
