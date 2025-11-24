package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
class JsonData {
    private int number;

    public int getNumber() { // json 데이터가 필요하면 변환을 해야하니까 필드가 있고 getter랑 setter가 필요함
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

class UserData {
    private String name;
    private int age;

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

@RestController
public class HelloController {
    @GetMapping(value="/hello", produces = MediaType.TEXT_PLAIN_VALUE) // 생산하는 데이터 명시적으로
    public String hello() {
        return "hello";
    }

//    @PostMapping("/hello") // 아무것도 안쓰면 APPLICATION_JSON_VALUE 로 인식
//    public String postHello() {
//        return "hello";
//    }

    @PostMapping("/post")
    public String post() { //
        return "post";
    }

    @PatchMapping("/patch")
    public String patch() {
        return "patch";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "delete";
    }

    @GetMapping(value="/json-data", produces= MediaType.APPLICATION_JSON_VALUE) // 생산하는 데이터 명시적으로
    public JsonData getJsonData() { // 반드시 변환이 되서 json으로 바뀜
        JsonData data = new JsonData();
        data.setNumber(100);
        return data;
    }

    @GetMapping(value="/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserData getUserData(@PathVariable int id) {
        UserData data = new UserData();
        // data.setName("John");
        // data.setAge(20);
        if (id == 1) {
            data.setName("John");
            data.setAge(20);
        }
        return data;
    }
}
