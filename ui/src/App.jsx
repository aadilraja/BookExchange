import { useState } from 'react'
import './App.css'
import { Routes, Route } from 'react-router-dom';
import Health from './pages/Health'
import AddBook from './pages/AddBook'
import GenreMenu from './Component/GenreMenu';

const App = () => {
   return (
      <>
         <Routes>
            <Route path='/health' element={<Health/>} />
            <Route path='/Books/new' element={<AddBook />}/>
            

         </Routes>
      </>
   );
};


export default App
