package com.stackroute.favouriteservice.Service;

import java.util.List;

import com.stackroute.favouriteservice.Exception.CricketMatchAlreadyExists;
import com.stackroute.favouriteservice.Exception.CricketMatchNotFoundException;
import com.stackroute.favouriteservice.domain.Cricket;

public interface CricMatchService {

	boolean SaveCircket (Cricket cricketMatch) throws CricketMatchAlreadyExists ;

	Cricket updateMovie(Cricket cricketmatch) throws CricketMatchNotFoundException;

	boolean deleteCricketbyId(int id) throws CricketMatchNotFoundException;

	Cricket getCricketbyId(int id) throws CricketMatchNotFoundException;

	List<Cricket> getMyFavoutires(String userId);
}
