import React from "react";
import Locker from "./Locker";

const Board = ({ game, onClickLocker }) => {
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
                                    <Locker idGame = {game.id} locker={locker} onClickLocker={onClickLocker}></Locker>
                                )
                            )}
                        </tr>
                    ))}
                    </table>
                }
                {game.status === "LOST" && <h2>Game Over</h2>}
            </center>
        </div>  
    );
}

export default Board;