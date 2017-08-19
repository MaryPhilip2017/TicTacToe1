package com.ttt.java.web;

import java.util.*;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tictactoe")
@Singleton
public class MyTicTacToeResource 
{
	private	GameBoard game;
	
	public MyTicTacToeResource()
	{
		System.out.println("Board is created for game");
		game = new GameBoard();
	}
	
	/*
	 * Reset the board.
	 */
	@GET
	@Path("/reset")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBoard()
	{
		game.boardInitialize();
		return Response.ok(game, MediaType.APPLICATION_JSON).build();
	}
	
	
	/**
	 * Take input json with proposed move and plays the game.
	 * @param input
	 */
	@POST
	@Path("/play")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response makeMyMove(MyMove input)
	{
		System.out.println("Going to move at [" + input.row + ", "+ input.col + "] on board.");
		game.userPlay(input.row, input.col);
		game.displayBoard();
		return Response.ok(game, MediaType.APPLICATION_JSON).build();
		
	}

}
