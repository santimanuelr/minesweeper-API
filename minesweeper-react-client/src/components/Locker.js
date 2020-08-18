import React from "react";

const Locker = ({ idGame, locker, onClickLocker, onClickFlag }) => {

    const tdStyle = {
        height: "78px",
        width: "78px",
        borderStyle: "solid",
        borderWidth: "2px",
        "white-space": "nowrap"
    };

    const square = {
        padding:"0px",
        margin:"0px",
        width:"26px",
        height:"26px",
        verticalAlign:"top"
    }

    const numberStyle = {
        textAlign: "center",
        margin: "0",
    }

    const emojiStyle = {
        textAlign: "center",
        margin: "0",
        fontSize: "small"
    }

    return (
        <td style={tdStyle}>
            {locker && !locker.exposed && <>
                <button style={square} 
                    onClick={() => onClickLocker(idGame, locker.point.x, locker.point.y)}>
                </button>
                <button style={square}
                    onClick={() => onClickFlag(idGame, locker.point.x, locker.point.y, true, false)}>
                    <span role="img" aria-label="flag">⛳️</span>
                </button>
                <button style={square} 
                    onClick={() => onClickFlag(idGame, locker.point.x, locker.point.y, false, true)}>
                    <span role="img" aria-label="question">❓</span>
                </button>
            </>}
            {locker && locker.exposed && locker.type === 'NUMBER' 
            && <h2 style = {numberStyle}>{locker.number}</h2>}
            {locker && !locker.exposed && locker.flag 
            && <h3 style = {emojiStyle}><span role="img" aria-label="flag">⛳️</span></h3>}
            {locker && !locker.exposed && locker.question 
            && <h3 style = {emojiStyle}><span role="img" aria-label="question">❓</span></h3>}
        </td>
    );
}

export default Locker;