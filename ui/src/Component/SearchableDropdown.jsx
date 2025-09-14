import React, { useState, useEffect, useRef } from 'react';
import { ChevronDown } from 'lucide-react';

/**
 * @param {{
 * options: string[];
 * selectedValue: string;
 * onValueChange: (value: string) => void;
 * placeholder?: string;
 * label?: string;
 * }} props
 */
const SearchableDropdown = ({ 
  options = [], 
  selectedValue, 
  onValueChange, 
  placeholder = "Select an option...",
  label 
}) => {
  const [isOpen, setIsOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState(selectedValue || "");

  const dropdownRef = useRef(null);
  const inputRef = useRef(null);

  useEffect(() => {
    setSearchTerm(selectedValue || "");
  }, [selectedValue]);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsOpen(false);
        setSearchTerm(selectedValue || "");
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [selectedValue]); 

  const filteredOptions = options.filter(option =>
    option.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleInputClick = () => {
    setIsOpen(true);
    inputRef.current?.focus();
  };

  const handleInputChange = (e) => {
    setSearchTerm(e.target.value);
    if (!isOpen) {
      setIsOpen(true);
    }
  };

  const handleOptionClick = (option) => {
    onValueChange(option); 
    setSearchTerm(option);
    setIsOpen(false);     
  };

  return (
    <div className="w-full">
      {/* Optional label */}
      {label && (
        <label className="block text-sm font-medium text-gray-700 mb-2">
          {label}
        </label>
      )}
      
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
                    ${selectedValue === option
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
    </div>
  );
};

export default SearchableDropdown;