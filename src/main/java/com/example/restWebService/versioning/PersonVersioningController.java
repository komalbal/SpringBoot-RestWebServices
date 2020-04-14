package com.example.restWebService.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("/v1/person")
	public PersonV1 personv1() {
		return new PersonV1("Komal Baldawa");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personv2() {
		return new PersonV2(new Name("Komal","Baldawa"));
	}
	
	
	//with param
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramv1() {
		return new PersonV1("Komal Baldawa");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramv2() {
		return new PersonV2(new Name("Komal","Baldawa"));
	}
	
	//with Headers
	@GetMapping(value="/person/header", headers="X_API_VERSION=1")
	public PersonV1 headerv1() {
		return new PersonV1("Komal Baldawa");
	}
	
	@GetMapping(value="/person/header", headers="X_API_VERSION=2")
	public PersonV2 headerv2() {
		return new PersonV2(new Name("Komal","Baldawa"));
	}
	
	//with Produces
	@GetMapping(value="/person/produces", produces="application/my.company.appv1+json")
	public PersonV1 producesv1() {
		return new PersonV1("Komal Baldawa");
	}
	
	@GetMapping(value="/person/produces", produces="application/my.company.appv2+json")
	public PersonV2 producesv2() {
		return new PersonV2(new Name("Komal","Baldawa"));
	}
}
