import './Styles/App.css'
import { Routes, Route } from 'react-router-dom';
import Health from './pages/Health'
import AddBook from './pages/AddBook'
import Authentication from './pages/Authentication';
import ConfirmRegisteration from './pages/ConfirmRegisteration'
import Hero from './pages/Hero';
import ErrorPage404 from './pages/ErrorPage404';
const App = () => {
   return (
      <>
         <Routes>
            <Route path='/' element={<Hero/>}/>
            <Route path='/healthz' element={<Health/>} />
            <Route path='/books/new' element={<AddBook />}/>
            <Route path='/Auth' element={<Authentication/>}/>
            <Route path='/registration-confirm/:token' element={<ConfirmRegisteration/>}/>
            <Route path='*' element={<ErrorPage404/>}/>
         </Routes>
      </>
   );
};


export default App
