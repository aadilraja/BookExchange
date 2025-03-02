import React from 'react';

const AddBookPage = () => {
    return (
        <div>
            <h1>Add a New Book</h1>
            <form>
                <div>
                    <label htmlFor="title">Title:</label>
                    <input type="text" id="title" name="title" />
                </div>
                <div>
                    <label htmlFor="author">Author:</label>
                    <input type="text" id="author" name="author" />
                </div>
                <div>
                    <label htmlFor="genre">Genre:</label>
                    <input type="text" id="genre" name="genre" />
                </div>
                <div>
                    <label htmlFor="year">Year:</label>
                    <input type="number" id="year" name="year" />
                </div>
                <button type="submit">Add Book</button>
            </form>
        </div>
    );
};

export default AddBookPage;