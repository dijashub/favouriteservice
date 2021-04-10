package com.stackroute.favouriteservice.Controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stackroute.favouriteservice.Exception.CricketMatchAlreadyExists;
import com.stackroute.favouriteservice.Exception.CricketMatchNotFoundException;
import com.stackroute.favouriteservice.Service.CricMatchService;
import com.stackroute.favouriteservice.domain.Cricket;

import io.jsonwebtoken.Jwts;

@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(path = "/api/v1/cricmatch")
public class CricMatchController {

	@Autowired
	private CricMatchService cricMatchService;

	public CricMatchController(final CricMatchService cricMatchService) {
		this.cricMatchService = cricMatchService;
	}

	@PostMapping("/cricmatch")
	public ResponseEntity<?> saveNewMovie(@RequestBody final Cricket cricket, HttpServletRequest request,
			HttpServletRequest response) {
		ResponseEntity<?> responseEntity;

		System.out.println("Saving Movie");
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
//		System.out.println("UserID::" + userId);
		try {
			cricket.setUserId(userId);
			cricMatchService.SaveCircket(cricket);
			responseEntity = new ResponseEntity<Cricket>(cricket, HttpStatus.CREATED);
		} catch (CricketMatchAlreadyExists Excep) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + Excep.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PutMapping(value = "/cricmatch/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") final Integer id, @RequestBody final Cricket cricketMatch) {
		ResponseEntity<?> responseEntity;
		try {
			final Cricket fetchedMovie = cricMatchService.updateMovie(cricketMatch);
			responseEntity = new ResponseEntity<Cricket>(fetchedMovie, HttpStatus.CREATED);
		} catch (CricketMatchNotFoundException Excep) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + Excep.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@DeleteMapping(value = "/cricmatch/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") final Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			cricMatchService.deleteCricketbyId(id);

		} catch (CricketMatchNotFoundException Excep) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + Excep.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		responseEntity = new ResponseEntity<String>("movie deleted successfully", HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(value = "/cricmatch/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") final Integer id) {
		ResponseEntity<?> responseEntity;
		Cricket thisMovie = null;
		try {
			thisMovie = cricMatchService.getCricketbyId(id);

		} catch (CricketMatchNotFoundException Excep) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + Excep.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		responseEntity = new ResponseEntity<Cricket>(thisMovie, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/cricmatch")
	public ResponseEntity<List<Cricket>> fetchMyMovies(final ServletRequest req, final ServletResponse res) {

		final HttpServletRequest request = (HttpServletRequest) req;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		return new ResponseEntity<List<Cricket>>(cricMatchService.getMyFavoutires(userId), HttpStatus.OK);
	}
}
