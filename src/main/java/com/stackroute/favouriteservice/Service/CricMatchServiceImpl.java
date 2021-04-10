package com.stackroute.favouriteservice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.Exception.CricketMatchAlreadyExists;
import com.stackroute.favouriteservice.Exception.CricketMatchNotFoundException;
import com.stackroute.favouriteservice.domain.Cricket;
import com.stackroute.favouriteservice.repository.CricMatchRepository;


@Service
public class CricMatchServiceImpl implements CricMatchService {

	private final transient CricMatchRepository crickMatchRepo;

	@Autowired
	public CricMatchServiceImpl(final CricMatchRepository crickMatchRepo) {
		super();
		this.crickMatchRepo = crickMatchRepo;
	}

	@Override
	public boolean SaveCircket(Cricket saveMovie) throws CricketMatchAlreadyExists {
		final Optional <Cricket>  movie= crickMatchRepo.findById(saveMovie.getId());

		if (movie.isPresent()) {
			throw new CricketMatchAlreadyExists("Could not save match" + saveMovie.getSquad() + "already exists");
		}
		crickMatchRepo.save(saveMovie);
		return true;
	}

//	@Override
//	public Cricket updateMovie(Cricket updateMovie) throws CricketMatchNotFoundException {
//		final Optional <Cricket> movie = crickMatchRepo.findById(updateMovie.getId());
//		if (!movie.isPresent()) {
//			throw new CricketMatchNotFoundException("Could not update Movie Since movie is not available");
//		}
//		movie.get().setComments(updateMovie.getComments());
//		movieRepo.save(movie.get());
//		return movie.get();
//	}

	@Override
	public boolean deleteCricketbyId(int id) throws CricketMatchNotFoundException {
		final Optional <Cricket> movie = crickMatchRepo.findById(id);
		if (!movie.isPresent()) {
			throw new CricketMatchNotFoundException("Could not delete Movie Since movie is not available");
		}
		crickMatchRepo.delete(movie.get());
		return true;
	}

	@Override
	public Cricket getCricketbyId(int id) throws CricketMatchNotFoundException {
		final Optional <Cricket> movie = crickMatchRepo.findById(id);
		if (!movie.isPresent()) {
			throw new CricketMatchNotFoundException("Could not get Movie Since movie is not available");
		}
		return movie.get();
	}

	@Override
	public List<Cricket> getMyFavoutires(String userId) {
		return crickMatchRepo.findByUserId(userId);
	}

	@Override
	public Cricket updateMovie(Cricket cricketmatch) throws CricketMatchNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
