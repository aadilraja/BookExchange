import React, { useState } from 'react';
import Illustration from  "../assets/illustration.png"



const Hero=()=>
{
    
  return (
   <div class="relative min-h-screen w-full overflow-hidden bg-[#252523]">
      <div class="absolute top-0 right-0 h-[150vh] w-1/3 origin-top-right -mr-48 rotate-[25deg] bg-[#F5C815] translate-26">
      <img src={Illustration} alt='illustration' className="w-full h-full object-contain -rotate-24 " />
      </div>
</div>
    

  );
}

export default Hero;