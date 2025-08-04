import { useState, useCallback, useRef } from 'react';
import axios from 'axios';
import CategoryMenu from '../Component/CategoryMenu';
import GenreMenu from '../Component/GenreMenu';
import { Upload } from 'lucide-react';

const AddBook = () => {
    const fileInputRef = useRef(null);
    const [selectedFile, setSelectedFile] = useState(null);
    const [book, setBook] = useState({
        id: 0,
        title: "",
        author: "",
        category: "",
        genres: []
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setBook(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleCategoryChange = useCallback((selectedCategory) => {
        setBook(prev => ({
            ...prev,
            category: selectedCategory
        }));
    }, []); 

    // Handle genres selection, memoized with useCallback
    const handleGenresChange = useCallback((selectedGenres) => {
        setBook(prev => ({
            ...prev,
            genres: selectedGenres
        }));
    }, []); // Empty dependency array means this function is created only once

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();
        
        try {
            // Added http:// to the URL to make it a valid request endpoint
            const response = await axios.post('http://localhost:8080/api/books', book);
            console.log('Book added successfully:', response.data);
            
            // Reset form state
            setBook({
                id: 0,
                title: "",
                author: "",
                category: "",
                genres: []
            });
            
            // It's generally better to use a more robust notification system than alert()
            // but keeping it for consistency with the original code.
            alert('Book added successfully!');
        } catch (error) {
            console.error('Error adding book:', error);
            alert('Error adding book. Please try again.');
        }
    };

    const handleBrowseFiles = () => {
        fileInputRef.current?.click();
    };

    const handleFileChange = (e) => {
        const file = e.target.files?.[0];
        if (file) {
            setSelectedFile(file);
        }
    };

    return (
      <div className="min-h-screen bg-gray-50 flex justify-center items-center p-4">
        <div className="w-full max-w-5xl">
            <div className="bg-white rounded-xl shadow-[0_0_25px_rgba(0,0,0,0.1)] flex flex-col lg:flex-row ">




                    {/* Image Upload Section */}
                    <div className="lg:col-span-1 p-6 bg-gray-50 border-b lg:border-b-0 lg:border-r border-gray-200 flex-1">
                        <h3 className="text-lg font-semibold text-gray-800 mb-4">Book Cover</h3>
                        
                        {/* Upload Area */}
                            <div 
                                onClick={handleBrowseFiles}
                                className="flex flex-col justify-center items-center border-2 border-dashed
                                border-gray-300 rounded-lg p-8 h-[330px] text-center hover:border-blue-400 hover:bg-blue-50 
                                transition-colors cursor-pointer mb-6">
                                <Upload className="h-12 w-12 text-gray-400 mb-3" />
                                <input 
                                    className='hidden' 
                                    type='file' 
                                    ref={fileInputRef}
                                    onChange={handleFileChange}
                                    id='file' 
                                    accept="image/jpeg, image/png" 
                                />
                                <p className="text-sm text-gray-600 mb-2">
                                    {selectedFile ? selectedFile.name : "Drop image here or"}
                                </p>
                                <p className="text-sm text-blue-600 font-medium">
                                    {selectedFile ? "Change file" : "click to browse"}
                                </p>
                            </div>
                        
                        {/* URL Input */}
                        <div className="space-y-3">
                            <label htmlFor="link-input" className="block text-sm font-medium text-gray-700">
                                Enter Link URL
                            </label>
                            <div className="flex gap-2">
                                <input
                                    id="link-input"
                                    type="url"
                                    placeholder="https://example.com"
                                    className="flex-1 px-3 py-2 border border-gray-300 rounded-md text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                    
                                />
                                <button
                                    className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 transition-colors text-sm font-medium"
                                >
                                    Add
                                </button>
                            </div>
                        </div>
                    </div>

                    {/* Form Section */}
                    <div className="lg:col-span-2 p-6 flex-1">
                        <h2 className="text-2xl font-bold text-gray-800 mb-6 text-center">Add New Book</h2>
                        
                        <form onSubmit={handleSubmit} className="space-y-4">
                            <div>
                                <label htmlFor="title" className="block text-sm font-medium text-gray-700 mb-2">
                                    Title
                                </label>
                                <input 
                                    className="block w-full px-3 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-colors" 
                                    type="text" 
                                    id="title" 
                                    name="title"
                                    value={book.title}
                                    onChange={handleInputChange}
                                    required 
                                />                  
                            </div>

                            <div>
                                <label htmlFor="author" className="block text-sm font-medium text-gray-700 mb-2">
                                    Author
                                </label>
                                <input  
                                    className="block w-full px-3 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-colors"  
                                    type="text" 
                                    id="author" 
                                    name="author"
                                    value={book.author}
                                    onChange={handleInputChange}
                                    required 
                                />
                            </div>

                            <div>
                                <label className="block text-sm font-medium text-gray-700 mb-2">
                                    Category
                                </label>
                                <CategoryMenu 
                                    selectedCategory={book.category}
                                    onCategoryChange={handleCategoryChange}
                                />
                            </div>

                            <div>
                                <label className="block text-sm font-medium text-gray-700 mb-2">
                                    Genres
                                </label>
                                <GenreMenu 
                                    selectedGenres={book.genres}
                                    onGenresChange={handleGenresChange}
                                />
                            </div>

                            <div className="pt-4">
                                <button
                                    type="submit"
                                    className="w-full bg-blue-600 text-white py-2 px-4 rounded-lg hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-colors font-medium"
                                >
                                    Add Book
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
             </div>
        </div>

       
    );
};

export default AddBook;
