import React from "react";
import Locker from "./Locker";

const Board = ({ game, onClickLocker, onClickFlag }) => {
    return (
        <div>
            <center>
                <h1>Mine Sweeper</h1>
                {game.status === "IN_PLAY" &&
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
                {game.status === "LOST" && <h2>Game Over</h2>}
                {game.status === "WON" && <h2>You WIN!</h2>}
            </center>
        </div>  
    );
}

export default Board;