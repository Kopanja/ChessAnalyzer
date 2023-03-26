from stockfish import *
import chess
from flask import Flask
from flask import request
from flask import json
from flask_cors import CORS, cross_origin



app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'


sto = Stockfish(r"C:\Users\kopan\OneDrive\Desktop\stockfish-11-win\Windows\stockfish_20011801_x64.exe", parameters={"Threads": 8})
sto.set_depth(20)

#fen = "r3k2r/pp1n1p1p/2p1pp2/2b1q3/4P3/2NB1PQ1/PPP4P/2KR3R w kq - 1 15"
startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"


pngMoves = ['d4', 'e6', 'Bf4', 'c5', 'Nf3', 'cxd4', 'Nxd4', 'Bc5', 'e3', 'Ne7', 'c3', 'O-O', 'Bd3', 'd6', 
'O-O', 'e5', 'Bg3', 'exd4', 'cxd4', 'Bb6', 'Qc2', 'Ng6', 'Nd2', 'Nc6', 'Nf3', 'Nce7', 'Nh4', 
'Bg4', 'h3', 'Be6', 'f4', 'Rc8', 'Qb1', 'Nxh4', 'Bxh7+', 'Kh8', 'Bxh4', 'g6', 'Bxg6', 'fxg6', 
'Bxe7', 'Qxe7', 'Qxg6', 'Bxh3', 'Qh5+', 'Qh7', 'Qxh3', 'Qxh3', 'gxh3', 'Rg8+', 'Kh2', 'Kg7', 
'Rg1+', 'Kf6', 'Rxg8', 'Rxg8', 'a4', 'Kf5', 'a5', 'Bc7', 'b4', 'Ke4', 'Re1', 'b6', 'a6', 'Bd8', 
'b5', 'Bh4', 'Re2', 'Kf3', 'Rc2', 'Kxe3', 'Rc7', 'Ra8', 'Rd7', 'Kxd4', 'Rxd6+', 'Kc5', 'Rh6', 'Bd8', 'Rh5+', 
'Kb4', 'Kg3', 'Rc8', 'Kg4', 'Rc5', 'Rxc5', 'Kxc5', 'f5', 'Kxb5', 'Kh5', 'Kxa6', 'Kg6', 'b5', 'h4', 'b4', 'h5', 'b3', 'h6', 'b2',
"h7","b1=Q","h8=Q","Qb7","Qd4","Qb6+","f6","Qxf6+","Qxf6+","Bxf6","Kxf6", "Kb5", "Ke5","a5","Ke4","Kb4","Kd3","Kb3","Kd2","a4","Kc1","Ka2","Kc2","a3","Kc1","Ka1","Kc2","a2","Kc1"]

class Move:
    def __init__(self, color = None, piece = None, fromSquare = None, toSquare = None, san = None, flags = None) -> None:
        self.color = color
        self.fromSquare = fromSquare
        self.toSquare = toSquare
        self.piece = piece
        self.san = san


class ForDrools:
    def __init__(self, preMoveFen, move, movesAfterBlunder, preMoveLine, postMoveLine) -> None:
        self.preMoveFen = preMoveFen
        self.move = move
        self.movesAfterBlunder = movesAfterBlunder
        self.preMoveLine = preMoveLine
        self.postMoveLine = postMoveLine
    def parse(self):
        moves = []
        preMoveLineParsed = []
        postMoveLineParsed = []
        for move in self.movesAfterBlunder:
            moves.append(move.__dict__)
        for san_move in self.preMoveLine:
            move = Move(fromSquare=san_move[0:2], toSquare=san_move[2:4], san=san_move)
            preMoveLineParsed.append(move.__dict__)
        for san_move in self.postMoveLine:
            move = Move(fromSquare=san_move[0:2], toSquare=san_move[2:4], san=san_move)
            postMoveLineParsed.append(move.__dict__)
        return {'preMoveFen' : self.preMoveFen, 'move' : self.move.__dict__, 'movesAfterBlunder' : moves, 'preMoveLine' : preMoveLineParsed, 'postMoveLine' : postMoveLineParsed}


def find_6_move_line(startFen, stockfish : Stockfish):
    line = []
    stockfishInitFen = stockfish.get_fen_position()
    stockfish.set_fen_position(startFen)
    for i in range(6):
        best_move = stockfish.get_best_move()
        line.append(best_move)
        stockfish.make_moves_from_current_position([best_move])
    print(line)
    stockfish.set_fen_position(stockfishInitFen)
    return line
    
    

def find_mistakes(moves, startFen, stockfish : Stockfish):
    mistakes = []
    droolsMistakes = []
    i = 0

    for move in moves:
        preMoveEvaluation = stockfish.get_evaluation()['value']
        preMoveFen = stockfish.get_fen_position()

        try:
            stockfish.make_moves_from_current_position([move.fromSquare+move.toSquare])
        except:
            print("EXCEPT")
            board = chess.Board()
            board.set_fen(stockfish.get_fen_position())
            board.push_san(move.san)
            stockfish.set_fen_position(board.fen())

        postMoveEval = stockfish.get_evaluation()['value']


        if(abs(preMoveEvaluation - postMoveEval) > 100):
            print(move.__dict__)
            preMoveLine = find_6_move_line(preMoveFen, stockfish)
            postMoveLine = find_6_move_line(stockfish.get_fen_position(), stockfish)
            droolsMistakes.append(ForDrools(preMoveFen, move, [moves[i+1],moves[i+2],moves[i+3],moves[i+4],moves[i+5],moves[i+6],], preMoveLine, postMoveLine))
            mistakes.append(move)
            break
        i = i+1
    return droolsMistakes

@app.route('/post-moves', methods = ['POST'])
@cross_origin()
def post_moves():
    sto.set_fen_position(startFen)
    parsedMoves = []
    moves = request.json
    for playerMove in moves:
        move = Move(playerMove['color'], playerMove['piece'], playerMove['from'], playerMove['to'], playerMove['san'], playerMove['flags'])
        parsedMoves.append(move)
    droolsMistakes = find_mistakes(parsedMoves, startFen, sto) 
    parsedMistakes = []
    for mist in droolsMistakes:
        parsedMistakes.append(mist.parse())
    with open('data.json', 'w') as fp:
        json.dump(parsedMistakes, fp)
    return parsedMistakes

if __name__ == "__main__":
    #find_mistakes(pngMoves, startFen, sto)
    app.run(debug=True)
