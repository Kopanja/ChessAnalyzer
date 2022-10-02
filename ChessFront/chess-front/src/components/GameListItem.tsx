import React from 'react'
import { Props } from '../types/Game.types'
import { Link } from "react-router-dom";
const GameListItem: React.FC<Props> = ({game}) => {

    const onMoreClick = () => {

    }
  return (
    <div>
        <p>{game.white.username}</p>
        <p>{game.black.username}</p>
        <button onClick={onMoreClick}>Analyze</button>
        <Link to="/chessBoard" state={{ data: game }} className="link">AAAAAAAAAA</Link>
    </div>
  )
}

export default GameListItem