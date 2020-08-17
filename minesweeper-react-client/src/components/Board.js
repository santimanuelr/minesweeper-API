import React from "react";
import Locker from "./Locker";

const Board = ({ lockers, onClickLocker }) => {
    return (
        <div>
            <center>
                <h1>Mine Sweeper</h1>
                <table id = "board">
                {lockers.map(row => (
                    <tr>
                        {row.map(
                            locker => (
                                <Locker locker={locker} onClickLocker={onClickLocker}></Locker>
                            )
                        )}
                    </tr>
                ))}
                </table>
            </center>
        </div>  
    );
}

export default Board;