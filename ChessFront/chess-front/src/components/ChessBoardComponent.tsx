import React from 'react'
import {Chessboard} from "react-chessboard";
import { useEffect, useState } from "react";
import {Chess} from "chess.js";
const ChessBoardComponent = () => {

    const [fen, setFen] = useState<string>('start')
    const [game, setGame] = useState<Chess>(new Chess());
    const [history, setHistory] = useState<[]>([]);
    const [squares, setSquares] = useState<[]>([]);


    const makeAMove = (move : {from : string, to : string}) => {
        
        console.log(move.from);
        console.log(move.to);
        const currentMove = game.move(move);
        //setGame(gameCopy);
        setGame(game);
        
        console.log(currentMove);
            
        return currentMove;
    }
    const onDrop = (sourceSquare : string, targetSquare : string) => {
        console.log(fen);
        console.log(sourceSquare);
        console.log(game.fen())
       
        const move = makeAMove({
          from: sourceSquare,
          to: targetSquare,
           // always promote to a queen for example simplicity
        });
        console.log(move);
        // illegal move
        if (move === null) return false;
        
       // setTimeout(makeRandomMove, 200);
        console.log(game.fen())
        setFen(game.fen());
        return true;
        
      }
    
  return (
    <div><Chessboard position={fen} onPieceDrop = {onDrop}/></div>
 //   <Chessboard position={this.state.fen} onSquareClick = {this.onSquareClick}
  //      squareStyles={this.state.squareStyles} onDrop = {this.onDrop}/>
  )
}

export default ChessBoardComponent