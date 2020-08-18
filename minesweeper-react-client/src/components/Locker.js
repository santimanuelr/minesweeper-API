import React from "react";

const Locker = ({ idGame, locker, onClickLocker, onClickFlag }) => {

    const tdStyle = {
        height: "26px",
        width: "30px",
        borderStyle: "solid",
        lineHeight: "30px",
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
        margin: "0"
    }

    return (
        <td style={tdStyle}>
            {locker && !locker.exposed && <>
                <button style={square} 
                onClick={() => onClickLocker(idGame, locker.point.x, locker.point.y)}>
                </button>
                <button style={square} 
                onClick={() => onClickFlag(idGame, locker.point.x, locker.point.y, true, false)}>
                    <span role="img">⛳️</span>
                </button>
                <button style={square} 
                onClick={() => onClickFlag(idGame, locker.point.x, locker.point.y, false, true)}>
                    <span role="img">❓</span>
                </button>
            </>}
            {locker && locker.exposed && locker.type === 'NUMBER' 
            && <h3 style = {numberStyle}>{locker.number}</h3>}
            {locker && !locker.exposed && locker.flag 
            && <h3 style = {numberStyle}><span role="img">⛳️</span></h3>}
            {locker && !locker.exposed && locker.question 
            && <h3 style = {numberStyle}><span role="img">❓</span></h3>}
        </td>
    );
}

export default Locker;