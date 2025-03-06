import React from 'react';
import { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Select from 'react-select';
import '../styles/AddBookPage.css';

const AddBook = () => {
    const navigate= useNavigate()

    const options = [
        { value: "action", label: "Action" },
        { value: "adventure", label: "Adventure" },
        { value: "crimeFiction", label: "Crime Fiction" },
        { value: "drama", label: "Drama" },
        { value: "dystopian", label: "Dystopian" },
        { value: "fantasy", label: "Fantasy" },
        { value: "historicalFiction", label: "Historical Fiction" },
        { value: "horror", label: "Horror" },
        { value: "literaryFiction", label: "Literary Fiction" },
        { value: "mystery", label: "Mystery" },
        { value: "romance", label: "Romance" },
        { value: "scienceFiction", label: "Science Fiction" },
        { value: "suspense", label: "Suspense" },
        { value: "thriller", label: "Thriller" },
        { value: "autobiography", label: "Autobiography/Biography" },
        { value: "business", label: "Business and Economics" },
        { value: "cookbooks", label: "Cookbooks" },
        { value: "history", label: "History" },
        { value: "memoir", label: "Memoir" },
        { value: "philosophy", label: "Philosophy" },
        { value: "poetry", label: "Poetry" },
        { value: "politics", label: "Politics" },
        { value: "religion", label: "Religion & Spirituality" },
        { value: "selfHelp", label: "Self-Help" },
        { value: "science", label: "Science & Math" },
        { value: "trueCrime", label: "True Crime" }
    ]

   const [selectedImage, setSelectedImage] = useState(null);
   const[Book,setBook]=useState({
        title:'',
        author:'',
        type:'',
        genres:[],
   })


   const handleImage=(event)=>
   {
    try{
        let file=event.target.files[0]   

            if (file)
            {
                setSelectedImage(file);
                console.log(file)
            }
            else
            {
                alert('failed to load the image try again')
            }
    }catch(exception)
    {
        console.log(exception)
        alert('an error occured')
    }
   }
   const handleGenreChange=(SGenres)=>
   {
        let selectedGenres= SGenres? SGenres.map(genre=>genre.value):[];

        setBook(prevBook=>
        ({
            ...prevBook,
            genres: selectedGenres

        }))
        console.log(selectedGenres)

   }
    const handleChangeInput=(e)=>
   {
        const {name,value}=e.target;
        setBook(prevBook=>(
            {
                ...prevBook,
                [name]:value
            }
        ))

   }

   const handleSubmit = async (e) => {
            e.preventDefault();

            console.log(Book)
            const formData = new FormData();
            formData.append("coverImg", selectedImage);
            formData.append("bookdto", 
                new Blob([JSON.stringify(Book)], {type: "application/json"})
            );
            

            try {
                const response = await axios.post("http://localhost:8080/api/book", formData, {
                    headers: {
                        "Content-Type": "multipart/form-data",
                    },
                });


                navigate("/Book/${Book.id}")
                console.log("Product added successfully:", response.data);
                alert("Product added successfully");
            } catch (error) {
                console.error("Error adding Book:", error);
                
                // More detailed error handling
                if (error.response) {
                    
                    alert(`Error: ${error.response.data}`);
                } else if (error.request) {
                   
                    alert("No response received from server");
                } else {
                    alert("Error in request setup");
                }
            }
}

    
    return (
       
         <div className='add-book-page'>
            <div className='ImageSelectionSection'>
                <div className='ImageViewCard'>
                    {selectedImage && (
                        
                        <img
                                alt="Selected"
                                src={URL.createObjectURL(selectedImage)}
                            />
                        
                    )}

                </div>
               

                
                <input
                    type="file"
                    name="myImage"
                    onChange={handleImage}
                />
                

            </div>
            <div className='bookDetailSection'>
            <form onSubmit={handleSubmit}>
                <div className='Form-detail'>
                    <input type="text" placeholder="Book Title" onChange={handleChangeInput} value={Book.title} name='title'/>
                </div>
                <div className='Form-detail'>
                    <input type="text" placeholder="Author Name" onChange={handleChangeInput} value={Book.author} name='author' />
                </div>
                <div className='Form-detail'>
                    <select name="type" id="Type" value={Book.type} onChange={handleChangeInput} placeholder="Type">
                        <option value="" disabled selected>Select book type</option>
                        <option value="Novel">Novel</option>
                        <option value="Comic Book">Comic Book</option>
                        <option value="Graphic Novel">Graphic Novel</option>
                        <option value="Educational">Educational</option>
                        <option value="Short Story">Short Story</option>
                    </select>
                    <Select
                        isMulti
                        name="genres"
                        options={options}
                        className="multi-select"
                        classNamePrefix="select"
                        placeholder="Genre"
                        onChange={handleGenreChange} 
                    />
                    
                </div>
                

                <div>
                   <input type="submit" value="Add Book" />
                </div>
                
            </form>
            </div>
        </div>
        
       
    );
};

export default AddBook;

