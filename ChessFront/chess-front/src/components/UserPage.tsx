import React from 'react'
import axios from 'axios'
import { useEffect, useState } from "react";
import { Game } from '../types/Game.types';
import GameListComponent from './GameListComponent';
const UserPage = () => {
   
    const [isUsernameEntered, setIsUsernameEntered] = useState<boolean>(false);
    const [username, setUsername] = useState<string>("");

    /*
    useEffect(() => {
        console.log("CCCCCCCCCC");
        axios.get("https://api.chess.com/pub/player/PlayerMark/games/2022/08")
        .then((res) => {
            console.log("AAAAAAAAAAAA")
            console.log(res.data);
        }).catch((err) => {
            console.log(err);
        })
    
        
        
      }, [])
*/
      const onButtonClick = () => {
        setIsUsernameEntered(true);
      }
  return (
    <div>
      <label>
    Chess.com Username:
    </label>
    <input name='email' required value={username} onChange={(e) => {setUsername(e.target.value)}} type="text"></input>
    <button onClick={onButtonClick}>Ok</button>
    {isUsernameEntered  &&

    <GameListComponent user={{username : username}}></GameListComponent>

    }
    </div>
  )
}

export default UserPage