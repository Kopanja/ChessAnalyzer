from model import StockfishEval, Move
from stockfish import *
import chess

sto = Stockfish(r"C:\Users\kopan\OneDrive\Desktop\stockfish-11-win\Windows\stockfish_20011801_x64.exe")
startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
board = chess.Board()


pngMoves = [{'color': 'w', 'piece': 'p', 'from': 'd2', 'to': 'd4', 'san': 'd4', 'flags': 'b'}, {'color': 'b', 'piece': 'p', 'from': 'd7', 'to': 'd5', 'san': 'd5', 'flags': 'b'}, {'color': 'w', 'piece': 'b', 'from': 'c1', 'to': 'f4', 'san': 
'Bf4', 'flags': 'n'}, {'color': 'b', 'piece': 'p', 'from': 'c7', 'to': 'c5', 'san': 'c5', 'flags': 'b'}, 
{'color': 'w', 'piece': 'n', 'from': 'g1', 'to': 'f3', 'san': 'Nf3', 'flags': 'n'}, 
{'color': 'b', 'piece': 'n', 'from': 'b8', 'to': 'c6', 'san': 'Nc6', 'flags': 'n'}, 
{'color': 'w', 'piece': 'p', 'from': 'e2', 'to': 'e3', 'san': 'e3', 'flags': 'n'}, 
{'color': 'b', 'piece': 'b', 'from': 'c8', 'to': 'f5', 'san': 'Bf5', 'flags': 'n'}, 
{'color': 'w', 'piece': 'p', 'from': 'c2', 'to': 'c3', 'san': 'c3', 'flags': 'n'}, 
{'color': 'b', 'piece': 'p', 'from': 'e7', 'to': 'e6', 'san': 'e6', 'flags': 'n'}, 
{'color': 'w', 'piece': 'b', 'from': 'f1', 'to': 'b5', 'san': 'Bb5', 'flags': 'n'}, 
{'color': 'b', 'piece': 'n', 'from': 'g8', 'to': 'f6', 'san': 'Nf6', 'flags': 'n'}, 
{'color': 'w', 'piece': 'b', 'from': 'b5', 'to': 'c6', 'san': 'Bxc6+', 'flags': 'c', 'captured': 'n'}, 
{'color': 'b', 'piece': 'p', 'from': 'b7', 'to': 'c6', 'san': 'bxc6', 'flags': 'c', 'captured': 'b'}, 
{'color': 'w', 'piece': 'n', 'from': 'f3', 'to': 'e5', 'san': 'Ne5', 'flags': 'n'}, {'color': 'b', 'piece': 'q', 'from': 'd8', 'to': 'b6', 'san': 'Qb6', 'flags': 'n'}, 
{'color': 'w', 'piece': 'p', 'from': 'b2', 'to': 'b3', 'san': 'b3', 'flags': 'n'}, {'color': 'b', 'piece': 'p', 'from': 'c5', 'to': 'd4', 'san': 'cxd4', 'flags': 'c', 'captured': 'p'}, 
{'color': 'w', 'piece': 'p', 'from': 'c3', 'to': 'd4', 'san': 'cxd4', 'flags': 'c', 'captured': 'p'}, {'color': 'b', 'piece': 'p', 'from': 'c6', 'to': 'c5', 'san': 'c5', 'flags': 'n'}, 
{'color': 'w', 'piece': 'k', 'from': 'e1', 'to': 'g1', 'san': 'O-O', 'flags': 'k'}, {'color': 'b', 'piece': 'p', 'from': 'c5', 'to': 'd4', 'san': 'cxd4', 'flags': 'c', 'captured': 'p'}, 
{'color': 'w', 'piece': 'q', 'from': 'd1', 'to': 'd4', 'san': 'Qxd4', 'flags': 'c', 'captured': 'p'}, {'color': 'b', 'piece': 'b', 'from': 'f8', 'to': 'c5', 'san': 'Bc5', 'flags': 'n'}, 
{'color': 'w', 'piece': 'q', 'from': 'd4', 'to': 'd1', 'san': 'Qd1', 'flags': 'n'}, {'color': 'b', 'piece': 'k', 'from': 'e8', 'to': 'g8', 'san': 'O-O', 'flags': 'k'}, 
{'color': 'w', 'piece': 'b', 'from': 'f4', 'to': 'g5', 'san': 'Bg5', 'flags': 'n'}, {'color': 'b', 'piece': 'b', 'from': 'c5', 'to': 'd6', 'san': 'Bd6', 'flags': 'n'},
 {'color': 'w', 'piece': 'b', 'from': 'g5', 'to': 'f6', 'san': 'Bxf6', 'flags': 'c', 'captured': 'n'}]





def parse_moves(pngMoves):
    move_objects = []
    i = 1
   
    for move in pngMoves:
        capture = None
        
        if "captured" in move:
            capture = move["captured"]
        
        move_objects.append(Move(color=move['color'], fromSquare=move['from'], toSquare=move['to'], piece=move['piece'], san = move['san'], moveNum=i, capture = capture))
        
        i = i + 1
    return move_objects

def get_capture_moves(move_objects):
    captureMoves = []
    for move in move_objects:
        if(move.capture):
            captureMoves.append(move)
    return captureMoves

all_move_objects = parse_moves(pngMoves)
captureMoves = get_capture_moves(all_move_objects)

for move in captureMoves:
    captureHistoryList = []
    captureHistoryMove = move
    index = move.moveNum
    print(captureHistoryMove.__dict__)
    for i in range(1,7):
        if(index - i < 0):
            break
        captureHistoryList.append(all_move_objects[index-i].__dict__)
        print(all_move_objects[index-i].__dict__)
    print("=================================================================================")
    
    
    

print("a")        


