# JAVA

# Call By Value

### Call by Value

- Call by Value 는 메서드를 호출할 때 값을 넘겨주기 때문에 Pass by Value 라고도 부릅니다.
- 메서드를 호출하는 호출자의 변수와 호출 당하는 수신자의 파리미터는 복사된 **서로 다른 변수**
- 값만을 전달하기 때문에 수신자의 파라미터를 수정해도 호출자의 변수에는 아무런 영향X

### Call by Reference

- Call by Reference 는 참조(주소)를 직접 전달하면 Pass By Referenct 라고도 부름
- 참조를 직접 넘기기 때문에 호출자의 변수와 수신자의 파라미터는 **완전히 동일한 변수**
- 메서드 내에서 파라미터를 수정하면 그대로 원본 변수에도 반영

## **Java 는 오직 Call by Value 로만 동작!**

### 1) JVM 메모리에 변수가 저장되는 위치

![image](https://github.com/SongYoungMin-304/JAVA/assets/56577599/673d2d87-aedc-44be-8798-860857a599f9)


- 원시 타입(Primitive Type) 은 Stack 영역에 변수와 함께 저장
- 참조 타입(Referenct Type) 객체는 Heap 영역에 저장되고 Stack 영역에 있는 변수가 **객체의 주소값**을 갖고 있습니다.

### 2) 원시 타입(Primitive Type) 전달

```java
public class callByValue {

    public int age;
    
    public callByValue(int age) {
         this.age = age;
    }

    public static void main(String[] args){
        int c = 1;
        int d = 2;

        modify(1, 2);

        System.out.println(c);
        System.out.println(d);
    }

    public static void modify(int c, int d){
      c = 5;
      d = 10;
    }
}

// 결과 1, 2 
```

![image](https://github.com/SongYoungMin-304/JAVA/assets/56577599/6e0b7e54-e1ce-4b95-b797-12f819e76276)


- stack 내부에 test() 와 modify() 라는 영역이 나뉘어져 있고 거기에 동일한 이름을 가지 변수 a,b가 존재
- modify() 영역의 값을 바꿔도 test() 영역의 변수는 변화가 없습니다.

### 3) 참조 타입(Reference Type) 전달

```java
public class callByValue {

    public int age;
    
    public callByValue(int age) {
         this.age = age;
    }

    public static void main(String[] args){
 
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
}

// new callByValue(30) 을 하는 순간 b는 다른 주소값을 가지게 된

before-----------
com.example.java.callByValue.callByValue@300ffa5d
com.example.java.callByValue.callByValue@1f17ae12
-----------
after-----------
com.example.java.callByValue.callByValue@300ffa5d
com.example.java.callByValue.callByValue@4d405ef7
-----------
com.example.java.callByValue.callByValue@300ffa5d
com.example.java.callByValue.callByValue@1f17ae12
```

![image](https://github.com/SongYoungMin-304/JAVA/assets/56577599/f8fdfebd-7934-42ba-b825-f4160e2d0f73)

![image](https://github.com/SongYoungMin-304/JAVA/assets/56577599/9f3b2da8-2aeb-4929-9411-0d311be15a3a)

![image](https://github.com/SongYoungMin-304/JAVA/assets/56577599/84563ddb-1e62-4760-86a9-d085e280602a)

![image](https://github.com/SongYoungMin-304/JAVA/assets/56577599/10928044-962b-4d80-b2f4-0404c9a8cddf)


## 결론

결국 주소값을 넘기는 게 결국 Call by Reference 아닌가?
→ Call by Reference는 참조 자체를 넘기기 때문에 새로운 객체를 할당하면 원본 변수도 영향 있음

**가장 큰 핵심은 호출자 변수와 수신자 파라미터는 Stack 영역 내에서 각각 존재하는 다른 변수**
