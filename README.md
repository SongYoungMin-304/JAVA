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



# bean scope
### 빈 스코프는 무엇인가?

- 빈이 사용되어지는 범위
- 앱이 구동되는 동안 한개만 만들어서 쓸 것인지 HTTP 요청마다 생성해서 쓸 것인지 등등

| singleton | (기본값) 스프링 Ioc 컨테이너당 하나의 인스턴스만 사용  |
| --- | --- |
| prototype | 매번 새로운 빈을 정의해서 사용 |
| request | HTTP 라이플 사이클 마다 한개의 빈을 사용, web-aware 컨택스트에서만 사용가능 |
| session | HTTP 세션마다 하나의 빈을 사용, web-aware 컨택스트에서만 사용가능 |
| application | ServletContext 라이프사이클 동안 한개의 빈만사용 |
| websocket | websocket 라이프사이클 안에서 한개의 빈만 사 |

**※ Singleton 스코프의 빈이 prototype의 빈을 주입 받는 경우 prototype 빈의 역할을 제대로 수행하지 못한다.**

```kotlin
@Component
public class Single {
   
   @Autowired
   ProtoType protoType;

   pubulic protoType getProtoType() {

   }
}

-------------------------------------------------------

@Component
@Scope(value = "prototype")
public class ProtoType {

}
```

```kotlin
@Component
@Scope(value = "prototypte", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProtoType {

}
```

- 해당 방식으로 정상적으로 Proto 방식으로 호출되게 할 수 있다.

### 싱글토 프로토 타입 주소값 확인 예재

```java
@Slf4j
@RestController
public class SingletonController {

    @Autowired
    SingleService singleService;

    @Autowired
    ProtoService protoService;

    @RequestMapping("/test")
    public String test(){
        log.info("주소 체크 싱글" + singleService.toString());
        log.info("주소 체크 프로토" + protoService.toString());
        singleService.print();
        return "songyoungmin";
    }

}
```

```java
@Slf4j
@Component
public class SingleService {

    public void print(){
        log.info("songPrint");
    }

}
```

```java
@Slf4j
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProtoService {

    public void print(){
        log.info("songPrint");
    }
}
```

- singleton 안에서 prototype 호출하면 처음 주입된 것이 그래도 사용이 되기 때문에
(proxyMode = ScopedProxyMode.TARGET_CLASS 통해서 정상 동작하도록 처리

```
2023-05-21 23:41:05.816  INFO 28096 --- [nio-8080-exec-1] c.e.j.callByValue.SingletonController    : 주소 체크 싱글com.example.java.callByValue.SingleService@48b33399
2023-05-21 23:41:05.818  INFO 28096 --- [nio-8080-exec-1] c.e.j.callByValue.SingletonController    : 주소 체크 프로토com.example.java.callByValue.ProtoService@489d9df1
2023-05-21 23:41:05.818  INFO 28096 --- [nio-8080-exec-1] c.e.java.callByValue.SingleService       : songPrint
2023-05-21 23:41:07.694  INFO 28096 --- [nio-8080-exec-2] c.e.j.callByValue.SingletonController    : 주소 체크 싱글com.example.java.callByValue.SingleService@48b33399
2023-05-21 23:41:07.695  INFO 28096 --- [nio-8080-exec-2] c.e.j.callByValue.SingletonController    : 주소 체크 프로토com.example.java.callByValue.ProtoService@6f8863f6
2023-05-21 23:41:07.695  INFO 28096 --- [nio-8080-exec-2] c.e.java.callByValue.SingleService       : songPrint
2023-05-21 23:41:08.423  INFO 28096 --- [nio-8080-exec-3] c.e.j.callByValue.SingletonController    : 주소 체크 싱글com.example.java.callByValue.SingleService@48b33399
2023-05-21 23:41:08.423  INFO 28096 --- [nio-8080-exec-3] c.e.j.callByValue.SingletonController    : 주소 체크 프로토com.example.java.callByValue.ProtoService@2e56da38
```

- 싱글톤은 주소값이 변경되지 않는다.
- 프로토타입은 주소값이 호출될 때마다 변경되는 것을 알 수 있다.
