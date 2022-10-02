import React from 'react'
import axios from 'axios'
import { useEffect, useState } from "react";
const UserPage = () => {
    const [games, setGames] = useState<any>();
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
        axios.get(`https://api.chess.com/pub/player/${username}/games/2022/08`)
        .then((res) => {
            console.log(res.data);
        }).catch((err) => {
            console.log("Ne postoji");
            console.log(err);
        })
      }
  return (
    <div>
      <label>
    Chess.com Username:
    </label>
    <input name='email' required value={username} onChange={(e) => {setUsername(e.target.value)}} type="text"></input>
    <button onClick={onButtonClick}>Ok</button></div>
  )
}

export default UserPage