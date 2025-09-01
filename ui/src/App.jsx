import './Styles/App.css'
import { Routes, Route } from 'react-router-dom';
import Health from './pages/Health'
import AddBook from './pages/AddBook'
import RegisterationForm from './pages/RegisterationForm';
import ConfirmRegisteration from './pages/ConfirmRegisteration'
import Login from './pages/Login';
const App = () => {
   return (
      <>
         <Routes>
            <Route path='/health' element={<Health/>} />
            <Route path='/books/new' element={<AddBook />}/>
            <Route path='/user/new' element={<RegisterationForm/>}/>
            <Route path='/registration-confirm/:token' element={<ConfirmRegisteration/>}/>
            <Route path='/user/login' element={<Login/>}/>
         </Routes>
      </>
   );
};


export default App
