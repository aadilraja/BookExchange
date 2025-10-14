import { FormatListBulleted } from "@mui/icons-material";
import { useEffect } from "react";
export default function Cat()
{

    function moveEyes(e)
    {
      const eyes = document.querySelectorAll(".eye");
      const mouseX = e.clientX;
      const mouseY = e.clientY;

      eyes.forEach((eye) => {
        const originalCx =
          parseFloat(eye.getAttribute("data-original-cx")) ||
          parseFloat(eye.getAttribute("cx"));
        const originalCy =
          parseFloat(eye.getAttribute("data-original-cy")) ||
          parseFloat(eye.getAttribute("cy"));

        // Store original positions once
        if (!eye.getAttribute("data-original-cx")) {
          eye.setAttribute("data-original-cx", originalCx);
          eye.setAttribute("data-original-cy", originalCy);
        }

        const svg = eye.closest("svg");
        if (!svg) return;

        const pt = svg.createSVGPoint();
        pt.x = mouseX;
        pt.y = mouseY;
        const svgP = pt.matrixTransform(svg.getScreenCTM().inverse());

        const deltaX = svgP.x - originalCx;
        const deltaY = svgP.y - originalCy;

        const angle = Math.atan2(deltaY, deltaX);

        const maxDistance = 10;
        const actualDistance = Math.sqrt(deltaX ** 2 + deltaY ** 2);
        const distance = Math.min(maxDistance, actualDistance / 10);

        const pupilX = originalCx + Math.cos(angle) * distance;
        const pupilY = originalCy + Math.sin(angle) * distance;

        eye.setAttribute("cx", pupilX);
        eye.setAttribute("cy", pupilY);
      });

    }


    useEffect(()=>{
            document.body.addEventListener("mousemove", moveEyes);

            return () => {
            document.body.removeEventListener("mousemove", moveEyes);
            };
  }, []);
   
    

    return(
        <>
        <svg width="500" height="500" viewBox="0 0 500 500">
            <defs>
                <clipPath id="cutEar">
                <rect x="0" y="0" width="500" height="500"/>
                <ellipse cx="250" cy="250" rx="105" ry="83.75"/>
                </clipPath>
            </defs>

           {/* Ears */}
            <ellipse cx="244" cy="118.5" rx="96" ry="37.5" 
                    transform="rotate(90 170 125)" 
                    fill="black" 
                    clip-path="url(#cutEar)"/>
            <ellipse cx="360.8" cy="92" rx="96" ry="37.5" 
                    transform="rotate(90 290 125)" 
                    fill="black" 
                    clip-path="url(#cutEar)"/>
            
            {/* <!-- Left whiskers --> */}
            <line x1="150" y1="240" x2="115" y2="236" stroke="black" stroke-width="2"/>
            <line x1="150" y1="255" x2="120" y2="255" stroke="black" stroke-width="2"/>
            <line x1="150" y1="270" x2="125" y2="276" stroke="black" stroke-width="2"/>
            
            {/* <!-- Right whiskers --> */}
            <line x1="350" y1="240" x2="386" y2="236" stroke="black" stroke-width="2"/>
            <line x1="350" y1="255" x2="377" y2="255" stroke="black" stroke-width="2"/>
            <line x1="325" y1="270" x2="368" y2="276" stroke="black" stroke-width="2"/>
            
            {/* <!-- Body with rounded bottom --> */}
            <path d="M 200,310 
                    L 180,460 
                    Q 180,480 200,480 
                    L 300,480 
                    Q 320,480 320,460 
                    L 305,310 
                    Z" 
                    fill="black"/>
            
            {/* <!-- Tail --> */}
            <path d="M 305 430 Q 380 460, 420 350" 
                    stroke="black" 
                    stroke-width="25" 
                    fill="none" 
                    stroke-linecap="round"/>
            
            {/* <!-- Face --> */}
            <ellipse cx="250" cy="250" rx="100" ry="78.75" fill="black" stroke="black"/>
            
            {/* <!-- Eyes (orange outer circles) --> */}
            <circle cx="202.5" cy="223.5" r="25" fill="orange"/>
            <circle cx="298.5" cy="223.5" r="25" fill="orange"/>
            
            {/* <!-- Pupils (black circles that will move) --> */}
            <circle class="eye" cx="202.5" cy="223.5" r="15" fill="black"/>
            <circle class="eye" cx="298.5" cy="223.5" r="15" fill="black"/>
            
            {/* <!-- Nose --> */}
            <ellipse cx="250" cy="258" rx="14" ry="10" fill="pink"/>
        </svg> 
        </>

    );

}