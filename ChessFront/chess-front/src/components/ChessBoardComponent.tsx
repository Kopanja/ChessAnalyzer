import React from 'react'
import {Chessboard} from "react-chessboard";
import { useEffect, useState } from "react";
import {Chess} from "chess.js";
import { useLocation } from "react-router-dom";
import axios from 'axios';
const ChessBoardComponent = () => {

    const [fen, setFen] = useState<string>('start')
    const [game, setGame] = useState<Chess>(new Chess());
    const [history, setHistory] = useState<[]>([]);
    const [squares, setSquares] = useState<[]>([]);
    const location = useLocation();
    


    //---------FOR CHESS ANALYZING--------------------------------------------------------
  
  const extractPGN = () => {
   
    const fullString = location.state?.data.pgn;
    const pgnOnly = fullString.split('\n')[22];
    console.log(location.state?.data)
    
    //ovo ce mozda trebati ali nisam sig
    //game.loadPgn(pgnOnly);
    
    
    const moves = [];
    const pgnList = pgnOnly.split(" ")
    
    for (let i = 1; i < pgnList.length; i+= 4){
      moves.push(pgnList[i]);
    }
    return moves;
  }
  
  const moves = extractPGN();
  
 
  const sendMoves = () => {
    /*
    axios.post("http://localhost:5000/post-moves", moves).then((res) => {
      console.log(res.data);
    })
    axios.post("http://localhost:5000/post-moves-saved-data", game.history({ verbose: true })
    */
    console.log( game.history({ verbose: true }), "a")
    axios.post("http://localhost:5000/post-moves",game.history({ verbose: true })).then((res) => {
      console.log("Stockfish zavrsio");
      console.log(res.data)
      axios.post("http://localhost:8080/api/test/post-moves", res.data).then((res) => {
        console.log("Drools zavrsio");
     // console.log(res.data);
    })
    })
    
  }
  
  const makeAMove = (move : {from : string, to : string}) => {
        
        const currentMove = game.move(move);
        setGame(game);
        
        console.log(currentMove);
            
        return currentMove;
    }
    const onDrop = (sourceSquare : string, targetSquare : string) => {

      
        const move = makeAMove({
          from: sourceSquare,
          to: targetSquare,
           // always promote to a queen for example simplicity
        });
        // illegal move
        if (move === null) return false;
        setFen(game.fen());
        return true;
        
      }
    
  const makeSavedMove = () => {
   
    let index = 0;
    const intervalId = setInterval(() => {
      game.move(moves[index]);
      setGame(game);
      setFen(game.fen());
      index += 1;
      if(index == moves.length){
        clearInterval(intervalId);
      }
      
    }, 30)
    
  }
  //------------------------------------------------------------------------
  //---------------For Stockfish AI-----------------------------------------

  const onDropStockfishOpponent = (sourceSquare : string, targetSquare : string) => {

      
    const move = makeAMove({
      from: sourceSquare,
      to: targetSquare,
       // always promote to a queen for example simplicity
    });
    // illegal move
    if (move === null) return false;
    setFen(game.fen());
    axios.post("http://localhost:5000/move-made", move).then((res) => {
        makeAMove({
          from : res.data.fromSquare,
          to: res.data.toSquare
        })
        setFen(game.fen());
    })
    
    return true;
    
  }

  //-----------------------------------------------------------------------
  return (
    <div><Chessboard position={fen} onPieceDrop = {onDropStockfishOpponent}/>
    <button onClick={makeSavedMove}>Make Move</button>
    <button onClick={sendMoves}>Send Moves</button>
    
    </div>
 //   <Chessboard position={this.state.fen} onSquareClick = {this.onSquareClick}
  //      squareStyles={this.state.squareStyles} onDrop = {this.onDrop}/>
  )
}

export default ChessBoardComponent