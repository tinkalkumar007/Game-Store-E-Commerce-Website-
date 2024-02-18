package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.Game;

public class GameDao extends JpaDao<Game> implements GenericDao<Game> {

	@Override
	public Game create(Game game) {
		game.setLastUpdateTime(new Date());
		return super.create(game);
	}

	@Override
	public Game update(Game game) {
		return super.update(game);
	}

	@Override
	public Game get(Object id) {
		return super.get(Game.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Game.class, id);
	}

	@Override
	public List<Game> listAll() {
		return super.findWithNamedQuery("Game.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Game.countAll");
	}

	public long countByCategory(int categoryId) {
		return super.countWithNamedQuery("Game.countByCategory","catId",categoryId);
	}
	
	public List<Game> listGameByCategory(int categoryId){
		return super.findWithNamedQuery("Game.findByCategory", "catId", categoryId);
	}
	
	public List<Game> listNewGames(){
		return super.findWithNamedQuery("Game.listNew", 0, 5);
	}
	
	public List<Game> search(String keyword){
		return super.findWithNamedQuery("Game.search", "keyword", keyword);
	}
	
	public Game findByTitle(String title) {
		List<Game> gameList=super.findWithNamedQuery("Game.findByTitle", "title", title);
		if(gameList.size()>0) {
			return gameList.get(0);
		}
		return null;
	}
	
	public List<Game> bestSellingGames(){
		return super.findWithNamedQuery("OrderDetail.bestSelling", 0, 5);
	}
	public List<Game> listMostFavoriteGames(){
		List<Game> mostFavGames=new ArrayList<Game>();
		
		List<Object[]> result=super.findWithNamedQueryObjects("Review.mostFavoredGames", 0, 5);
		if(!result.isEmpty()) {
			for(Object[] elements:result) {
				Game game=(Game) elements[0];
				mostFavGames.add(game);
			}
		}
		return mostFavGames;
	}
}
