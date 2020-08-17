import React from "react";

const Locker = ({ locker, onClickLocker }) => {

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

    return (
        <td style={tdStyle}>
            {locker && locker.type}
            <button style={square} onClick={() => onClickLocker()}></button>
        </td>
    );
}

export default Locker;