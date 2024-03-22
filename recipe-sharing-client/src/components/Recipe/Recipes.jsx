import React, { useState } from 'react'
import Header from "../header/header"
import Items from "../items"
import { Button } from 'react-bootstrap';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { Modal } from '@mui/material';
import RCServiceComponent from "../../context/RCService";
import "./Recipe.scss";

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
    borderRadius: "20px",
};

function Recipes({ logOut, data, setData })
{
    const RCService = React.useContext(RCServiceComponent);
    const token = localStorage.getItem("token");
    const [createRecipe, setCreateRecipe] = useState(false);
    const [description, setDescription] = useState();
    const [content, setContent] = useState();
    const [author, setAuthor] = useState();
    const [prepTime, setPrepTime] = useState();
    const [cookTime, setCookTime] = useState();
    const [categoryId, setCategoryId] = useState();

    const handleClose = () =>
    {
        setCreateRecipe(false)
    }

    const addRecipe = async (e) =>
    {
        e.preventDefault()
        let result;
        try
        {
            result = await RCService.PostRecipes(token, 123, description, content, author, prepTime, cookTime, categoryId)
        } catch (e)
        {
            console.log('e', e)
        }
        setData(result)
    }

    return (
        <div
        >
            <Modal
                hideBackdrop
                open={ createRecipe }
                onClose={ handleClose }
                aria-labelledby="child-modal-title"
                aria-describedby="child-modal-description"
            >
                <Box sx={ style }
                >
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Create New Recipe
                        <span style={ { marginRight: "0px", position: "absolute", cursor: 'pointer', float: 'right', marginTop: '-30px', width: '20px', right: 0 } }
                            onClick={ handleClose }
                        >
                            x
                        </span>
                    </Typography>
                    <br />
                    <form>
                        <label>
                            Description:
                            <br />
                            <input type="text" name="description" onChange={ (e) => setDescription(e.target.value) } />
                        </label>
                        <label>
                            Content:
                            <br />
                            <input type="text" name="content" onChange={ (e) => setContent(e.target.value) } />
                        </label>
                        <label>
                            Author:
                            <br />
                            <input type="text" name="author" onChange={ (e) => setAuthor(e.target.value) } />
                        </label>
                        <label>
                            PrepTime:
                            <br />
                            <input type="number" name="preptime" onChange={ (e) => setPrepTime(e.target.value) } />
                        </label>
                        <label>
                            CookTime:
                            <br />
                            <input type="number" name="cooktime" onChange={ (e) => setCookTime(e.target.value) } />
                        </label>
                        <label>
                            category Id:
                            <br />
                            <input type="number" name="categoryId" onChange={ (e) => setCategoryId(e.target.value) } />
                        </label>
                        <Button type="submit"
                            id="names" className="btn mb-3" variant="outline-dark"
                            style={ { float: "right" } }
                            onClick={ (e) => addRecipe(e) }>
                            Add
                        </Button>
                    </form>
                </Box>
            </Modal>
            <Header logOut={ logOut } />
            <br />
            <Button id="names" className="btn mb-3" variant="outline-dark"
                style={ { fontFamily: "initial", marginRight: "900px", marginTop: "10px" } }
                onClick={ () => setCreateRecipe(true) }
            >
                Create New Recipe
            </Button>
            <Items data={ data } />
        </div>
    )
}

export default Recipes;

