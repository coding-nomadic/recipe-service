import React, { useState } from 'react'
import { Button, Col, Container, Row } from 'react-bootstrap';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import RCServiceComponent from "../context/RCService";
import { useNavigate } from "react-router-dom";

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

function Login({ logIn })
{
  const [SignUpOpen, setSignUpOpen] = React.useState(false);
  const handleSignUpOpen = () => setSignUpOpen(true);
  const handleSignUpClose = () => setSignUpOpen(false);
  const [userName, setUserName] = useState();
  const [password, setPassword] = useState();
  const [email, setEmail] = useState();
  const [fullName, setFullName] = useState();
  const [mobileNumber, setMobileNumber] = useState();
  const RCService = React.useContext(RCServiceComponent);
  let navigate = useNavigate();

  const signIn = async (e) =>
  {
    e.preventDefault();
    let result;
    try
    {
      result = await RCService.postAuthenticate(userName, password);
      if (!!result)
      {
        logIn()
        // Set Token:
        localStorage.setItem("token", result.token);
        // Navigate to home page:
        navigate("/home")
      }
      return null;
    } catch (e)
    {
      console.log('e', e)
    }
  }

  const signUp = async (e) =>
  {
    e.preventDefault();
    let result2;
    try
    {
      result2 = await RCService.SignUp(userName, password, email, fullName, mobileNumber)
    } catch (e)
    {
      console.log('e', e)
    }
  }

  const toggleSignUp = () =>
  {
    handleSignUpOpen();
  }

  const toggleSignIn = () =>
  {
    handleSignUpClose();
  }

  return (
    <div>
      { !SignUpOpen &&
        <Box sx={ style }>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Sign In
          </Typography>
          <br />
          <form>
            <label>
              User Name:
              <br />
              <input type="text" name="username" onChange={ (e) => setUserName(e.target.value) } />
            </label>
            <br />
            <br />
            <label>
              Password:
              <br />
              <input type="text" name="password" onChange={ (e) => setPassword(e.target.value) } />
            </label>
            <br />
            <i style={ { color: "blue", fontSize: "13px", float: "right", cursor: "pointer" } }
              onClick={
                toggleSignUp
              }>
              Sign Up
            </i>
            <br />
            <br />
            <Button type="submit"
              id="names" className="btn mb-3" variant="outline-dark"
              style={ { float: "right" } }
              onClick={ (e) => signIn(e) }>
              Submit
            </Button>
          </form>
        </Box>
      }
      { SignUpOpen && <Box sx={ style }>
        <Typography id="modal-modal-title" variant="h6" component="h2">
          Sign Up
        </Typography>
        <br />
        <form>
          <label>
            User Name:
            <br />
            <input type="text" name="username" onChange={ (e) => setUserName(e.target.value) } />
          </label>
          <br />
          <br />
          <label>
            Password:
            <br />
            <input type="text" name="password" onChange={ (e) => setPassword(e.target.value) } />
          </label>
          <br />
          <label>
            Email:
            <br />
            <input type="text" name="email" onChange={ (e) => setEmail(e.target.value) } />
          </label>
          <br />
          <br />
          <label>
            Full Name:
            <br />
            <input type="text" name="fullName" onChange={ (e) => setFullName(e.target.value) } />
          </label>
          <br />
          <label>
            Mobile number:
            <br />
            <input type="text" name="mobile" onChange={ (e) => setMobileNumber(e.target.value) } />
          </label>
          <br />

          <i style={ { color: "blue", fontSize: "13px", float: "right", cursor: "pointer" } }
            onClick={ toggleSignIn }>
            Sign In
          </i>
          <br />
          <br />
          <Button type="submit"
            id="names" className="btn mb-3" variant="outline-dark"
            style={ { float: "right" } }
            onClick={ (e) => signUp(e) }>
            Submit
          </Button>
        </form>
      </Box> }
    </div>
  )
}

export default Login