package listAMapOnThread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * 解决线程不安全的问题
 * 异常 java.util.ConcurrentModificationException
 */

public class test {


    public static void main(String[] args) {

        /**
         * List 解决线程不安全的问题
         */
//        arrayListNotSafe();
//        vectorIsSafe();
//        synchronizedList();
//        copyOnWriteArrayList();
        /**
         * HashSet
         */
//        hashSetIsNotSafe();
//        synchronizedSet();
//        copyOnWriteArraySet();

        /**
         * hashMap
         */
//        hashMapIsNotSafe();
//        synchronizedMap();
        concurrentHashMap();

    }

    /**
     * ArrayList 是线程不安全的
     */
    public static void arrayListNotSafe() {
        List list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * Vector 是线程安全的，但效率低
     */
    public static void vectorIsSafe() {
        List list = new Vector();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 9));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 使用集合的方法解决arrayList线程不安全的问题
     */
    public static void synchronizedList() {
        List list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 读写分离，使用锁的机制
     */
    public static void copyOnWriteArrayList() {
        List list = new CopyOnWriteArrayList();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * HashSet 是线程不安全的
     */
    public static void hashSetIsNotSafe() {
        Set set = new HashSet();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 9));
                System.out.println(set.toString());
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 集合工具类方式
     */
    private static void synchronizedSet() {
        Set set = Collections.synchronizedSet(new HashSet());

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 9));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 读写分离
     */
    private static void copyOnWriteArraySet() {
        Set set = new CopyOnWriteArraySet();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {

                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * hashMap 是线程不安全的
     */
    private static void hashMapIsNotSafe() {
        Map map = new HashMap();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0, 1), UUID.randomUUID().toString().substring(0, 2));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }


    /**
     * 集合方法
     */
    private static void synchronizedMap() {
        Map map = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(1, 2), UUID.randomUUID().toString().substring(2, 3));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * Map
     */
    private static void concurrentHashMap() {
        Map map = new ConcurrentHashMap();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(9,11),UUID.randomUUID().toString().substring(1,3));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
