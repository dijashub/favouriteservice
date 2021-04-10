package com.stackroute.favouriteservice.Controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.Service.CricMatchService;
import com.stackroute.favouriteservice.domain.Cricket;;

@RunWith(SpringRunner.class)
@WebMvcTest(CricMatchController.class)
public class MovieControllerTest {

	@Autowired
	private transient MockMvc mockMvc;

	@MockBean
	private transient CricMatchService service;

	@InjectMocks
	private CricMatchController movieController;

	private transient Cricket cricketMatch;

	static List<Cricket> cricketMatches;

	@Before
	public void setUp() {
//		movies = new ArrayList<Movie>();
//		movie = new Movie(1234, "1", "POC", "Good Movie", "www.abc.com", "Sep 15 2018", 45.4, 343, "Jhon123");
//		movies.add(movie);
//		movie = new Movie(3435, "2", "POC - 2", "Nice Movie", "www.pocNice.com", "Oct 15 2018", 12.4, 123, "Mark234");
//		movies.add(movie);
	}

	@Ignore
	@Test
	public void testSaveMovie() throws Exception {
//		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE1NzQ0NzU5ODd9.2GL_WwUsuCz0-Xk8zvgCzFuGnvKRbS0nETiEz3zOpfc";
//		when(service.SaveMovie(cricketMatch)).thenReturn(true);
//		mockMvc.perform(post("/api/v1/movieservice/movie").header("authorization", "Bearer " + token)
//				.contentType(MediaType.APPLICATION_JSON).content(JsonToString(cricketMatch))).andExpect(status().isCreated())
//		.andDo(print());
//		verify(service, times(1)).SaveMovie(Mockito.any(cricketMatch.class));
//		verifyNoMoreInteractions(service);
	}

	@Ignore
	@Test
	public void testUpdateMovie() throws Exception {
//		movie.setComments("Movie is not so good");
//		when(service.updateMovie(movie)).thenReturn(movies.get(0));
//		mockMvc.perform(put("/api/movie/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(JsonToString(movie)))
//		.andExpect(status().isCreated());
//		verify(service, times(1)).updateMovie(Mockito.any(Movie.class));
//		verifyNoMoreInteractions(service);
	}

	@Ignore
	@Test
	public void testDeleteMovie() throws Exception {
		when(service.deleteCricketbyId(1)).thenReturn(true);
		mockMvc.perform(delete("/api/v1/movieservice/movie/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).deleteCricketbyId(1);
		verifyNoMoreInteractions(service);
	}

//	@Test
//	public void testFetchMovieById() throws Exception {
//		when(service.getMoviebyId(1)).thenReturn(movies.get(0));
//		mockMvc.perform(get("/api/movie/{id}", 1)).andExpect(status().isOk());
//		verify(service, times(1)).getMoviebyId(1);
//		verifyNoMoreInteractions(service);
//	}

	@Ignore
	@Test
	public void testGetMyMovies() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE1NzQ0NzU5ODd9.2GL_WwUsuCz0-Xk8zvgCzFuGnvKRbS0nETiEz3zOpfc";
		when(service.getMyFavoutires("123")).thenReturn(cricketMatches);
		mockMvc.perform(get("/api/v1/movieservice/movies").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).getMyFavoutires("123");
		verifyNoMoreInteractions(service);
	}

	private static String JsonToString(final Object obj) {

		String result = null;
		final ObjectMapper objectMap = new ObjectMapper();
		String JsonContent = null;
		try {
			JsonContent = objectMap.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			result = "Json Parse Error";
		}
		result = JsonContent;
		return result;
	}

}
