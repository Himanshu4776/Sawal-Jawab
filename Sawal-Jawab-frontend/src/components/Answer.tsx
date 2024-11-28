import React from 'react';
import { Button } from '@/components/ui/button';
import { ThumbsUp, User } from 'lucide-react';

// Define types for props
interface AnswerProps {
  answer: {
    id: number;
    author: string;
    timestamp: string;
    text: string;
    votes: number;
  };
  handleVote: (questionId: number, answerId: number) => void;
  questionId: number;
}

export const Answer: React.FC<AnswerProps> = ({
  answer,
  handleVote,
  questionId,
}) => {
  return (
    <div className="border-l-2 border-gray-200 pl-4 pt-4">
      <div className="flex items-center gap-2 mb-2">
        <User className="w-4 h-4" />
        <span className="text-sm text-gray-500">{answer.author}</span>
        <span className="text-sm text-gray-500">• {answer.timestamp}</span>
      </div>
      <p className="mb-2">{answer.text}</p>
      <div className="flex items-center gap-4 text-sm text-gray-500">
        <Button
          variant="ghost"
          size="sm"
          onClick={() => handleVote(questionId, answer.id)}
          className="flex items-center gap-1"
        >
          <ThumbsUp className="w-4 h-4" />
          {answer.votes}
        </Button>
      </div>
    </div>
  );
};
