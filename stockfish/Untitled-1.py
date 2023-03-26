from tracemalloc import start
from stockfish import *
import chess

class ChessComGame:
    def __init__(self, moves, playerColor) -> None:
        self.moves = moves
        self.playerColor = playerColor
    
class StockfishGameEval:
    def __init__(self) -> None:
        pass

class StockfishEval:
     def __init__(self, oldFenEval, newFenEval, bestMove, bestMoveFenEval):
         self.oldFenEval = oldFenEval
         self.newFenEval = newFenEval
         self.bestMove = bestMove
         self.bestMoveFenEval = bestMoveFenEval

pngMoves = ['d4', 'e6', 'Bf4', 'c5', 'Nf3', 'cxd4', 'Nxd4', 'Bc5', 'e3', 'Ne7', 'c3', 'O-O', 'Bd3', 'd6', 
'O-O', 'e5', 'Bg3', 'exd4', 'cxd4', 'Bb6', 'Qc2', 'Ng6', 'Nd2', 'Nc6', 'Nf3', 'Nce7', 'Nh4', 
'Bg4', 'h3', 'Be6', 'f4', 'Rc8', 'Qb1', 'Nxh4', 'Bxh7+', 'Kh8', 'Bxh4', 'g6', 'Bxg6', 'fxg6', 
'Bxe7', 'Qxe7', 'Qxg6', 'Bxh3', 'Qh5+', 'Qh7', 'Qxh3', 'Qxh3', 'gxh3', 'Rg8+', 'Kh2', 'Kg7', 
'Rg1+', 'Kf6', 'Rxg8', 'Rxg8', 'a4', 'Kf5', 'a5', 'Bc7', 'b4', 'Ke4', 'Re1', 'b6', 'a6', 'Bd8', 
'b5', 'Bh4', 'Re2', 'Kf3', 'Rc2', 'Kxe3', 'Rc7', 'Ra8', 'Rd7', 'Kxd4', 'Rxd6+', 'Kc5', 'Rh6', 'Bd8', 'Rh5+', 
'Kb4', 'Kg3', 'Rc8', 'Kg4', 'Rc5', 'Rxc5', 'Kxc5', 'f5', 'Kxb5', 'Kh5', 'Kxa6', 'Kg6', 'b5', 'h4', 'b4', 'h5', 'b3', 'h6', 'b2',
"h7","b1=Q","h8=Q","Qb7","Qd4","Qb6+","f6","Qxf6+","Qxf6+","Bxf6","Kxf6", "Kb5", "Ke5","a5","Ke4","Kb4","Kd3","Kb3","Kd2","a4","Kc1","Ka2","Kc2","a3","Kc1","Ka1","Kc2","a2","Kc1"]

 
 

def test_board_san(pngMoves, fen):
    board = chess.Board()
    board.set_fen(fen)
    for move in pngMoves:
        board.push_san(move)
    print(board.fen())

def test_stockfish(pngMoves, startFen,stockfish):
    gameBoard = chess.Board()
    stockfishBoard = chess.Board()

    gameBoard.set_fen(startFen)
    stockfishBoard.set_fen(startFen)

    stockfish.set_fen_position(startFen)
    print(stockfish.get_evaluation())
    
    stoMoveUCI = stockfish.get_best_move()
    stockfishBoard.push_uci(stoMoveUCI)
    
    gameBoard.push_san(pngMoves[0])

    stockfish.set_fen_position(gameBoard.fen())
    print("Actual Game: " ,stockfish.get_evaluation())

    stockfish.set_fen_position(stockfishBoard.fen())
    print("Stock Game: " ,stockfish.get_evaluation()['value'])



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


def test_nesto(pngMoves, startFen,stockfish):
    gameBoard = chess.Board()
    stockfishBoard = chess.Board()

    gameBoard.set_fen(startFen)
    

    for move in pngMoves:
        stockfishBoard.set_fen(gameBoard.fen())
        stockfish.set_fen_position(gameBoard.fen())
        preMoveEvaluation = stockfish.get_evaluation()['value']
       
        

        gameBoard.push_san(move)
        
        stockfish.set_fen_position(gameBoard.fen())
        actualGameEval = stockfish.get_evaluation()['value']

        stockfish.set_fen_position(stockfishBoard.fen())
        stoMoveUCI = stockfish.get_best_move()
        stockfishBoard.push_uci(stoMoveUCI)
        stockfishGameEval = stockfish.get_evaluation()['value']

        if(abs(preMoveEvaluation - actualGameEval) > 100):
            print("VELIKA RAZLIKA")
            #print("Pre move Evaluation: ", preMoveEvaluation)
            print("Actual Game: ", actualGameEval, "Move: ", move, "Eval difference: ", abs(actualGameEval-preMoveEvaluation))
            #print("Stock Game: ", stockfishGameEval, "Move: ", stoMoveUCI , "Eval difference: ", abs(stockfishGameEval-preMoveEvaluation))
            #print("Stock vs Actual Eval: ", abs(stockfishGameEval-actualGameEval))
            print(create_stockfish_lines(gameBoard.fen(), stockfish))
            gameLine, gameLineEval = create_stockfish_lines(gameBoard.fen(), stockfish)
            stockLine, stockLineEval = create_stockfish_lines(stockfishBoard.fen(), stockfish)
            print("Game Eval: ", max(gameLineEval), min(gameLineEval))
            print("Stock Eval: ", max(stockLineEval), min(stockLineEval))
        else:
            print("Move: ", move)
            print("Actual Game: ", actualGameEval, " Eval difference: ", abs(actualGameEval-preMoveEvaluation))
            gameLine, gameLineEval = create_stockfish_lines(gameBoard.fen(), stockfish)
            stockLine, stockLineEval = create_stockfish_lines(stockfishBoard.fen(), stockfish)
            print("Game Eval: ", max(gameLineEval), min(gameLineEval))
            print("Stock Eval: ", max(stockLineEval), min(stockLineEval))
        print("===================================================================================================")


def create_stockfish_lines(startFen,stockfish):
    board = chess.Board()
    board.set_fen(startFen)
    line = []
    lineEval = []
    for i in range(12):
        print("AAAAAA")
        stockfish.set_fen_position(board.fen())
        #print("StockfishEval: ", stockfish.get_evaluation()['value'])
        lineEval.append(stockfish.get_evaluation()['value'])
        bestMove = stockfish.get_best_move()
        line.append(bestMove)
        board.push_uci(bestMove)

    return line, lineEval
        




def fen_to_array(fen):
    board = chess.Board()
    board.set_fen(fen)
    #board.push_uci("Nf3")
    #board.push_san("Nf3")
    chess.Move()
    print(board.san)
    sto.make_moves_from_current_position(["e2e4"])
    
    #board.push(chess.Move.from_uci("f3f4"))
   # sto.set_fen_position(board.fen())
    #print(board.fen())



sto = Stockfish(r"C:\Users\kopan\OneDrive\Desktop\stockfish-11-win\Windows\stockfish_20011801_x64.exe")
sto.set_depth(18)

#fen = "r3k2r/pp1n1p1p/2p1pp2/2b1q3/4P3/2NB1PQ1/PPP4P/2KR3R w kq - 1 15"
startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
#sto.set_fen_position(startFen)
#print(sto.get_board_visual())
#sto.get_evaluation()
#fen_to_array(startFen)
#test_board_san(pngMoves, startFen)

test_nesto(pngMoves, startFen,sto)
print("Gorov")
#print(sto.get_board_visual())

