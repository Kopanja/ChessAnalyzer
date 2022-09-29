import React from 'react'
import axios from 'axios'
import { useEffect, useState } from "react";
const UserPage = () => {
    const [games, setGames] = useState<any>();

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

  return (
    <div>UserPage</div>
  )
}

export default UserPage