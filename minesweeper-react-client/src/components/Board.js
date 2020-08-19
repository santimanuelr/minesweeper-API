import React from "react";
import Locker from "./Locker";

const Board = ({ game, onClickLocker, onClickFlag }) => {
    return (
        <div>
            <center style={{"backgroundColor":"paleturquoise"}}>
                <h1>Mine Sweeper</h1>
                {game.gameStatus === "IN_PLAY" &&
                    <table id = "board">
                    {game.lockers.map(row => (
                        <tr>
                            {row.map(
                                locker => (
                                    <Locker idGame = {game.id} locker={locker} onClickLocker={onClickLocker} onClickFlag={onClickFlag}></Locker>
                                )
                            )}
                        </tr>
                    ))}
                    </table>
                }
                {game.gameStatus === "LOST" && <h2>Game Over</h2>}
                {game.gameStatus === "WON" && <h2>You WIN!</h2>}
            </center>
        </div>  
    );
}

export default Board;