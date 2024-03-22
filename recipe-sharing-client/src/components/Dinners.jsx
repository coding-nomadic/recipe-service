import React from 'react'
import Header from "./header/header"

function Dinners({ logOut })
{
    return (
        <div>
            <Header logOut={ logOut } />
            <br />
            Dinners
        </div>
    )
}

export default Dinners