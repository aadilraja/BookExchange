import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import './App.css'
import Home  from './pages/HomePage'
import AddBook from './pages/AddBookPage'
import Book from './pages/BookPage'
import Navbar from './component/navbar'

function App() {

  return (
    <Router>
        <div>
        <Navbar/>
            <Routes>
              <Route index element={<Home />} />
              <Route path="/AddBook" element={<AddBook />} />
              <Route path="/Book/:book_id" element={<Book />} />

            </Routes>

      </div>

    </Router>
     
  );

  
}

export default App
