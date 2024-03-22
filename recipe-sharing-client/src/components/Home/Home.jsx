import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import React, { useEffect, useState, Fragment } from "react";
import grill from "../../images/delish.jpeg";
import Header from "../header/header"
import RCServiceComponent from "../../context/RCService";
import Items from "../items"
import Footer from "../footer"
import "./Home.scss"

function Home({ logOut, data, setData })
{
    const RCService = React.useContext(RCServiceComponent);
    const token = localStorage.getItem("token");

    const myFunction = async () =>
    {
        let result3;
        try
        {
            result3 = await RCService.GetRecipes(token)
        } catch (e)
        {
            console.log('e', e)
        }
        setData(result3)
    }

    useEffect(() =>
    {
        myFunction()
    }, [])

    return (
        <div>
            <h1 className="heading"
                style={ { position: "absolute", marginLeft: "30px", zIndex: "2" } }
            >
                <i>
                    <b>
                        <Link to={ "/" } style={ { color: "black", textDecoration: "none" } }>
                            Recipe Sharing
                        </Link>
                    </b>
                </i>
            </h1>
            <img src={ grill } alt="grill"
                className="images"
                style={ { width: "100%", position: "relative", height: '600px', objectFit: "cover" } }
            />
            <div>
                <Header logOut={ logOut } />
            </div>
            <div>
                <Items data={ data } />
            </div>
            <br />
            <div className="footer">
                <Footer />
            </div>
        </div>
    )
}

export default Home;

