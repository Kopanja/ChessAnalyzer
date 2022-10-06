import React from 'react'
import {Chessboard} from "react-chessboard";
import { useEffect, useState } from "react";
import {Chess} from "chess.js";
import { useLocation } from "react-router-dom";
const ChessBoardComponent = () => {

    const [fen, setFen] = useState<string>('start')
    const [game, setGame] = useState<Chess>(new Chess());
    const [history, setHistory] = useState<[]>([]);
    const [squares, setSquares] = useState<[]>([]);
    const location = useLocation();
    


  const extractPGN = () => {
    const fullString = location.state?.data.pgn;
    const pgnOnly = fullString.split('\n')[22];
    const moves = [];
    const pgnList = pgnOnly.split(" ")
    console.log(pgnList);
    for (let i = 1; i < pgnList.length; i+= 4){
      moves.push(pgnList[i]);
    }
    console.log(location.state?.data);
    console.log(moves);
    return moves;
  }
  
  
  const moves = extractPGN();
   
  
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
    
  const makeSavedMove = () => {
    const currentMove = game.move('d4');
    moves.forEach((move, index) => {
      setTimeout(()=>{
        game.move(move);
        setGame(game);
        setFen(game.fen());
      }, index*1000);
      
      
    })
    
  }
  return (
    <div><Chessboard position={fen} onPieceDrop = {onDrop}/>
    <button onClick={makeSavedMove}>Make Move</button>
    
    </div>
 //   <Chessboard position={this.state.fen} onSquareClick = {this.onSquareClick}
  //      squareStyles={this.state.squareStyles} onDrop = {this.onDrop}/>
  )
}

export default ChessBoardComponent