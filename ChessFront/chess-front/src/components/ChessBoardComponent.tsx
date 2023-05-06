import React from 'react'
import { Chessboard } from "react-chessboard";
import { useEffect, useState } from "react";
import { Chess } from "chess.js";
import { useLocation } from "react-router-dom";
import axios from 'axios';
const ChessBoardComponent = () => {

  const [fen, setFen] = useState<string>('start')
  const [game, setGame] = useState<Chess>(new Chess());
  const [arrows, setArrows] = useState<string[][]>([]);
  const [history, setHistory] = useState<[]>([]);
  const [squares, setSquares] = useState<[]>([]);
  const location = useLocation();



  //---------FOR CHESS ANALYZING--------------------------------------------------------

  useEffect(() => {}, [arrows])

  const extractPGN = () => {

    const fullString = location.state?.data.pgn;
    const pgnOnly = fullString.split('\n')[22];
    //console.log(location.state?.data)

    //ovo ce mozda trebati ali nisam sig
    //game.loadPgn(pgnOnly);


    const moves = [];
    const pgnList = pgnOnly.split(" ")

    for (let i = 1; i < pgnList.length; i += 4) {
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
    console.log(game.history({ verbose: true }), "a")
    axios.post("http://localhost:5000/post-moves", game.history({ verbose: true })).then((res) => {
      console.log("Stockfish zavrsio");
      console.log(res.data)
      axios.post("http://localhost:8080/api/test/post-moves", res.data).then((res) => {
        console.log("Drools zavrsio");
        console.log(res.data);
      })
    })

  }

  const makeAMove = (move: { from: string, to: string }) => {

    const currentMove = game.move(move);
    game.setComment("AAAAAA");
    setGame(game);

    //console.log(currentMove);

    return currentMove;
  }
  const onDrop = (sourceSquare: string, targetSquare: string) => {


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
      if (index === moves.length) {
        clearInterval(intervalId);
      }

    }, 30)

  }
  //------------------------------------------------------------------------
  //---------------For Stockfish AI-----------------------------------------

  const onDropStockfishOpponent = (sourceSquare: string, targetSquare: string) => {
    const move = makeAMove({
      from: sourceSquare,
      to: targetSquare,
      // always promote to a queen for example simplicity
    });
    if (move === null) return false;
    setFen(game.fen());
    console.log(move);

    //console.log(game.fen())
    const playerMove = { fromSquare: sourceSquare, toSquare: targetSquare }
    axios.post("http://localhost:8080/api/test/post-one-move-2", playerMove).then((res) => {
      console.log(res.data);
      const canCaptures = res.data.canCaptures;
      const stockfishMove = res.data.stockfishMove;
      const bestPlayerMoves = res.data.bestPlayerMoves;
      setArrows([]);
      if (canCaptures.length > 0) {

        //arrows.push([res.data.fromSquare, res.data.toSquare]);
        //arrows.push(['a1', 'a5']);
        canCaptures.forEach((canCapture: any) => {
          setArrows([...arrows, [canCapture.fromSquare, canCapture.toSquare]]);
        });
      
      }

      if (bestPlayerMoves.length > 0) {

        //arrows.push([res.data.fromSquare, res.data.toSquare]);
        //arrows.push(['a1', 'a5']);
        console.log(bestPlayerMoves)
        bestPlayerMoves.forEach((bestPlayerMove: any) => {
          setArrows([...arrows, [bestPlayerMove.fromSquare, bestPlayerMove.toSquare]]);
          console.log(arrows)
        });
      }
      const move = makeAMove({
        from: stockfishMove.fromSquare,
        to: stockfishMove.toSquare,
        // always promote to a queen for example simplicity
      });
      // illegal move
      if (move === null) return false;
      setFen(game.fen());
    }); /*
    const move = makeAMove({
      from: sourceSquare,
      to: targetSquare,
       // always promote to a queen for example simplicity
    });
    // illegal move
    if (move === null) return false;
    setFen(game.fen());
    console.log(move);
   
    axios.post("http://localhost:5000/move-made", move).then((res) => {
        console.log(res.data)
        makeAMove({
          from : res.data.fromSquare,
          to: res.data.toSquare
        })
        const stockMove = {fromSquare : res.data.fromSquare, toSquare : res.data.toSquare, san : '', piece : '', boardNum : 0}
        axios.post("http://localhost:8080/api/test/post-one-move-2", stockMove).then((res) =>
        {
          console.log(res.data);
        });
        setFen(game.fen());
        //posalji potez drools-u, dobij odgovor

    })*/

    return true;

  }

  const startGame = () => {
    axios.get("http://localhost:8080/api/test/init-board").then(res => console.log("drools poceo"));
    axios.get("http://localhost:5000/start-game").then(res => console.log("stockfish poceo"));
  }

  const onArrowsChangeTest = (data: any) => {
    //console.log("test", data)
    const currentArrowsPolygons = document.getElementsByTagName("polygon");
    //console.log(currentArrowsPolygons)
    for (let i = 0; i < currentArrowsPolygons.length; i++) {
      currentArrowsPolygons[i].style.fill = "rgb(255,61,61)";
      currentArrowsPolygons[i].style.opacity = "0.7";
    }

    const currentArrowsLines = document.getElementsByTagName("line");

    for (let i = 0; i < currentArrowsLines.length; i++) {
      currentArrowsLines[i].style.stroke = "rgb(255,61,61)";
      currentArrowsLines[i].style.opacity = "0.7";
    }
  }
  //-----------------------------------------------------------------------
  return (
    <div><Chessboard position={fen} onPieceDrop={onDropStockfishOpponent} customArrows={arrows} onArrowsChange={onArrowsChangeTest} />
      <button onClick={startGame}>Start Game</button>
      <button onClick={makeSavedMove}>Make Move</button>
      <button onClick={sendMoves}>Send Moves</button>

    </div>
    //   <Chessboard position={this.state.fen} onSquareClick = {this.onSquareClick}
    //      squareStyles={this.state.squareStyles} onDrop = {this.onDrop}/>
  )
}

export default ChessBoardComponent