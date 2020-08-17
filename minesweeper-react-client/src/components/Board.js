import React from "react";
import Locker from "./Locker";

const Board = ({ }) => {

    let matrix = Array(9).fill().map(()=>Array(9).fill());

    return (
        <div>
            <center>
                <h1>Mine Sweeper</h1>
                {matrix.map(row => (
                    <div>
                        {row.map(
                            locker => (
                                <Locker></Locker>
                            )
                        )}
                    </div>
                ))}
            </center>
        </div>  
    );
}

export default Board;