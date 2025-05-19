package org.d13.test;

import java.util.HashMap;

public class MyTest {
    /**
     * 测试 Map.computeIfAbsent方法的作用。
     * 预计作用： 如果key不存在，或者为null，重新计算，并将计结果放入map
     *          如果key存在，
     * @param args
     */
    public static void main(String[] args) {
        // 创建一个 HashMap
        HashMap<String, Integer> prices = new HashMap<>();

        // 往HashMap中添加映射项
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // 计算 Shirt 的值
        int shirtPrice = prices.computeIfAbsent("Shirt", key -> 280);
        System.out.println("Price of Shirt: " + shirtPrice);

        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);
        //如果key存在
        int  bagPrice = prices.computeIfAbsent("Bag", key -> 333);
        System.out.println("Price of  Bag : " + bagPrice);
        // 输出更新后的HashMap
        System.out.println("Updated HashMap2: " + prices);
        //测试null值
        prices.put("xxx", null);
        System.out.println("HashMap: " + prices);
        int  xxxPrice = prices.computeIfAbsent("xxx", key -> 999);
        System.out.println("Price of  xxx : " + bagPrice);
        System.out.println("Updated HashMap3: " + prices);
    }
}