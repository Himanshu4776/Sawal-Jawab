import { useState } from 'react';
import { RegisterData, RegisterResponse } from './types';
import { useToast } from "./use-toast";



export const useRegister = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const { toast } = useToast();

  const apiKey = import.meta.env.VITE_API_URL;

  const register = async (registerData: RegisterData): Promise<RegisterResponse> => {
    setIsLoading(true);
    setError(null);

    try {
      const response = await fetch(`${apiKey}/user/register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          userName: registerData.username,
          email: registerData.email,
          password: registerData.password
        }),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || 'Registration failed');
      }
      
      return {
        success: true,
        message: 'Registration successful',
        token: data.token
      };

    } catch (err) {
      const message = err instanceof Error ? err.message : 'Registration failed';
      setError(message);
      return {
        success: false,
        message
      };
    } finally {
      setIsLoading(false);
    }
  };

  const handleRegister = async (
    registerData: RegisterData,
    setShowRegisterModal: (show: boolean) => void,
    setRegisterData: (data: RegisterData) => void,
    setRegisterError: (error: string) => void
  ) => {
    setRegisterError('');
    
    if (!registerData.username || !registerData.email || !registerData.password) {
      setRegisterError('Please fill in all fields');
      toast({
        variant: "destructive",
        title: "Registration Error",
        description: "Please fill in all fields"
      });
      return;
    }

    if (registerData.password !== registerData.confirmPassword) {
      setRegisterError('Passwords do not match');
      toast({
        variant: "destructive",
        title: "Registration Error",
        description: "Passwords do not match"
      });
      return;
    }

    const response = await register(registerData);
    
    if(response && response.success) {
      setShowRegisterModal(false);
      setRegisterData({
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
      });
      toast({
        title: "Registration Successful",
        description: "Welcome to Sawal Jawab! Please log in to continue."
      });
    } else {
      setRegisterError(response?.message);
      toast({
        variant: "destructive",
        title: "Registration Failed",
        description: response?.message || "An error occurred during registration"
      });
    }
  };
  return {
    register,
    isLoading,
    error,
    handleRegister
  };
};