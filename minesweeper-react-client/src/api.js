export const API_URL = "https://minesweeper-java-api.herokuapp.com/api/games"
export const API_URL_LOCKERS = "https://minesweeper-java-api.herokuapp.com/api/lockers"

// export const API_URL = "http://localhost:8080/api/games"
// export const API_URL_LOCKERS = "http://localhost:8080/api/lockers"

export const putLockerActions = async (idGame, x, y, flag, question, uncheck) => {
    const requestOptionsPut = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        idGame: idGame,
        x:x,
        y:y,
        exposed:false,
        question:question,
        flag:flag,
        uncheck:uncheck
      })
    };
    const response = await fetch(API_URL_LOCKERS, requestOptionsPut)
    if (response.ok) {
      return response.json();
    } else {
      console.error(response);
      return {};
    }
  }

export const fetchPutLocker = async (idGame, x, y) => {
    const requestOptionsPut = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
        idGame: idGame,
        x:x,
        y:y,
        exposed:true,
        question:false,
        flag:false,
        uncheck:false
        })
    };
    const response = await fetch(API_URL_LOCKERS, requestOptionsPut)
    if (response.ok) {
      return response.json();
    } else {
      console.error(response);
      return {};
    }
}