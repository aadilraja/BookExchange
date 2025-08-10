import { useState, useCallback, useRef, useEffect } from 'react';
import axios from 'axios';
import CategoryMenu from '../Component/CategoryMenu';
import GenreMenu from '../Component/GenreMenu';
import { Upload, X } from 'lucide-react';

const AddBook = () => {
    const fileInputRef = useRef(null);

    const UploadStatus = {
        IDLE: 'idle',
        UPLOADING: 'uploading',
        SUCCESS: 'success',
        ERROR: 'error',
    };

    const [status, setStatus] = useState(UploadStatus.IDLE);
    const [selectedFile, setSelectedFile] = useState(null);
    const [imagePreview, setImagePreview] = useState(null);
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

    const handleGenresChange = useCallback((selectedGenres) => {
        setBook(prev => ({
            ...prev,
            genres: selectedGenres
        }));
    }, []); 

    function CreateForm(book,img)
    {
        const formData = new FormData();
        formData.append("bookDTO", new Blob([JSON.stringify(book)], { type: "application/json" }));
        formData.append("coverImg",img);
        return formData;
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
       let formData=CreateForm(book,selectedFile);
        
        try {
            const response = await axios.post('http://localhost:8080/api/books', formData,
            {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
            }
            );
            console.log('Book added successfully:', response.data);
            
            setBook({
                id: 0,
                title: "",
                author: "",
                category: "",
                genres: []
            });
            
            // Reset file upload
            setSelectedFile(null);
            setImagePreview(null);
            setStatus(UploadStatus.IDLE);
            
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
        if (e.target.files && e.target.files[0]) {
            const file = e.target.files[0];
            setSelectedFile(file);
            setStatus(UploadStatus.SUCCESS);
            
            // Create preview URL
            const reader = new FileReader();
            reader.onload = (event) => {
                setImagePreview(event.target.result);
            };
            reader.readAsDataURL(file); // Use 'file' directly, not selectedFile
        }
    };

    const handleRemoveImage = (e) => {
        e.stopPropagation(); // Prevent triggering the file browser
        setSelectedFile(null);
        setImagePreview(null);
        setStatus(UploadStatus.IDLE);
        if (fileInputRef.current) {
            fileInputRef.current.value = '';
        }
    };

    // Cleanup preview URL on unmount
    useEffect(() => {
        return () => {
            if (imagePreview) {
                URL.revokeObjectURL(imagePreview);
            }
        };
    }, [imagePreview]);

    return (
        <div className="min-h-screen bg-gray-50 flex justify-center items-center p-4">
            <div className="w-full max-w-5xl">
                <div className="bg-white rounded-xl shadow-[0_0_25px_rgba(0,0,0,0.1)] flex flex-col lg:flex-row">
                    {/* Image Upload Section */}
                    <div className="lg:col-span-1 p-6 bg-gray-50 border-b lg:border-b-0 lg:border-r border-gray-200 flex-1">
                        <h3 className="text-lg font-semibold text-gray-800 mb-4">Book Cover</h3>
                        
                        {/* Upload Area */}
                        <div 
                            onClick={handleBrowseFiles}
                            className="flex flex-col justify-center items-center border-2 border-dashed
                            border-gray-300 rounded-lg p-4 h-[330px] text-center hover:border-blue-400 hover:bg-blue-50 
                            transition-colors cursor-pointer mb-6 relative">
                        
                            {status === UploadStatus.SUCCESS && imagePreview ? (
                                <div className="relative w-full h-full flex items-center justify-center">
                                    <img 
                                        src={imagePreview} 
                                        alt="Book cover preview" 
                                        className="max-w-full max-h-full object-contain rounded-lg"
                                    />
                                    <button
                                        onClick={handleRemoveImage}
                                        className="absolute top-2 right-2 bg-red-500 text-white rounded-full p-1 hover:bg-red-600 transition-colors"
                                        title="Remove image"
                                    >
                                        <X className="h-4 w-4" />
                                    </button>
                                    <div className="absolute bottom-2 left-2 bg-black bg-opacity-50 text-white text-xs px-2 py-1 rounded">
                                        {selectedFile.name}
                                    </div>
                                </div>
                            ) : (
                                <>
                                    <Upload className="h-12 w-12 text-gray-400 mb-3" />
                                    <input 
                                        className='hidden' 
                                        type='file' 
                                        ref={fileInputRef}
                                        onChange={handleFileChange}
                                        id='file' 
                                        accept="image/jpeg, image/png, image/jpg" 
                                    />
                                    <p className="text-sm text-gray-600 mb-2">
                                        Drop image here or
                                    </p>
                                    <p className="text-sm text-blue-600 font-medium">
                                        click to browse
                                    </p>
                                </>
                            )}
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
                                    type="button"
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