import React from "react";
import Locker from "./Locker";

const Board = ({ lockers }) => {

    let matrix = Array(9).fill().map(()=>Array(9).fill());

    return (
        <div>
            <center>
                <h1>Mine Sweeper</h1>
                <table id = "board">
                {lockers.map(row => (
                    <tr>
                        {row.map(
                            locker => (
                                <Locker locker={locker}></Locker>
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