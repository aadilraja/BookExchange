//todo:add coffee mug, open book on top

import { useEffect, useState, useRef } from "react";

export default function Cat() {
  const [isMouseIdle, setMouseIdle] = useState(false);
  const idleTimerRef = useRef(null);
  
  function centerEyes(eyes) {
  eyes.forEach((eye) => {
    const originalCx = parseFloat(eye.getAttribute("data-original-cx")) || parseFloat(eye.getAttribute("cx"));
    const originalCy = parseFloat(eye.getAttribute("data-original-cy")) || parseFloat(eye.getAttribute("cy"));
    eye.setAttribute("cx", originalCx);
    eye.setAttribute("cy", originalCy);
  });
}

function resetIdleTimer(eyes) {
  clearTimeout(idleTimerRef.current);
  idleTimerRef.current = setTimeout(() => {
    console.log("🛑 Mouse is idle");
    centerEyes(eyes); // return all eyes to center
    setMouseIdle(true);
  }, 3000);
}


  function moveEyes(e) {
    if (isMouseIdle) {
      console.log("🟢 Mouse became active again");
    }
    setMouseIdle(false);

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

      const maxDistance = 7;
      const actualDistance = Math.sqrt(deltaX ** 2 + deltaY ** 2);
      const distance = Math.min(maxDistance, actualDistance / 10);

      const pupilX = originalCx + Math.cos(angle) * distance;
      const pupilY = originalCy + Math.sin(angle) * distance;

      eye.setAttribute("cx", pupilX);
      eye.setAttribute("cy", pupilY);
    });
    resetIdleTimer(eyes);

  }

  useEffect(() => {
    document.body.addEventListener("mousemove", moveEyes);
    resetIdleTimer();

    return () => {
      document.body.removeEventListener("mousemove", moveEyes);
      clearTimeout(idleTimerRef.current);
    };
  }, []);

  return (
    <>
    
    
      <svg width="500" height="500" viewBox="0 0 500 500">
        <g id="cat">
        <defs>
          <clipPath id="cutEar">
            <rect x="0" y="0" width="500" height="500" />
            <ellipse cx="250" cy="250" rx="105" ry="83.75" />
          </clipPath>
        </defs>

        {/* Ears */}
        <ellipse
          cx="244"
          cy="118.5"
          rx="96"
          ry="37.5"
          transform="rotate(90 170 125)"
          fill="black"
          clipPath="url(#cutEar)"
        />
        <ellipse
          cx="360.8"
          cy="92"
          rx="96"
          ry="37.5"
          transform="rotate(90 290 125)"
          fill="black"
          clipPath="url(#cutEar)"
        />

        {/* Left whiskers */}
        <line x1="150" y1="240" x2="115" y2="236" stroke="black" strokeWidth="2" />
        <line x1="150" y1="255" x2="120" y2="255" stroke="black" strokeWidth="2" />
        <line x1="150" y1="270" x2="125" y2="276" stroke="black" strokeWidth="2" />

        {/* Right whiskers */}
        <line x1="350" y1="240" x2="386" y2="236" stroke="black" strokeWidth="2" />
        <line x1="350" y1="255" x2="377" y2="255" stroke="black" strokeWidth="2" />
        <line x1="325" y1="270" x2="368" y2="276" stroke="black" strokeWidth="2" />

        {/* Body with rounded bottom */}
        <path
          d="M 200,310 L 175,465 Q 180,480 200,480 L 305,480 Q 320,480 320,460 L 305,310 Z"
          fill="black"
        />

        {/* Tail */}
        <path
          d="M 305 430 Q 380 460, 420 350"
          stroke="black"
          strokeWidth="25"
          fill="none"
          strokeLinecap="round"
        />

        {/* Face */}
        <ellipse cx="250" cy="250" rx="100" ry="78.75" fill="black" stroke="black" />

        
        {/* Eyes (orange outer circles) */}
        <circle  cx="202.5" cy="223.5" r="25" fill="orange" />
        <circle  cx="298.5" cy="223.5" r="25" fill="orange" />

        {/* Pupils (black circles that will move) */}
        <circle className="eye" cx="202.5" cy="223.5" r="15" fill="black" />
        <circle className="eye" cx="298.5" cy="223.5" r="15" fill="black" />

        {/* Nose */}
        <ellipse cx="250" cy="258" rx="14" ry="10" fill="#BB897D" />

        </g>
  
        <g className="book-1-bottom">
          <polygon 
            points="105,464 105,444 245,464 245,488" 
            fill="#F06292" 
            stroke="#333" 
            strokeWidth="1.5"
          />
          <polygon 
            points="105,444 180,434 325,457 245,464" 
            fill="#E91E63" 
            stroke="#333" 
            strokeWidth="1.5"
          />
          <polygon 
            points="245,464 325,457 325,478 245,488" 
            fill="#FFFEF0" 
            stroke="#333" 
            strokeWidth="1.5"
          />
          <line x1="310" y1="462" x2="255" y2="468" stroke="#BFBFBF" strokeWidth="1"/>
          <line x1="315" y1="465" x2="255" y2="475" stroke="#BFBFBF" strokeWidth="1"/>
          <line x1="318" y1="470" x2="255" y2="480" stroke="#BFBFBF" strokeWidth="1"/>
          
          <polygon 
            points="120,462 120,452 230,470 230,480" 
            fill="#F8BBD0" 
            stroke="#333" 
            strokeWidth="1"
          />
        </g>

        <g className="book-2-middle" transform="translate(-10, -24)">
          <polygon 
            points="105,464 105,444 245,464 245,488" 
            fill="#FFEB3B" 
            stroke="#333" 
            strokeWidth="1.5"
          />
          <polygon 
            points="105,444 180,434 325,457 245,464" 
            fill="#FDD835" 
            stroke="#333" 
            strokeWidth="1.5"
          />
          <polygon 
            points="245,464 325,457 325,478 245,488" 
            fill="#FFFEF0" 
            stroke="#333" 
            strokeWidth="1.5"
          />
          <line x1="310" y1="460" x2="255" y2="469" stroke="#BFBFBF" strokeWidth="1"/>
          <line x1="315" y1="465" x2="255" y2="475" stroke="#BFBFBF" strokeWidth="1"/>
          <line x1="318" y1="470" x2="255" y2="480" stroke="#BFBFBF" strokeWidth="1"/>
           <polygon 
            points="120,462 120,452 230,470 230,480" 
            fill="#eada67" 
            stroke="#333" 
            strokeWidth="1"
          />
        </g>

        <g className="book-3-top" transform="translate(5, -48)">
          <polygon 
            points="105,464 105,444 245,464 245,488" 
            fill="#64B5F6" 
            stroke="#333" 
            strokeWidth="1.5"
          />
          <polygon 
            points="105,444 180,434 325,457 245,464" 
            fill="#42A5F5" 
            stroke="#333" 
            strokeWidth="1.5"
          />
          <polygon 
            points="245,464 325,457 325,478 245,488" 
            fill="#FFFEF0" 
            stroke="#333" 
            strokeWidth="1.5"
          />
          <line x1="310" y1="460" x2="255" y2="469" stroke="#BFBFBF" strokeWidth="1"/>
          <line x1="315" y1="465" x2="255" y2="475" stroke="#BFBFBF" strokeWidth="1"/>
          <line x1="318" y1="470" x2="255" y2="480" stroke="#BFBFBF" strokeWidth="1"/>
          <polygon 
            points="120,462 120,452 230,470 230,480" 
            fill="#5b9fd6" 
            stroke="#333" 
            strokeWidth="1"
          />
          
          
        </g>
        <g>
          <path d="M 340 450 L 400 450" stroke="#BB897D" strokeWidth="4"  />
        </g>

</svg>
         

    </>
  );
}
