import { useState } from 'react'
import './App.css'
import { Routes, Route } from 'react-router-dom';
import Health from './Component/Health'


const App = () => {
   return (
      <>
         <Routes>
            <Route path='/health'element={<Health/>} />
         </Routes>
      </>
   );
};


export default App
