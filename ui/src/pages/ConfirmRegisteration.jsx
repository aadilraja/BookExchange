
import axios from "axios";
import { useState,useEffect} from "react";
import { useParams,useNavigate } from "react-router-dom";


const ConfirmRegisteration = () => {
  const { token } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const confirmToken = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/auth/registration-confirm?token=${token}`
        );

        if (response.status === 200) {
          alert('Registration confirmed successfully!');
          navigate('/');
        } else {
          alert('Registration could not be verified!');
        }
      } catch (error) {
        console.error('Confirmation failed:', error);
        alert(`An error occurred: ${error.response?.data?.message || error.message}`);
      }
    };

    if (token) {
      confirmToken();
    } else {
      alert('Token does not exist');
    }
  }, [token, navigate]);

  return (
    <div className="min-h-screen flex justify-center items-center">
      <h1>Processing registration confirmation...</h1>
    </div>
  );
};

export default ConfirmRegisteration;