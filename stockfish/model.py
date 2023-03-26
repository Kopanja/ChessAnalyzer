class StockfishEval:
     def __init__(self, oldFenEval, newFenEval, bestMove, bestMoveFenEval):
         self.oldFenEval = oldFenEval
         self.newFenEval = newFenEval
         self.bestMove = bestMove
         self.bestMoveFenEval = bestMoveFenEval


class Move:
    def __init__(self, color, fromSquare, toSquare, piece, san, moveNum, capture = None) -> None:
        self.color = color
        self.fromSquare = fromSquare
        self.toSquare = toSquare
        self.piece = piece
        self.san = san
        self.capture = capture
        self.preMoveEval = 0
        self.postMoveEval = 0
        self.bestMoveEvaluation = 0
        self.bestMove = ""
        self.fen = ""
        self.moveNum = moveNum
    def setStockEval(self, preMoveEval, postMoveEval, bestMove, bestMoveEvaluation, fen):
        self.preMoveEval = preMoveEval
        self.postMoveEval = postMoveEval
        self.bestMoveEvaluation = bestMoveEvaluation
        self.bestMove = bestMove
        self.fen = fen