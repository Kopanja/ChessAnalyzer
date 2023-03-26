from stockfish import *
import chess
from flask import Flask
from flask import request
from flask import json
from flask_cors import CORS, cross_origin
from move import Move


#nadjes sve blundere sa stockfishom, uzmes linije {fen pre poteza, potez, po 3 poteza unapred}, i to saljes u drools da uradis
#u droolsu je uradjena za sad deo analize prvog polja (fen pre poteza)

stockfish = Stockfish(r"C:\Users\kopan\OneDrive\Desktop\stockfish-11-win\Windows\stockfish_20011801_x64.exe")
startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"




app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'


stockfish.set_fen_position(startFen)

@app.route('/move-made', methods = ['POST'])
@cross_origin()
def post_moves():
    playerMove = request.json
    move = Move(playerMove['color'], playerMove['piece'], playerMove['from'], playerMove['to'], playerMove['san'], playerMove['flags'])
    makePlayerMove(move)
    stockMove = playLastStockfishMove()
    return stockMove

def makePlayerMove(move):
    stockfish.make_moves_from_current_position([move.fromSquare+move.toSquare])
    

def playAndGetStockfishMove():
    stockfishMove = stockfish.get_best_move()
    stockfish.make_moves_from_current_position([stockfishMove])
    moveJson = {'fromSquare' : stockfishMove[0:2], 'toSquare' : stockfishMove[2:4]}
    stockfishMove = stockfish.get_top_moves(8)
    print(stockfishMove)
    return moveJson

def playLastStockfishMove():
    stockfishMove = stockfish.get_top_moves(8)[-1]
    stockfishMove = stockfishMove['Move']
    stockfish.make_moves_from_current_position([stockfishMove])
    moveJson = {'fromSquare' : stockfishMove[0:2], 'toSquare' : stockfishMove[2:4]}
    print(stockfishMove)
    return moveJson


if __name__ == "__main__":
    app.run(debug=True)