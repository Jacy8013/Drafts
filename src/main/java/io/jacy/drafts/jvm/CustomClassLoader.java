package io.jacy.drafts.jvm;

/**
 * @author Jacy
 */
public class CustomClassLoader extends ClassLoader {

    /**
     * 重写该方法可以打破双亲委派机制， 因为该机制通过模板方法固定在默认设计中
     * <p>
     * 但非特定情况不需要打破
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 源码中, 先查缓存,命中返回, 未命中交给parent
        return super.loadClass(name);
    }

    /**
     * 不需要打破双亲机制的情况下, 只需要重写该方法实现具体逻辑即可
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 查看指定的parent classLoader
        System.out.println(getParent());

        // TODO 自己加载类逻辑, 可以自定义对class文件加密解密(该方法解密, 加密在编译时执行)
        return super.findClass(name);
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        new CustomClassLoader().findClass("");

        Throwable t = new Exception("bbb", new RuntimeException("aaa"));
        t.printStackTrace();
        System.out.println("=====================");

        Throwable rootCause = t;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }

        rootCause.printStackTrace();
        System.out.println("=====================");
        t.printStackTrace();
    }
}
