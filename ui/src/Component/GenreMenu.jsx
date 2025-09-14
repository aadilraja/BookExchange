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

function GenreMenu({ selectedGenres = [], onGenresChange, selectedCategory }) {
  const [isOpen, setIsOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const dropdownRef = useRef(null);
  const inputRef = useRef(null);

  const options = selectedCategory ? categoryGenreMap[selectedCategory] || [] : [];

  const filteredOptions = options.filter(option =>
    option.toLowerCase().includes(searchTerm.toLowerCase()) &&
    !selectedGenres.includes(option)
  );

  // Handle clicking outside to close dropdown
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsOpen(false);
        setSearchTerm('');
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  const handleInputClick = () => {
    if (selectedCategory) {
      setIsOpen(true);
      setTimeout(() => inputRef.current?.focus(), 0);
    }
  };

  const handleOptionClick = (option) => {
    const newItems = [...selectedGenres, option];
    onGenresChange(newItems);
    setSearchTerm('');
    setTimeout(() => inputRef.current?.focus(), 0);
  };

  const removeSelectedItem = (itemToRemove) => {
    const newItems = selectedGenres.filter(item => item !== itemToRemove);
    onGenresChange(newItems);
  };

  const handleInputChange = (e) => {
    setSearchTerm(e.target.value);
    if (!isOpen) setIsOpen(true);
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Backspace' && searchTerm === '' && selectedGenres.length > 0) {
      removeSelectedItem(selectedGenres[selectedGenres.length - 1]);
    }
    if (e.key === 'Escape') {
      setIsOpen(false);
      setSearchTerm('');
      inputRef.current?.blur();
    }
  };

  const getDisplayText = () => {
    if (isOpen && searchTerm) return searchTerm;
    if (!isOpen && selectedGenres.length === 0) return '';
    if (!isOpen && selectedGenres.length === 1) return selectedGenres[0];
    if (!isOpen && selectedGenres.length > 1) return `${selectedGenres.length} genres selected`;
    return searchTerm;
  };

  const getPlaceholder = () => {
    if (!selectedCategory) return "Select a category first...";
    if (selectedGenres.length === 0) return "Search and select genres...";
    return "Search for more genres...";
  };

  const clearAll = (e) => {
    e.stopPropagation();
    onGenresChange([]);
    setSearchTerm('');
  };

  return (
    <div className="w-full">
      <div className="relative" ref={dropdownRef}>
        {/* Main input container */}
        <div 
          className={`
            min-h-12 w-full border-2 rounded-lg px-3 py-2 cursor-text
            flex items-center flex-wrap gap-1
            ${isOpen ? 'border-blue-500 ring-2 ring-blue-200' : 'border-gray-300 hover:border-gray-400'}
            transition-all duration-200
            ${!selectedCategory ? 'bg-gray-100 cursor-not-allowed' : 'bg-white'}
          `}
          onClick={handleInputClick}
        >
          {/* Show selected genres as tags when not searching */}
          {!isOpen && selectedGenres.length > 0 && (
            <div className="flex flex-wrap gap-1 mr-2">
              {selectedGenres.slice(0, 3).map((genre, index) => (
                <span 
                  key={index}
                  className="inline-flex items-center gap-1 bg-blue-100 text-blue-800 px-2 py-1 rounded text-sm font-medium"
                >
                  {genre}
                  <button
                    onClick={(e) => {
                      e.stopPropagation();
                      removeSelectedItem(genre);
                    }}
                    className="hover:bg-blue-200 rounded p-0.5 transition-colors"
                  >
                    <X size={12} />
                  </button>
                </span>
              ))}
              {selectedGenres.length > 3 && (
                <span className="text-gray-500 text-sm">
                  +{selectedGenres.length - 3} more
                </span>
              )}
            </div>
          )}
          
          {/* Search input */}
          <input
            ref={inputRef}
            type="text"
            value={getDisplayText()}
            onChange={handleInputChange}
            onKeyDown={handleKeyDown}
            onFocus={() => selectedCategory && setIsOpen(true)}
            placeholder={getPlaceholder()}
            className="flex-1 min-w-0 outline-none bg-transparent text-gray-700 placeholder-gray-400"
            disabled={!selectedCategory}
          />
          
          {/* Clear all button */}
          {selectedGenres.length > 0 && !isOpen && (
            <button
              onClick={clearAll}
              className="ml-2 hover:bg-gray-100 rounded p-1 transition-colors"
              title="Clear all selections"
            >
              <X size={16} className="text-gray-400" />
            </button>
          )}
          
          {/* Dropdown arrow */}
          {selectedCategory && (
            <ChevronDown 
              size={20} 
              className={`text-gray-400 transition-transform duration-200 ml-2 ${isOpen ? 'rotate-180' : ''}`}
            />
          )}
        </div>

        {/* Dropdown menu */}
        {isOpen && selectedCategory && (
          <div className="absolute z-10 w-full mt-1 bg-white border border-gray-300 rounded-lg shadow-lg max-h-60 overflow-y-auto">
            {/* Show selected genres in dropdown when searching */}
            {selectedGenres.length > 0 && (
              <div className="px-3 py-2 bg-gray-50 border-b border-gray-200">
                <div className="text-xs font-medium text-gray-600 mb-2">
                  Selected ({selectedGenres.length}):
                </div>
                <div className="flex flex-wrap gap-1">
                  {selectedGenres.map((item, index) => (
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

            {/* Available options */}
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
                {searchTerm ? 'No matching genres found' : 'No more genres available'}
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
}
 
export default GenreMenu;