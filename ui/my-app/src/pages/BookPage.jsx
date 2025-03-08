import { useParams } from 'react-router-dom';
import React, { useState, useEffect } from "react";
import '../styles/BookPage.css';
import axios from 'axios';

const Book = () => {
    const { book_id } = useParams();
    const [book,setBook]=useState
        ({
            title:'',
            author:'',
            type:'',
            genres:[],
        })
    const [coverImageUrl, setCoverImageUrl] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!book_id) {
            console.log("no book id given")
        }
        axios.get(`http://localhost:8080/api/book/img?bookId=${book_id}`,
            { responseType: "blob" }
            ).then((response) => {
                setCoverImageUrl(URL.createObjectURL(response.data));
                console.log(coverImageUrl)
            })
            .catch((error) => {
                console.error("Error loading image:", error);
                setError("Failed to load image");
            });
        axios.get(`http://localhost:8080/api/book?bookId=${book_id}`,
            {responseType:"json"}
        ).then(
            (response)=>
            {
                setBook(response.data)
                console.log(response.data)
            }
        ).catch((error)=>
        {
            console.error("Error loading image:", error);
        })
    }, [book_id]);

    return (
        <div className='BookView'>
            <div className='CoverImage'>
                {error ? (
                    <div className="error-message">{error}</div>
                ) : coverImageUrl ? (
                    <img alt="Book Cover" src={coverImageUrl} />
                ) : (
                    <div className="loading-placeholder">Loading...</div>
                )}
            </div>
            <div className='BookDetails'>
                <h2 className='bookTitle'>
                    {book.title}
                </h2>
                <p>
                    {book.type}
                </p>
                <div className="genreSection">
                    {book.genres.map((genre, index) => (
                        <p key={index}>{genre}</p>
                    ))}
                </div>


            </div>
        </div>
    );
};

export default Book;
