import React, { useState, useEffect } from 'react';
import { ArrowRight, BookOpen, Users, Sparkles } from 'lucide-react';

export default function Hero() {
  const [email, setEmail] = useState('');
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    setIsVisible(true);
  }, []);

  const handleSubmit = () => {
    console.log('Email submitted:', email);
    setEmail('');
  };

  return (
  <div className="min-h-screen w-screen">
  <nav className="bg-amber-500 h-16 flex items-center px-6">
    {/* Logo Section */}
    <div className="flex items-center space-x-3">
      {/* Logo img */}
      <div className="bg-black h-8 w-8 rounded"></div>

      {/* Text */}
      <p className="text-white font-semibold text-lg">Book Exchange</p>
    </div>
  </nav>
</div>

   
   
   
  
  );
}