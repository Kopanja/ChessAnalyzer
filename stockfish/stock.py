from stockfish import *
import chess
from flask import Flask
from flask import request
from flask import json
from flask_cors import CORS, cross_origin
from model import StockfishEval, Move
print("A")
sto = Stockfish(r"C:\Users\kopan\OneDrive\Desktop\stockfish-11-win\Windows\stockfish_20011801_x64.exe")
startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
board = chess.Board()
#board.set_fen(fen)
#board.push_uci("f3f4")
#print(board.fen())




app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'


def find_mistakes(pngMoves, startFen, stockfish):
    mistakes = []
    gameBoard = chess.Board()
    gameBoard.set_fen(startFen)
    for move in pngMoves:
        stockfish.set_fen_position(gameBoard.fen())
        preMoveEvaluation = stockfish.get_evaluation()['value']

        gameBoard.push_san(move)

        stockfish.set_fen_position(gameBoard.fen())
        postMoveEval = stockfish.get_evaluation()['value']


        if(abs(preMoveEvaluation - postMoveEval) > 100):
            mistakes.append(move)
    return mistakes

def stockfish_analysis(move_objects, startFen, stockfish):
    gameBoard = chess.Board()
    gameBoard.set_fen(startFen)
    for move in move_objects:
        preMoveFen = gameBoard.fen()
        stockfish.set_fen_position(gameBoard.fen())
        bestMove = stockfish.get_best_move()
        preMoveEvaluation = stockfish.get_evaluation()['value']

        #Actual Move Evaluation
        gameBoard.push_san(move.san)

        postMoveFen = gameBoard.fen()
        stockfish.set_fen_position(gameBoard.fen())
        postMoveEval = stockfish.get_evaluation()['value']

        #Best Move Evaluation
        gameBoard.set_fen(preMoveFen)
        
        gameBoard.push_uci(bestMove)
        stockfish.set_fen_position(gameBoard.fen())

        bestMoveEvaluation =  stockfish.get_evaluation()['value']

        #Return to post move FEN
        gameBoard.set_fen(postMoveFen)
        move.setStockEval(postMoveEval, preMoveEvaluation, bestMove, bestMoveEvaluation, preMoveFen)
    return move_objects

       



def parse_moves(pngMoves):
    move_objects = []
    i = 1
    for move in pngMoves:
        capture = None
        if "captured" in move:
            capture = move["captured"]
            print("this will execute")
        move_objects.append(Move(color=move['color'], fromSquare=move['from'], toSquare=move['to'], piece=move['piece'], san = move['san'], moveNum=i, capture = capture))
        i = i + 1
    return move_objects

@app.route('/post-moves', methods = ['POST'])
@cross_origin()
def post_moves():
   # print(request.json[0]['from'])
    pngMoves = request.json
    print(pngMoves)
    move_objects = parse_moves(pngMoves=pngMoves)
    move_objects = stockfish_analysis(move_objects, startFen, sto)
    #mistakes = find_mistakes(pngMoves, startFen, sto)
    json_objects = []
    for move in move_objects:
        json_objects.append(move.__dict__)
    
    with open('data.json', 'w') as fp:
        json.dump(json_objects, fp)
    return json_objects

@app.route('/post-moves-saved-data', methods = ['POST'])
@cross_origin()
def post_moves_saved_data():
    f = open('data.json')
    json_objects = json.load(f)
    f.close()
    return json_objects
    

@app.route('/position-eval', methods = ['POST'])
def position_eval():

    print(request.json)
    move_uci = request.json["move"]["from"] + request.json["move"]["to"]
    print("Move: " + str(move_uci))

    new_fen = request.json["newFen"]["fenString"]
    new_fen_eval = get_fen_eval(new_fen)
    print("New Fen Eval: " + str(new_fen_eval))

    old_fen = request.json["oldFen"]["fenString"]
    old_fen_eval = get_fen_eval(old_fen)
    print("Old Fen Eval: " + str(old_fen_eval))

    best_move = sto.get_best_move()
    print("Best move: " + str(best_move))

    best_move_eval = get_fen_eval_after_best_move(old_fen)
    print("Eval after best move: " + str(best_move_eval))

    stockfish_eval = {"oldFenEval": old_fen_eval["value"], "newFenEval": new_fen_eval["value"], "bestMove": best_move, "bestMoveFenEval": best_move_eval["value"]}
    return stockfish_eval

def get_fen_eval(fen):
    sto.set_fen_position(fen)
    return sto.get_evaluation()

def get_fen_eval_after_best_move(fen):
    sto.set_fen_position(fen)
    best_move = sto.get_best_move()
    board.set_fen(fen)
    board.push_uci(best_move)
    sto.set_fen_position(board.fen())
    return sto.get_evaluation()


@app.route('/')
def ok():
    return "oki"



if __name__ == "__main__":
    app.run(debug=True)