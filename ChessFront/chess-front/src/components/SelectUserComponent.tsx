import React from 'react'
import { useEffect, useState } from "react";
const SelectUserComponent = () => {

    const [username, setUsername] = useState<string>();

    const onButtonClick = (username : string) => {

    }
  return (
    <div>
        <label>
                Email:
            </label>
            <input name='email' required value={username} onChange={(e) => {setUsername(e.target.value)}} type="text"></input>
            <button>Ok</button>
    </div>
  )
}

export default SelectUserComponent