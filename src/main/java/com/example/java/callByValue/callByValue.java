package com.example.java.callByValue;

public class callByValue {

    public int age;

    public callByValue(int age){
        this.age = age;
    }


    // 자바는 call by value
    // 원시 타입 ->  int 등 stack 영역에 저장됨
    // 참조 타입 ->  객체... 주소는 stack 영역에 저장되고 객체는 Heap 영역에 저장됨
    // -> 따라서 주소값(변수)를 넘기고 그걸 수정하면 해당 주소에 있는 Heap 영역의 값을 수정하기 때문에 변경되는 것처럼 보임
    // -> 하지만 변수가 다른 객체 값을 세팅하면 해당 부분의 주소 값이 변경 되기 때문에 본래의 값을 건드리지 않음


    public static void main(String[] args) {

        int c = 1;
        int d = 2;

        modify(1,2);

        System.out.println(c);
        System.out.println(d);


        callByValue a = new callByValue(10);
        callByValue b = new callByValue(20);

        modify(a, b);


        System.out.println(a);
        System.out.println(b);

        System.out.println(a.age);
        System.out.println(b.age);
    }

    // callByValue(30) 임으로 새로운 주소를 할당 받았고
    // 그걸 바꾼다고 해서 기존의 값이 바뀌지는 않는다.
    public static void modify(callByValue a, callByValue b){

        System.out.println("before-----------");
        System.out.println(a);
        System.out.println(b);
        System.out.println("-----------");

        a.age++;
        b = new callByValue(30);
        b.age++;

        System.out.println("after-----------");
        System.out.println(a);
        System.out.println(b);
        System.out.println("-----------");
    }

    public static void modify(int c, int d){
        c = 5;
        d = 10;
    }

}

