import React, { useState, useRef, useEffect } from 'react';
import { ChevronDown, X } from 'lucide-react';

const categoryGenreMap = {
  "Fiction": [
    "Mystery", "Thriller", "Science Fiction", "Fantasy", "Horror", "Romance",
    "Historical Fiction", "Crime", "Adventure", "Paranormal", "Dystopian",
    "Contemporary", "Literary Fiction", "Humor", "Western", "Drama"
  ],
  "Non-Fiction": [
    "Biography", "Memoir", "History", "Self-Help", "Business",
    "True Crime", "Travel", "Philosophy", "Science/Technology"
  ],
  "Children's": [
    "Adventure", "Fantasy", "Humor", "Contemporary", "Poetry"
  ],
  "Young Adult": [
    "Fantasy", "Romance", "Mystery", "Adventure", "Dystopian",
    "Paranormal", "Contemporary", "Drama"
  ],
  "Biography": [
    "Memoir", "Autobiography", "Historical"
  ],
  "History": [
    "Military History", "Political History", "Cultural History", "Biography (Historical Figures)"
  ],
  "Self-Help": [
    "Motivational", "Psychology", "Health & Wellness", "Productivity"
  ],
  "Business": [
    "Entrepreneurship", "Leadership", "Finance", "Economics"
  ],
  "Academic/Reference": [
    "Textbook", "Dictionary", "Encyclopedia", "Research"
  ]
};

export default function GenreMenu({ selectedGenres = [], onGenresChange, selectedCategory }) {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedItems, setSelectedItems] = useState(selectedGenres);
  const [searchTerm, setSearchTerm] = useState('');
  const dropdownRef = useRef(null);
  const inputRef = useRef(null);

  // Sync with parent component
  useEffect(() => {
    setSelectedItems(selectedGenres);
  }, [selectedGenres]);

  // Notify parent when selection changes
  useEffect(() => {
    if (onGenresChange) {
      onGenresChange(selectedItems);
    }
  }, [selectedItems, onGenresChange]);

  const options = selectedCategory ? categoryGenreMap[selectedCategory] || [] : [];

  const filteredOptions = options.filter(option =>
    option.toLowerCase().includes(searchTerm.toLowerCase()) &&
    !selectedItems.includes(option)
  );

  // Handle clicking outside to close dropdown
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsOpen(false);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  const handleInputClick = () => {
    setIsOpen(true);
    inputRef.current?.focus();
  };

  const handleOptionClick = (option) => {
    setSelectedItems(prev => [...prev, option]);
    setSearchTerm('');
    inputRef.current?.focus();
  };

  const removeSelectedItem = (itemToRemove) => {
    setSelectedItems(prev => prev.filter(item => item !== itemToRemove));
  };

  const handleInputChange = (e) => {
    setSearchTerm(e.target.value);
    if (!isOpen) setIsOpen(true);
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Backspace' && searchTerm === '' && selectedItems.length > 0) {
      removeSelectedItem(selectedItems[selectedItems.length - 1]);
    }
  };

  const getDisplayText = () => {
    if (searchTerm) return searchTerm;
    if (selectedItems.length === 0) return '';
    if (selectedItems.length === 1) return selectedItems[0];
    return `${selectedItems.length} genres selected`;
  };

  const getPlaceholder = () => {
    if (!selectedCategory) return "Select a category first...";
    if (selectedItems.length === 0) return "Search and Select Genres...";
    return "";
  };

  return (
    <div className="w-full">
      <div className="relative" ref={dropdownRef}>
        {/* Main input container */}
        <div 
          className={`
            min-h-12 w-full border-2 rounded-lg px-3 py-2 cursor-text
            flex items-center 
            ${isOpen ? 'border-blue-500 ring-2 ring-blue-200' : 'border-gray-300 hover:border-gray-400'}
            transition-all duration-200
            ${!selectedCategory ? 'bg-gray-100 cursor-not-allowed' : ''}
          `}
          onClick={selectedCategory ? handleInputClick : undefined}
        >
          {/* Search input */}
          <input
            ref={inputRef}
            type="text"
            value={getDisplayText()}
            onChange={handleInputChange}
            onKeyDown={handleKeyDown}
            placeholder={getPlaceholder()}
            className="flex-1 outline-none bg-transparent text-gray-700 placeholder-gray-400"
            readOnly={!selectedCategory || (selectedItems.length > 1 && !searchTerm)}
            disabled={!selectedCategory}
          />
          
          {/* Clear button */}
          {selectedItems.length > 0 && !searchTerm && (
            <button
              onClick={(e) => {
                e.stopPropagation();
                setSelectedItems([]);
              }}
              className="mr-2 hover:bg-gray-100 rounded p-1 transition-colors"
            >
              <X size={16} className="text-gray-400" />
            </button>
          )}
          
          {/* Dropdown arrow */}
          {selectedCategory && (
            <ChevronDown 
              size={20} 
              className={`text-gray-400 transition-transform duration-200 ${isOpen ? 'rotate-180' : ''}`}
            />
          )}
        </div>

        {/* Dropdown menu */}
        {isOpen && selectedCategory && (
          <div className="absolute z-10 w-full mt-1 bg-white border border-gray-300
           rounded-lg shadow-lg max-h-60 overflow-y-auto">
            {selectedItems.length > 0 && !searchTerm && (
              <div className="px-3 py-2 bg-gray-50 border-b border-gray-200">
                <div className="text-xs font-medium text-gray-600 mb-2">Selected Genres:</div>
                <div className="flex flex-wrap gap-1">
                  {selectedItems.map((item, index) => (
                    <span 
                      key={index}
                      className="inline-flex items-center gap-1 bg-blue-100 text-blue-800 px-2 py-1 rounded text-xs font-medium"
                    >
                      {item}
                      <button
                        onClick={(e) => {
                          e.stopPropagation();
                          removeSelectedItem(item);
                        }}
                        className="hover:bg-blue-200 rounded p-0.5 transition-colors"
                      >
                        <X size={12} />
                      </button>
                    </span>
                  ))}
                </div>
              </div>
            )}

            {filteredOptions.length > 0 ? (
              filteredOptions.map((option, index) => (
                <div
                  key={index}
                  onClick={() => handleOptionClick(option)}
                  className="px-3 py-2 hover:bg-blue-50 cursor-pointer text-gray-700 border-b border-gray-100 last:border-b-0 transition-colors"
                >
                  {option}
                </div>
              ))
            ) : (
              <div className="px-3 py-2 text-gray-500 italic">
                {searchTerm ? 'No matching options found' : 'No more options available'}
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
}
