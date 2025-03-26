import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import './App.css'
import Home  from './pages/HomePage'
import AddOrRequest from './pages/AddOrRequestPage'
import Book from './pages/BookPage'
import Navbar from './component/navbar'

function App() {

  return (
    <Router>
        <div>
        <Navbar/>
            <Routes>
              <Route index element={<Home />} />
              <Route path="/book/AddOrRequest" element={<AddOrRequest />} />
              <Route path="/book/:book_id" element={<Book />} />

            </Routes>

      </div>

    </Router>
     
  );

  
}

export default App
