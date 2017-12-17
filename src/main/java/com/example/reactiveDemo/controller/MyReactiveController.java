package com.example.reactiveDemo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactiveDemo.domain.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/app/person")
public class MyReactiveController 
{
	Map<Long, Person> personList = new HashMap<Long, Person>();
	
	@PostConstruct
	public void initCall()
	{
		personList.put(Long.valueOf(1), new Person(1L, "Rashmi", 27));
		personList.put(Long.valueOf(2), new Person(2L, "Raja", 34));
	}
	
	@GetMapping("/")
	public Flux<Person> getAll()
	{
		return Flux.fromIterable(personList.entrySet().stream().
				map(entry -> entry.getValue()).
				collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public Mono<Person> getById(@PathVariable Long id)
	{
		return Mono.justOrEmpty(personList.get(id));
	}
	
	@PostMapping("/post")
	public Mono<ResponseEntity<String>> savePerson(@RequestBody Person person)
	{
		personList.put(person.getId(), person);
		return Mono.just(new ResponseEntity<>("Post Successful", HttpStatus.CREATED));
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<String>> deletePerson(@PathVariable Long id)
	{
		personList.remove(id);
		return Mono.just(new ResponseEntity<>("Deleted Successful", HttpStatus.CREATED));
	}

}
