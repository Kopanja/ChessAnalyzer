const types = [
    'CORRECT_SQUARE',
	'WRONG_SQUARE',
	'GAME_STARTED',
	'GAME_ENDED',
	'ROUND_STARTED',
	'ROUND_ENDED']

export interface WSEvent {
    type : string;
    square : string;
    isCorrect : boolean;
}