# TicTacToe1

Reset the board using:
curl -g -X GET http://localhost:8080/TTT/ws/tictactoe/reset

Board is 0-index two-dimensional array.

Make a move using:
curl -g -H "Content-Type: application/json" -X POST -d @move.json http://localhost:8080/TTT/ws/tictactoe/play
where move.json has contents like below 
{"row": "0","col": "0"}
Provide the row and column numbers you want to play with.
Service will make it's own move after that.
