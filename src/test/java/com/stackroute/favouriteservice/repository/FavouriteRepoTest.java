package com.stackroute.favouriteservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.Cricket;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
public class FavouriteRepoTest {

	@Autowired
	private transient CricMatchRepository repo;

	public CricMatchRepository getRepo() {
		return repo;
	}

	public void setRepo(CricMatchRepository repo) {
		this.repo = repo;
	}
	@Test
	public void testSaveMovie() throws Exception{
		repo.save(new Cricket(1, "1234", "13 Sep 2018", "true", "New Zealand", "Australia", "","John123" ));
		final List<Cricket> movies  = repo.findByUserId("John123");
		Assert.assertEquals("1234", movies.get(0).getUnique_id());
	}
	@Test
	@Ignore
	public void testUpdateMovie() throws Exception{
		repo.save(new Cricket(1, "1234", "13 Sep 2018", "true", "New Zealand", "Australia", "","John123" ));
		final List<Cricket> movies  = repo.findByUserId("John123");
		Assert.assertEquals(movies.get(0).getSquad(), "Anabella");
		//movies.get(0).setComments("Thriller Movie");
		final List<Cricket> tempMovie = repo.findByUserId("jhon123");
		//Assert.assertEquals(tempMovie.get(0).getComments(), "Thriller Movie");
	}
	
	@Test
	public void testDeleteMovie() throws Exception {
		repo.save(new Cricket(1, "1234", "13 Sep 2018", "true", "New Zealand", "Australia", "","Adam13" ));
		final List<Cricket> movies  = repo.findByUserId("Adam13");
		Assert.assertEquals(movies.get(0).getTeam1(), "New Zealand");
		repo.delete(movies.get(0));
		Assert.assertEquals(0, repo.findByUserId("Adam13").size());
	}
	
	@Test
	public void testGetMovie() throws Exception {
		repo.save(new Cricket(4, "1234", "13 Sep 2018", "true", "New Zealand", "Australia", "13 Sep 2018","John123" ));
		final List<Cricket> movies  = repo.findByUserId("John123");
		Assert.assertEquals(movies.get(0).getTeam1(), "New Zealand");
	}
	@Test
	public void getAllMovies() throws Exception {
		repo.save(new Cricket(1, "1234", "13 Sep 2018", "true", "New Zealand", "Australia", "13 Sep 2018","Claus123" ));
		repo.save(new Cricket(2, "123334", "13 Sep 2018", "true", "New Zealand", "Australia", "13 Sep 2018","Stephen123" ));
		final List<Cricket> movies = repo.findByUserId("Claus123");
		Assert.assertEquals(movies.get(0).getTeam2(),"Australia");

	}
	
}
