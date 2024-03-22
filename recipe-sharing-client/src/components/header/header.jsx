import React from 'react'
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import "./header.scss";
import { useNavigate } from "react-router-dom";

function Header({ logOut })
{
  let navigate = useNavigate();

  const signOut = () =>
  {
    logOut()
    localStorage.clear();
    navigate("/")
  }

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div style={ { marginLeft: "40vw" } }>
        <Link to={ "/home" } className="navbar-brand" style={ { fontFamily: "initial", marginRight: "150px", marginLeft: "-300px" } } href="#">Recipe Sharing</Link>
        <Link to={ "/dinners" } className="navbar-brand" style={ { fontFamily: "initial" } } href="#">Dinners</Link>
        <Link to={ "/foodnews" } className="navbar-brand" style={ { fontFamily: "initial" } } href="#">Food News </Link>
        <Link to={ "/recipes" } className="navbar-brand" style={ { fontFamily: "initial" } } href="#">Recipes</Link>
        <Link to={ "/tips" } className="navbar-brand" style={ { fontFamily: "initial" } } href="#">Tips</Link>
        <Button id="names" className="btn mb-3" variant="outline-dark"
          style={ { fontFamily: "initial", marginLeft: "250px", marginRight: "-300px", marginTop: "10px" } }
          onClick={ signOut }
        >
          Sign Out
        </Button>

      </div>
    </nav>
  )
}

export default Header;