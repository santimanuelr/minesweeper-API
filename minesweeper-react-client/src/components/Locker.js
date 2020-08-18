import React from "react";

const Locker = ({ idGame, locker, onClickLocker, onClickFlag }) => {

    const tdStyle = {
        height: "30px",
        width: "30px",
        borderStyle: "solid",
        lineHeight: "30px"
    };

    const square = {
        padding:"0px",
        margin:"0px",
        width:"30px",
        height:"30px",
        verticalAlign:"top"
    }

    const numberStyle = {
        textAlign: "center",
        margin: "0"
    }

    return (
        <td style={tdStyle}>
            {locker && locker.type}
            {locker && !locker.exposed && <>
                <button style={square} 
                onClick={() => onClickLocker(idGame, locker.point.x, locker.point.y)}>
                </button>
                <button style={square} 
                onClick={() => onClickFlag(idGame, locker.point.x, locker.point.y)}>
            </button></>}
            {locker && locker.exposed && locker.type === 'NUMBER' 
            && <h3 style = {numberStyle}>{locker.number}</h3>}
        </td>
    );
}

export default Locker;