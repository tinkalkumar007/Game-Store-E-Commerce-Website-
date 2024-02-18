package com.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.entity.Category;
import com.entity.Game;

public class GameDaoTest {

	private static GameDao gameDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GameDaoTest.setUpBeforeClass();
		gameDao=new GameDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		GameDaoTest.tearDownAfterClass();
	}

	@Test
	public void testCreateGame() throws ParseException, IOException {
		Game game=new Game();
		Category cat=new Category("Action Games");
		cat.setCategoryId(11);
		game.setCategory(cat);
		game.setTitle("God of War");
		game.setCreator("Cory Barlog");
		game.setDescription("God of War is a third-person action-adventure video game. It features an over-the-shoulder free camera, a departure from the previous installments, which featured a fixed cinematic camera (with the exception of 2007's two-dimensional side-scroller Betrayal)");
		game.setPrice(123.58f);
		game.setIsbn("125345");
		
		DateFormat dateFormat=new SimpleDateFormat("DD/MM/YYYY");
		Date publishDate=dateFormat.parse("20/04/2020");
		game.setPublished(publishDate);
		String filePath="C:\\Users\\User\\Desktop\\Tinkal\\Games\\God_of_War.jpg";
		byte[] img=Files.readAllBytes(Paths.get(filePath));
		game.setImage(img);
		
		Game g=gameDao.create(game);
		assertTrue(g.getGameId()>0);
	}

}
