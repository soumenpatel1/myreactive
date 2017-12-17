package com.example.reactiveDemo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person 
{

	private String name;

	private int age;
	
	private Long id;

	public Person(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("age") int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id='" + id + '\'' +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

}
