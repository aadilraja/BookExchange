import React, { useState, useEffect, useRef } from 'react';
import { ChevronDown } from 'lucide-react';

// In a real app, this would likely be passed as a prop or fetched from an API.
const allCategories = [
  "Fiction",
  "Non-Fiction",
  "Children's",
  "Young Adult",
  "Biography",
  "History",
  "Self-Help",
  "Business",
  "Academic/Reference"
];


/**
 * A searchable dropdown menu component for categories.
 * @param {{
 * selectedCategory: string;
 * onCategoryChange: (category: string) => void;
 * }} props
 */
const CategoryMenu = ({ selectedCategory, onCategoryChange }) => {
    // State to manage if the dropdown is open or closed.
    const [isOpen, setIsOpen] = useState(false);
    // State for the text inside the search input.
    const [searchTerm, setSearchTerm] = useState(selectedCategory || "");

    // Refs to the dropdown and input elements for event handling.
    const dropdownRef = useRef(null);
    const inputRef = useRef(null);

    // Effect to update the input's text if the selectedCategory prop changes from the parent.
    useEffect(() => {
        setSearchTerm(selectedCategory || "");
    }, [selectedCategory]);

    // Effect to handle clicks outside the component to close the dropdown.
    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setIsOpen(false);
                // When closing, revert the search term to the actual selected category.
                setSearchTerm(selectedCategory || "");
            }
        };

        // Add event listener when the component mounts.
        document.addEventListener('mousedown', handleClickOutside);
        // Clean up the event listener when the component unmounts.
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, [selectedCategory]); // Re-run if selectedCategory changes.

    // Filter the category options based on the user's search term.
    const filteredOptions = allCategories.filter(option =>
        option.toLowerCase().includes(searchTerm.toLowerCase())
    );

    // Opens the dropdown and focuses the input field.
    const handleInputClick = () => {
        setIsOpen(true);
        inputRef.current?.focus();
    };

    // Updates the search term as the user types.
    const handleInputChange = (e) => {
        setSearchTerm(e.target.value);
        if (!isOpen) {
            setIsOpen(true);
        }
    };

    // Handles the selection of an option from the dropdown.
    const handleOptionClick = (option) => {
        onCategoryChange(option); // Notify the parent component of the change.
        setSearchTerm(option);     // Update the input text to show the selection.
        setIsOpen(false);          // Close the dropdown.
    };

    // Placeholder text for the input.
    const placeholder = "Select a category...";

    return (
        <div className="relative" ref={dropdownRef}>
            {/* Main input container */}
            <div
                className={`\
                    min-h-12 w-full border-2 rounded-lg px-3 py-2 cursor-text
                    flex items-center
                    ${isOpen ? 'border-blue-500 ring-2 ring-blue-200' : 'border-gray-300 hover:border-gray-400'}
                    transition-all duration-200
                `}
                onClick={handleInputClick}
            >
                {/* Search input */}
                <input
                    ref={inputRef}
                    type="text"
                    value={searchTerm}
                    onChange={handleInputChange}
                    placeholder={placeholder}
                    className="flex-1 outline-none bg-transparent text-gray-700 placeholder-gray-400"
                />

                {/* Dropdown arrow */}
                <ChevronDown
                    size={20}
                    className={`text-gray-400 transition-transform duration-200 ${isOpen ? 'rotate-180' : ''}`}
                />
            </div>

            {/* Dropdown menu */}
            {isOpen && (
                <div className="absolute z-10 w-full mt-1 bg-white border border-gray-300 rounded-lg shadow-lg max-h-60 overflow-y-auto">
                    {filteredOptions.length > 0 ? (
                        filteredOptions.map((option, index) => (
                            <div
                                key={index}
                                onClick={() => handleOptionClick(option)}
                                className={`
                                    px-3 py-2 cursor-pointer border-b border-gray-100 last:border-b-0 transition-colors
                                    ${selectedCategory === option
                                        ? 'bg-blue-50 text-blue-700 font-medium'
                                        : 'text-gray-700 hover:bg-blue-50'
                                    }
                                `}
                            >
                                {option}
                            </div>
                        ))
                    ) : (
                        <div className="px-3 py-2 text-gray-500 italic">
                            No matching options found
                        </div>
                    )}
                </div>
            )}
        </div>
    );
};

export default CategoryMenu;