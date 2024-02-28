import React from 'react';
import { FaInstagram, FaFacebook, FaLinkedin, FaGoogle, FaSpotify } from 'react-icons/fa';

import '../styles/FooterCompstyle.css';

function FooterComp() {
  return (
    <div className='FooterMainContainer'>
      <div className='FooterIcons'>
        <FaInstagram className='Icon' />
        <FaFacebook className='Icon' />
        <FaLinkedin className='Icon' />
        <FaGoogle className='Icon' />
        <FaSpotify className='Icon' />
      </div>
      <div className='FooterLinks'>
        <a className='FooterEachLink' href='/privacy'>Privacy Statement</a>
        <a className='FooterEachLink' href='/cookie'>Cookie Statement</a>
        <a className='FooterEachLink' href='/accessibility'>Accessibility</a>
      </div>
    </div>
  );
}

export default FooterComp;
