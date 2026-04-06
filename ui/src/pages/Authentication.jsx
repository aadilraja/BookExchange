import { useState } from 'react';
import { Eye, EyeOff } from 'lucide-react';
import logo from '../assets/logo.jpg';
import googleIcon from '../assets/google_logo.png';

const Authentication = () => {
    const [auth, setAuth] = useState('sign up');
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    return (
        <div className="min-h-screen flex items-center justify-end bg-[url('../assets/bg_1.png')] bg-cover bg-center pr-10 font-robotoMono">

            <div className="bg-white rounded-3xl w-full max-w-[460px] px-10 py-10 shadow-xl">

                {auth === 'sign up' && (
                    <div className="flex flex-col items-center gap-3">

                        {/* Logo */}
                        <img src={logo} alt='logo' className='w-[110px] h-[110px] object-contain' />

                        {/* Heading */}
                        <div className="text-center">
                            <h1 className='text-[46px] font-bold font-robotoMono text-black leading-tight'>
                                Hello there
                            </h1>
                            <p className='text-[15px] font-medium font-robotoMono text-gray-600 mt-1'>
                                Sign Up to share books
                            </p>
                        </div>

                        {/* Form */}
                        <form noValidate className="w-full space-y-3 mt-2">

                            <input
                                type="text"
                                placeholder="User Name"
                                className="w-full px-5 py-3.5 bg-gray-100 border border-gray-200 rounded-full text-gray-500 placeholder-gray-400 text-[15px] focus:outline-none focus:ring-2 focus:ring-orange-300"
                            />

                            <input
                                type="email"
                                placeholder="Email Address"
                                className="w-full px-5 py-3.5 bg-gray-100 border border-gray-200 rounded-full text-gray-500 placeholder-gray-400 text-[15px] focus:outline-none focus:ring-2 focus:ring-orange-300"
                            />

                            <div className="relative">
                                <input
                                    type={showPassword ? "text" : "password"}
                                    placeholder="Password"
                                    className="w-full px-5 py-3.5 bg-gray-100 border border-gray-200 rounded-full text-gray-500 placeholder-gray-400 text-[15px] focus:outline-none focus:ring-2 focus:ring-orange-300 pr-12"
                                />
                                <button type="button" onClick={() => setShowPassword(!showPassword)}
                                    className="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600">
                                    {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                                </button>
                            </div>

                            <div className="relative">
                                <input
                                    type={showConfirmPassword ? "text" : "password"}
                                    placeholder="Confirm Password"
                                    className="w-full px-5 py-3.5 bg-gray-100 border border-gray-200 rounded-full text-gray-500 placeholder-gray-400 text-[15px] focus:outline-none focus:ring-2 focus:ring-orange-300 pr-12"
                                />
                                <button type="button" onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                                    className="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600">
                                    {showConfirmPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                                </button>
                            </div>

                            {/* Button row */}
                            <div className="flex items-center gap-4 pt-2">
                                <button type="submit"
                                    className="py-3 px-8 rounded-full bg-[#F28F00] hover:bg-[#e07e00] text-white font-bold text-[17px] transition-colors whitespace-nowrap">
                                    Sign Up
                                </button>
                                <div className="flex items-center gap-2">
                                    <span className="text-gray-500 text-[14px]">or continue with</span>
                                    <img src={googleIcon} alt="google" className="w-8 h-8" />
                                </div>
                            </div>

                        </form>

                        {/* Footer */}
                        <p className="text-gray-600 text-[14px] mt-1">
                            already have an account?{' '}
                            <button onClick={() => setAuth('sign in')}
                                className="text-blue-500 font-semibold hover:underline">
                                Sign In
                            </button>
                        </p>

                    </div>
                )}

                {auth === 'sign in' && (
                    <div className="flex flex-col items-center gap-3">

                        <img src={logo} alt='logo' className='w-[110px] h-[110px] object-contain' />

                        <div className="text-center">
                            <h1 className='text-[46px] font-bold font-robotoMono text-black leading-tight'>
                                Hello there
                            </h1>
                            <p className='text-[15px] font-medium font-robotoMono text-gray-600 mt-1'>
                                Sign In to share books
                            </p>
                        </div>

                        <form noValidate className="w-full space-y-3 mt-2">

                            <input
                                type="email"
                                placeholder="Email Address"
                                className="w-full px-5 py-3.5 bg-gray-100 border border-gray-200 rounded-full text-gray-500 placeholder-gray-400 text-[15px] focus:outline-none focus:ring-2 focus:ring-orange-300"
                            />

                            <div className="relative">
                                <input
                                    type={showPassword ? "text" : "password"}
                                    placeholder="Password"
                                    className="w-full px-5 py-3.5 bg-gray-100 border border-gray-200 rounded-full text-gray-500 placeholder-gray-400 text-[15px] focus:outline-none focus:ring-2 focus:ring-orange-300 pr-12"
                                />
                                <button type="button" onClick={() => setShowPassword(!showPassword)}
                                    className="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600">
                                    {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                                </button>
                            </div>

                            <div className="flex items-center gap-4 pt-2">
                                <button type="submit"
                                    className="py-3 px-8 rounded-full bg-[#F28F00] hover:bg-[#e07e00] text-white font-bold text-[17px] transition-colors whitespace-nowrap">
                                    Sign In
                                </button>
                                <div className="flex items-center gap-2">
                                    <span className="text-gray-500 text-[14px]">or continue with</span>
                                    <img src={googleIcon} alt="google" className="w-8 h-8" />
                                </div>
                            </div>

                        </form>

                        <p className="text-gray-600 text-[14px] mt-1">
                            don't have an account?{' '}
                            <button onClick={() => setAuth('sign up')}
                                className="text-blue-500 font-semibold hover:underline">
                                Sign Up
                            </button>
                        </p>

                    </div>
                )}

            </div>
        </div>
    );
};

export default Authentication;