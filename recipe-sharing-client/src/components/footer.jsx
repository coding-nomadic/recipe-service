import React from 'react'
import { BsInstagram } from 'react-icons/bs';
import { GrFacebook } from 'react-icons/gr';
import { BsTwitter } from 'react-icons/bs';

function footer()
{
  return (
    <div>
      <i>
        <b>
          Recipe Sharing
        </b></i>
      &nbsp;
      <BsInstagram /> &nbsp;
      <GrFacebook /> &nbsp;
      <BsTwitter />
    </div>
  )
}

export default footer;

