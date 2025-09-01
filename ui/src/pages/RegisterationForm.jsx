import { useState } from 'react';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

const RegistrationForm = () => {
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [serverError, setServerError] = useState('');
    const [isRegistered, setIsRegistered] = useState(false); // New state for success
    const [userEmail, setUserEmail] = useState(''); // Store email for confirmation message

    const { register, handleSubmit, watch, formState: { errors }, reset } = useForm({
        mode: 'onBlur'
    });
    
    const password = watch('password');
    const navigate = useNavigate();

    const onSubmit = async (data) => {
        setIsSubmitting(true); 
        setServerError('');   

        try {
            const payload = {
                name: data.name,
                email: data.email,
                password: data.password,
                confirmPassword: data.confirmPassword,
                role: ["USER"]
            };

            await axios.post("http://localhost:8080/api/auth/register", payload);
            
            // Instead of redirecting, show success state
            setUserEmail(data.email);
            setIsRegistered(true);
            reset(); // Clear the form

        } catch (err) {
            const errorMessage = err.response?.data?.message || 'Registration failed. Please try again.';
            setServerError(errorMessage);
        } finally {
            setIsSubmitting(false);
        }
    };

    const handleGoToLogin = () => {
        navigate('/user/login');
    };

    const handleRegisterAnother = () => {
        setIsRegistered(false);
        setUserEmail('');
        setServerError('');
    };

    // Success confirmation view
    if (isRegistered) {
        return (
            <div className="bg-gray-100 min-h-screen flex justify-center items-center font-sans">
                <div className='bg-white p-8 rounded-xl shadow-lg w-full max-w-md text-center'>
                    <div className="mb-6">
                        {/* Success icon */}
                        <div className="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
                            <svg className="w-8 h-8 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                            </svg>
                        </div>
                        
                        <h1 className='text-3xl font-bold mb-2 text-gray-800'>Registration Successful!</h1>
                        <p className="text-gray-600 mb-4">
                            Welcome! Your account has been created successfully.
                        </p>
                        <p className="text-sm text-gray-500 mb-6">
                            A confirmation email has been sent to <span className="font-medium">{userEmail}</span>
                        </p>
                    </div>

                    
                </div>
            </div>
        );
    }

    // Original registration form
    return (
        <div className="bg-gray-100 min-h-screen flex justify-center items-center font-sans">
            <div className='bg-white p-8 rounded-xl shadow-lg w-full max-w-md'>
                <h1 className='text-3xl font-bold mb-6 text-center text-gray-800'>Create Your Account</h1>
                
                <form className='space-y-4' onSubmit={handleSubmit(onSubmit)} noValidate>
                    {/* Name Input */}
                    <div>
                        <label htmlFor='name' className="block mb-1 text-sm font-medium text-gray-600">Name</label>
                        <input
                            id="name"
                            className="block w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Enter your full name"
                            {...register("name", { required: "Name is required" })}
                        />
                        {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name.message}</p>}
                    </div>

                    {/* Email Input */}
                    <div>
                        <label htmlFor='email' className="block mb-1 text-sm font-medium text-gray-600">Email</label>
                        <input
                            id="email"
                            type="email"
                            className="block w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="you@example.com"
                            {...register("email", { 
                                required: "Email is required",
                                pattern: {
                                    value: /^\S+@\S+$/i,
                                    message: "Invalid email address"
                                }
                            })}
                        />
                        {errors.email && <p className="text-red-500 text-sm mt-1">{errors.email.message}</p>}
                    </div>

                    {/* Password Input */}
                    <div>
                        <label htmlFor='password' className="block mb-1 text-sm font-medium text-gray-600">Password</label>
                        <input
                            id="password"
                            type="password"
                            className="block w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Enter a strong password"
                            {...register("password", { 
                                required: "Password is required",
                                minLength: {
                                    value: 8,
                                    message: "Password must be at least 8 characters long"
                                }
                             })}
                        />
                        {errors.password && <p className="text-red-500 text-sm mt-1">{errors.password.message}</p>}
                    </div>
                    
                    {/* Confirm Password Input */}
                    <div>
                        <label htmlFor='confirmPassword' className="block mb-1 text-sm font-medium text-gray-600">Confirm Password</label>
                        <input
                            id="confirmPassword"
                            type="password"
                            className="block w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Re-enter your password"
                            {...register("confirmPassword", {
                                required: "Please confirm your password",
                                validate: value => value === password || "Passwords do not match"
                            })}
                        />
                        {errors.confirmPassword && <p className="text-red-500 text-sm mt-1">{errors.confirmPassword.message}</p>}
                    </div>
                    
                    {/* Server-Side Error */}
                    {serverError && <div className="p-3 text-sm text-red-700 bg-red-100 rounded-lg">{serverError}</div>}

                    <button
                        type="submit"
                        disabled={isSubmitting}
                        className="w-full bg-blue-600 text-white py-3 px-4 rounded-lg hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-all font-semibold disabled:bg-blue-300 disabled:cursor-not-allowed"
                    >
                        {isSubmitting ? 'Signing Up...' : 'Sign Up'}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default RegistrationForm;