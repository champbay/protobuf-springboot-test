package com.champbay.blog.protobuf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.champbay.blog.protobuf.model.AddressBookProtos;
import com.champbay.blog.protobuf.model.AddressBookProtos.Person;

@Controller
public class TestController {
	
	@RequestMapping(value = "/test", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    @ResponseBody
    public AddressBookProtos.Person getPersonProto(@RequestBody AddressBookProtos.Person person) {
		System.out.println("id: " + person.getId());
		System.out.println("name: " + person.getName());
		System.out.println("email: " + person.getEmail());
		
		//这个地方需要加入将protobuf的对象转换成普通的java pojo的方法 ！
		
		Person.Builder builder = Person.newBuilder();
		builder.setId(2);
		builder.setName("myhello");
		builder.setEmail("myhello@aaa.com");
		
		return builder.build();
    }

}
