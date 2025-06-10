package com.it.util;
/*
学习类与继承
 */

public class javabasicdemo2 {
    public static void main(String[] args) {
        Cat cat=new Cat();
        Dog dog=new Dog();


        cat.makeSound();
        dog.makeSound();
        cat.breath();
        dog.breath();


        dog.lookHome();
        cat.catchMouse();

    }
}


interface Animal{
    void makeSound();//声音

}

class Manmal{
    public void breath(){
        System.out.println("呼吸");

    }
}

class Dog extends Manmal implements Animal{
    @Override
    public void makeSound() {
        System.out.println("汪汪汪");
    }
    //看家
    public void lookHome(){
        System.out.println("看家");
    }
}

class Cat extends Manmal implements Animal{
    @Override
    public void makeSound() {
        System.out.println("喵喵喵");
    }
    //抓老鼠
    public void catchMouse(){
        System.out.println("抓老鼠");
    }
}
