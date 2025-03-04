import React from 'react';
import { useState } from 'react';
import axios from "axios";
import '../styles/AddBookPage.css';

const AddBook = () => {
   const[Book,setBook]=useState({
        title:'',
        author:'',
        type:'',
   })
    const handleChangeInput=(e)=>
   {
        const {name,value}=e.target;
        setBook({...Book,[name]:value})

   }

   const handleSubmit=async(e)=>{
         e.preventDefault();

         const formData= new FormData();
         formData.append("Book",
            new Blob([JSON.stringify(Book)],{type:"application/json"})
         );
         await axios.post("http://localhost:8080/api/Book",formData)
                    .then((response) => {
                        console.log("Product added successfully:", response.data);
                        alert("Product added successfully");
                    })
                    .catch((error) => {
                        console.error("Error adding product:", error);
                        alert("Error adding product");
                    });
    }


    
    return (
        <div className='add-book-page'>
            <h1>Add The Book</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <input type="text" placeholder="Book Title" onChange={handleChangeInput} value={Book.title} name='title'/>
                </div>
                <div>
                    <input type="text" placeholder="Author Name" onChange={handleChangeInput} value={Book.author} name='author' />
                </div>
                <div>
                    <select name="type" id="Type" value={Book.type} onChange={handleChangeInput}>
                        <option value="DEFAULT" disabled>Choose a Book Type</option>
                            <option value="Historical">Historical</option>
                            <option value="Picaresque">Picaresque</option>
                            <option value="Gothic" >Gothic</option>
                            <option value="Romantic" >Romantic</option>
                            <option value="Detective" >Detective</option>
                            <option value="Science Fiction" >Science Fiction</option>
                            <option value="Fantasy" >Fantasy</option>
                            <option value="Western" >Western</option>
                            <option value="Epic" >Epic</option>
                            <option value="Satire" >Satire</option>   
                    </select>
                    
                </div>
                <div>
                    <p>Genre</p>
                    <select multiple data-placeholder="Add Genre">
                        <option>Sketch</option>
                        <option selected>Framer X</option>
                        <option>Photoshop</option>
                        <option>Principle</option>
                        <option>Invision</option>
                    </select>
                    <a class="dribbble" href="https://dribbble.com/shots/5112850-Multiple-select-animation-field" target="_blank">
                    <img src="https://cdn.dribbble.com/assets/dribbble-ball-1col-dnld-e29e0436f93d2f9c430fde5f3da66f94493fdca66351408ab0f96e2ec518ab17.png" alt="" />
                    </a>
                    
                </div>

                <div>
                   <input type="submit" value="Add Book" />
                </div>
                
            </form>
        </div>
    );
};

export default AddBook;

