import { useState } from 'react';
import { useToast } from "../hooks/use-toast";
import { QuestionPayload } from './types';

export const useAddQuestion = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const { toast } = useToast();
  
  const apiKey = import.meta.env.VITE_API_URL;

  const addQuestion = async (questionData: QuestionPayload) => {
    setIsLoading(true);
    setError(null);

    try {
      const response = await fetch(`${apiKey}/questions/save`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        body: JSON.stringify({
          title: questionData.title,
          content: questionData.content
        })
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || 'Failed to add question');
      }

      toast({
        title: "Success",
        description: "Question added successfully"
      });
      
      return {
        success: true,
        data: data
      };

    } catch (err) {
      const message = err instanceof Error ? err.message : 'Failed to add question';
      setError(message);
      toast({
        variant: "destructive",
        title: "Error",
        description: message
      });
      return {
        success: false,
        error: message
      };
    } finally {
      setIsLoading(false);
    }
  };

  return {
    addQuestion,
    isLoading,
    error
  };
};
