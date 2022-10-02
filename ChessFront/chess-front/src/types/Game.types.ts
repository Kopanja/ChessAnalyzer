import { Player } from "./Player.types";

export interface Game {
    black: Player;
    white: Player;
    pgn: string;
  }

  export interface Props {
    game: Game;
  
  }