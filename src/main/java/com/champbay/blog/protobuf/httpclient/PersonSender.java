package com.champbay.blog.protobuf.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.champbay.blog.protobuf.model.AddressBookProtos.Person;

public class PersonSender {
	
	public void sendPerson() {
		try {
			String url = "http://localhost:8080/test";
			HttpPost httpPost = new HttpPost(url);
			
			httpPost.setHeader("Content-Type", "application/x-protobuf");
			
			Person.Builder person = Person.newBuilder();
			person.setId(1);
			person.setName("hello");
			person.setEmail("hello@aaa.com");
			
			byte[] personContent = person.build().toByteArray();
			
			httpPost.setEntity(new ByteArrayEntity(personContent));
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(httpPost);
			
			int code = response.getStatusLine().getStatusCode();
			System.out.println("response code: " + code);
			
			HttpEntity entity = response.getEntity();
			
			byte[] responseContent = EntityUtils.toByteArray(entity);
			
			Person fromPerson = Person.parseFrom(responseContent);
			System.out.println("id: " + fromPerson.getId());
			System.out.println("name: " + fromPerson.getName());
			System.out.println("email: " + fromPerson.getEmail());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new PersonSender().sendPerson();
	}

}
