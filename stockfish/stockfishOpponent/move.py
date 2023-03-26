class Move:
    def __init__(self, color, piece, fromSquare, toSquare, san, flags) -> None:
        self.color = color
        self.fromSquare = fromSquare
        self.toSquare = toSquare
        self.piece = piece
        self.san = san