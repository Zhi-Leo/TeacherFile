package test;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Javabasicdemo {
    public static void main(String[] args) {
        String str="hello world";
        System.out.println(str.length());
        System.out.println(str.equals("world"));

        ArrayList<String> name = new ArrayList<>();
        name.add("张三");
        name.add("李四");
        name.add("王五");
        name.add("1");
        System.out.println(name.size());
        for(String a:name )
        {
            System.out.println(a);
        }
        for (int i = 0; i < name.size(); i++) {
            System.out.println(name);
        }
        ArrayList<person>  persons = new ArrayList<>();
        persons.add(new person("张三",18));
        persons.add(new person("李四",28));
        persons.add(new person("王五",38));
        for(person p:persons){
            System.out.println(p.getName()+"---"+p.getAge());
        }
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i).getName()+"---"+persons.get(i).getAge());
        }




    }

}

class person{
    private String name;
    private int age;

    public person() {
    }

    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}




class fu{

}