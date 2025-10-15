//TODO: CTA button Animation
//TODO: create cat svg img with eyes follow and mug animation
//TODO: some background pattern 





import logo from '../assets/logo.jpg'
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Cat from '../Component/Cat'
import '../Styles/App.css'




export default function Hero() {

  const navigate=useNavigate();
  return (
  <div className="min-h-screen w-screen flex flex-col ">
      <nav className="h-16 flex flex-shrink-0 justify-between items-center px-6 shadow">
        {/* Logo Section */}
        <div className="flex items-center space-x-2">
          {/* Logo img */}
          <div className="h-12 w-12 rounded-full overflow-hidden">
            <img
              src={logo}
              alt="logo"
              className="h-full w-full object-cover"
            />
          </div>

          <p className="font-bold text-lg font-caveat text-[28px] text-amber-600">Book Exchange</p>
        </div>
       {/* Quick Links */}
        <div className="flex items-center p-4">
          <ul className="flex flex-row items-center space-x-6 text-black font-medium">
            <li>
                <a href="#" className=" group py-4 px-2 ">
                  <span className="relative after:content-[''] after:absolute after:left-0 after:bottom-0 after:w-0 after:h-[2px] after:bg-black after:transition-all after:duration-400 group-hover:after:w-full">
                    About Us
                  </span>
                </a>
              </li>
              <li>
                  <button
                    className="bg-amber-600 text-white px-5 py-2 rounded-full 
                    font-semibold hover:bg-white hover:text-black hover:animate-none hover:scale-110 
                     active:bg-amber-600 active:scale-90 transition-all duration-200  "
                    onClick={()=>setTimeout(()=>navigate('/users/login'),300)}
                  >
                    Login
                  </button>
            </li>
          </ul>
        </div>
      </nav>

      <main className='flex flex-row flex-1  overflow-auto '>
        {/* Left Side */}
        <div className='flex-2  flex flex-col space-y-8 mt-8 mx-6'>
          {/* heading */}
            <div className='flex flex-col justify-center px-6 max-w-4xl mx-6'>
              <h1 className='font-black text-5xl sm:text-6xl  xl:text-[70px] leading-26'>
                Exchange books,
                <span className='text-amber-600 block italic leading-none -mt-1 md:-mt-2'>
                  share stories!!
                </span>
              </h1>
            </div>
            {/* Quote */}
            <div className='flex flex-col justify-center px-6  max-w-4xl mx-6'>
              <h2 className='text-[20px] sm:text-[24px] md:text-[26px] xl:text-[32px] text-left font-medium font-robotoSerif text-[#1A1A1A] leading-12 tracking-tight'>
                Connect with fellow book lovers. Trade the
                <br className='hidden sm:block'/>
                <span className='sm:inline block mt-2 sm:mt-0'> books you've read for new adventures</span>
                <br className='hidden sm:block'/>
                <span className='sm:inline block mt-2 sm:mt-0'>waiting to be discovered.</span>
              </h2>
            </div>

            {/* CTA Button */}
          <div className='flex justify-center items-center px-6'>
          <button className='group bg-[#1A1A1A] text-white h-[60px] 
                            px-8 py-4 rounded-[45px] text-[26px]
                            font-roboto font-semibold flex items-center mr-28
                            gap-3  active:scale-95  transition-all duration-300  '
                            id='shine-button'
                            onClick={() => setTimeout(()=>navigate('/users/new'),300)}
                            >
                <span className='group-hover:-translate-x-0.5  transition-transform duration-300 '>Get Started</span> 
                <ArrowForwardIosIcon className='text-[26px] group-hover:translate-x-2 group-hover:scale-115 transition-all duration-300'/>
          </button>
          </div>
          {/* badges */}
          <div className='flex flex-row justify-evenly mx-6 mt-2 font-roboto '>
            <div className='text-center'>
              <p className='font-bold text-3xl md:text-4xl text-[#1A1A1A] mb-2'>
                2.5K+
              </p>
              <p className='text-sm md:text-base text-gray-600'>
                Active Members
              </p>
            </div>
            
            <div className='text-center'>
              <p className='font-bold text-3xl md:text-4xl text-[#1A1A1A] mb-2'>
                15K+
              </p>
              <p className='text-sm md:text-base text-gray-600'>
                Books Traded
              </p>
            </div>
            
            <div className='text-center mr-32'>
              <p className='font-bold text-3xl md:text-4xl text-[#1A1A1A] mb-2'>
                98%
              </p>
              <p className='text-sm md:text-base text-gray-600'>
                Happy Traders
              </p>
            </div>
          </div>
        </div>  
        {/* Right Side */}
        <div className=' flex-1 flex justify-center items-center h-[100%]'>


            <Cat/>
          

        </div>

      </main>

</div>

   
   
   
  
  );
}