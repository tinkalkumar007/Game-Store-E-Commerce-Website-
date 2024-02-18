package com.services;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dao.CategoryDao;
import com.dao.GameDao;
import com.entity.Category;
import com.entity.Game;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class GameServices {
	HttpServletRequest req;
	HttpServletResponse resp;
	GameDao gameDao;
	CategoryDao catDao;
	public GameServices(HttpServletRequest req,HttpServletResponse resp) {
		this.req=req;
		this.resp=resp;
		gameDao=new GameDao();
		catDao=new CategoryDao();
	}
	public void listGame() throws ServletException, IOException {
		listGame(null);
	}
	public void listGame(String msg) throws ServletException, IOException {
		if(msg!=null) {
			req.setAttribute("message", msg);
		}
		req.setAttribute("listGames", gameDao.listAll());
		RequestDispatcher rqd=req.getRequestDispatcher("listGames.jsp");
		rqd.forward(req, resp);
	}
	public void newGame() throws ServletException, IOException {
		List<Category> catList=catDao.findWithNamedQuery("Category.findAll");
	    req.setAttribute("listCategory", catList);
	    RequestDispatcher rqd=req.getRequestDispatcher("game_form.jsp");
		rqd.forward(req, resp);
	}
	
	public void createGame() throws ServletException, IOException {
		String title=req.getParameter("title");
		System.out.println(title);
		Game gameExist=gameDao.findByTitle(title);
		if(gameExist!=null) {
			String msg="Could not create game. Game with title '"+title+"' already exists.";
			listGame(msg);
			return;
		}
		Game game=new Game();
		createGameField(game);
		Game newGame=gameDao.create(game);
		if(newGame.getGameId()>0) {
			String msg="A new game has been created successfully.";
			listGame(msg);
		}
	}
	public void editGame() throws ServletException, IOException {
		Integer id=Integer.parseInt(req.getParameter("id"));
		List<Category> catList=catDao.findWithNamedQuery("Category.findAll");
		Game game=gameDao.get(id);
		req.setAttribute("game", game);
		req.setAttribute("listCategory", catList);
		
		RequestDispatcher rqd=req.getRequestDispatcher("game_form.jsp");
		rqd.forward(req, resp);
	}
	
	public void createGameField(Game game) throws ServletException, IOException {		
		Integer categoryId=Integer.parseInt(req.getParameter("category"));
		
        String title=req.getParameter("title");
		String creator=req.getParameter("creator");
		String isbn=req.getParameter("isbn");
		Float price=Float.parseFloat(req.getParameter("price"));
		String description=req.getParameter("description");
		DateFormat dateFormat=new SimpleDateFormat("MM/DD/YYYY");
		Date publishedDate=null;
		try {
			publishedDate=dateFormat.parse(req.getParameter("published"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Error parsing publish date(Date format is MM/DD/YYYY).");
		}
		
		game.setTitle(title);
		game.setIsbn(isbn);
		game.setDescription(description);
		game.setCreator(creator);
		
		Category cat=catDao.get(categoryId);
		game.setCategory(cat);
		
		game.setPrice(price);
		game.setPublished(publishedDate);
		
		Part part=req.getPart("gameImage");
		if(part!=null && part.getSize()>0) {
			long size=part.getSize();
			byte[] imgBytes=new byte[(int) size];
			InputStream inputStream=part.getInputStream();
			inputStream.read(imgBytes);
			inputStream.close();
			
			game.setImage(imgBytes);
		}
	}
	
	public void updateGame() throws ServletException, IOException {
		Integer gameId=Integer.parseInt(req.getParameter("gameId"));
		
		Game game=gameDao.get(gameId);
		String title=req.getParameter("title");
		Game findByTitle=gameDao.findByTitle(title);
		
		if(findByTitle!=null && !game.equals(findByTitle)) {
			String msg="Could not update game because a game with same title already exist.";
			listGame(msg);
			return;
		}
		createGameField(game);
		gameDao.update(game);
		String msg="Game has been updated successfully.";
		listGame(msg);
	}
	public void deleteGame() throws ServletException, IOException {
		Integer gameId=Integer.parseInt(req.getParameter("id"));
		Game game=gameDao.get(gameId);
		
		if(game==null) {
			String msg="Could not delete game. Game with game Id "+gameId+" doesn't exist.";
			listGame(msg);
			return; 
		}
		gameDao.delete(gameId);
		String msg="Game has been deleted successfully.";
		listGame(msg);
	}
	
	public void getGameDetails() throws ServletException, IOException {
		Integer gameId=Integer.parseInt(req.getParameter("id"));
		Game game=gameDao.get(gameId);
		
		req.setAttribute("game", game);
		String filePath="frontend/game_detail.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	
	public void listGameByCategory() throws ServletException, IOException {
		int catId=Integer.parseInt(req.getParameter("id"));
		Category cat=catDao.get(catId);
		req.setAttribute("category", cat);
		
		List<Game> gameList=gameDao.listGameByCategory(catId);
		req.setAttribute("gameList", gameList);
		
		String filePath="frontend/listGameByCategory.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	public void search() throws ServletException, IOException {
		String keyword=req.getParameter("keyword");
		List<Game> gamelist=null;
		if(keyword==null) {
			gamelist=gameDao.listAll();
		}
		else {
			gamelist=gameDao.search(keyword);
		}
		req.setAttribute("result", gamelist);
		req.setAttribute("keyword", keyword);
		String filePath="frontend/search_result.jsp";
		RequestDispatcher rqd=req.getRequestDispatcher(filePath);
		rqd.forward(req, resp);
	}
	
}
