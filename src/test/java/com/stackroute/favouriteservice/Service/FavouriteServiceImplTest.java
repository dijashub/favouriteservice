package com.stackroute.favouriteservice.Service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.Exception.CricketMatchAlreadyExists;
import com.stackroute.favouriteservice.Exception.CricketMatchNotFoundException;
import com.stackroute.favouriteservice.domain.Cricket;
import com.stackroute.favouriteservice.repository.CricMatchRepository;

public class FavouriteServiceImplTest {

	@Mock
	private transient CricMatchRepository cricRepo;

	private transient Cricket cricketMatch;

	@InjectMocks
	private transient CricMatchServiceImpl cricketServiceImpl;

	transient Optional<Cricket> options;

	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		cricketMatch = new Cricket(1, "1234", "13 Sep 2018", "true", "New Zealand", "Australia", "","John123" );
		options = Optional.of(cricketMatch);
	}

	@Test
	public void testSaveFavourite() throws CricketMatchAlreadyExists {
		when(cricRepo.save(cricketMatch)).thenReturn(cricketMatch);
		final boolean flag = cricketServiceImpl.SaveCircket(cricketMatch);
		Assert.assertTrue("Saving Movie failed, the call in movieDaoImpl is retruning true", flag);
		verify(cricRepo, times(1)).save(cricketMatch);
	}

	@Test(expected = CricketMatchAlreadyExists.class)
	public void testFavouriteFaliure() throws CricketMatchAlreadyExists {
		when(cricRepo.findById(1)).thenReturn(options);
		when(cricRepo.save(cricketMatch)).thenReturn(cricketMatch);
		final boolean flag = cricketServiceImpl.SaveCircket(cricketMatch);
		Assert.assertFalse("saving Movie failes", flag);
		verify(cricRepo, times(1)).findById(cricketMatch.getId());
	}

//	@Test(expected =CricketMatchNotFoundException.class)
//	public void testUpdateMovie() throws CricketMatchNotFoundException {
//		when(cricRepo.findById(1)).thenReturn(options);
//		when(cricRepo.save(cricketMatch)).thenReturn(cricketMatch);
//		cricketMatch.setComments("Thriller Movie is not that feary");
//		final Cricket movie1 = movieServiceImpl.updateMovie(cricketMatch);
//		Assert.assertEquals("updating Movie failes", "Thriller Movie is not that feary", movie1.getComments());
//		verify(cricRepo, times(1)).save(movie1);
//		verify(cricRepo, times(1)).findById(movie1.getId());
//	}

	@Test
	public void testDeleteFavourite() throws CricketMatchNotFoundException {
		when(cricRepo.findById(1)).thenReturn(options);
		doNothing().when(cricRepo).delete(cricketMatch);
		final boolean flag = cricketServiceImpl.deleteCricketbyId(1);
		Assert.assertEquals("Deleting movie success", true, flag);
		verify(cricRepo, times(1)).delete(cricketMatch);
		verify(cricRepo, times(1)).findById(cricketMatch.getId());
	}

	@Test
	public void testGetFavouriteById() throws Exception {
		when(cricRepo.findById(1)).thenReturn(options);
		Cricket cricketMatch1 = cricketServiceImpl.getCricketbyId(1);
		Assert.assertEquals("fetchin movie by id failed", cricketMatch1, cricketMatch);
		verify(cricRepo, times(1)).findById(cricketMatch.getId());

	}

	@Test
	public void testGetMyFavourites() {
		final List<Cricket> movieList = new ArrayList<>();
		movieList.add(cricketMatch);
		when(cricRepo.findByUserId("John123")).thenReturn(movieList);
		final List<Cricket> cricket1 = cricketServiceImpl.getMyFavoutires("John123");
		Assert.assertEquals(movieList, cricket1);
		verify(cricRepo, times(1)).findByUserId("John123");

	}
}
