
import axios from "axios";
import { useState,useEffect} from "react";
import { useParams,useNavigate } from "react-router-dom";
import { CheckCircle, XCircle, AlertCircle, ArrowRight } from 'lucide-react';
import '../Styles/dotLoadingAnimation.css'


const ConfirmRegisteration = () => {
  const { token } = useParams();
  const [status, setStatus] = useState('processing'); 
  const [message, setMessage] = useState('');
  const navigate = useNavigate();


  useEffect(() => {
    const confirmToken = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/auth/registration-confirm?token=${token}`
        );

        if (response.status === 200) {
          setStatus('success');
          setMessage('Registration confirmed successfully!');

        } else {
          setStatus('error');
          setMessage('Registration could not be verified. Please try again.');        }
      } catch (error) {
        console.error('Confirmation failed:', error);
        setStatus('error');
        setMessage('An error occurred during confirmation. Please contact support.');
      }
    };

    if (token) {
      confirmToken();
    } else {
      setStatus('error');
      setMessage('Invalid confirmation token.');
    }
  }, [token]);

 return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex justify-center items-center p-4">
      <div className="bg-white rounded-2xl shadow-xl p-8 max-w-md w-full text-center ">

       {/* Status Icon */}
        <div className="mb-6">
          {status === 'processing' && (
            <div className="mx-auto w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center">
              <AlertCircle className="w-8 h-8 text-blue-600 animate-pulse" />
            </div>
          )}
          
          {status === 'success' && (
            <div className="mx-auto w-16 h-16 bg-green-100 rounded-full flex items-center justify-center animate-bounce">
              <CheckCircle className="w-8 h-8 text-green-600" />
            </div>
          )}
          
          {status === 'error' && (
            <div className="mx-auto w-16 h-16 bg-red-100 rounded-full flex items-center justify-center">
              <XCircle className="w-8 h-8 text-red-600" />
            </div>
          )}
        </div>

        

      {/* Title and Message */}
        <div className="flex flex-col items-center">
          {status === 'processing' && (
            <>
              <h1 className="text-2xl font-semibold text-gray-800 mb-2 flex items-center justify-center">
                Processing registration confirmation
                 <div className="dot-loading-animation">
                  <div className="dot"></div>
                  <div className="dot"></div>
                  <div className="dot"></div>
                </div>
              </h1>
              <p className="text-gray-600">Please wait while we verify your account...</p>
            </>
          )}
          
          {status === 'success' && (
            <>
              <h1 className="text-2xl font-semibold text-green-800 mb-2">
                Registration Confirmed!
              </h1>
              <p className="text-gray-600 mb-4">{message}</p>
              <button className="text-white flex items-center justify-center bg-green-600 rounded-[10px] w-[265px] h-[60px] p-2 text-[18px] 
                                  hover:scale-105 transition-all duration-500 cursor-pointer"

                onClick={()=>navigate('/user/login')}>
                  Go to login page
                  <ArrowRight className="w-6 h-6 ml-2" />
              </button>
            </>
          )}
          
          {status === 'error' && (
            <>
              <h1 className="text-2xl font-semibold text-red-800 mb-2">
                Confirmation Failed
              </h1>
              <p className="text-gray-600 mb-6">{message}</p>
              <div className="space-y-3">
                <button 
                  onClick={() => window.location.reload()}
                  className="w-full bg-blue-600 text-white py-2 px-4 rounded-lg hover:bg-blue-700 transition-colors text-[15px]"
                >
                  Try Again
                </button>
                <button 
                  onClick={() => navigate("/user/new")}
                  className="w-full bg-gray-200 text-gray-800 py-2 px-4 rounded-lg hover:bg-gray-300 transition-colors text-[15px]"
                >
                  Back to Registration
                </button>
              </div>
            </>
          )}
        </div>
        {/* Footer */}
        <div className="mt-1 pt-2 ">
          <p className="text-xs text-gray-500 text-[15px]">
            Having trouble? <a href="/support" className="text-blue-600 hover:underline">Contact Support</a>
          </p>
        </div>
    </div>
  </div>
);
};

export default ConfirmRegisteration;