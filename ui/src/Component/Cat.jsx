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
        <g id="books" >
            <g className="book">
            {/* Book spine (left side) */}
            <polygon 
              points="105,454 105,414 245,459 245,500" 
              fill="#8B4513" 
              stroke="#333" 
              strokeWidth="1.5" 
            />

            {/* Book top */}
            <polygon 
              points="105,414 180,389 320,436 245,459" 
              fill="#D2691E" 
              stroke="#333" 
              strokeWidth="1.5" 
            />

            {/* Book pages (right side) with page lines */}
            <polygon 
              points="245,459 320,436 320,476 245,500" 
              fill="#FFFEF0" 
              stroke="#333" 
              strokeWidth="1.5" 
            />
            
        <line x1="310" y1="446" x2="255" y2="465" stroke="black" strokeWidth="1.5"/>
        <line x1="320" y1="455" x2="255" y2="475" stroke="black" strokeWidth="1.5"/>
        <line x1="305" y1="467" x2="270" y2="480" stroke="black" strokeWidth="1.5"/>
        <line x1="315" y1="470" x2="260" y2="490" stroke="black" strokeWidth="1.5"/>


            
          </g>
          <g className="book-top">
              {/* Book spine (front facing) */}
              <polygon 
                points="130,380 130,360 205,335 205,355" 
                fill="#5A7D5A" 
                stroke="#333" 
                strokeWidth="1.5" 
              />
              
              {/* Book top */}
              <polygon 
                points="130,360 205,335 280,360 205,385" 
                fill="#7BA67B" 
                stroke="#333" 
                strokeWidth="1.5" 
              />

            {/* Book pages (right side) */}
            <polygon 
              points="205,355 280,360 280,380 205,385" 
              fill="#FFFEF0" 
              stroke="#333" 
              strokeWidth="1.5" 
            />
            
            {/* Page lines on the right edge */}
            <line x1="280" y1="362" x2="210" y2="357" stroke="black" strokeWidth="1.5"/>
            <line x1="280" y1="365" x2="220" y2="361" stroke="black" strokeWidth="1.5"/>
            <line x1="280" y1="368" x2="215" y2="364" stroke="black" strokeWidth="1.5"/>
            <line x1="280" y1="372" x2="225" y2="368" stroke="black" strokeWidth="1.5"/>
            <line x1="280" y1="376" x2="218" y2="372" stroke="black" strokeWidth="1.5"/>
          </g>

        </g>

      </svg>
    </>
  );
}
