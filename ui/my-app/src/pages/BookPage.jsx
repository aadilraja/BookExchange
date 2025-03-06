import { useParams } from 'react-router-dom';
import React, { useState, useEffect } from "react";
import '../styles/BookPage.css';
import axios from 'axios';

const Book = () => {
    const { book_id } = useParams();
    const [coverImageUrl, setCoverImageUrl] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (book_id) {
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
        }
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
        </div>
    );
};

export default Book;
