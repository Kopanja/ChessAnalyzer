import { Chessboard } from "react-chessboard";
import { BoardPosition, CustomSquareStyles } from "react-chessboard/dist/chessboard/types";
import { useEffect, useState } from "react";
import { Chess, Square } from "chess.js";
import { Client } from '@stomp/stompjs';
import axios from "axios";
import ProgressBar from "@ramonak/react-progress-bar";
import "../styles/knight-game.css"

const SOCKET_URL = 'ws://localhost:8080/ws-message';
const KnightGameComponent = () => {

  const types = {
    CORRECT_SQUARE : 'CORRECT_SQUARE',
	  WRONG_SQUARE : 'WRONG_SQUARE',
	  GAME_STARTED : 'GAME_STARTED',
	  GAME_ENDED : 'GAME_ENDED',
	  ROUND_STARTED : 'ROUND_STARTED',
	  ROUND_ENDED : 'ROUND_ENDED',
    TICK_EVENT : 'TIMER_TICK'
  }
  const [knightSquare, setKnightSquare] = useState<BoardPosition>({});
  const [isConnected, setIsConnected] = useState<boolean>(false);
  const [squareStyles, setSquareStyles] = useState<CustomSquareStyles>({});
  const [progressBarValue, setProgressBarValue] = useState<number>(100);
  const [client, setClient] = useState<Client>(new Client({
    brokerURL: SOCKET_URL,
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
    onConnect: () => {
      if(isConnected){
        return
      }
      setIsConnected(true);
      console.log("Connected!!")
      client.subscribe('/topic/message', function (msg) {
        parseEvent(JSON.parse(msg.body));
      });
    },
    onDisconnect: () => {
      console.log("Disconnected!!")
    }
  }));
  const parseEvent = (event : any) => {
    
    if(event.type === types.CORRECT_SQUARE){
      const square = event.square  as Square;
      //squareStyles[square] = {backgroundColor : 'green'}
      setSquareStyles((state) => {return {...state, [square] : {backgroundColor : 'green'}}})
    }

    if(event.type === types.WRONG_SQUARE){
      const square = event.square  as Square;
      console.log(event)
      //squareStyles[square] = {backgroundColor : 'red'}
      setSquareStyles((state) => {return {...state, [square] : {backgroundColor : 'red'}}})
     
    }

    if(event.type === types.ROUND_STARTED){
      console.log(event);
      const a = event.square  as Square
      const position = {} as BoardPosition
      position[a] = 'wN';
      setKnightSquare(position)
    }
    if(event.type === types.ROUND_ENDED){
      console.log(event);
      console.log("Round Ended")
      setSquareStyles({} as CustomSquareStyles)
      setProgressBarValue(state => (state + event.scorePoints) >= 100 ? 100 : state + event.scorePoints);
    }

    if(event.type === types.GAME_ENDED){
      console.log(event);
      console.log("Game Ended")
    }

    if(event.type === types.GAME_STARTED){
      console.log(event);
      setProgressBarValue(100);
      setSquareStyles({} as CustomSquareStyles);
    }

    if(event.type === types.TICK_EVENT){
      const newProgressBarValue = progressBarValue - 10
      setProgressBarValue(state => state + event.scorePoints);
      //console.log("PROGRESS VALUE",progressBarValue)
    }
  }

  client.activate();

  const startGame = () => {
    axios.get("http://localhost:8080/api/test/start-knight-game").then(res => {
    console.log("igra je pocela")
    console.log(res)
    const a = res.data.square  as Square
    const position = {} as BoardPosition
    position[a] = 'wN';
    //setKnightSquare(position)
  }
    );
  }

  const onSquareClick = (data : any) => {
    console.log(data);
    
    axios.post("http://localhost:8080/api/test/square-selected", {square : data})

  } 
  
  
  const a = {e5 : {backgroundColor : 'red'}} as CustomSquareStyles

  const getPositionObject = (data : any) => {
    console.log(data);
  }
  return (
    <div className="main-container">
      <Chessboard getPositionObject = {getPositionObject} onSquareClick = {onSquareClick} customSquareStyles = {squareStyles} position = {knightSquare} boardWidth={600}/>
      <button onClick={startGame}>Start Game</button>
      <ProgressBar completed={progressBarValue} width="500px" isLabelVisible={false} />
    </div>
    )
}

export default KnightGameComponent