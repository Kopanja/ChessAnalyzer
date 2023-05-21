import React from 'react'
import { useEffect, useState } from "react";
import { Props, User } from '../types/User.types';
import axios from 'axios';
import { Game } from '../types/Game.types';
import GameListItem from './GameListItem';

const GameListComponent : React.FC<Props> = ( {user} ) => {
    const [games, setGames] = useState<Game[]>([]);
    useEffect(() => {
        console.log("CCCCCCCCCC");
        axios.get(`https://api.chess.com/pub/player/${user.username}/games/2022/08`)
        .then((res) => {
            setGames(res.data.games.slice(0,10));
        }).catch((err) => {
            console.log(err);
        })
    
        
        
      }, [])
  return (
    <div>
        {games.map((game, index) => (
            <GameListItem key={index} game = {game}></GameListItem>
        ))}
    </div>
  )
}

export default GameListComponent