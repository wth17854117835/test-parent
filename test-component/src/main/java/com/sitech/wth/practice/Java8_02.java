package com.sitech.wth.practice;

import com.sitech.wth.bean.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wangth_oup
 * @date: 2020-08-07 14:33
 * @description: Optional类与Stream的使用
 * Optional 类(java.util.Optional) 是一个容器类，代表一个值存在或不存在，原来用 null 表示一个值不存在，
 * 现在 Optional 可以更好的表达这个概念。并且可以避免空指针异常。
 **/
public class Java8_02 {

    /**
     * Optional.of(T t) : 创建一个 Optional 实例。
     * Optional.empty() : 创建一个空的 Optional 实例。
     * Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例。
     * isPresent() : 判断是否包含值。
     * orElse(T t) : 如果调用对象包含值，返回该值，否则返回t。
     * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值。
     * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()。
     * flatMap(Function mapper):与 map 类似，要求返回值必须是Optional。
     */
    public static void main(String[] args) {

        //------------1.创建Optional类---------------
        Optional<String> empty = Optional.empty();
        String name = "wangth";
        Optional<String> opt = Optional.of(name);

        //------------2.isPresent()检查Optional对象中是否有值---------------
        boolean b = opt.isPresent();//有值非空返回true
        //java8之前，检查空值
//        if(name != null){
//            System.out.println(name.length());
//        }
        //Java8中，我们就可以使用如下方式来检查空值了
        opt.ifPresent(name1 -> System.out.println(name1.length()));

        String name1 = null;
//        Optional<String> opt1 = Optional.of(name1);
        Optional<String> opt2 = Optional.ofNullable(name1);
        System.out.println(opt2);

        //------------3.orElse()和orElseGet()------------
        String nullName = null;
        String name2 = Optional.ofNullable(nullName).orElse("wangth3");
        String name3 = Optional.ofNullable(nullName).orElseGet(() -> "wangth3");

        //当Optional中含有值，orElse()照常执行。所以可以看到，当值存在时，orElse相比于orElseGet，多创建了一个对象
        String notNullName = "wangth";
//        String name4 = Optional.ofNullable(nullName).orElse(getDefaultName());
//        String name5 = Optional.ofNullable(nullName).orElseGet(Java8_02::getDefaultName);
        System.out.println("Using orElse:");
        String name4 = Optional.ofNullable(notNullName).orElse(getDefaultName());
        System.out.println("Using orElseGet:");
        String name5 = Optional.ofNullable(notNullName).orElseGet(Java8_02::getDefaultName);
        System.out.println("name4:=="+name4+";name5:=="+name5);

        //------------4.orElseThrow():当遇到一个不存在的值的时候，并不返回一个默认值，而是抛出异常。------------
//        String name6 = Optional.ofNullable(nullName).orElseThrow( IllegalArgumentException::new);

        //------------5.get()方法表示是Optional对象中获取值。------------
        String name7 = opt.get();

        //------------6.filter():接收一个函数式接口，当符合接口时，则返回一个Optional对象，否则返回一个空的Optional对象。------------
        boolean isWangth = opt.filter(n -> "wangth".equals(name)).isPresent();
        System.out.println(isWangth);
        boolean isWangth001 = opt.filter(n -> "wangth001".equals(name)).isPresent();
        System.out.println(isWangth001);
        boolean wangth = filterPersonByOptional(new Person("wangth", 18));
        System.out.println(wangth);
        boolean zhoucx = filterPersonByOptional(new Person("zhoucx", 28));
        System.out.println(zhoucx);

        //------------7.map():如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()。------------
        List<String> names = Arrays.asList("wangth001", "wangth002", "", "wangth003", "", "wangth004");
        Optional<List<String>> listOptional = Optional.of(names);
        int size = listOptional
                .map(List::size)
                .orElse(0);
        System.out.println(size);

        //------------8.flatMap()与map()类似，要求返回值必须是Optional------------
        Person person = new Person("wangth", 18);
        Optional<Person> personOptional = Optional.of(person);
        String s = personOptional.map(Person::getName).orElse("zhoucx");
        System.out.println(s);
        String s1 = personOptional.flatMap(m -> Optional.ofNullable(m.getName())).orElse(null);
        System.out.println(s1);

        //------------Stream-----------------
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("张三", 18));
        personList.add(new Person("赵四", 25));
        personList.add(new Person("王五", 30));
        personList.add(new Person("马六", 50));
        personList.add(new Person("周七", 80));
        personList.stream()
                .filter(p -> p.getAge() > 20)
                .filter((p) -> {
                    System.out.println("Stream API 中间操作");
                    return p.getAge() < 80;
                })
                .limit(2)
                .collect(Collectors.toList())
                .forEach((p) -> System.out.println(p.toString()));
        personList.stream()
                .filter(p -> "王五".equals(p.getName()))
                .map(Person::getName)
//                .map(p -> p.getName())
                .forEach(System.out::println);

        //把流中元素收集到List
        List<Person> collect = personList.stream().collect(Collectors.toList());
        //把流中元素收集到Set
        Set<Person> collect1 = personList.stream().collect(Collectors.toSet());
        //把流中元素收集到创建的集合
        ArrayList<Person> collect2 = personList.stream().collect(Collectors.toCollection(ArrayList::new));
        //计算流中元素的个数
        Long collect3 = personList.stream().collect(Collectors.counting());
        //对流中元素的整数属性求和
        Integer collect4 = personList.stream().collect(Collectors.summingInt(Person::getAge));
        //收集流中Integer属性的统计值
        IntSummaryStatistics collect5 = personList.stream().collect(Collectors.summarizingInt(Person::getAge));
        int min = collect5.getMin();//最小值
        int max = collect5.getMax();//最大值
        long sum = collect5.getSum();//总和
        long count = collect5.getCount();//数量
        double average = collect5.getAverage();//平均值
        //计算流中元素Integer属性的平均值
        Double collect6 = personList.stream().collect(Collectors.averagingInt(Person::getAge));
        //连接流中每个字符串
        String collect7 = personList.stream().map(Person::getName).collect(Collectors.joining());
        //根据比较器选择最大值
        Optional<Person> collect8 = personList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)));
        Integer min1 = personList.stream().map(Person::getAge).min(Integer::compare).get();
        //根据比较器选择最小值
        Optional<Person> collect9 = personList.stream().collect(Collectors.minBy(Comparator.comparingInt(Person::getAge)));

        //排序（年龄正序）
        Collections.sort(personList, (p1,p2) -> {
            if(p1.getAge() == p2.getAge()){
                return p1.getName().compareTo(p2.getName());
            }
            return Integer.compare(p1.getAge(), p2.getAge());
        });

    }

    public static String getDefaultName() {
        System.out.println("Getting Default Name");
        return "wangth3";
    }

    /**
     * 过滤年龄在25-35之间的人
     * @param person
     * @return
     */
    public static boolean filterPersonByOptional(Person person){
        return Optional.ofNullable(person)
                .map(Person::getAge)
                .filter(p -> p >= 25)
                .filter(p -> p <= 35)
                .isPresent();
    }
}
